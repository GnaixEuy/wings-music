package cn.limitless.wingsmusic.entity;

import cn.limitless.wingsmusic.enums.Gender;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {


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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}