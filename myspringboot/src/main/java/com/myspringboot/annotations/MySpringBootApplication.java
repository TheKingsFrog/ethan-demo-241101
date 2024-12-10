package com.myspringboot.annotations;

import com.myspringboot.webserver.MyWebServerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
@Import(MyWebServerAutoConfiguration.class)
public @interface MySpringBootApplication {

    // 没有配置扫描路径，默认扫描正在解析的所在类的包路径

}
