package org.java_demos.spring_core.spring;

import org.springframework.context.annotation.*;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan
public class Config {

    @Bean
    public Singleton singleton() {
        System.out.println("==============================SINGLETON");
        return new Singleton();
    }

    @Bean
    @Scope(value = "prototype"
//            ,            proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    public Prototype prototype() {
        return new Prototype();
    }

    @Bean
    public List<String> messages() {
        return Arrays.asList("a", "b", "c");
    }
}
