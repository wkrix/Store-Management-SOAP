package hu.klayton.wade.sm.service;

import hu.klayton.wade.sm.dto.StockMovementDTO;
import hu.klayton.wade.sm.dto.SummaryDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.hu>
 */
@WebService(targetNamespace = "http://klayton.wade.hu/sm/wsdl/StockMovementService")
public interface StockMovementService {

    void save(StockMovementDTO stockMovementDTO);

    List<SummaryDTO> findByWareHouseId(Long id);
}
