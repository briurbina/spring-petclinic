package org.springframework.samples.petclinic.bdd.testdata.models;

public class Pet {

	public String name;

	public String birthDate;

	public PetType petType;

	public enum PetType {

		BIRD("bird"), CAT("cat"), DOG("dog"), HAMSTER("hamster"), LIZARD("lizard"), SNAKE("snake");

		private final String type;

		PetType(final String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}

	}

}
