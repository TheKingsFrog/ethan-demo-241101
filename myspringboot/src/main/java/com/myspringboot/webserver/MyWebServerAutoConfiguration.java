package com.myspringboot.webserver;

import com.myspringboot.webserver.impl.JettyWebServer;
import com.myspringboot.webserver.impl.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyWebServerAutoConfiguration {

    @Bean
    @Conditional(MyTomcatCondition.class)
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

    @Bean
    @Conditional(MyJettyCondition.class)
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }

}
