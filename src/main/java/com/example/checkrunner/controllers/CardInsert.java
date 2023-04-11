package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.DiscountCard;
import com.example.checkrunner.services.DiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insertCard")
public class CardInsert extends HelloServlet{

    Repository<DiscountCard> repository = new DiscountCardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DBConnection.init();
        try {
            String code = (String) request.getParameter("name");
            int discount = Integer.parseInt(request.getParameter("discount"));
            DiscountCard card = new DiscountCard(0, code, discount);
            if(repository.add(card) == 0){
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
