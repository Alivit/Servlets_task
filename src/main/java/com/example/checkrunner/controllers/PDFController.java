package com.example.checkrunner.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getPDF")
public class PDFController extends HttpServlet {

//    String path = "D:\Clevertec\\CheckRunner\\src\\main\\resources\\Clevertec_Template.pdf";
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<object><embed src=\"D:\Clevertec\\CheckRunner\\src\\main\\resources\\Clevertec_Template.pdf\" width=\"700\" height=\"500\" /></object>");
//        out.println("</body></html>");
//    }
}
