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
import org.example.aadassignment01backend.bo.custom.CustomerBO;
import org.example.aadassignment01backend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {

    JsonbConfig jsonbConfig=new JsonbConfig().withFormatting(true);
    Jsonb jsonb= JsonbBuilder.create(jsonbConfig);
    static Logger logger= LoggerFactory.getLogger(CustomerController.class);
    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       logger.info("customer get method");
       String id=req.getParameter("id");
       String all=req.getParameter("all");
       String search=req.getParameter("search");
       String nextid=req.getParameter("nextid");

       if (all!=null){
           try (var writer=resp.getWriter()){
               writer.write(jsonb.toJson(customerBO.getAllCustomers()));

           } catch (SQLException | JsonbException e) {
               logger.error("Faild with:",e.getMessage());
               throw new RuntimeException(e);
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
       } else if (id!=null){
           try(var writer=resp.getWriter()){
               writer.write(jsonb.toJson(customerBO.search(id)));

           } catch (SQLException | JsonbException e) {
               logger.error("Faild with:",e.getMessage());
               throw new RuntimeException(e);
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
       } else if (search!=null){
         try (var writer=resp.getWriter()){
             writer.write(jsonb.toJson(customerBO.searchByContact(search)));
         } catch (SQLException | JsonbException e) {
             logger.error("Faild with:",e.getMessage());
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
       } else if (nextid!=null){
           try (var writer=resp.getWriter()){
               writer.write(jsonb.toJson(customerBO.nextCustomerId()));
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
        try(var writer=resp.getWriter()){
            logger.info("Inside customer post method");

            CustomerDTO customerDTO=jsonb.fromJson(req.getReader(),CustomerDTO.class);
            boolean isSaved=customerBO.saveCustomer(customerDTO);

            if (isSaved){
                writer.write("Customer saved successfully");
                logger.info("Customer saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Customer not saved");
                logger.error("Customer not saved");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (SQLException | JsonbException e) {
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
            logger.info("Inside customer put method");
            CustomerDTO customerDTO=jsonb.fromJson(req.getReader(),CustomerDTO.class);
            System.out.println(customerDTO);
            boolean isUpdated=customerBO.updateCustomer(customerDTO);

            if (isUpdated){
                writer.write("Customer updated successfully");
                logger.info("Customer updated successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Customer not updated");
                logger.error("Customer not updated");
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
           logger.info("inside customer delete method");
           String id=req.getParameter("id");
           boolean isDelete=customerBO.deleteCustomer(id);

           if (isDelete){
               writer.write("Customer deleted successfully");
               logger.info("Customer deleted successfully");
               resp.setStatus(HttpServletResponse.SC_CREATED);
           }
           else {
               writer.write("Customer not deleted");
               logger.error("Customer not deleted");
               resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           }

       } catch (SQLException | JsonbException e) {
           logger.error("Faild with:",e.getMessage());
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
    }
}
