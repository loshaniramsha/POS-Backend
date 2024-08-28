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
import org.example.aadassignment01backend.bo.custom.ItemBO;
import org.example.aadassignment01backend.bo.custom.OrderDetailsBO;
import org.example.aadassignment01backend.dto.OrderDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/order-details")

public class OrderDetailsController extends HttpServlet {
    JsonbConfig config=new JsonbConfig().withFormatting(true);
    Jsonb jsonb= JsonbBuilder.create(config);
    static Logger logger=LoggerFactory.getLogger(OrderDetailsController.class);
    OrderDetailsBO orderDetailsBO= (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAILS);
    ItemBO itemBO= (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("order-details get method");
        String id=req.getParameter("id");

        if (id!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(orderDetailsBO.searchByOrderId(id)));

            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
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
            logger.info("order-details post method");

            OrderDetailsDTO orderDetailsDTO=jsonb.fromJson(req.getReader(),OrderDetailsDTO.class);
            boolean isSaved=orderDetailsBO.save(orderDetailsDTO);
           boolean isUpdated= itemBO.updateQty(orderDetailsDTO.getItemId(),String.valueOf(orderDetailsDTO.getQty()));

           if (isSaved && isUpdated){
               writer.write("Order Details Saved Success");
               logger.info("Order Details Saved Success");
               resp.setStatus(HttpServletResponse.SC_CREATED);
           }
           else {
               writer.write("Order Details Saved Failed");
               logger.info("Order Details Saved Failed");
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
