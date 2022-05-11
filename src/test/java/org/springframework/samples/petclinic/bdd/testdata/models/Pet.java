package org.springframework.samples.petclinic.bdd.testdata.models;

import com.google.gson.annotations.SerializedName;

public class Pet {

	public String name;

	public String birthDate;

	public PetType petType;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((petType == null) ? 0 : petType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		}
		else if (!birthDate.equals(other.birthDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (petType != other.petType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pet [birthDate=" + birthDate + ", name=" + name + ", petType=" + petType + "]";
	}

	// restructure enum? test it out
	// need tostrong to return a nicely formatted name
	public enum PetType {

		@SerializedName("bird")
		BIRD("bird"), @SerializedName("cat")
		CAT("cat"), @SerializedName("dog")
		DOG("dog"), @SerializedName("hamster")
		HAMSTER("hamster"), @SerializedName("lizard")
		LIZARD("lizard"), @SerializedName("snake")
		SNAKE("snake");

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
