package com.ydcun.java.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

class Person2{
    String name;
    String leve;
    Person2(String name,String leve){
        this.name = name;
        this.leve = leve;
    }

    @Override
    public String toString() {

        return name+":"+leve;
    }
}
public class StreamMap {

    @Test
    public void map(){
        String[] str = "ydcun,1 ydcun,2".split(" ");
        Stream<Person2> person2Stream = Arrays.stream(str).map(s -> {
            String[] split = s.split(",");
            return new Person2(split[0], split[1]);
        });
        person2Stream.forEach(System.out::println);
    }
}
