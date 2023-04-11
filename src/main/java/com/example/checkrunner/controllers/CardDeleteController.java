package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.DiscountCard;
import com.example.checkrunner.services.DiscountCardService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс сервлет, который отвечает за удаление карт
 * из базы данных
 */
@WebServlet("/deleteCard")
public class CardDeleteController extends HttpServlet {

    /**
     * Это поле интерфейса описывающее поведение
     * сервис обработчика запросов sql
     * @see Repository
     */
    Repository<DiscountCard> repository = new DiscountCardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DiscountCard card = repository.find(id);

            if(card == null){
                printWriter.print("Card not found!!!");
                printWriter.flush();
            }
            else if(repository.remove(card) == 0) {
                printWriter.print("SQL error");
                printWriter.flush();
            }
            else {
                printWriter.print(new Gson().toJson(card) + " was deleted");
                printWriter.flush();
            }
        }
        catch (Exception e){
            printWriter.print(e);
            printWriter.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
