package org.java_demos.spring_core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

//        System.out.println(applicationContext.getBean("prototype"));
//        System.out.println(applicationContext.getBean("prototype"));
        System.out.println(applicationContext.getBean(Singleton.class));
        System.out.println(applicationContext.getBean(Singleton.class));
        System.out.println(applicationContext.getBean(Singleton.class).getPrototype());
        System.out.println(applicationContext.getBean(Singleton.class).getPrototype());
    }
}