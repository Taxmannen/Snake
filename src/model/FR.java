package model;

import java.io.BufferedReader; 
import java.io.FileReader;

/**
 * Reading a File
 * @author Goran
 *
 */
public class FR {
	
	/**
	 * Reading a highScore file 
	 * @return a text form, from the file.
	 * @throws Exception
	 */
	public String readingFile() throws Exception {

		FileReader file = new FileReader("highScoreSnake.txt");
		BufferedReader reader = new BufferedReader(file);

		String text = " ";
		String line = reader.readLine();

		while (line != null) {

			text += line;
			line = reader.readLine();

			reader.close();
			file.close();
			
		}
		
		return text;
	}
	
}