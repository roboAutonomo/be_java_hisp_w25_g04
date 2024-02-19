package com.breakingbytes.be_java_hisp_w25_g04.service;


import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.FollowersCountDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import java.io.IOException;

import java.util.List;

public interface IUserService {


    FollowersCountDTO getCountFollowersOfSeller(int id);
    UserFollowersDTO getUsersFollowersOf(int userId, String order);
    UserFollowedDTO getUsersFollowed(int userId, String order);
    void follow(int userId, int userIdToFollow);

}
