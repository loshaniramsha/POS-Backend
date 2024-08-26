package org.example.aadassignment01backend.bo;

import org.example.aadassignment01backend.bo.custom.CustomerBO;
import org.example.aadassignment01backend.bo.custom.ItemBO;
import org.example.aadassignment01backend.bo.custom.OrderDetailsBO;
import org.example.aadassignment01backend.bo.custom.OrdersBO;
import org.example.aadassignment01backend.bo.custom.impl.CustomerBOImpl;
import org.example.aadassignment01backend.bo.custom.impl.ItemBOImpl;
import org.example.aadassignment01backend.bo.custom.impl.OrderDetailsBOImpl;
import org.example.aadassignment01backend.bo.custom.impl.OrdersBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,ORDERS,ORDERDETAILS
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
