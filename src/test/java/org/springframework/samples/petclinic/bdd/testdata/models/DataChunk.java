package org.springframework.samples.petclinic.bdd.testdata.models;

import java.util.Map;

public class DataChunk {

	public String name;

	public Map<String, String> dataMap;

	public int getIntDataValue(String dataElementName) {
		return Integer.parseInt(this.dataMap.get(dataElementName));
	}

	public String getStringDataValue(String dataElementName) {
		return this.dataMap.get(dataElementName);
	}

	public String getName() {
		return this.name;
	}

}
