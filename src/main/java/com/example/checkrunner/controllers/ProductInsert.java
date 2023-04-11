package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.Product;
import com.example.checkrunner.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insertProduct")
public class ProductInsert extends HttpServlet {

    Repository<Product> repository = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DBConnection.init();
        try {
            String name = (String) request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String status = (String) request.getParameter("status");

            Product product = new Product(0, name, price, status, 1);
            if(repository.add(product) == 0){
                writer.print("SQL ошибка");
                writer.flush();
            }
            else {
                writer.print("Карта добавлена");
                writer.flush();
            }
        }
        catch (Exception e){
            writer.print(e);
        }

    }
}
