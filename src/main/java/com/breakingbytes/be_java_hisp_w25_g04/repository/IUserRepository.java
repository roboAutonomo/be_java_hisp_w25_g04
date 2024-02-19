package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    public List<User> getUsers();
    public Optional<User> getUser(UserDTO userDTO);
}
