package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(int userId);
    void addFollowing(User user, Seller following);
}
