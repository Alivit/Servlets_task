package com.example.checkrunner.controllers;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.DiscountCard;
import com.example.checkrunner.services.DiscountCardService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cards")
public class CardView extends HttpServlet {

    Repository<DiscountCard> repository = new DiscountCardService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DBConnection.init();
        try {
            List<DiscountCard> cards = repository.getAll();

            cards.forEach(card -> {
                writer.println(new Gson().toJson(card));
                writer.flush();
            } );
        } catch (Exception e){
            writer.print(e);
            writer.flush();
        }
    }
}
