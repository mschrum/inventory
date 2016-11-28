package org.launchcode.inventory.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
public class Privilege extends AbstractEntity {

	private String name;

	public Privilege() {}

	public Privilege(final String name) {
		super();
		this.name = name;
	}

}
