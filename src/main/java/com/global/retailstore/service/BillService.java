package com.global.retailstore.service;

import com.global.retailstore.model.User;

/**
 * This interface represents a method declation for billing to user
 *
 * @author  Sheik Abdulla
 * @since 1.0
 * @version 1.0
 *
 */
public interface BillService {

    User generateBill(User user);
}
