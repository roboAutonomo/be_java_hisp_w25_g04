package com.breakingbytes.be_java_hisp_w25_g04.service;


import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;

import java.io.IOException;

public interface IUserService {
    public UserFollowersDTO getUsersFollowersOf(UserDTO userDTO);
    public UserFollowedDTO getUsersFollowed(UserDTO userDTO);
    void follow(int userId, int userIdToFollow);
}
