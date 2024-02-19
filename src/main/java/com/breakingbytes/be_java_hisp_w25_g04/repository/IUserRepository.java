package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;

import java.util.List;

public interface IUserRepository {

    Seller getSellerById(int idSeller);
}
