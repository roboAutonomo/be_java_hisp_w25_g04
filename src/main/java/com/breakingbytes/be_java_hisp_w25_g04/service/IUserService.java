package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ResponseDTO;

public interface IUserService {
    ResponseDTO unfollowUser(String userId, String userIdToUnfollow);
}
