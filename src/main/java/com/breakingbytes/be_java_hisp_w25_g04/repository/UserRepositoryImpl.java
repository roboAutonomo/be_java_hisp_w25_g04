package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    @Override
    public Seller getSellerById(int idSeller) {
        List<Seller> sellers = DbMock.getInstance().getListOfSellers();
        return sellers.stream()
                .filter( s -> s.getId() == idSeller)
                .findFirst()
                .get();
    }
}
