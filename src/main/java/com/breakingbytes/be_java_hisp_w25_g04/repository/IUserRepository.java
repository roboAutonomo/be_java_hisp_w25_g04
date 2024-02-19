package com.breakingbytes.be_java_hisp_w25_g04.repository;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import java.util.Optional;
import java.util.List;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;

import java.util.List;
import java.util.Optional;

import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;
import java.util.Optional;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

     void setUserFollowings(Integer userId, List<Seller> userFollowings);
    void setSellerFollowers(Integer sellerId, List<User> sellerFollowers);
    List<User> findAll();
    public Optional<User> findById(int userId);
    void addFollowing(User user, Seller following);
}
