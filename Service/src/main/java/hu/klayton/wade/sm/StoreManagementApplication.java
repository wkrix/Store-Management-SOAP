package hu.klayton.wade.sm;

import hu.klayton.wade.sm.service.ProductService;
import hu.klayton.wade.sm.service.StockMovementService;
import hu.klayton.wade.sm.service.WareHouseService;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class StoreManagementApplication {

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean
    public EndpointImpl productEndpoint(ProductService productService) {
        return createEndpoint(productService, "/products");
    }

    @Bean
    public EndpointImpl stockMovementEndpoint(StockMovementService stockMovementService) {
        return createEndpoint(stockMovementService, "/stockmovements");
    }

    @Bean
    public EndpointImpl wareHouseEndpoint(WareHouseService wareHouseService) {
        return createEndpoint(wareHouseService, "/warehouses");
    }

    private EndpointImpl createEndpoint(Object service, String path) {
        EndpointImpl endpoint = new EndpointImpl(bus, service);
        endpoint.publish(path);
        endpoint.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        return endpoint;
    }
}
