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

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CollectorsTests {

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
  public void givenUsers_withToSet_thenSuccess() {
    Set<String> toSet = userList.stream().map(User::getName).collect(Collectors.toSet());
    assertEquals(2, toSet.size());
  }

  @Test
  public void givenUsers_withToMap_thenSuccess() {
    Map<String, String> userMap =
        userList.stream()
            .collect(Collectors.toMap(user -> user.getUsername(), user -> user.getMobile()));

    // 重复的key
    Map<String, User> duplicateMap =
        Stream.concat(userList.stream(), userList.stream())
            .peek(user -> log.debug("username, {}", user.getUsername()))
            .collect(
                Collectors.toMap(
                    user -> user.getUsername(), user -> user, (existing, replace) -> existing));
    // TreeMap
    Map<String, User> treeMap =
        Stream.concat(userList.stream(), userList.stream())
            .peek(user -> log.debug("username, {}", user.getUsername()))
            .collect(
                Collectors.toMap(
                    user -> user.getUsername(),
                    user -> user,
                    (existing, replace) -> existing,
                    TreeMap::new));

    assertEquals(2, duplicateMap.size());
  }

  @Test
  public void givenUsers_withToTreeSet_thenSuccess() {
    Comparator<User> byAge = Comparator.comparing(User::getAge);

    TreeSet<User> users =
        userList.stream().collect(Collectors.toCollection(() -> new TreeSet<>(byAge)));
    assertEquals(30, users.stream().map(User::getAge).findFirst().orElse(-1));
    assertTrue(users.size() > 3);
  }

  @Test
  public void givenUsers_withSimpleSolarFunction_thenGetResult() {
    Double avg = userList.stream().collect(averagingDouble(User::getAge));
    int sum = userList.stream().collect(summingInt(User::getAge));

    // 统计数据
    DoubleSummaryStatistics stat = userList.stream().collect(summarizingDouble(User::getAge));
    System.out.println(stat);
  }

  @Test
  public void givenUsers_withGroupingByAge_thenGetStatWithStream() {
      Map<Integer, List<User>> result = userList.stream().collect(groupingBy(user -> (int) Math.floor(user.getAge() / 10)));

    Map<Integer, DoubleSummaryStatistics> resulta = userList.stream().collect(groupingBy(user -> (int) Math.floor(user.getAge() / 10), summarizingDouble(User::getAge)));

    Map<Integer, List<UserDTO>> collect = userList.stream().collect(groupingBy(
            user -> (int) Math.floor(user.getAge() / 10),
            mapping(user -> new UserDTO(
                    user.getId(),
                    user.getUsername() + ":" + user.getName()
            ), toList())
    ));
    System.out.println(collect);
  }

  @Test
  public void givenString_thenMappingAndFiltering_thenChainThemTogether() {
    List<String> strings = List.of
            ("bb","ddd","cc","a");
    Map<Integer, TreeSet<String>> collect = strings.stream().collect(
            groupingBy(
                    String::length,
                    mapping(String::toUpperCase,
                            filtering(s -> s.length() > 1,
                                    toCollection(TreeSet::new))
                    )

            )
    );

    System.out.println(collect);
  }

  @Test
  @DisplayName("join拼接")
  public void givenUsers_withJoining_thenGetString() {

    Map<String, String> requestParams = Map.of("name", "张三",
            "username", "zhangsha",
            "email", "zhangsan@local.dev");
    String url = requestParams.keySet().stream()
            .map(
                    key -> key + "=" + requestParams.get(key)
            ).sorted().collect(joining(
                    "&", "http://local.dev/api?",
                    ""

            ));

    System.out.println(url);
  }
  }