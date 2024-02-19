package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ISellerRepository sellerRepository;

    @Override
    public void follow(int userId, int userIdToFollow) {
        Optional<User> me = this.userRepository.findById(userId);

        if (me.isEmpty()){
          throw new NotFoundException("Usuario no encontrado");
        }

        Optional<Seller> userToFollow = this.sellerRepository.findById(userIdToFollow);

        if (userToFollow.isEmpty()){
            throw new NotFoundException("Vendedor no encontrado");
        }


        this.sellerRepository.addFollower(userToFollow.get() ,me.get());
    }
}
