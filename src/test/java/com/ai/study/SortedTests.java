package com.ai.study;

import com.ai.study.vo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class SortedTests {
    private final User[] arrayOfUsers = {
            User.builder()
                    .id(1L)
                    .username("zhangsan")
                    .name("张三")
                    .age(40)
                    .enabled(true)
                    .mobile("13029292927")
                    .build(),
            User.builder()
                    .id(2L)
                    .username("lisi")
                    .name("李四")
                    .age(33)
                    .enabled(false)
                    .mobile("13029292928")
                    .build(),
            User.builder()
                    .id(3L)
                    .username("wangwu")
                    .name("王五")
                    .enabled(true)
                    .age(54)
                    .mobile("13029292929")
                    .build()
    };
    private List<User> userList;

    @BeforeEach
    void setup() {
        userList = Arrays.asList(arrayOfUsers);
    }
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
        List<String> sortedListByMethodReference = list.stream().sorted(String::compareTo).collect(Collectors.toList());

        list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        //降序
        list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        list.stream().sorted(
                (a, b) -> {
                    return b.compareTo(a);
                }
        ).collect(Collectors.toList());

        List<User> userSorted = userList.stream().sorted((a, b) -> a.getAge().compareTo(b.getAge())).collect(Collectors.toList());
        List<User> userOtherSorted = userList.stream().sorted(Comparator.comparing(
                a -> a.getUsername(),
                (a,b)-> a.compareTo(b)

        )).collect(Collectors.toList());
        //中文排序  汉语拼音
        Collator sortedByZhCH = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);
        List<User> userOtherSortedByChinese = userList.stream().sorted(
                Comparator.comparing(
                User::getName,
                        sortedByZhCH


        )).collect(Collectors.toList());

    }
}
