package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<UserDTO> getUsersFollowersOf(UserDTO userDTO) {
        Optional<User> user = this.userRepository.getUser(userDTO.getId());
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<Seller> userFollowes = user.get().getFollowing();
        if(userFollowes.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no tiene seguidores");
        return userFollowes.stream().map(u -> mapper.map(u, UserDTO.class)).toList();
    }
}
