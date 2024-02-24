package com.sismanut.sismanut.coreClasses.userAutentication;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter @Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class UserDefaultEntity extends CrudGenericEntity implements UserDetails {
//    @Autowired
//    UserDefaultRepository<UsuarioEntity> repository;
    @Column(unique = true, length = 50)
    private String login;
    @Column(nullable = false, length = 30)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role.equals(Role.ADMIN)){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_VISITOR"));
        } else if(this.role.equals(Role.USER)){
            return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_VISITOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_VISITOR"));
        }
    }

    @Override
    public String getPassword() {
//        String passwordByLogin = repository.getPasswordByLogin(this.login);
//
//        if(login == null || login.isEmpty()){
//            throw new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND);
//        }
//
//        return passwordByLogin;
        return null;
    }

    @Override
    public String getUsername() {
//        String nameByLogin = repository.getUserNameByLogin(this.login);
//
//        if(nameByLogin == null || nameByLogin.isEmpty()){
//            throw new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND);
//        }
//
//        return nameByLogin;
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
