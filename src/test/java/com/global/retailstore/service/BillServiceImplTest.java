package com.global.retailstore.service;

import com.global.retailstore.exceptions.CartIsEmptyException;
import com.global.retailstore.exceptions.InvaildUserException;
import com.global.retailstore.model.Product;
import com.global.retailstore.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BillServiceImplTest {

    @Mock
    BillServiceImpl billService=new BillServiceImpl();



    @Test
    public void UserTypeAsEmployee(){
        Product product1= Product.builder().productName("Toothpaste").unit(1L)
                .amount(BigDecimal.valueOf(1000)).isGrocery(false)
                .build();
        List<Product> shoppingCart= new ArrayList<>();
        shoppingCart.add(product1);
        User user= User.builder().userType("Employee").userId(1234L).
                registeredDate(LocalDate.of(2015,05,23)).shoppingCart(shoppingCart)
                        .build();
        User actualUser=billService.generateBill(user);


        assert(actualUser.getUserDiscount().compareTo(BigDecimal.valueOf(300))==0);
        assert(actualUser.getFlatDiscount().compareTo(BigDecimal.valueOf(35))==0);
        assert(actualUser.getNetAmountPayable().compareTo(BigDecimal.valueOf(665))==0);


    }

    @Test
    public void UserTypeAsAffiliated(){
        Product product1= Product.builder().productName("Toothpaste").unit(1L)
                .amount(BigDecimal.valueOf(1000)).isGrocery(false)
                .build();
        List<Product> shoppingCart= new ArrayList<>();
        shoppingCart.add(product1);
        User user= User.builder().userType("affiliated").userId(1234L).
                registeredDate(LocalDate.of(2015,05,23)).shoppingCart(shoppingCart)
                .build();
        User actualUser=billService.generateBill(user);

        assert(actualUser.getUserDiscount().compareTo(BigDecimal.valueOf(100))==0);
        assert(actualUser.getFlatDiscount().compareTo(BigDecimal.valueOf(45))==0);
        assert(actualUser.getNetAmountPayable().compareTo(BigDecimal.valueOf(855))==0);


    }

    @Test
    public void UserTypeAsCustomerWithTwoYearsRegistered(){
        Product product1= Product.builder().productName("Toothpaste").unit(1L)
                .amount(BigDecimal.valueOf(1000)).isGrocery(false)
                .build();
        List<Product> shoppingCart= new ArrayList<>();
        shoppingCart.add(product1);
        User user= User.builder().userType("customer").userId(1234L).
                registeredDate(LocalDate.of(2015,05,23)).shoppingCart(shoppingCart)
                .build();
        User actualUser=billService.generateBill(user);

        assert(actualUser.getUserDiscount().compareTo(BigDecimal.valueOf(50))==0);
        assert(actualUser.getFlatDiscount().compareTo(BigDecimal.valueOf(45))==0);
        assert(actualUser.getNetAmountPayable().compareTo(BigDecimal.valueOf(905))==0);


    }

    @Test
    public void UserTypeAsCustomerWithInTwoYearsRegistered(){
        Product product1= Product.builder().productName("Toothpaste").unit(1L)
                .amount(BigDecimal.valueOf(1010)).isGrocery(false)
                .build();
        List<Product> shoppingCart= new ArrayList<>();
        shoppingCart.add(product1);
        User user= User.builder().userType("customer").userId(1234L).
                registeredDate(LocalDate.now()).shoppingCart(shoppingCart)
                .build();
        User actualUser=billService.generateBill(user);

        assert(actualUser.getUserDiscount().compareTo(BigDecimal.valueOf(0))==0);
        assert(actualUser.getFlatDiscount().compareTo(BigDecimal.valueOf(50))==0);
        assert(actualUser.getNetAmountPayable().compareTo(BigDecimal.valueOf(960))==0);


    }

    @Test
    public void EmptyUserId(){

        User user= User.builder().userType("Employee").shoppingCart(null).build();
        assertThrows(InvaildUserException.class,()->billService.generateBill(user));

    }

    @Test
    public void EmptyUserType(){

        User user= User.builder().userId(1235L).shoppingCart(null).build();
        assertThrows(InvaildUserException.class,()->billService.generateBill(user));

    }

    @Test
    public void EmptyShoppingCart(){

        User user= User.builder().userId(1235L).userType("Employee").build();
        assertThrows(CartIsEmptyException.class,()->billService.generateBill(user));

    }


}