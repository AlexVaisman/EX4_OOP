package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;

/**
 * This class does multiple actions on files. such as: read csv file and write
 * kml file.
 * 
 * @author Shay Naor and Alex Vaisman.
 *
 */
public class MyFileUtils {

	/**
	 * This function recived a start line and path to a csv file and create a
	 * ArrayList String[] that contains the csv file data.
	 * 
	 * @param path      location of csv file.
	 * @param startLine from which line to read.
	 * @return ArrayList String[] contains the csv file data.
	 * @throws IOException if the path is incorrect the function throws IOException.
	 */
	public static ArrayList<String[]> readCSVFile(String path, int startLine) throws IOException {
		int counter = 0;
		String line = "";
		String cvsSplitBy = ",";
		String[] userInfo = {};
		ArrayList<String[]> container = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(path));

		while ((line = br.readLine()) != null) {
			userInfo = line.split(cvsSplitBy);
			if (counter >= startLine)
				container.add(userInfo);
			counter++;

		}
		br.close();
		return container;
	}

	/**
	 * This function recives folder path and return a ArrayList String that contains
	 * all the paths for csv files in folder. The algorithem taken from :
	 * https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java.
	 * 
	 * @param folder that contains the csv files.
	 * @return ArrayList String that contains all the paths for csv files in folder.
	 */
	public static ArrayList<String> listFilesForFolder(final File folder) throws NullPointerException {
		ArrayList<String> listFiles = new ArrayList<>();
		String path = "";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if (fileEntry.isFile()) {
					String temp = fileEntry.getName();
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("csv")) {
						path = folder.getAbsolutePath() + "\\" + fileEntry.getName();
						listFiles.add(path);
					}
				}
			}
		}
		return listFiles;
	}


}