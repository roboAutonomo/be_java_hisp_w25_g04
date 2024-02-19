package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll();

    Optional<User> findById(int id);

}
