package com.breakingbytes.be_java_hisp_w25_g04.repository;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;

import java.nio.channels.spi.SelectorProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbMock {
    private static DbMock dbMock;
    private List<User> listOfUsers;
    private List<Seller> listOfSellers;
    private List<Post> listOfPost;
    private List<Product> listOfProduct;
    private DbMock(){
        this.listOfProduct = loadProductData();
        this.listOfPost = loadPostsData();
        this.listOfSellers = loadSellerData();
        this.listOfUsers = loadUserData();
        addFollowersToVendors();
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public List<Seller> getListOfSellers() {
        return listOfSellers;
    }

    public List<Post> getListOfPost() {
        return listOfPost;
    }

    public List<Product> getListOfProduct() {
        return listOfProduct;
    }

    public static DbMock getInstance(){
        if(dbMock == null){
            dbMock = new DbMock();
        }
        return  dbMock;
    }


    private List<User> loadUserData() {
        List<User> list = new ArrayList<>();
        list.add(new User(1,
                "Pepe",
                new ArrayList<>(List.of(this.listOfSellers.get(0)))));

        list.add(new User(2,
                "Carlos",
                new ArrayList<>(List.of(this.listOfSellers.get(0),
                        this.listOfSellers.get(1)
                ))));
        return list;
    }
    private List<Seller> loadSellerData() {
        List<Seller> list = new ArrayList<>();

        list.add(new Seller(
                100,
                "Juan",
                new ArrayList<>(), //Sellers
                new ArrayList<>(), //Users
                new ArrayList<>(List.of(this.listOfPost.get(0),this.listOfPost.get(1)))
        ));
        list.add(new Seller(
                101,
                "Robert",
                new ArrayList<>(), //Sellers
                new ArrayList<>(), //Users
                new ArrayList<>(List.of(this.listOfPost.get(1),this.listOfPost.get(3), this.listOfPost.get(4)))
        ));
        return list;
    }

    private void addFollowersToVendors(){
        this.listOfSellers.get(0).getFollowers().add(this.listOfUsers.get(0));
        this.listOfSellers.get(0).getFollowers().add(this.listOfUsers.get(1)); // Juan tiene de seguidor a Carlos
        this.listOfSellers.get(1).getFollowers().add(this.listOfUsers.get(0));
    }

    private List<Post> loadPostsData() {
        List<Post> list = new ArrayList<>();
        list.add(new Post(1, LocalDate.of(2024,02,20), this.listOfProduct.get(0), 100, 1500.0));
        list.add(new Post(2, LocalDate.of(2023,02,20), this.listOfProduct.get(1), 100, 1000.0));
        list.add(new Post(3, LocalDate.now(), this.listOfProduct.get(2), 200, 240.0));
        list.add(new Post(4, LocalDate.now(), this.listOfProduct.get(3), 100, 20.0));
        list.add(new Post(5, LocalDate.now(), this.listOfProduct.get(4), 300, 30.0));
        return list;
    }

    private List<Product> loadProductData() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Laptop", "Electronics", "Dell", "Silver", "Thin and lightweight design"));
        list.add(new Product(2, "AAAAAAAA", "Electronics", "Samsung", "Black", "5G capable"));
        list.add(new Product(3, "Running Shoes", "Apparel", "Nike", "Blue", "Breathable mesh upper"));
        list.add(new Product(4, "Headphones", "Electronics", "Sony", "Red", "Noise-canceling feature"));
        list.add(new Product(5, "Backpack", "Accessories", "JanSport", "Gray", "Multiple compartments"));
        return list;
    }

}
