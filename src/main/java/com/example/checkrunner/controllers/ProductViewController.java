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
import java.util.List;

@WebServlet("/products")
public class ProductViewController extends HttpServlet {

    Repository<Product> repository = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        try {
            String page_num = request.getParameter("page");
            int page = Integer.parseInt(page_num);
            int total;

            String page_size = request.getParameter("pagesize");
            if(page_size == null){
                total = 20;
            }
            else{
                total = Integer.parseInt(page_size);
            }

            if(page > 0){
                page--;
                page = page*total;
            }

            List<Product> products = repository.getAll();

            writer.print("<h1>Page No: " + page_num + "</h1>");
            writer.print("<table border='1' cellpadding='4' width='60%'>");
            writer.print("<tr><th>Id</th><th>Name</th><th>Price</th><th>Status</th>");
            products.stream().skip(page).limit(total).forEach(card ->{
                writer.print("<tr><td>"+card.getId()+"</td><td>"+card.getName()+
                        "</td><td>"+card.getPrice()+"</td><td>"+card.getStatus()+"</td></tr>");
            });
            writer.print("</table>");

            writer.print("<a href='products?page=1&pagesize=" + total + "'>1</a> ");
            writer.print("<a href='products?page=2&pagesize=" + total + "'>2</a> ");
            writer.print("<a href='products?page=3&pagesize=" + total + "'>3</a> ");

            writer.close();
        }
        catch (Exception e){
            writer.print(e);
            writer.flush();
        }

    }
}
