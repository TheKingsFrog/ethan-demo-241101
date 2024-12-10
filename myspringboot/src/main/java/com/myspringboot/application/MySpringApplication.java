package com.myspringboot.application;

import com.myspringboot.webserver.MyWebServer;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

public class MySpringApplication {

    public static void run(Class clazz) {

        // 创建一个spring容器 通过注解的方式配置bean
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 注册 配置类bean
        context.register(clazz);
        // 启动spring 解析传入的类
        context.refresh();
        // 启动tomcat
//        startTomcat(context); 种类单一，需要扩展

        MyWebServer myWebServer = getWebServer(context);
        myWebServer.start();
    }

    private static MyWebServer getWebServer(AnnotationConfigWebApplicationContext context) {

        Map<String, MyWebServer> webServerMap = context.getBeansOfType(MyWebServer.class);

        if(webServerMap.isEmpty()){
            throw new NullPointerException();
        }

        if(webServerMap.size() > 1){
            throw new IllegalStateException();
        }

        return webServerMap.values().stream().findFirst().get();

    }

    private static void startTomcat(WebApplicationContext webApplicationContext) {

        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();

        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8080);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        // springMVC 给我们提供了一种功能，
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(webApplicationContext));
        // tomcat启动后，接收到的所有请求都由DispatcherServlet处理
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }


    }

}
