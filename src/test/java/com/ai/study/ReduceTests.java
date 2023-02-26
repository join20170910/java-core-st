package com.ai.study;

import com.ai.study.vo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ReduceTests {

    private static final User[] arrayOfUsers = {
            User.builder()
                    .id(1L).username("zhangsan")
                    .name("张三")
                    .enabled(true)
                    .roles(List.of("ROLE_ADMIN","ROLE_USER"))
                    .mobile("13029292927").build(),

            User.builder()
                    .id(2L).username("lisi")
                    .name("李四")
                    .enabled(false)
                    .roles(List.of("ROLE_ADMIN"))
                    .mobile("13029292928").build(),
            User.builder()
                    .id(3L).username("wangwu")
                    .name("王五")
                    .enabled(true)
                    .roles(List.of("ROLE_USER"))
                    .mobile("13029292929").build()

    };
    private List<User> userList;
    @BeforeEach
    void setup(){
        userList = Arrays.asList(arrayOfUsers);
    }





    @Test
    @DisplayName("reduce 求和")
    public void givenUsers_thenCompareReduceAndCollect(){

        // 设计上, reduce 应该和不可变对象一起工作。
        // 如果使用可变对象，也可以得到结果，但是不是线程安全的
        // 而且通常意义上来说，reduce 的性能要弱于 collect
        // 但 reduce 是一个灵活 的选项，在务个语言构架中有广泛的应用
        Integer sumByReduce = userList.stream().map(User::getAge)
                .reduce(
                        0, (Integer acc, Integer curr) ->
                        {
                            log.debug("acc {}, curr: {}", acc, curr);
                            return acc + curr;
                        }
                );
        assertEquals(103,sumByReduce);

        userList.stream().map(
                User::getAge
        ).reduce(
                0,(Integer acc,Integer curr) -> Integer.sum(acc,curr)
        );

    }

    @Test
    @DisplayName("统计最大值")
    public void givenUsers_thenReduceToMaxId(){
        Optional<User> userOptional
                = userList.stream().reduce(
                (acc, curr) -> {
                    log.debug("acc {}, curr: {}", acc, curr);
                    return acc.getId() > curr.getId() ? acc : curr;
                }
        );
        assertTrue(userOptional.isPresent());
        assertEquals(3L,userOptional.get().getId());
    }

    @Test
    @DisplayName("计数器")
    public void givenUsers_thenReduceToCount(){
        Integer count = userList.stream().reduce(
                0, (acc, curr) -> acc + 1,
                Integer::sum
        );
        assertEquals(3,count);
    }

    @Test
    public void givenUsers_thenReduceToList(){
        List<User> objects = Collections.emptyList();
        userList.parallelStream().reduce(
                objects,
                (acc, curr) ->{
                    List<User> newAcc = new ArrayList<User>();
                     newAcc.addAll(acc);
                    newAcc.add(curr);
                     return newAcc;
                },
                (left,right) ->{
                    List<User> merged = new ArrayList<>();
                    merged.addAll(left);
                    merged.addAll(right);
                    return merged;
                }
        );
    }
}
