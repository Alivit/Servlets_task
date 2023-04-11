package com.example.checkrunner.file.factory;

import com.example.checkrunner.file.impls.ReceiptPDF;
import com.example.checkrunner.file.impls.ReceiptTXT;
import com.example.checkrunner.file.interf.Inputable;
import com.example.checkrunner.file.missed.MissedRepository;

/**
 * Класс выступающий в качестве фабрики
 */
public class FileRepository {

    /**
     * Метод возвращающий ссылку объекта на нужный класс
     */
    public static Inputable getRepository(String type) {
        return switch (type) {
            case "PDF" -> new ReceiptPDF();
            case "TXT" -> new ReceiptTXT();
            default -> new MissedRepository();
        };
    }
}
