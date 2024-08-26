package org.example.aadassignment01backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetails implements Serializable {

    private String orderId;
    private String itemId;
    private int qty;
    private double unitPrice;
    private double total;
}
