package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.entity.Product;
import com.example.checkrunner.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс сервлет, который отвечает за обновление продукта
 * в базе данных
 */
@WebServlet("/updateProduct")
public class ProductUpdateController extends HttpServlet {

    /**
     * Это поле интерфейса описывающее поведение
     * сервис обработчика запросов sql
     * @see Repository
     */
    Repository<Product> repository = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = (String) request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String status = (String) request.getParameter("status");

            if(repository.find(id) == null){
                writer.print("Карта не найдена");
                writer.flush();
            }
            else {
                Product product = new Product(0, name, price, status, 1);
                if(repository.update(product) == 0) {
                    writer.print("SQL error");
                    writer.flush();
                }
                else {
                    writer.print("Product was updated");
                    writer.flush();
                }
            }

        }catch (Exception e){
            writer.print(e);
            writer.flush();
        }
    }
}
