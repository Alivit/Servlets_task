package file;

import builder.impls.PromotionalTest;
import com.example.checkrunner.entity.Promotional;
import com.example.checkrunner.file.factory.FileRepository;
import com.example.checkrunner.file.interf.Inputable;
import com.example.checkrunner.util.RequestUtil;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReceiptPDFTest {

    private static File FILE_RECEIPT;

    @BeforeEach
    void init(){
        FILE_RECEIPT = new File("src/main/resources/Receipt.pdf");
    }

    @Test
    void testInputInFile(){
        RequestUtil request = new RequestUtil();
        Inputable input = FileRepository.getRepository("PDF");
        input.inputInFile(request);
        try {
            List<String> list = new ArrayList<>();
            PdfReader reader = new PdfReader(String.valueOf(FILE_RECEIPT));
            for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
                TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
                String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
                list.add(text);
            }
            reader.close();
            assertThat(list.get(0)).contains("IP Ivanov Ivan Ivanovich");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInputInFileWithPromotional(){
        RequestUtil request = new RequestUtil();
        ArrayList<Promotional> promotionals = new ArrayList<>();
        promotionals.add(PromotionalTest.aPromotional().build());
        request.setPromotional(promotionals);

        Inputable input = FileRepository.getRepository("PDF");
        input.inputInFile(request);
        try {
            List<String> list = new ArrayList<>();
            PdfReader reader = new PdfReader(String.valueOf(FILE_RECEIPT));
            for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
                TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
                String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
                list.add(text);
            }
            reader.close();
            assertThat(list.get(0)).contains("14,44");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
