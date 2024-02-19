package com.breakingbytes.be_java_hisp_w25_g04.repository;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import java.util.Optional;
import java.util.List;

public interface IUserRepository {
    public List<User> getUsers();
    public Optional<User> findById(int userId);
    void addFollowing(User user, Seller following);
}
