package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    @Override
    public Optional<User> findById(int userId) {
        return DbMock.getInstance().getListOfUsers().stream().filter(user -> user.getId() == userId).findFirst();
    }

    @Override
    public void setUserFollowings(Integer userId, List<Seller> userFollowings) {
        DbMock
            .getInstance()
            .getListOfUsers()
            .stream()
            .filter(u -> u.getId() == userId)
            .findFirst()
            .get()
            .setFollowing(userFollowings);
    }

    @Override
    public void setSellerFollowers(Integer sellerId, List<User> sellerFollowers) {
        DbMock
                .getInstance()
                .getListOfSellers()
                .stream()
                .filter(s -> s.getId() == sellerId)
                .findFirst()
                .get()
                .setFollowers(sellerFollowers);
    }
}
