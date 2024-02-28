package com.breakingbytes.be_java_hisp_w25_g04.utils;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.util.List;
import java.util.Optional;

public class UserFactory {

    public static Optional<Seller> getSellerById(Integer idUser){
        Seller seller = new Seller();
        seller.setId(idUser);
        seller.setName("Juan");
        seller.setFollowers(List.of(
                new User(1,"Martin",null),
                new User(2,"Ana",null),
                new User(3,"Camila",null)
        ));
        return Optional.of(seller);
    }

    public static List<UserDTO> getSortedList(){
        return List.of(
                new UserDTO(2,"Ana"),
                new UserDTO(3,"Camila"),
                new UserDTO(1,"Martin")
        );
    }
}
