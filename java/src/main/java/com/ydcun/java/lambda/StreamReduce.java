package com.ydcun.java.lambda;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamReduce {
    @Test
    public void reduce(){
        // 100为acc初始值
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(100, (acc, n) -> acc * n);
        System.out.print(reduce);
    }
}
