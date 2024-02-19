package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISellerRepository sellerRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserFollowersDTO getUsersFollowersOf(UserDTO userDTO) {
        Optional<Seller> user = this.sellerRepository.findById(userDTO.getId());
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<User> userFollowes = user.get().getFollowers();
        if(userFollowes.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no tiene seguidores");
        List<UserDTO> followers = userFollowes.stream().map(u -> mapper.map(u, UserDTO.class)).toList();
        return new UserFollowersDTO(user.get().getId(), user.get().getName(), followers);
    }

    @Override
    public UserFollowedDTO getUsersFollowed(UserDTO userDTO) {
        Optional<User> user = this.userRepository.findById(userDTO.getId());
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<Seller> userFollowes = user.get().getFollowing();
        if(userFollowes.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no sigue a vendedores");
        List<UserDTO> followed = userFollowes.stream().map(u -> mapper.map(u, UserDTO.class)).toList();
        return new UserFollowedDTO(user.get().getId(), user.get().getName(), followed);
    }
  
    @Override
    public void follow(int userId, int userIdToFollow) {
        Optional<User> me = this.userRepository.findById(userId);

        if (me.isEmpty()){
          throw new NotFoundException("Usuario no encontrado");
        }

        Optional<Seller> userToFollow = this.sellerRepository.findById(userIdToFollow);

        if (userToFollow.isEmpty()){
            throw new NotFoundException("Vendedor no encontrado");
        }


        this.sellerRepository.addFollower(userToFollow.get() ,me.get());
    }
}
