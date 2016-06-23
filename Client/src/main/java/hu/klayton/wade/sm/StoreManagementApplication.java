package hu.klayton.wade.sm;

import hu.wade.klayton.sm.wsdl.productservice.ProductService;
import hu.wade.klayton.sm.wsdl.stockmovementservice.StockMovementService;
import hu.wade.klayton.sm.wsdl.warehouseservice.WareHouseService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreManagementApplication {

    @Value("${ws.server.host}")
    private String URI;

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    public ProductService productClient() {
        return createClient(ProductService.class, "products");
    }

    @Bean
    public WareHouseService wareHouseClient() {
        return createClient(WareHouseService.class, "warehouses");
    }

    @Bean
    public StockMovementService stockMovementClient() {
        return createClient(StockMovementService.class, "stockmovements");
    }

    private <T> T createClient(Class<T> clazz, String path) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(URI + path);
        factory.setServiceClass(clazz);
        return clazz.cast(factory.create());
    }
}
