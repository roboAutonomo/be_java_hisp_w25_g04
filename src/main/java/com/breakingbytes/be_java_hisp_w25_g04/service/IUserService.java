package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.FollowersCountDTO;

public interface IUserService {
    FollowersCountDTO getCountFollowersOfSeller(int id);
}
