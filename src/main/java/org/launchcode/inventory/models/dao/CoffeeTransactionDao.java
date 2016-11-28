//package org.launchcode.inventory.models.dao;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.launchcode.inventory.models.CoffeeTransaction;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@Transactional
//@Repository
//public interface CoffeeTransactionDao extends CrudRepository <CoffeeTransaction, Integer>{
//	
//    List <CoffeeTransaction> findAllByOrderByCreatedDesc();
//    List<CoffeeTransaction> findByName(int coffeeStockId);
//
//}
