package org.example.aadassignment01backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aadassignment01backend.bo.BOFactory;
import org.example.aadassignment01backend.bo.custom.OrdersBO;
import org.example.aadassignment01backend.dto.OrdersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orders")

public class OrdersController extends HttpServlet {
    JsonbConfig config=new JsonbConfig().withFormatting(true);
    Jsonb jsonb= JsonbBuilder.create(config);
    static Logger logger= LoggerFactory.getLogger(OrdersController.class);
    OrdersBO ordersBO= (OrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    @Override
    public void init() throws ServletException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("orders get method");
        String id=req.getParameter("id");
        String all=req.getParameter("all");
        String search=req.getParameter("search");
        String nextid=req.getParameter("nextid");
        
        if (all!=null){
            try(var writer=resp.getWriter()){
                writer.write(jsonb.toJson(ordersBO.getAllOrders()));
                
            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (id!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(ordersBO.search(id)));

            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (search!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(ordersBO.searchByOrderId(search)));

            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (nextid !=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(ordersBO.nextOrderId()));

            } catch (SQLException | JsonbException e) {
                logger.error("faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType()==null){
           resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
       }
       try (var writer=resp.getWriter()){
           logger.info("orders post method");
           OrdersDTO ordersDTO=jsonb.fromJson(req.getReader(),OrdersDTO.class);
           boolean isSaved=ordersBO.saveOrder(ordersDTO);

           if (isSaved){
               writer.write("Order Saved Success");
               logger.info("Order Saved Success");
               resp.setStatus(HttpServletResponse.SC_CREATED);
           }
           else {
               writer.write("Order Saved Failed");
               logger.error("Order Saved Failed");
               resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           }
       } catch (JsonbException | SQLException e) {
           logger.error("Faild with:",e.getMessage());
           logger.error("Faild with:",e.getMessage());
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType()==null){
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        try (var writer=resp.getWriter()){
            logger.info("orders put method");

            OrdersDTO ordersDTO=jsonb.fromJson(req.getReader(),OrdersDTO.class);
            boolean isUpdate=ordersBO.updateOrder(ordersDTO);

            if (isUpdate){
                writer.write("Order Update Success");
                logger.info("Order Update Success");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else {
                writer.write("Order Update Failed");
                logger.error("Order Update Failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (SQLException | JsonbException e) {
            logger.error("Faild with:",e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try (var writer=resp.getWriter()){
           logger.info("orders delete method");

           String id=req.getParameter("id");
           boolean isDeleted=ordersBO.deleteOrder(id);

           if (isDeleted){
               writer.write("Order Deleted Success");
               logger.info("Order Deleted Success");
               resp.setStatus(HttpServletResponse.SC_OK);
           }

       } catch (SQLException | JsonbException e) {
           logger.error("Faild with:",e.getMessage());
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
    }
}
