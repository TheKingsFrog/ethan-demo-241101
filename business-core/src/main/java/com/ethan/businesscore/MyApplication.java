package com.ethan.businesscore;

import com.myspringboot.annotations.MySpringBootApplication;
import com.myspringboot.application.MySpringApplication;

//@MySpringBootApplication
public class MyApplication {

//    @Bean
//    public TomcatWebServer tomcatWebServer() {
//        return new TomcatWebServer();
//    }

//    @Bean
//    public JettyWebServer jettyWebServer() {
//        return new JettyWebServer();
//    }

    public static void main(String[] args) {
        MySpringApplication.run(MyApplication.class);
    }

}
