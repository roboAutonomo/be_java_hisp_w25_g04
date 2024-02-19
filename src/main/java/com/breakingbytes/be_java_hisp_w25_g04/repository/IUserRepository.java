package com.breakingbytes.be_java_hisp_w25_g04.repository;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;

public interface IUserRepository {

    Seller getSellerById(int idSeller);
}
