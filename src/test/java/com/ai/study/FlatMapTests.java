package com.ai.study;

import com.ai.study.vo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatMapTests {

    private static final User[] arrayOfUsers = {
            User.builder()
                    .id(1L).username("zhangsan")
                    .name("张三")
                    .enabled(true)
                    .mobile("13029292927").build(),

            User.builder()
                    .id(2L).username("lisi")
                    .name("李四")
                    .enabled(false)
                    .mobile("13029292928").build(),
            User.builder()
                    .id(3L).username("wangwu")
                    .name("王五")
                    .enabled(true)
                    .mobile("13029292929").build()

    };
    private List<User> userList;
    @BeforeEach
    void setup(){
        userList = Arrays.asList(arrayOfUsers);
    }

    @Data
    @AllArgsConstructor
    static class Profile{
        private String username;
        private String greeting;
    }
    static class ThirdPartyApi{
        //findAny 可能会为空
        static Optional<Profile> findByUsername(String username){
            return Arrays.stream(arrayOfUsers).filter(
                    user -> "zhangsan".equals(username) && user.getUsername().equals(username)
            ).findAny()
                    .map(
                            user -> new Profile(user.getUsername(),"hello " +user.getName())
                    );

        }
    }
    @Test
    @DisplayName("orElse")
    public void givenUsers_withOptional_thenDealElaseWithStream(){
        String greeting = ThirdPartyApi.findByUsername("zhangsan").map(
                Profile::getGreeting
        ).orElse("未知用户");
        assertEquals("未知用户",greeting);
    }
}
