package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {

	private String homePath;

	public FileManager(String home) {
		homePath = home;
	}

	public void createHomeDirectory() {
		createSubDirectory("");
	}

	public void writeFile(String name, String txt) throws Exception {
		BufferedWriter writer = null;
		File file = new File(homePath + "/" + name);
		writer = new BufferedWriter(new FileWriter(file, false));
		writer.write(txt);
		writer.close();
	}

	public void createSubDirectory(String name) {
		File dir = new File(homePath + name);
		dir.mkdir();
	}

	public ArrayList<String> readFile(String name) throws Exception {

		FileReader fr = new FileReader(homePath + "/" + name);
		BufferedReader reader = new BufferedReader(fr);
		ArrayList<String> lines = new ArrayList<String>();

		while (reader.ready()) {
			lines.add(reader.readLine());
		}
		reader.close();
		return lines;
	}

}
