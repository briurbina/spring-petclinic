package org.springframework.samples.petclinic.bdd.testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonSyntaxException;

import org.assertj.core.util.Arrays;
import org.springframework.samples.petclinic.bdd.testdata.models.DataChunk;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;

import io.cucumber.java.PendingException;
import io.cucumber.messages.internal.com.google.gson.Gson;

public class DataManager {

	Map<String, Persona> personaLookup = new HashMap<>();

	Map<String, DataChunk> dataChunkLookup = new HashMap<>();

	Persona activePersona;

	private static DataManager activeInstance = new DataManager();

	public static DataManager getInstance() {
		return DataManager.activeInstance;
	}

	private DataManager() {
		loadPersonas();
		loadDataChunks();
	}

	public void reset() {
		this.activePersona = null;
	}

	public Persona findPersonaByNameOrAlias(String nameOrAlias) {
		if (this.activePersona != null) {
			return this.activePersona;
		}
		this.activePersona = this.personaLookup.get(nameOrAlias);
		return this.activePersona;
	}

	public DataChunk findDataByName(String name) {
		return this.dataChunkLookup.get(name);
	}

	private void loadPersonas() {
		List<Persona> personas = getDataFromJsonFiles(Persona.class, Persona[].class, "testdata/personas");
		for (Persona p : personas) {
			this.personaLookup.put(p.getName(), p);
			for (String alias : p.getAliases()) {
				this.personaLookup.put(alias, p);
			}
		}
	}

	private void loadDataChunks() {
		List<DataChunk> dataChunks = getDataFromJsonFiles(DataChunk.class, DataChunk[].class, "testdata/datachunks");
		for (DataChunk p : dataChunks) {
			this.dataChunkLookup.put(p.getName(), p);
		}
	}

	private <T> List<T> getDataFromJsonFiles(Class<T> objClass, Class<T[]> arrayClass, String folderName) {
		List<T> returnData = new ArrayList<>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(folderName);
		assert url != null;
		String path = url.getPath();
		File[] files = new File(path).listFiles();
		assert files != null;
		for (File f : files) {
			if (f.isDirectory()) {
				List<T> listOfData = getDataFromJsonFiles(objClass, arrayClass, folderName + "/" + f.getName());
				returnData.addAll(listOfData);
			}
			else {
				if (f.getName().endsWith(".json")) {
					BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(f));
					}
					catch (FileNotFoundException e) {
						throw new RuntimeException("Unable to read data from" + f.getAbsolutePath());
					}
					try {
						T p = new Gson().fromJson(br, objClass);
						if (p == null) {
							throw new RuntimeException("Unable to parse JSON from" + f.getAbsolutePath());
						}
						else {
							returnData.add(p);
						}
					}
					catch (JsonSyntaxException e) {
						T[] p = new Gson().fromJson(br, arrayClass);
						if (p == null) {
							throw new RuntimeException(
									"Unable to parse multiple JSON entries from" + f.getAbsolutePath());
						}
						else {
							returnData.addAll((Collection<? extends T>) Arrays.asList(p));
						}
					}
				}
			}
		}
		return returnData;
	}

	public Persona getModdedPersona(String personaNameOrAlias, List<String> dataChunkNames) {
		throw new PendingException("Need to implement getModdedPersona()");
	}

	public Persona getModdedPersona(String personaNameOrAlias, io.cucumber.datatable.DataTable dataModNames) {
		List<String> column = dataModNames.column(0);
		List<String> names = column.subList(0, column.size() - 1);
		return this.getModdedPersona(personaNameOrAlias, names);

	}

}
