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

/**
 * Класс сервлет, который отвечает за обновление карты
 * в базе данных
 */
@WebServlet("/updateCard")
public class CardUpdateController extends HttpServlet{

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
            int id = Integer.parseInt(request.getParameter("id"));
            String code = (String) request.getParameter("name");
            int discount = Integer.parseInt(request.getParameter("discount"));

            if(repository.find(id) == null){
                writer.print("Card not found");
                writer.flush();
            }
            else {
                DiscountCard card = new DiscountCard(id, code, discount);
                if(repository.update(card) == 0) {
                    writer.print("SQL error");
                    writer.flush();
                }
                else {
                    writer.print("Card was updated");
                    writer.flush();
                }
            }

        }catch (Exception e){
            writer.print(e);
            writer.flush();
        }
    }
}
