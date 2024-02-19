package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UnfollowResponseDTO;

public interface IUserService {
    public UnfollowResponseDTO unfollowUser(String userId, String userIdToUnfollow);
}
