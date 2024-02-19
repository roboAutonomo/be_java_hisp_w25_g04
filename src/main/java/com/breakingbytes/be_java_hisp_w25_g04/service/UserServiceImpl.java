package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<UserDTO> getUsersFollowersOf(UserDTO userDTO) {
        List<User> users = this.userRepository.getUsers();
        if (users.isEmpty()) throw new NotFoundException("No se encontraron usuarios");
        Optional<User> user = this.userRepository.getUser(userDTO);
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<User> userFollowes = users.stream().filter(u -> u.getFollowing().contains(user.get())).toList();
        if(user.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no tiene seguidores");
        return null;
    }
}
