package com.ai.study.vo;

import lombok.*;

import java.util.List;

/**
 * 用户实体类，实现了 UserDetails 接口
 */
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data

public class User {
    private static final long serialVersionUID = 1L;

    /**
     * 自增长 ID，唯一标识
     */

    private Long id;
    private Integer age;

    /**
     * 用户名
     */

    private String username;

    /**
     * 手机号
     */

    private String mobile;

    /**
     * 姓名
     */

    private String name;

    /**
     * 是否激活，默认激活
     */
    @Builder.Default

    private Boolean enabled = true;

    /**
     * 账户是否未过期，默认未过期
     */
    @Builder.Default

    private Boolean accountNonExpired = true;

    /**
     * 账户是否未锁定，默认未锁定
     */
    @Builder.Default

    private Boolean accountNonLocked = true;

    /**
     * 密码是否未过期，默认未过期
     */
    @Builder.Default

    private Boolean credentialsNonExpired = true;

    /**
     * 密码哈希
     */

    private String password;

    /**
     * 电邮地址
     */

    private String email;

    /**
     * 是否启用两步验证
     */
    @Builder.Default
    private boolean usingMfa = false;

    /**
     * 两步验证的key
     */

    private String mfaKey;
    private List<String> roles;

}
