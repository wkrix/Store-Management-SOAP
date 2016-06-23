package hu.klayton.wade.sm.service;

import hu.klayton.wade.sm.dto.WareHouseDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.hu>
 */
@WebService(targetNamespace = "http://klayton.wade.hu/sm/wsdl/WareHouseService")
public interface WareHouseService {
    List<WareHouseDTO> findAll();

    void save(WareHouseDTO wareHouseDTO);

    void delete(Long id);

    WareHouseDTO findOne(Long id);
}
