package com.ydcun.java.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

class NaturaSupplier implements Supplier<Long> {
    long x =0;
    public Long get(){
        x++;
        return x;
    }
}
public class Filter {
    @Test
    public void filter(){
        Stream<Long> natural = Stream.generate(new NaturaSupplier());
        Stream<Long> longStream = natural.filter((n) -> n % 2 == 0);
        longStream.limit(20).forEach(System.out::println);
    }

    @Test
    public void filter2(){
        String[] array = {"java"," ydcun","",null,"\n\n","python"};
        Stream<String> stringStream = Arrays.stream(array)
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> s.trim());
        stringStream.forEach(System.out::println);
    }
}
