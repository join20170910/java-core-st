package com.ai.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedTests {

    @Test
    public void givenCollections_withoutStream_thenSort(){
        List<String> list = Arrays.asList("One", "Abc", "BCD");
    Collections.sort(list, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    });
        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals("Abc",sortedList.get(0));

        List<String> sortedListByFunc = list.stream().sorted(
                (a, b) -> {
                    return a.compareTo(b);
                }
        ).collect(Collectors.toList());
    System.out.println(sortedListByFunc);
    }
}
