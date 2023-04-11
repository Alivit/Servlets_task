package com.example.checkrunner.controllers;

import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.file.factory.FileRepository;
import com.example.checkrunner.file.interf.Inputable;
import com.example.checkrunner.logic.OutputLogic;
import com.example.checkrunner.util.RequestUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс сервлет, который отвечает за вывод
 * сгенерированного пдф на странице
 */
@WebServlet("/getPDF")
public class PDFController extends HttpServlet {

    /**
     * Это поле поле класса обработчика запросов c ссылкой на него
     * @see RequestUtil
     */
    static RequestUtil req = new RequestUtil();

    /**
     * Это поле хранящяя ссылку на пдф
     */
    private String pathPDF = "resources/Receipt.pdf";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        try {
            String args[] = request.getParameterValues("args");
            RequestUtil.parseRequest(args);
            DBConnection.init();
            req.workWithBD();
            req.comparison();
            OutputLogic.viewReceipt(req);
            Inputable input = FileRepository.getRepository("PDF");
            input.inputInFile(req);

            writer.println("<html><body>");
            writer.println("<iframe src=\"" + pathPDF +"\" width=\"100%\" height=\"100%\" ></iframe>");
            writer.println("</body></html>");
        }
        catch (Exception e){
            writer.print(e);
            writer.flush();
        }

    }
}
