package com.global.retailstore.service;

import com.global.retailstore.exceptions.CartIsEmptyException;
import com.global.retailstore.exceptions.InvaildUserException;
import com.global.retailstore.model.Product;
import com.global.retailstore.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService{

    @Override
    public User generateBill(User user) {

        if(Optional.ofNullable(user.getUserId()).isEmpty()){
            throw new InvaildUserException("User ID is missing ");
        }
        if(Optional.ofNullable(user.getUserType()).isEmpty()){
            throw new InvaildUserException("User Type is missing");
        }
        if( Optional.ofNullable(user.getShoppingCart()).isEmpty() || user.getShoppingCart().size()<=0){
            throw new CartIsEmptyException("Cart is Empty ");
        }

        amountPayable(user);

        return user;



    }

    private void amountPayable(User user){

        BigDecimal total=user.getShoppingCart().stream().map(product -> product.getAmount().multiply(BigDecimal.valueOf(product.getUnit())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal discountByUserType =calculateDiscount(user,user.getShoppingCart());

        BigDecimal totalAfterUserDiscount=total.subtract(discountByUserType);

        Integer flatDiscount=(totalAfterUserDiscount.intValue()/100)*5;

         BigDecimal amountPayable= totalAfterUserDiscount.subtract(BigDecimal.valueOf(flatDiscount));

         user.setTotal(total);
         user.setTotalAfterUserDiscount(totalAfterUserDiscount);
         user.setUserDiscount(discountByUserType);
         user.setFlatDiscount(BigDecimal.valueOf(flatDiscount));
         user.setNetAmountPayable(amountPayable);





    }
    private BigDecimal calculateDiscount(User user, List<Product> productList) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal discountPercent=BigDecimal.ZERO;

        if ("employee".equalsIgnoreCase(user.getUserType())) {
            discountPercent=BigDecimal.valueOf(0.30);
        } else if ("affiliated".equalsIgnoreCase(user.getUserType())) {
            discountPercent=BigDecimal.valueOf(0.10);
        }
        else if ("customer".equalsIgnoreCase(user.getUserType()) && ChronoUnit.YEARS
                .between(user.getRegisteredDate(), LocalDate.now()) > 2) {
            discountPercent=BigDecimal.valueOf(0.05);
        }

            for(Product product:productList){
            if (!product.getIsGrocery()) {
                    discount = discount.add(product.getAmount().multiply(BigDecimal.valueOf(product.getUnit()))).multiply(discountPercent);
                }
            }
        return discount;
        }


}
