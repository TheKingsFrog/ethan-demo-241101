package com.ethan.ethandemo241101;

import com.ethan.ethandemo241101.demos.web.User;
import com.ethan.ethandemo241101.functionalinterface.TestFunctionalInterface;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
class EthanDemo241101ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello world!");
    }

    @Test
    void test() {
        Predicate<Integer> isEven = n -> n % 2 == 0;

        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6);
        integerList.stream().filter(isEven).forEach(System.out::println);

    }

    @Test
    void test2() {
        Predicate<String> stringPredicate = s -> s.equals("hello");

        List<String> stringList = Arrays.asList("hello", "world", "hello");

        stringList.stream().filter(stringPredicate).forEach(System.out::println);

    }

    @Test
    void test3() {
        Function<String, Integer> stringIntegerFunction = String::length;

        List<String> stringList = Arrays.asList("hello", "world", "hello");

        stringList.stream().map(stringIntegerFunction).forEach(System.out::println);
    }

    @Test
    void test4() {
        List<String> stringList = Arrays.asList("hello", "world", "test");
        System.out.println(stringList.stream().reduce("print:", (a, b) -> a + " " + b));
    }

    @Test
    void test5() {
        List<String> subjectList = Arrays.asList("Java", "C++", "Python", "C#", "Golang", "JavaScript");

        Predicate<String> jPredicate = s -> s.toLowerCase().startsWith("j");
        Predicate<String> cPredicate = s -> s.toLowerCase().endsWith("t");
        Predicate<String> lengthPredicate = s -> s.length() > 4;

        subjectList.stream().filter(jPredicate.and(cPredicate).and(lengthPredicate)).forEach(System.out::println);

    }

    @Test
    void test6() {

        Function<String, String> stringFunction = String::toUpperCase;

        List<String> stringList = Arrays.asList("Java", "C++", "Python", "C#", "Golang", "JavaScript");

        String result = stringList.stream().map(stringFunction).collect(Collectors.joining(","));

        System.out.println(result);

    }

    @Test
    void test7() {

        List<User> userList = new ArrayList<>();

        userList.add(new User("Ethan", 1));
        userList.add(new User("Ethan", 3));
        userList.add(new User("Ethan", 4));
        userList.add(new User("Ethan", 5));

        User user = userList.stream().min(User.comparator()).get();

        System.out.println(user);

    }

    @Test
    void test8() {
        List<Integer> integerList = Arrays.asList(0,0,3,0,0,0,0,0,0,0);

        IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());

    }

    @Test
    void test9() {

        TestFunctionalInterface testFunctionalInterface = () -> System.out.println("hello world!");

    }

}
