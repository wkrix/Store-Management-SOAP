package hu.klayton.wade.sm.controller;

import hu.wade.klayton.sm.wsdl.warehouseservice.WareHouseDTO;
import hu.wade.klayton.sm.wsdl.warehouseservice.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Walter Krix <wkrix89@gmail.com>
 */
@Controller
@RequestMapping(value = "/warehouse")
public class WareHouseController {

    @Autowired
    private WareHouseService wareHouseClient;

    @RequestMapping(value = "/list_warehouses", method = RequestMethod.GET)
    public String listWareHouses(final Model model) {
        final List<WareHouseDTO> wareHouseDTOs = wareHouseClient.findAll();
        model.addAttribute(wareHouseDTOs);
        return "warehouse/list_warehouses";
    }

    @RequestMapping(value = "/create_warehouse", method = RequestMethod.GET)
    public String createWareHouse(final Model model) {
        model.addAttribute(new WareHouseDTO());
        return "warehouse/warehouse_creator";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createWareHouse(@ModelAttribute final WareHouseDTO wareHouseDTO, final Errors errors) {
        if (errors.hasErrors()) {
            return "warehouse/warehouse_creator";
        }

        wareHouseClient.save(wareHouseDTO);
        return "redirect:/warehouse/list_warehouses";
    }

    @RequestMapping(value = "/delete/{warehouseId}", method = RequestMethod.GET)
    public String deleteWareHouse(@PathVariable("warehouseId") final Long id) {
        wareHouseClient.delete(id);
        return "redirect:/warehouse/list_warehouses";
    }

    @RequestMapping(value = "/modify/{warehouseId}", method = RequestMethod.GET)
    public String modifyWareHouse(@PathVariable("warehouseId") final Long id, final Model model) {
        final WareHouseDTO wareHouseDTO = wareHouseClient.findOne(id);
        model.addAttribute(wareHouseDTO);
        return "warehouse/warehouse_creator";
    }

}
