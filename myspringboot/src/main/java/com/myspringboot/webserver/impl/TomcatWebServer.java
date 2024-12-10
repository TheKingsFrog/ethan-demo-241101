package com.myspringboot.webserver.impl;

import com.myspringboot.webserver.MyWebServer;


public class TomcatWebServer implements MyWebServer {
    @Override
    public void start() {
        System.out.println("启动tomcat");
    }
}
