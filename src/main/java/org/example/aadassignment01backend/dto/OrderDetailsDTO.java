package org.example.aadassignment01backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetailsDTO implements Serializable {
    private String orderId;
    private String itemId;
    private int qty;
    private double unitPrice;
    private double total;
}
