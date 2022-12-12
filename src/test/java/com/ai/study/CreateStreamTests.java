package com.ai.study;

import com.ai.study.vo.User;
import com.ai.study.vo.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CreateStreamTests {
  private final User[] arrayOfUsers = {
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

  @Test
  @DisplayName("数组创建流")
  public void givenUsers_createStreamWithArray(){
      Arrays.stream(arrayOfUsers)
              .forEach(System.out::println);
  }

  @Test
  @DisplayName("List集合创建 流")
  public void givenUsers_createStreamWithList(){
      var list = userList.stream()
              .peek(
                      user -> log.debug("user: {}",user)
              ).collect(Collectors.toList());

      assertEquals(userList.size(), list.size());
  }

  @Test
  @DisplayName("创建 流")
  public void givenUsers_createStreamWithStreamOf(){
      List<User> list = Stream.of(arrayOfUsers[0], arrayOfUsers[1], arrayOfUsers[2]).peek(
              user -> log.debug("user: {}", user)
      ).collect(Collectors.toList());


      assertEquals(userList.size(), list.size());
  }
    @Test
    @DisplayName(" Stream .of 任意对象 创建 流")
    public void givenInt_createStreamWithStreamOf(){
        List<Object> list = Stream.of(1, 2, arrayOfUsers[2]).peek(
                user -> log.debug("user: {}", user)
        ).collect(Collectors.toList());
        assertEquals(userList.size(), list.size());
    }

    @Test
    @DisplayName(" Iterate 创建 流")
    public void givenUsers_createStreamWithIterate(){
        Stream.iterate(
                0,n -> n+1
        ).limit(10).peek(
                n-> log.debug("the number is : {}",n)

        ).collect(Collectors.toList());
    }

    @Test
    @DisplayName(" generate 创建 流")
    public void givenUsers_createStreamWithGenerate(){
        Stream.generate(
                ()-> Math.random()
        ).limit(10).peek(
                n-> log.debug("the number is : {}",n)

        ).collect(Collectors.toList());
    }

    @Test
    @DisplayName("")
    public void givenUsers_whenForEachOrdered_thenPrintInfo(){
        ArrayList<User> toSort = new ArrayList<>();
        for (User user : userList){
            toSort.add(user);
        }

        toSort.sort(Comparator.comparing(User::getUsername));
        for (User user: toSort){
            log.debug("用户信息： {}",user);
        }

    }
    @Test
    @DisplayName("")
    public void givenUsers_whenForEachOrdered_thenPrintInfoUsingStream(){
      userList.stream().sorted(
              Comparator.comparing(User::getUsername)
      ).forEachOrdered(
              user -> log.debug("用户信息: {}",user)
      );
    }

    @Test
    @DisplayName("")
    public void givenUsers_whenMatchUsername_thenFindFirstUsing(){
        Optional<User> lisi = userList.stream().filter(
                user ->
                        user.getUsername().equals("lisi")

        ).findFirst();
        assertTrue(lisi.isPresent());
        assertEquals("lisi",lisi.get().getUsername());
    }

    @Test
    @DisplayName("map 函数")
    public void givenUsers_whenMap_thenTransformUsingStream(){
        Set<UserDTO> userDTOS = userList.stream().map(
                user -> UserDTO.builder().username(user.getUsername())
                        .name(user.getName())
                        .enabled(user.getEnabled() ? "激活" : "禁用")
                        .mobile(user.getMobile())
                        .build()
        ).collect(Collectors.toSet());
        assertEquals(3,userDTOS.size());
    }

    @Test
    @DisplayName("count min max 函数")
    public void givenUsers_when_thenTransformUsingStream(){
        long count = userList.stream().filter(user -> user.getMobile().startsWith("130"))
                .count();
    System.out.println(count);
        Optional<User> max = userList.stream().max(Comparator.comparing(User::getId));
        Optional<User> min = userList.stream().min(Comparator.comparing(User::getId));
    }
}
