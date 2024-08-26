package org.example.aadassignment01backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Orders implements Serializable {
    private String orderId;
    private String date;
    private double discount;
    private double total;
    private String customerId;
}
