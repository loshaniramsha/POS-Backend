package org.example.aadassignment01backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aadassignment01backend.bo.BOFactory;
import org.example.aadassignment01backend.bo.custom.CustomerBO;
import org.example.aadassignment01backend.bo.custom.ItemBO;
import org.example.aadassignment01backend.dto.CustomerDTO;
import org.example.aadassignment01backend.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")

public class ItemController extends HttpServlet {

    JsonbConfig jsonbConfig=new JsonbConfig().withFormatting(true);
    Jsonb jsonb= JsonbBuilder.create(jsonbConfig);
    static Logger logger= LoggerFactory.getLogger(CustomerController.class);
    ItemBO itemBO= (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("item get method");
        String id=req.getParameter("id");
        String all=req.getParameter("all");
        String search=req.getParameter("search");
        String nextid=req.getParameter("nextid");

        if (all!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(itemBO.getAllItem()));

            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (id!=null){
            try(var writer=resp.getWriter()){
                writer.write(jsonb.toJson(itemBO.search(id)));

            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (search!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(itemBO.searchByName(search)));
            } catch (SQLException | JsonbException e) {
                logger.error("Faild with:",e.getMessage());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (nextid!=null){
            try (var writer=resp.getWriter()){
                writer.write(jsonb.toJson(itemBO.nextItemId()));
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
            logger.info("Inside item post method");

            ItemDTO itemDTO=jsonb.fromJson(req.getReader(),ItemDTO.class);
            System.out.println(itemDTO);
            boolean isSaved=itemBO.saveItem(itemDTO);

            if (isSaved){
                writer.write("item saved successfully");
                logger.info("item saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("item not saved");
                logger.error("item not saved");
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
            logger.info("Inside item put method");
            ItemDTO itemDTO=jsonb.fromJson(req.getReader(),ItemDTO.class);
            System.out.println(itemDTO);
            boolean isUpdated=itemBO.updateItem(itemDTO);

            if (isUpdated){
                writer.write("item updated successfully");
                logger.info("item updated successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("item not updated");
                logger.error("item not updated");
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
            logger.info("inside item delete method");
            String id=req.getParameter("id");
            boolean isDelete=itemBO.deleteItem(id);

            if (isDelete){
                writer.write("item deleted successfully");
                logger.info("item deleted successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else {
                writer.write("item not deleted");
                logger.error("item not deleted");
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
