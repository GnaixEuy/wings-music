package cn.limitless.wingsmusic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {


    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name = "nickname", length = 64)
    private String nickname;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @Column(name = "last_login_ip", length = 64)
    private String lastLoginIp;

    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    @Column(name = "open_id", length = 32)
    private String openId;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();


}