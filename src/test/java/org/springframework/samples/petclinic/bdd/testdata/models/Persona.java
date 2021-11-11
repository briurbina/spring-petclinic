package org.springframework.samples.petclinic.bdd.testdata.models;

import java.util.ArrayList;
import java.util.List;

public class Persona {

	public String name;

	public List<String> aliases = new ArrayList<>();

	public String email;

	public String accountKey;

	public String getName() {
		return this.name;
	}

	public List<String> getAliases() {
		return this.aliases;
	}

}
