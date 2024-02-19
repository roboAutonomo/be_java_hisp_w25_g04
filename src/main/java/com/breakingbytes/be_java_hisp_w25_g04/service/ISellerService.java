package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;

import java.util.List;

public interface ISellerService {
    public void addPost(PostDTO postDTO);
    List<PostDTO> findAllPosts();
}
