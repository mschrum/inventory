package org.launchcode.inventory.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.inventory.models.CoffeeStock;
import org.launchcode.inventory.models.dao.CoffeeStockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoffeeStockController extends AbstractController{
	@Autowired
	CoffeeStockDao coffeeStockDao;
	
    @RequestMapping(value = "/addToOrderGuide", method = RequestMethod.GET)
    public String createProductForm() {
        return "addToOrderGuide";
    }

    @RequestMapping(value = "/addToOrderGuide", method = RequestMethod.POST)
    public String create(HttpServletRequest request, Model model) {
    		String name=request.getParameter("name");
    		String roast=request.getParameter("roast");
    		String body=request.getParameter("body");
    		String acidity=request.getParameter("acidity");
    		String strPrice=request.getParameter("price");
    		float price=Float.parseFloat(strPrice);
    		String description=request.getParameter("description");
            // Perform some validation
            CoffeeStock existingCoffeeStock = coffeeStockDao.findByName(name);
            if (existingCoffeeStock != null) {
                return this.displayError(
                        "This coffee " + name + " already exits in the order guide.", model);
            }
           
            // Validation passed. Create and persist a new CoffeeStock entity
            CoffeeStock newCoffeeStock = new CoffeeStock(name, roast, body, acidity, price, description);
            coffeeStockDao.save(newCoffeeStock);    	
    	return "addToOrderGuide";
    }
    
    @RequestMapping(value = "/buyInventory", method = RequestMethod.GET)
    public String buyForm(Model model) {
    	List<CoffeeStock> coffeeStock = coffeeStockDao.findAll();
    	model.addAttribute("coffeeStock", coffeeStock);
        return "buyInventory";
    }
    
    @RequestMapping(value = "/buyInventory", method = RequestMethod.POST)
    public String buy(HttpServletRequest request, Model model) {
    		String name=request.getParameter("coffeeOrder");
    		String strNumberOfPounds=request.getParameter("numberOfPounds");
    		float numberOfPounds=Float.parseFloat(strNumberOfPounds);
            // Perform some validation
            CoffeeStock existingCoffeeStock = coffeeStockDao.findByName(name);
            if (existingCoffeeStock == null) {
                return this.displayError(
                        "The coffee " + name + " does not exist in the order guide.", model);
            }
           
            // Validation passed. Buy Coffee
            existingCoffeeStock.buyPounds(numberOfPounds); 	
            coffeeStockDao.save(existingCoffeeStock);
    	return "redirect:buyInventory";
    }
    
    @RequestMapping(value = "/customerOrder", method = RequestMethod.GET)
    public String sellForm(Model model) {
    	List<CoffeeStock> coffeeStock = coffeeStockDao.findAll();
    	model.addAttribute("coffeeStock", coffeeStock);
        return "customerOrder";
    }
    
    @RequestMapping(value = "/customerOrder", method = RequestMethod.POST)
    public String sell(HttpServletRequest request, Model model) {
    		String name=request.getParameter("coffeeOrder");
    		String strNumberOfPounds=request.getParameter("numberOfPounds");
    		float numberOfPounds=Float.parseFloat(strNumberOfPounds);
            // Perform some validation
            CoffeeStock existingCoffeeStock = coffeeStockDao.findByName(name);
            if (existingCoffeeStock == null) {
                return this.displayError(
                        "The coffee " + name + " does not exist in the order guide.", model);
            }
           
            // Validation passed. Buy Coffee
            existingCoffeeStock.sellPounds(numberOfPounds); 	
            coffeeStockDao.save(existingCoffeeStock);
    	return "redirect:customerOrder";
    }
}
