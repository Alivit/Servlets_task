package logic;

import builder.impls.PromotionalTest;
import com.example.checkrunner.entity.Promotional;
import com.example.checkrunner.logic.OutputLogic;
import com.example.checkrunner.util.RequestUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputLogicTest {

    @Test
    void testGetCheck(){
        RequestUtil request = new RequestUtil();
        List<Promotional> promotionals = new ArrayList<>();

        promotionals.add(PromotionalTest.aPromotional().build());
        request.setPromotional(promotionals);

        List<String> list = OutputLogic.getReceipt(request);
        assertThat(list.get(13).contains("Паста")).isTrue();
    }

}