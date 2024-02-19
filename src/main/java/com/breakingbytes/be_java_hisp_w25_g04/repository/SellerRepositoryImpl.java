package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SellerRepositoryImpl implements ISellerRepository{
    @Override
    public Optional<Seller> findById(int sellerId) {
        return DbMock.getInstance().getListOfSellers().stream().filter(seller -> seller.getId() == sellerId).findFirst();
    }

    @Override
    public Void addFollower(Seller seller, User follower) {
        seller.addFollower(follower);
        return null;
    }
}
