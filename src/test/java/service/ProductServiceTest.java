package service;

import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.Product;
import com.example.checkrunner.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    ProductService productService = null;
    private static Stream<Arguments> productArgumentsProvider(){
        return Stream.of(
                Arguments.of(new Product(12, "Milk", 3.14, "promotion", 0))
        );
    }
    @BeforeEach
    void init() throws FileNotFoundException {
        DBConnection.init();
        productService = new ProductService();
    }
    @ParameterizedTest
    @MethodSource("productArgumentsProvider")
    public void addTest(Product product) throws SQLException {
        productService.add(product);
        assertThat(productService.preparedStatement.toString().contains("Milk")).isTrue();

    }

    @Test
    public void getAllTest() throws SQLException {
        List<Product> productList = productService.getAll();
        assertThat(productList.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("productArgumentsProvider")
    public void removeTest(Product product) throws SQLException {
        productService.remove(product);
        assertThat(productService.preparedStatement.toString().contains("12")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("productArgumentsProvider")
    public void updateTest(Product product) throws SQLException {
        productService.update(product);
        assertThat(productService.preparedStatement.toString().contains("3.14")).isTrue();
    }
}