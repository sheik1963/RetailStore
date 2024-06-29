package com.global.retailstore.controller;


import com.global.retailstore.exceptions.CartIsEmptyException;
import com.global.retailstore.exceptions.InvaildUserException;
import com.global.retailstore.model.Product;
import com.global.retailstore.model.User;
import com.global.retailstore.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.beancontext.BeanContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/generate")
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping("/bill")

    ResponseEntity<Object>  generateBill(@RequestBody  User user) {

       return new ResponseEntity<>(billService.generateBill(user), HttpStatus.OK);


    }






}
