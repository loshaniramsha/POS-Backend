package org.example.aadassignment01backend.dao;

import org.example.aadassignment01backend.dao.custom.impl.CustomerDAOImpl;
import org.example.aadassignment01backend.dao.custom.impl.ItemDAOImpl;
import org.example.aadassignment01backend.dao.custom.impl.OrderDetailsDAOImpl;
import org.example.aadassignment01backend.dao.custom.impl.OrdersDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;

    }
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDERS,ORDERDETAILS
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
                case ITEM:
                    return new ItemDAOImpl();
                    case ORDERS:
                        return new OrdersDAOImpl();
                        case ORDERDETAILS:
                            return new OrderDetailsDAOImpl();
            default:
                return null;

        }
    }
}
