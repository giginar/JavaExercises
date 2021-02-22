import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {

		List<String> newList = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader("text_to_count.txt"));) {

			String line = "";
			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("[^A-Z a-z]", "");
				line = line.trim();
				line = line.toLowerCase();
				String[] temporaryList = line.split(" ");
				for (String string : temporaryList) {
					newList.add(string);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("results.txt"), false));) {
			for (String string : newList) {
				writer.write(string + "\n");
				writer.flush();
			}

		} catch (Exception e) {
			System.out.println("Error Write");
		}

		Map<String, Integer> sortedMapping = new TreeMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"));) {

			String line = "";

			while ((line = reader.readLine()) != null) {

				if (!line.contentEquals("")) {
					if (sortedMapping.get(line) != null) {
						sortedMapping.put(line, sortedMapping.get(line) + 1);
					} else {
						sortedMapping.put(line, 1);
					}
				}
			}

			System.out.println("Here are the results:");
			for (String key : sortedMapping.keySet()) {
				System.out.println(key + ":" + sortedMapping.get(key));
			}
			System.out.println("I strongly recommend to check the reults from the file.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("results.txt"), false));) {
			for (String key : sortedMapping.keySet()) {
				writer.write(key + ":" + sortedMapping.get(key) + "\n");
				writer.flush();
			}

		} catch (Exception e) {
			System.out.println("Error Write");
		}
	}
}
