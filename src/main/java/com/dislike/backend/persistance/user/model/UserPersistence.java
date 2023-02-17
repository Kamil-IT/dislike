package com.dislike.backend.persistance.user.model;


import com.dislike.backend.persistance.video.model.DislikePersistence;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPersistence implements UserDetails {

    @Id
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<DislikePersistence> userPersistence;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

//    TODO: GrantedAuthority add column
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(Role.USER.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPersistence that = (UserPersistence) o;
        return username != null && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
