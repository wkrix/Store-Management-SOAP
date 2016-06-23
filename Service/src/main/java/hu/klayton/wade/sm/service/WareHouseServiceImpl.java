package hu.klayton.wade.sm.service;

import hu.klayton.wade.sm.dao.StockMovementRepository;
import hu.klayton.wade.sm.dao.WareHouseRepository;
import hu.klayton.wade.sm.domain.StockMovement;
import hu.klayton.wade.sm.domain.WareHouse;
import hu.klayton.wade.sm.dto.WareHouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.hu>
 */
@Service
@Transactional
@WebService(
        portName = "WareHousePort",
        serviceName = "WareHouseService",
        targetNamespace = "http://klayton.wade.hu/sm/wsdl/WareHouseService",
        endpointInterface = "hu.klayton.wade.sm.service.WareHouseService")
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

//    @PostConstruct
//    private void initDummyData() {
//        WareHouse wareHouse = new WareHouse("Budapesti raktár", "Nem túl nagy raktár", new Date(), new Date(System.currentTimeMillis() + 1610927890));
//        WareHouse wareHouse2 = new WareHouse("Pécsi raktár", "Normál méretű raktár", new Date(), new Date(System.currentTimeMillis() + 1616927896));
//        wareHouseRepository.save(wareHouse);
//        wareHouseRepository.save(wareHouse2);
//    }

    @Override
    public List<WareHouseDTO> findAll() {
        final List<WareHouse> wareHouses = wareHouseRepository.findAll();
        final List<WareHouseDTO> wareHouseDTOs = new ArrayList<>();
        for (WareHouse wareHouse : wareHouses) {
            wareHouseDTOs.add(WareHouseDTO.createDTO(wareHouse));
        }
        return wareHouseDTOs;
    }

    @Override
    public void save(final WareHouseDTO wareHouseDTO) {
        final List<StockMovement> stockMovements = stockMovementRepository.findByWareHouseId(wareHouseDTO.getId());
        WareHouse wareHouse = wareHouseDTO.createWareHouse();
        wareHouse.setStockMovements(stockMovements);
        if (wareHouse.getCreationDate() == null) {
            wareHouse.setCreationDate(new Date());
        }
        wareHouseRepository.save(wareHouse);
    }

    @Override
    public void delete(final Long id) {
        wareHouseRepository.delete(id);
    }

    @Override
    public WareHouseDTO findOne(Long id) {
        WareHouse wareHouse = wareHouseRepository.findOne(id);
        return WareHouseDTO.createDTO(wareHouse);
    }
}
