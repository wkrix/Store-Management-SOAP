package hu.klayton.wade.sm.service;

import hu.klayton.wade.sm.dto.ProductDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.hu>
 */
@WebService(targetNamespace = "http://klayton.wade.hu/sm/wsdl/ProductService")
public interface ProductService {

    List<ProductDTO> findAll();

    void save(ProductDTO productDTO);

    void delete(Long id);

    ProductDTO findOne(Long id);
}
