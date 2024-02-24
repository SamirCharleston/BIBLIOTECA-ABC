package com.sismanut.sismanut.coreClasses.userAutentication;

import com.sismanut.sismanut.entities.UsuarioEntity;
import com.sismanut.sismanut.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
@Repository
public interface UserDefaultRepository extends JpaRepository<UsuarioEntity, UUID> {
    UserDetails findByLogin(String login);
    @Query(value = "SELECT u.password FROM UsuarioEntity u WHERE login = :login")
    String getPasswordByLogin(@RequestParam("login") String login);
    @Query(value = "SELECT u.nome FROM UsuarioEntity u WHERE login = :login")
    String getUserNameByLogin(@RequestParam("login") String login);
}
