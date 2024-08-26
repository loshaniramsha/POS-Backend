package org.example.aadassignment01backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemDTO implements Serializable {
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemQty;
}
