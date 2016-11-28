package org.launchcode.inventory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




@Entity
@Table(name = "coffee_stock")
public class CoffeeStock extends AbstractEntity{

	private String name;
	private String roast;
	private String body;
	private String acidity;
	private float price;
	private float poundsOwned;
	private String description;
	//private List<CoffeeTransaction> transactions;
	
	public CoffeeStock(){}
	
	public CoffeeStock(String name, String roast, String body, String acidity, float price, String description){
		this.name=name;
		this.roast=roast;
		this.body=body;
		this.acidity=acidity;
		this.price=price;
		this.poundsOwned=0;
		this.description=description;
		//transactions = new ArrayList<CoffeeTransaction>();
	}

    @NotNull
    @Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @NotNull
    @Column(name = "roast")
	public String getRoast() {
		return roast;
	}

	public void setRoast(String roast) {
		this.roast = roast;
	}

    @NotNull
    @Column(name = "body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

    @NotNull
    @Column(name = "acidity")
	public String getAcidity() {
		return acidity;
	}

	public void setAcidity(String acidity) {
		this.acidity = acidity;
	}
	
    @NotNull
    @Column(name = "price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

    @NotNull
    @Column(name = "pounds_owned")
	public float getPoundsOwned() {
		return poundsOwned;
	}

	public void setPoundsOwned(float poundsOwned) {
		this.poundsOwned = poundsOwned;
	}

    @NotNull
    @Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
//	@OneToMany(mappedBy = "coffeeStock", cascade = CascadeType.ALL)
//	public List<CoffeeTransaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(List<CoffeeTransaction> transactions) {
//		this.transactions = transactions;
//	}
	
    public void buyPounds(float numberOfPounds) {

        if (numberOfPounds < 0) {
            throw new IllegalArgumentException("Can not purchase a negative number of pounds.");
        }

        setPoundsOwned(poundsOwned + numberOfPounds);

//        CoffeeTransaction transaction = new CoffeeTransaction(this, numberOfPounds, CoffeeTransaction.TransactionType.BUY);
//        this.transactions.add(transaction);
    }

    public void sellPounds(float numberOfPounds) {

        if (numberOfPounds > poundsOwned) {
            throw new IllegalArgumentException("Number to sell exceeds pounds of coffee in stock");
        }

        setPoundsOwned(poundsOwned - numberOfPounds);

//        CoffeeTransaction transaction = new CoffeeTransaction(this, numberOfPounds, CoffeeTransaction.TransactionType.SELL);
//        this.transactions.add(transaction);
        }
}
