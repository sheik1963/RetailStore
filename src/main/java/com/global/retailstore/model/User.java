package com.global.retailstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class User {


    Long userId;
    String userType;
    LocalDate registeredDate;
    List<Product> shoppingCart;
    BigDecimal Total;
    BigDecimal TotalAfterUserDiscount;
    BigDecimal UserDiscount;
    BigDecimal flatDiscount;
    BigDecimal netAmountPayable;



}
