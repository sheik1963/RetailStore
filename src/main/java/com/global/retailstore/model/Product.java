package com.global.retailstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

   String productName;
   Boolean isGrocery;
   BigDecimal amount;
   Long unit;
}
