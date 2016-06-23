package hu.klayton.wade.sm;

import hu.klayton.wade.sm.dao.ProductRepository;
import hu.klayton.wade.sm.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StoreManagementApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository.deleteAll();
        Product product= new Product("Peach", "Fresh", new Date(), new Date(System.currentTimeMillis() + 1616927899));
        productRepository.save(product);
    }


    @Test
    public void loadProduct(){
        List<Product> products = productRepository.findAll();
        assertEquals("Did not get all products", 1, products.size());
    }


}
