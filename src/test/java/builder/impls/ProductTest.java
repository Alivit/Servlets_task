package builder.impls;

import builder.interf.TestBuilder;
import com.example.checkrunner.entity.Product;
import lombok.AllArgsConstructor;
import lombok.With;

@AllArgsConstructor
@With
public class ProductTest implements TestBuilder<Product> {
    private int id = 1;
    private String name = "Паста";
    private int amount = 3;
    private double price = 2.12;
    private String status = "акция";

    private ProductTest() {
    }

    public static ProductTest aProduct() {
        return new ProductTest();
    }

    @Override
    public Product build() {
        final var server = new Product(id, name, price, status, 0);
        server.setAmount(amount);
        ;
        return server;
    }
}
