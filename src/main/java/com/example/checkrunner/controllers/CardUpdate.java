package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.DiscountCard;
import com.example.checkrunner.services.DiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateCard")
public class CardUpdate extends HttpServlet{

    Repository<DiscountCard> repository = new DiscountCardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DBConnection.init();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String code = (String) request.getParameter("name");
            int discount = Integer.parseInt(request.getParameter("discount"));

            if(repository.find(id) == null){
                writer.print("Карта не найдена");
                writer.flush();
            }
            else {
                DiscountCard card = new DiscountCard(id, code, discount);
                if(repository.update(card) == 0) {
                    writer.print("SQL ошибка");
                    writer.flush();
                }
                else {
                    writer.print("Карта обновлена");
                    writer.flush();
                }
            }

        }catch (Exception e){
            writer.print(e);
            writer.flush();
        }
    }
}
