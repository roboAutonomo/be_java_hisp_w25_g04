package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.FollowersCountDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.DbMock;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ModelMapper mapper;

    public FollowersCountDTO getCountFollowersOfSeller(int id){
        Seller seller = userRepository.getSellerById(id);
        return new FollowersCountDTO(seller.getId(), seller.getName(), seller.getFollowing().size());
    }

    @Override
    public UserFollowersDTO getFollowersSortedByOrder(int userId, String order) {
        Seller seller = userRepository.getSellerById(userId);
        List<User> followers = seller.getFollowers();
        List<User> followersSorted;

        if(order.equals("name_asc")){
            followersSorted = followers.stream()
                    .sorted(Comparator.comparing(User::getName))
                    .collect(Collectors.toList());
        }else{
            followersSorted = followers.stream()
                    .sorted(Comparator.comparing(User::getName, Comparator.reverseOrder()))
                    .collect(Collectors.toList());
        }

        List<UserDTO> followersDto = followersSorted.stream().map(u -> mapper.map(u, UserDTO.class)).toList();
        return new UserFollowersDTO(seller.getId(), seller.getName(), followersDto);
    }

    @Override
    public UserFollowedDTO getFollowedsSortedByOrder(int userId, String order) {
        Seller seller = userRepository.getSellerById(userId);
        List<Seller> followeds = seller.getFollowing();
        List<Seller> followedsSorted;

        if(order.equals("name_asc")){
            followedsSorted = followeds.stream()
                    .sorted(Comparator.comparing(User::getName))
                    .collect(Collectors.toList());
        }else{
            followedsSorted = followeds.stream()
                    .sorted(Comparator.comparing(User::getName, Comparator.reverseOrder()))
                    .collect(Collectors.toList());
        }


        List<UserDTO> followedsDto = followedsSorted.stream().map(u -> mapper.map(u, UserDTO.class)).toList();
        return new UserFollowedDTO(seller.getId(), seller.getName(), followedsDto);
    }

}
