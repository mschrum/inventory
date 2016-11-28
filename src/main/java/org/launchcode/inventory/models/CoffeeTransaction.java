//package org.launchcode.inventory.models;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//
//
//@Entity
//@Table(name = "transactions")
//public class CoffeeTransaction extends AbstractEntity{
//
//	public enum TransactionType {
//        BUY("buy"), SELL("sell");
//        @SuppressWarnings("unused")
//		private String type;
//
//        TransactionType(String type){
//            this.type = type;
//        }
//    }
//	
//    private TransactionType type;
//    private float pounds;
//    private float price;
//    private Date transactionTime;
//    private String name;
//    private CoffeeStock coffeeStock;
//    
//    public CoffeeTransaction(CoffeeStock coffeeStock, float pounds, TransactionType type){
//        this.pounds = pounds;
//        this.coffeeStock = coffeeStock;
//        this.transactionTime = new Date();
//        this.name = coffeeStock.getName();
//        this.price = coffeeStock.getPrice();
//        this.type = type;
//    }
//	
//	@ManyToOne
//	public CoffeeStock getCoffeeStock() {
//		return coffeeStock;
//	}
//
//	public void setCoffeeStock(CoffeeStock coffeeStock) {
//		this.coffeeStock = coffeeStock;
//	}
//
//	@NotNull
//    @Column(name = "pounds")
//	public float getPounds() {
//		return pounds;
//	}
//
//	public void setPounds(float pounds) {
//		this.pounds = pounds;
//	}
//
//	@NotNull
//    @Column(name = "price")
//	public float getPrice() {
//		return price;
//	}
//
//	public void setPrice(float price) {
//		this.price = price;
//	}
//	
//	@NotNull
//    @Column(name = "transaction_time")
//	public Date getTransactionTime() {
//		return transactionTime;
//	}
//
//	public void setTransactionTime(Date transactionTime) {
//		this.transactionTime = transactionTime;
//	}
//
//	@NotNull
//    @Column(name = "name")
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@NotNull
//    @Column(name = "type")
//	public TransactionType getType() {
//		return type;
//	}
//
//	public void setType(TransactionType type) {
//		this.type = type;
//	}
//    
//}
