package org.launchcode.inventory.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.inventory.models.CoffeeStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CoffeeStockDao extends CrudRepository<CoffeeStock, Integer>{

	CoffeeStock findByName(String name);
	CoffeeStock findByUid(int uid);
	List<CoffeeStock> findAll();
	
}
