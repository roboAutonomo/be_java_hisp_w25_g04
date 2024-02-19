package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.FollowersCountDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import java.io.IOException;

import java.util.List;

public interface IUserService {
    FollowersCountDTO getCountFollowersOfSeller(int id);
    UserFollowersDTO getFollowersSortedByOrder(int userId, String order);
    UserFollowedDTO getFollowedsSortedByOrder(int userId, String order);
    UserFollowersDTO getUsersFollowersOf(UserDTO userDTO);
    UserFollowedDTO getUsersFollowed(UserDTO userDTO);
    void follow(int userId, int userIdToFollow);
}
