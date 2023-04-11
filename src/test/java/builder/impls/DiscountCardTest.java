package builder.impls;

import builder.interf.TestBuilder;
import com.example.checkrunner.entity.DiscountCard;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aDiscountCard")
@With
public class DiscountCardTest implements TestBuilder<DiscountCard> {
    private int id = 0;
    private String code = "1234";
    private int discount = 10;

    @Override
    public DiscountCard build() {
        final var server = new DiscountCard();
        server.setId(id);
        server.setCode(code);
        server.setDiscount(discount);
        return server;
    }
}

