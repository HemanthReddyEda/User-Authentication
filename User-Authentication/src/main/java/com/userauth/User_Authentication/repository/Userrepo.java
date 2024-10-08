package com.userauth.User_Authentication.repository;

import com.userauth.User_Authentication.model.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepo extends JpaRepository<Usermodel,Integer> {
    Optional<Usermodel> findByName(String name);

    Optional<Usermodel> findByEmail(String name);
}
