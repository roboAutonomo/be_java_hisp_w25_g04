package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDto;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    LastPostsDto getPostOfVendorsFollowedByUser(int id, String order);
}
