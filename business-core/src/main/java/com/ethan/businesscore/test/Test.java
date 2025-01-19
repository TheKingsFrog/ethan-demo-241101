package com.ethan.businesscore.test;

import com.ethan.businesscore.web.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test<T extends Person> {

    public static Test<BlackMan> testBlackMan = new Test<>();

    public static Test<WhitePig> testWhitePig = new Test<>();

    static {
        testBlackMan.setSkin("White");
    }

    private String skin;

    private void setSkin(String skin) {
        this.skin = skin;
    }

    public static void main(String[] args) {
        System.out.println(Test.testBlackMan);
        System.out.println(Test.testWhitePig);
    }

}

