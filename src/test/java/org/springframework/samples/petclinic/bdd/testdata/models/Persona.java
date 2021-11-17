package org.springframework.samples.petclinic.bdd.testdata.models;

import java.util.ArrayList;
import java.util.List;

public class Persona {

	public String name;

	public List<String> aliases = new ArrayList<>();

	public String accountKey;

	public String firstName;

	public String lastName;

	public String address;

	public String city;

	public String telephone;

	public String getName() {
		return this.name;
	}

	public List<String> getAliases() {
		return this.aliases;
	}

}
