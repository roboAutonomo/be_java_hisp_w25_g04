package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.RequestPostDTO;

import java.util.List;

public interface ISellerService {
    public void addPost(RequestPostDTO requestPostDTO);
    List<RequestPostDTO> findAllPosts();
    public Boolean quitFollower(String sellerId, String userId);
}
