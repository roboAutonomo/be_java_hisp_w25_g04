package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;

import java.util.List;

public interface IUserService {
    public List<UserDTO> getUsersFollowersOf(UserDTO userDTO);
    public List<UserDTO> getUsersFollowed(UserDTO userDTO);
}
