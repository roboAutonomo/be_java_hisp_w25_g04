package com.breakingbytes.be_java_hisp_w25_g04.service;


import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import com.breakingbytes.be_java_hisp_w25_g04.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.FollowersCountDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.DbMock;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    ISellerRepository sellerRepository;

    @Autowired
    Mapper mapper;

    public FollowersCountDTO getCountFollowersOfSeller(int id){
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        return new FollowersCountDTO(seller.get().getId(), seller.get().getName(), seller.get().getFollowing().size());
    }

    @Override
    public UserFollowersDTO getUsersFollowersOf(int userId, String order) {
        Optional<Seller> user = this.sellerRepository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<User> userFollowes = user.get().getFollowers();
        if(userFollowes.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no tiene seguidores");
        List<UserDTO> followers = userFollowes.stream().map(u -> mapper.modelMapper().map(u, UserDTO.class)).toList();
        if(order.equals("name_asc")){
            followers = followers.stream()
                    .sorted(Comparator.comparing(UserDTO::getName))
                    .collect(Collectors.toList());
        }else if(order.equals("name_desc")){
            followers = followers.stream()
                    .sorted(Comparator.comparing(UserDTO::getName, Comparator.reverseOrder()))
                    .collect(Collectors.toList());
        }
        return new UserFollowersDTO(user.get().getId(), user.get().getName(), followers);
    }

    @Override
    public UserFollowedDTO getUsersFollowed(int userId, String order) {
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("ID de usuario invalido");
        List<Seller> userFolloweds = user.get().getFollowing();
        if(userFolloweds.isEmpty()) throw new NotFoundException("El usuario con id: " + user.get().getId() + " no sigue a vendedores");
        List<UserDTO> followed = userFolloweds.stream().map(u -> mapper.modelMapper().map(u, UserDTO.class)).toList();
        if (order.equals("name_asc")){
            followed = followed.stream()
                    .sorted(Comparator.comparing(UserDTO::getName))
                    .collect(Collectors.toList());
        }else if(order.equals("name_desc")){
            followed = followed.stream()
                    .sorted(Comparator.comparing(UserDTO::getName,
                            Comparator.reverseOrder()))
                    .collect(Collectors.toList());
        }
        return new UserFollowedDTO(user.get().getId(), user.get().getName(), followed);
    }
  
    @Override
    public void follow(int userId, int userIdToFollow) {
        Optional<User> me = this.userRepository.findById(userId);

        if (me.isEmpty()){
          throw new BadRequestException("Usuario no encontrado"); // Not Found ??
        }

        Optional<Seller> userToFollow = this.sellerRepository.findById(userIdToFollow);

        if (userToFollow.isEmpty()){
            throw new BadRequestException("Vendedor no encontrado"); // Not found ??
        }

        if(userToFollow.get().getFollowers().contains(me.get())){
            throw new BadRequestException("Ya estas siguiendo a ese usuario");
        }

        this.sellerRepository.addFollower(userToFollow.get(), me.get());
        this.userRepository.addFollowing(me.get(), userToFollow.get());
    }
}
