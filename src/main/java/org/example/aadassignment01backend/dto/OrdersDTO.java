package org.example.aadassignment01backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrdersDTO implements Serializable {
    private String orderId;
    private String date;
    private double discount;
    private double total;
    private String customerId;


}
