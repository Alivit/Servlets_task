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

/**
 * Класс сервлет, который отвечает за вставку карт
 * в базу данных
 */
@WebServlet("/insertCard")
public class CardInsertController extends HelloServlet{

    /**
     * Это поле интерфейса описывающее поведение
     * сервис обработчика запросов sql
     * @see Repository
     */
    Repository<DiscountCard> repository = new DiscountCardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String code = (String) request.getParameter("code");
            int discount = Integer.parseInt(request.getParameter("discount"));
            DiscountCard card = new DiscountCard(0, code, discount);
            if(repository.add(card) == 0){
                writer.print("SQL error");
                writer.flush();
            }
            else {
                writer.print("Card was added");
                writer.flush();
            }
        }
        catch (Exception e){
            writer.print(e);
        }

    }

}
