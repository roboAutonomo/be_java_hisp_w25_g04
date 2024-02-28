package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        Seller seller = FactoryUsers.getSellerThree();
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListAsc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.of(seller));
        List<UserDTO> usersResult = userService.getUsersFollowersOf(idUser, order).getFollowers();

        assertThat(expectedSortedList).isEqualTo(usersResult);
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento descendente de seguidores por nombre.")
    public void testSortedListFollowersDesc(){
        Integer idUser = 3;
        String order = "name_desc";
        Seller seller = FactoryUsers.getSellerThree();
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListDesc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.of(seller));
        List<UserDTO> usersResult = userService.getUsersFollowersOf(idUser, order).getFollowers();

        assertThat(expectedSortedList).isEqualTo(usersResult);
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento ascendente por nombre de la lista de seguidos.")
    public void testSortedListFollowedsAsc(){
        Integer idUser = 2;
        String order = "name_asc";
        User user = FactoryUsers.getUserTwo();
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListAsc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.empty());
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        List<UserDTO> usersResult = userService.getUsersFollowed(idUser, order).getFollowed();

        assertThat(expectedSortedList).isEqualTo(usersResult);
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento descendente por nombre de la lista de seguidos.")
    public void testSortedListFollowedsDesc(){
        Integer idUser = 2;
        String order = "name_desc";
        User user = FactoryUsers.getUserTwo();
        List<UserDTO> expectedSortedList= FactoryUsers.getSortedListDesc();

        when(sellerRepository.findById(idUser)).thenReturn(Optional.empty());
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        List<UserDTO> usersResult = userService.getUsersFollowed(idUser, order).getFollowed();

        assertThat(expectedSortedList).isEqualTo(usersResult);
    }
}
