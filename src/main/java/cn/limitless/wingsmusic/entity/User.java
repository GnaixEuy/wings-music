package cn.limitless.wingsmusic.entity;

import cn.limitless.wingsmusic.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User extends BaseEntity {


    @ToString.Include
    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @ToString.Include
    @Column(name = "nickname", length = 64)
    private String nickname;

    @ToString.Include
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ToString.Include
    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    @ToString.Include
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @ToString.Include
    @Column(name = "last_login_ip", length = 64)
    private String lastLoginIp;

    @ToString.Include
    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    @ToString.Include
    @Column(name = "open_id", length = 32)
    private String openId;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();


}