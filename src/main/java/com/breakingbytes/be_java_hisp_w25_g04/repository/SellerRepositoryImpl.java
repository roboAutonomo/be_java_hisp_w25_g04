package com.breakingbytes.be_java_hisp_w25_g04.repository;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import java.util.Optional;

@Repository
public class SellerRepositoryImpl implements ISellerRepository{
    @Override
    public List<Seller> getSellers() {
        return DbMock.getInstance().getListOfSellers();
    }

    @Override
    public void addFollower(Seller seller, User follower) {
        seller.addFollower(follower);
    }
  
    public Optional<Seller> findById(int sellerId) {
        return DbMock.getInstance().getListOfSellers().stream().filter(s -> s.getId() == sellerId).findFirst();
    }
  
}
