package com.ai.study.vo;

import lombok.*;

@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data

public class UserDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 自增长 ID，唯一标识
     */

    private Long id;

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

    private String enabled;

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

    public UserDTO(Long id, String s) {
        this.id = id;
        this.name = s;
    }
}
