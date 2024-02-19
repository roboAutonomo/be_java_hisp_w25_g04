package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{

    @Override
    public List<User> getUsers() {
        return DbMock
                .getInstance()
                .getListOfUsers();
    }

    @Override
    public Optional<User> getUser(UserDTO userDTO) {
        return DbMock
                .getInstance()
                .getListOfUsers()
                .stream()
                .filter(u -> u.getId() == userDTO.getId())
                .findFirst();
    }
}
