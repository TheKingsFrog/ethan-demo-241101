package com.ethan.businesscore.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS包含三个参数：
 * 期望值（expected value）：当前线程认为变量的值
 * 新值（new value）：需要将变量更新为的新值
 * 目标值（target value）：变量的实际值
 * @param <E>
 */
public class LockFreeQueue<E> {

    private static class Node<E> {
        volatile E item;
        volatile Node<E> next;

        Node(E item) {
            this.item = item;
            this.next = null;
        }

    }

    // 队列头指针
    private final AtomicReference<Node<E>> head;

    // 队列尾指针
    private final AtomicReference<Node<E>> tail;

    public LockFreeQueue() {
        // 哑节点
        Node<E> dummy = new Node<>(null);
        head = new AtomicReference<>(dummy);
        tail = new AtomicReference<>(dummy);
    }

    /**
     * 添加元素到队列尾部
     * @param item
     */
    public void offer(E item) {

        Node<E> newNode = new Node<>(item);

        while (true) {

            // 获取当前尾节点
            Node<E> currentTailNode = tail.get();

            // 尾节点的下一个节点
            Node<E> nextTailNode = currentTailNode.next;

            // 确保tail未被其他线程更新
            if(currentTailNode == tail.get()) {

                if(nextTailNode == null) {

                    // 尝试将新节点链接到尾节点
                    if(currentTailNode == null && compareAndSetNext(currentTailNode, null, newNode)) {
                        // 成功后更新tail指针
                        tail.compareAndSet(currentTailNode, newNode);
                        return;
                    }

                } else {
                    // 帮助推进tail指针
                    tail.compareAndSet(currentTailNode, nextTailNode);
                }

            }

        }

    }

    /**
     * 从队列头部移除元素
     * @return
     */
    public E poll() {

        while(true) {

            // 获取当前头节点
            Node<E> currentHeadNode = head.get();

            // 头节点的下一个节点
            Node<E> nextNode = currentHeadNode.next;

            // 确保head未被其他线程更新
            if(currentHeadNode == head.get()) {
                if(nextNode == null) {
                    return null;
                }
                // 尝试更新head指针
                if(head.compareAndSet(currentHeadNode, nextNode)) {
                    E item = nextNode.item;
                    // 帮助垃圾回收
                    nextNode.item = null;
                    return item;
                }
            }

        }

    }

    /**
     * 查看队列头部的元素（不删除）
     * @return
     */
    public E peek() {
        Node<E> next = head.get().next;
        return next == null ? null : next.item;
    }

    // 辅助方法：CAS 更新节点的 next 指针
    private boolean compareAndSetNext(Node<E> node, Node<E> expect, Node<E> update) {
        return node.next == expect && (node.next = update) == update;
    }

}
