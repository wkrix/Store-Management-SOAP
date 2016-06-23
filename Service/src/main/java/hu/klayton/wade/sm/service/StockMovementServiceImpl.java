package hu.klayton.wade.sm.service;

import hu.klayton.wade.sm.dao.ProductRepository;
import hu.klayton.wade.sm.dao.StockMovementRepository;
import hu.klayton.wade.sm.dao.WareHouseRepository;
import hu.klayton.wade.sm.domain.Product;
import hu.klayton.wade.sm.domain.StockMovement;
import hu.klayton.wade.sm.domain.WareHouse;
import hu.klayton.wade.sm.dto.StockMovementDTO;
import hu.klayton.wade.sm.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.hu>
 */
@Service
@Transactional
@WebService(
        portName = "StockMovementPort",
        serviceName = "StockMovementService",
        targetNamespace = "http://klayton.wade.hu/sm/wsdl/StockMovementService",
        endpointInterface = "hu.klayton.wade.sm.service.StockMovementService")
public class StockMovementServiceImpl implements StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Override
    public void save(StockMovementDTO stockMovementDTO) {
        final Product product = productRepository.findOne(stockMovementDTO.getProductId());
        final WareHouse wareHouse = wareHouseRepository.findOne(stockMovementDTO.getWareHouseId());
        if (product.getStockMovement() != null) {
            stockMovementRepository.delete(product.getStockMovement());
        }
        StockMovement stockMovement = stockMovementDTO.createStockMovement();
        stockMovement.setCreationDate(new Date());

        stockMovement.addProduct(product);
        stockMovement.addWareHouse(wareHouse);
        stockMovementRepository.save(stockMovement);
    }

    @Override
    public List<SummaryDTO> findByWareHouseId(Long id) {
        return stockMovementRepository.findSummaryDTOByWareHouseId(id);
    }
}
