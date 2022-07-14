package org.java_demos.spring_core.spring;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class Singleton {

//    @Autowired
//    Prototype prototype;

    @Lookup
    public Prototype getPrototype() {
        return null;
    }
}
