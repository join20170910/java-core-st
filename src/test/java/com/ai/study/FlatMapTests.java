package com.ai.study;

import com.ai.study.vo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FlatMapTests {

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

    @Test
    @DisplayName("")
    public void givenUsersWithRoles_whenParentChild_withoutFlatMap(){

        // 嵌套的结构
        List<List<String>> users = userList.stream().map(
                User::getRoles
        ).peek(
                roles -> log.debug("roles :{}", roles)
        ).collect(toList());
 log.debug("users: {}",users);


    }

    @Test
    @DisplayName("")
    public void givenUsersWithRoles_withFlatMap(){
        List<String> flatMapStr = userList.stream()
                .flatMap(user -> user.getRoles().stream())
                .peek(role -> log.debug("roles: {}", role))
                .collect(toList());
    System.out.println(flatMapStr);
    }

    @Test
    @DisplayName("通过 flatmap  去掉 Optional ")
    public void givenUsers_withOptional_thenFlatMapWithStream(){
        List<Profile> profileList = userList.stream()
                .map(user -> ThirdPartyApi.findByUsername(user.getUsername()))
                .flatMap(Optional::stream)
                .peek(profile -> log.debug("profile: {}", profile))
                .collect(toList());
    System.out.println(profileList);
    }

}
