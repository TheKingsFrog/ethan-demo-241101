package com.ethan.businesscore;

import com.ethan.businesscore.functionalinterface.TestFunctionalInterface;
import com.ethan.businesscore.myclassloader.MyClassLoader;
import com.ethan.businesscore.myoptional.myException.ValueAbsentException;
import com.ethan.businesscore.mythread.MyCallable;
import com.ethan.businesscore.mythread.MyThread;
import com.ethan.businesscore.web.Cat;
import com.ethan.businesscore.web.Person;
import com.ethan.businesscore.web.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
class BusinessCoreApplicationTests {

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

    @Test
    void test10() {
        Predicate<String> stringPredicate = s -> s.length() > 4;
        boolean hello = stringPredicate.negate().test("hello");
        System.out.println(hello);
    }

    @Test
    void test11() {

        // 例子：Long转Double

        // 实现方式1
        Long cash = 10L;
        String cashStr = String.valueOf(cash);
        Double cashDouble = Double.valueOf(cash);

        // 实现方式2
        Function<Long, String> longToStringFunction = String::valueOf;
        Function<Long, Double> toDoubleFunction = longToStringFunction.andThen(Double::valueOf);
        toDoubleFunction.apply(10L);

        // 问题：这两种实现方式，哪一种更好？如果是第一种，那第二种通过andThen处理数据有什么优势？

    }

    /**
     * Comparator多属性排序: 先按名字不分大小写排，再按GID倒序排，最后按年龄正序排
     */
    @Test
    void test12() {

        List<Person> peopleList = Lists.newArrayList(new Person("dai", "301", 10), new Person("dai", "303", 10),
                new Person("dai", "303", 8), new Person("dai", "303", 6), new Person("dai", "303", 11),
                new Person("dai", "302", 9), new Person("zhang", "302", 9), new Person("zhang", "301", 9),
                new Person("Li", "301", 8));

        peopleList.stream()
                .sorted(Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Person::getGid, Comparator.reverseOrder())
                        .thenComparingInt(Person::getAge)
                ).forEach(System.out::println);

    }

    @Test
    void test13() {

        // normal optional
        Optional<Person> personOptional = Optional.of(new Person("dai", "301", 10));

        // null optional
        Optional<Person> nullOptional = Optional.empty();
        Optional<Person> nullOptional2 = Optional.ofNullable(null);

        // orElse get
        Person person = nullOptional.orElse(new Person("null", "null", -1));
        Person person1 = personOptional.get();
        System.out.println(person);
        System.out.println(person1);

        // orElseGet
        nullOptional.orElseGet(() -> new Person("null", "null", -1));

        // orElseThrow
        try {
            nullOptional.orElseThrow(ValueAbsentException::new);
        } catch (ValueAbsentException e) {
            System.out.println(e.getMessage());
        }

        // map
        personOptional.map(i -> {
            if(i.getAge() == 10){
                i.age = -1;
            } else {
                i.age = 1;
            }
            return i;
        });

        System.out.println("map result ==>" + personOptional.get());

        // flatmap
        Optional<Person> newPersonOptional = personOptional.flatMap(i -> {
            i.age = 1000;
            return Optional.of(i);
        });
        System.out.println(newPersonOptional.get());

    }

    @Test
    void test14() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(contextClassLoader.getParent());
        System.out.println(contextClassLoader.getParent().getParent());
    }

    @Test
    void test15() throws ClassNotFoundException {
        Class<Person> person = (Class<Person>) Class.forName("Person");
        System.out.println(person);
    }

    @Test
    void test16() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class<Person> personClass = Person.class;
        System.out.println("获取全限定类名");
        System.out.println(personClass.getName());
        System.out.println("获取类名");
        System.out.println(personClass.getSimpleName());
        System.out.println("实例化对象");
        Person p1 = personClass.newInstance();
        System.out.println(p1);

        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println("获取全限定类名");
        System.out.println(aClass.getName());
        System.out.println("获取类名");
        System.out.println(aClass.getSimpleName());
        System.out.println("实例化对象");
        Person p2 = aClass.newInstance();
        System.out.println(p2);

        Class<?> person2 = Class.forName("com.ethan.ethandemo241101.demos.web.Person");
        System.out.println("获取全限定类名");
        System.out.println(person2.getName());
        System.out.println("获取类名");
        System.out.println(person2.getSimpleName());
        System.out.println("实例化对象");
        Person p3 = (Person) person2.newInstance();
        System.out.println(p3);

    }

    @Test
    void test17() {
        Class<Person> personClass = Person.class;
        Class<? super Person> superclass = personClass.getSuperclass();
        System.out.println(superclass);
        System.out.println(personClass);
    }

    @Test
    void test18() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.ethan.ethandemo241101.demos.web.Person");
        Person person = (Person) aClass.newInstance();
        System.out.println(person);

        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person person2 = (Person) constructor.newInstance("Hello");
        System.out.println(person2);
    }

    @Test
    void test19() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.ethan.ethandemo241101.demos.web.Person");
        Field[] declaredFields = aClass.getDeclaredFields();

        Person person = (Person) aClass.newInstance();

        Field phone = aClass.getDeclaredField("phone");
        phone.setAccessible(true);
        phone.set(person, "18417173348");
        System.out.println(person);

        System.out.println(phone.getType());

    }

    @Test
    void test20() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class<?> aClass = Class.forName("com.ethan.ethandemo241101.demos.web.Person");

        Person person = (Person) aClass.newInstance();

        Method setPhone = aClass.getDeclaredMethod("setPhone", String.class);

        setPhone.setAccessible(true);

        setPhone.invoke(person, "hello world 2");

        System.out.println(person);


    }

    @Test
    void test21() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Person person = (Person) Class.forName("com.ethan.ethandemo241101.demos.web.Person").newInstance();
        person.sayHello("hcl");

        // 为什么这里会报NoSuchMethod异常？Person类明明是有这个方法的
        Object object = new Person();
        // 反射调用 getMethod("sayHello") 时，未明确指定方法的参数类型。getMethod 需要精确匹配方法名和参数类型列表。如果未指定参数类型，而目标方法有参数，就会导致找不到该方法。
        Method sayHello = object.getClass().getMethod("sayHello", String.class);
        sayHello.invoke( object, "invoke method");

    }

    @Test
    void test22() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = Class.forName("com.ethan.ethandemo241101.demos.web.Person", true, myClassLoader);
        aClass.newInstance();
    }

    @Test
    void test23() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("re.properties"));
        String classfullpath = (String) properties.get("classfullpath");
        String method = properties.get("method").toString();
        String parameters = Optional.ofNullable(properties.get("parameters").toString()).get();

        Class<?> aClass = Class.forName(classfullpath);
        Object object = aClass.newInstance();

        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(object, "hahaha");

        Method declaredMethod;
        if (StringUtils.isNotBlank(parameters)) {
            declaredMethod = aClass.getDeclaredMethod(method, Class.forName(parameters));
            declaredMethod.invoke(object, "hello");
        } else {
            declaredMethod = aClass.getDeclaredMethod(method);
            declaredMethod.invoke(object);
        }


    }

    /**
     * 反射优化
     * @throws ClassNotFoundException
     */
    @Test
    void test24() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方法耗时：" + (end - start) + "ms");

        long start2 = System.currentTimeMillis();
        Class<?> aClass = Class.forName("com.ethan.ethandemo241101.demos.web.Cat");
        Object object = aClass.newInstance();
        Method hi = aClass.getMethod("hi");
        hi.setAccessible(true);
        for (int i = 0; i < 100000000; i++) {
            hi.invoke(object);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("反射方法耗时：" + (end2 - start2) + "ms");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

    }

    @Test
    void test25() throws ExecutionException, InterruptedException {

        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCallable());

        Thread thread = new Thread(stringFutureTask);
        thread.start();

        String s = stringFutureTask.get();

        System.out.println(s);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyThread());



    }

}
