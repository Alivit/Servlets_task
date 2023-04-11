package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.entity.Product;
import com.example.checkrunner.services.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductView extends HttpServlet {

    Repository<Product> repository = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Product> products = repository.getAll();

            products.forEach(product -> {
                writer.println(new Gson().toJson(product));
                writer.flush();
            } );
        }
        catch (Exception e){
            writer.print(e);
            writer.flush();
        }

    }
}
