package com.ydcun.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Person{
    String name;
    Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class MethodReference {
    public static void main(String[] args) {
        String[] str = "job ydcun my".split(" ");
        List<Person> list = Arrays.asList(str).stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.print(list);

    }
}
