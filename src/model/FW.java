package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.io.FileWriter;


/**
 * Write to a HighScore file
 * @author Goran
 *
 */
public class FW {
	
	private File file;

	/**
	 * Creating a file, so the user can save the highscore.
	 * @param newScore
	 */
	public void writeFile (int newScore) {

		file = new File("highScoreSnake.txt");

		if(file.exists()) 
			System.out.println("file already exist");

		else {

			try {
				file.createNewFile();
			} 

			catch (IOException e) {

				e.printStackTrace();
			}
		}

		try {
			FileWriter fileW = new FileWriter(file);
			BufferedWriter buffW = new BufferedWriter(fileW); 


			String name = JOptionPane.showInputDialog("You set a new HIGHSCORE. What is your name?");
			buffW.write(String.format("%s %s", name,newScore));

			System.out.println("File Writter");

			buffW.close();
			fileW.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}