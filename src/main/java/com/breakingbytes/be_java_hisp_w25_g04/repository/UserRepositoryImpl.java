package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    @Override
    public void addPost(Post post, Seller seller) {
        seller.getPosts().add(post);
        DbMock.getInstance().getListOfPost().add(post);
        DbMock.getInstance().getListOfProduct().add(post.getProduct());
    }

    @Override
    public Optional<Seller> findSellerById(int userId) {
        return DbMock.getInstance().getListOfSellers()
                .stream().filter(seller -> seller.getId() == userId)
                .findFirst();
    }
}
