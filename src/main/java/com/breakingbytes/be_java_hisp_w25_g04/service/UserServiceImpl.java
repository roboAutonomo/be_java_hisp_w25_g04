package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UnfollowResponseDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.DbMock;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public UnfollowResponseDTO unfollowUser(String userId, String userIdToUnfollow) {
        Integer userIdInt = Integer.valueOf(userId),
                userIdToUnfollowInt = Integer.valueOf(userIdToUnfollow);

        Optional<User> userOpt = userRepository.findById(userIdInt);
        if(userOpt.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado.");
        }
        User user = userOpt.get();

        List<Seller> userFollowings = user.getFollowing();
        Optional<Seller> userToUnfollowOpt = userFollowings
                                                    .stream()
                                                    .filter(us -> us.getId() == userIdToUnfollowInt)
                                                    .findFirst();

        if(userToUnfollowOpt.isEmpty()) {
            throw new NotFoundException("El usuario que se quiere dejar de seguir no fue encontrado.");
        }

        Seller sellerToUnfollow = userToUnfollowOpt.get();
        userFollowings.remove(sellerToUnfollow);
        userRepository.setUserFollowings(userIdInt, userFollowings);

        List<User> sellerFollowers = sellerToUnfollow.getFollowers();
        sellerFollowers.remove(user);
        userRepository.setSellerFollowers(userIdToUnfollowInt, sellerFollowers);

        return new UnfollowResponseDTO("El usuario " + user.getName() +
                                        " ha dejado de seguir a: " + sellerToUnfollow.getName());
    }
}
