package org.launchcode.inventory.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity{
	
	private String name;
	
	public Role (){};
	
	public Role (final String name){
		super();
		this.name=name;
	}

}
