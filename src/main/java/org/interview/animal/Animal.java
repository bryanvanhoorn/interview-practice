package org.interview.animal;

import lombok.Getter;
import lombok.Setter;

public class Animal {

    @Getter
    @Setter
    String name;

    public Animal(String name) {
        this.name = name;
    }
}
