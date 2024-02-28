package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTests {

    @Mock
    private UserRepositoryImpl userRepository;


    @InjectMocks
    private SellerServiceImpl sellerService;


    @Test
    @DisplayName("T-0008 Posts Ordenados Por Fecha Ascendente")
    void postOrderedByAscDateTestOk() {
        //ARRANGE
        User user = FactoryUsers.getInstance().getListOfUsers().get(0); //Primer usuario con Id 1
        user.getFollowing().get(0).setPosts(FactoryUsers.getInstance().getPostsWithoutOrder());

        //Se agrega Post con fecha vieja mayor a 2 semanas
        Post p1 = new Post(3, LocalDate.now().minusWeeks(6), new Product(), 100, 2000.0);
        p1.setPostId(5);
        user.getFollowing().get(0).getPosts().add(p1);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        LastPostsDTO expected = new LastPostsDTO(user.getId(),
                FactoryUsers.getInstance().convertPostToResponsePostDTO(
                        FactoryUsers.getInstance().getPostsWithoutOrder()));
        //ACT

        LastPostsDTO result = sellerService.getPostOfVendorsFollowedByUser(user.getId(),"");
        //ASSERT

        assertEquals(expected, result);
    }
}
