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

public interface IUserRepository {
    // public void addPost(Post post, Seller seller);
    public List<User> getUsers();
    public Optional<User> findById(int userId);
    void addFollowing(User user, Seller following);
}
