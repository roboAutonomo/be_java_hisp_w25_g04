package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepositoryImpl userRepository;
    @Mock
    private SellerRepositoryImpl sellerRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento ascendente de seguidores por nombre.")
    public void testSortedListFollowersAsc(){
        Integer idUser = 3;
        String order = "name_asc";
        Seller seller = FactoryUsers.getSellerById(idUser);
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListAsc();

        when(sellerRepository.findById(3)).thenReturn(Optional.of(seller));

        List<UserDTO> usersResult = userService.getUsersFollowersOf(idUser, order).getFollowers();

        Assertions.assertEquals(expectedSortedList.get(0), usersResult.get(0));
        Assertions.assertEquals(expectedSortedList.get(1), usersResult.get(1));
        Assertions.assertEquals(expectedSortedList.get(2), usersResult.get(2));
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento descendente de seguidores por nombre.")
    public void testSortedListFollowersDesc(){
        Integer idUser = 3;
        String order = "name_desc";
        Seller seller = FactoryUsers.getSellerById(idUser);
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListDesc();

        when(sellerRepository.findById(3)).thenReturn(Optional.of(seller));

        List<UserDTO> usersResult = userService.getUsersFollowersOf(idUser, order).getFollowers();

        Assertions.assertEquals(expectedSortedList.get(0), usersResult.get(0));
        Assertions.assertEquals(expectedSortedList.get(1), usersResult.get(1));
        Assertions.assertEquals(expectedSortedList.get(2), usersResult.get(2));
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento ascendente de seguidos por nombre.")
    public void testSortedListFollowedsAsc(){
        Integer idUser = 2;
        String order = "name_asc";
        User user = FactoryUsers.getUserById(idUser);
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListAsc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.empty());
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        List<UserDTO> usersResult = userService.getUsersFollowed(idUser, order).getFollowed();

        Assertions.assertEquals(expectedSortedList.get(0), usersResult.get(0));
        Assertions.assertEquals(expectedSortedList.get(1), usersResult.get(1));
        Assertions.assertEquals(expectedSortedList.get(2), usersResult.get(2));
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento descendente de seguidos por nombre.")
    public void testSortedListFollowedsDesc(){
        Integer idUser = 2;
        String order = "name_desc";

        User user = FactoryUsers.getUserById(idUser);
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListDesc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.empty());
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        List<UserDTO> usersResult = userService.getUsersFollowed(idUser, order).getFollowed();

        Assertions.assertEquals(expectedSortedList.get(0), usersResult.get(0));
        Assertions.assertEquals(expectedSortedList.get(1), usersResult.get(1));
        Assertions.assertEquals(expectedSortedList.get(2), usersResult.get(2));
    }
}
