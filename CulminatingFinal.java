import arc.*;  
import java.awt.Color;
import java.awt.image.BufferedImage;

/* 
-------------------------------------------------------------------------------
Name:	    Multiple Choice Game (ICS3U1)
Author:		Merit. Setaya 
Version Number: 1234
Created:	1/21/2024
-------------------------------------------------------------------------------
*/

public class CulminatingFinal{
	public static void main(String[]args){
		
		// Intialize the console window with a specific size and title
        Console con=new Console("Multiple-Choice Game", 1280, 720);
        
        // Show the starting screen(StartGame.jpg)
        BufferedImage imgBackground = con.loadImage("StartGame.jpg");
		con.drawImage(imgBackground, 0, 0);
		con.repaint();
		System.out.println(" Debug: Displayed 'StartGame.jpg' ");
		
		// Wait for the user to click to continue
        MouseClick(con);  
	
		// Show the main menu screen(MainMenu.jpg)
		BufferedImage imgMainMenu=con.loadImage("MainMenu.jpg");
		con.drawImage(imgMainMenu,0,0);
		System.out.println(" Debug: Displayed 'MainMenu.jpg' ");
		
		// Asks user for their name
		con.println(" Enter your name: ");
		String strUserName=con.readLine();
		System.out.println(" Debug: User entered name: " + strUserName);
		
		// Main Menu Options
        char chrMenuChoice = ' ';
        while(chrMenuChoice!='q'){
            con.println(" Enter Menu Choice: ");
			chrMenuChoice=con.readChar();
			System.out.println( " Debug: User selected menu option: " + chrMenuChoice);
			
            if (chrMenuChoice == 'p' || chrMenuChoice == 'P') {
                // Clear the screen and show available quizzes
                con.setBackgroundColor(Color.BLACK);
                ClearScreen(con);
                showAvailableQuizzes(con);
			
			// Ask the user for their quiz choice
                con.println(" Enter your choice of the quiz: ");
                String strQuizChoice = con.readLine();
				System.out.println(" Debug: User selected quiz: " + strQuizChoice);
		
                if (strQuizChoice.equalsIgnoreCase("kia")
                        || strQuizChoice.equalsIgnoreCase("toyoto")
                        || strQuizChoice.equalsIgnoreCase("rolls-royce")) {
                    // Switch to plain console for quiz gameplay
                    con.setBackgroundColor(Color.BLACK);
                    playQuizzes(con, strUserName, strQuizChoice);
                } else {
                    con.println(" Invalid Choice ");
                }
            } else if (chrMenuChoice == 'v' || chrMenuChoice == 'V') {
                // Show high scores
                con.setBackgroundColor(Color.BLACK);
                recordedHighScores(con);
            } else if (chrMenuChoice == 'h' || chrMenuChoice == 'H') {
                // Show help option
                con.setBackgroundColor(Color.BLACK);
                HelpOption(con);
            } else if (chrMenuChoice == 's' || chrMenuChoice == 'S') {
                // Show secret menu
                con.setBackgroundColor(Color.BLACK);
                SecretMenu(con);
            } else if (chrMenuChoice == 'q' || chrMenuChoice == 'Q') { // Exit the game
                con.setBackgroundColor(Color.BLACK);
                con.println(" Thank you for playing Multiple Choice Game! ");
                con.println();
            } else {  // Invalid menu choice
                con.println(" Invalid Choice. Please select one of the available options");
                con.println();
            }
        }
    }
     
	public static void MouseClick(Console con) { //wait for mouse click to continue
		System.out.println(" Debug: Waiting for mouse click");
		while (con.currentMouseButton() != 1) { //wait for the mouse button to be clicked
        }
		System.out.println(" Debug: Mouse clicked, redirecting to Main Menu");
    }
    
    //Clear the screen
	public static void ClearScreen(Console con) {
        con.clear();
    }

//Method to show available quizzes
	public static void showAvailableQuizzes(Console con){		  
        String strLine; //String variable to hold each line from the file
        TextInputFile quizNames=new TextInputFile("tests.txt"); //open file that contains the list of quizzes
        con.println(" Avaliable Quizzes: "); //header for avaliable quizzes
	
		while(quizNames.eof()==false){ //loop ro read and display each line from the file
			strLine = quizNames.readLine();
			con.println(strLine);
		}

    // Close the file after reading
    quizNames.close();
	}

	public static void HelpOption(Console con) {
    // Clear the screen before displaying help content
		ClearScreen(con);
    
    // Provides instructions for the user
		con.println(" You are given three quiz choices: Kia, Toyoto, Rolls-Royce ");
		con.println(" Each question has 4 answers. Enter the corresponding letter (a, b, c, d) for your answer. ");
		con.println(" User name, quiz, and score will be shown at the front of each question. ");
		con.println(" Final score and result of the quiz will be shown at the end. ");
		con.println();
    
		con.println(" Enter X to go back to Main Menu "); // Asks user to return to the main menu
		String strOption=con.readLine();
    
    // If the user enters 'X', return to the main menu
		if (strOption.equalsIgnoreCase("X")) {
			BufferedImage imgMainMenu = con.loadImage("MainMenu.jpg"); // Load the main menu image
			con.clear(); // Clear the screen
			con.drawImage(imgMainMenu, 0, 0); // Show  the main menu image
		}
	} 

// Method to display the secret menu
	public static void SecretMenu(Console con){
	// Clear the screen before shwoing the secret menu contest
		ClearScreen(con);
    
    // Show jokes for the secret menu
		con.println(" What do you call a fly without wings? A walk ");
		con.println(" Why do you call a cold puppy? A chili dog ");
		con.println(" Two fish are in a tank. One turns to the other and says, “Any idea how to drive this thing? ");
		con.println(); //Blank line for spacing 
	
		con.println(" Enter X to go back to Main Menu "); // Asks user to return to the main menu
		String strOption=con.readLine();
    
    // If the user enters 'X', return to the main menu
		if (strOption.equalsIgnoreCase("X")) {
			BufferedImage imgMainMenu = con.loadImage("MainMenu.jpg"); // Load the main menu image
			con.clear(); // Clear the screen
			con.drawImage(imgMainMenu, 0, 0); // Show  the main menu image
		}
	}

//Method to execute the quiz gameplay
	public static void playQuizzes(Console con, String strUserName, String strQuizChoice) {
		TextInputFile quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt"); //Open the selected quiz file
		System.out.println(" Debug: Opening quiz file: " + strQuizChoice.toLowerCase()+".txt");
    
    //Count the number of lines in the quiz file
		int intLines=0;
		while(quizFile.eof()==false){
			quizFile.readLine();
			intLines=intLines+1;
		}
		quizFile.close();
		System.out.println("Debug: Total lines in quiz file: " + intLines);
    
    // Calculate the number of questions in the quiz
		int intAmount = intLines / 6;
			
		String[][]strquizParts=new String[intAmount][7]; // Array to store quiz questions, options, and answers
		int[]randomNumbers=new int[intAmount];
			
		quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt"); // Reopen the quiz file to load the data into the array
		System.out.println(" Debug: Loading quiz data into array. ");
    
    // Loop to load quiz data
		int intCount;
		for (intCount = 0; intCount < intAmount; intCount++) {
			strquizParts[intCount][0] = quizFile.readLine(); // Question
			strquizParts[intCount][1]=quizFile.readLine(); // Option A
			strquizParts[intCount][2]=quizFile.readLine(); // Option B
			strquizParts[intCount][3]=quizFile.readLine(); // Option C
			strquizParts[intCount][4]=quizFile.readLine(); // Option D
			strquizParts[intCount][5]=quizFile.readLine(); // Correct Answer
			randomNumbers[intCount]=(int)(Math.random()*100)+1; // Random number for shuffling
			strquizParts[intCount][6]=""+randomNumbers[intCount];
			System.out.println(" Debug: Loaded question: " + (intCount+1) );
		}
		quizFile.close();
		System.out.println("Debug: Finished loading quiz data.");
	
// Sort the questions based on random numbers for shuffling
		String[] strTemp;
		int tempNum;
		for (int intRow2 = 0; intRow2 < randomNumbers.length - 1; intRow2++) {
			for (int intRow = 0; intRow < randomNumbers.length - 1 - intRow2; intRow++) {
				if (randomNumbers[intRow] > randomNumbers[intRow + 1]) {
					// Swap the random numbers
					tempNum = randomNumbers[intRow];
					randomNumbers[intRow] = randomNumbers[intRow + 1];
					randomNumbers[intRow + 1] = tempNum;

					// Swap the corresponding quiz data
					strTemp = strquizParts[intRow];
					strquizParts[intRow] = strquizParts[intRow + 1];
					strquizParts[intRow + 1] = strTemp;
				}
			}
		} 
		
    // Initialize variables for tracking correct answers and score
		int intCorrect = 0;
		double dblPercentage = 0;
		 
		// Loop through each question
		for (intCount = 0; intCount < intAmount; intCount++) {
			ClearScreen(con); // Clear the screen for each question
			highScores(con, strUserName, strQuizChoice, intCorrect, intAmount, dblPercentage);
			con.println(" Question: " + strquizParts[intCount][0]);
			con.println(" a) " + strquizParts[intCount][1]);
			con.println(" b) " + strquizParts[intCount][2]);
			con.println(" c) " + strquizParts[intCount][3]);
			con.println(" d) " + strquizParts[intCount][4]);	
			con.println(" Enter your answer(a,b,c,d): ");
		  
		  // Read the user's answer
			String strAnswer=con.readLine();
			con.println();
			   
			// Determine if the answer is correct
			int intuserAnswer = 0;
			if (strAnswer.equalsIgnoreCase("a")) {
				intuserAnswer = 1;
			} else if (strAnswer.equalsIgnoreCase("b")) {
				intuserAnswer = 2;
			} else if (strAnswer.equalsIgnoreCase("c")) {
				intuserAnswer = 3;
			} else if (strAnswer.equalsIgnoreCase("d")) {
				intuserAnswer = 4;
			}
        
			// Check if the answer matches the correct option(in the quizfile)
			if (strquizParts[intCount][intuserAnswer].equals(strquizParts[intCount][5])) {
				con.println("Correct");
				con.println();
				intCorrect++;
			} else {
				con.println("Wrong. The correct answer was: " + strquizParts[intCount][5]);
				con.println();
			}
			// Update the score percentage
			dblPercentage=((double)intCorrect/(double)intAmount)*100;
		}
    
    // Display the final result after completing the quiz
		dblPercentage = ((double) intCorrect / (double) intAmount) * 100; // Calculate the percentage score

		if (dblPercentage >= 50) { // If the score is 50% or higher, the user passes
			con.println(" Congratulations, you passed the quiz! ");
			con.println(" Quiz: " + strQuizChoice); // Display the selected quiz name
			con.println(" Score: " + intCorrect + "/" + intAmount); // Display the number of correct answers out of total questions
			con.println(" Percentage: " + dblPercentage + "%"); // Display the percentage score

			writeScore(strUserName, strQuizChoice, dblPercentage); // Write the score to the high scores file

    // Prompt user to return to the main menu
			con.println(" Enter X to go back to Main Menu ");
			String strOption = con.readLine();

    // If the user enters 'X', return to the main menu
			if (strOption.equalsIgnoreCase("X")) {
				BufferedImage imgMainMenu = con.loadImage("MainMenu.jpg"); // Load the main menu image
				con.clear(); // Clear the screen of text
				con.drawImage(imgMainMenu, 0, 0); // Display the main menu image
			}
		} else { // If the score is less than 50%, the user does not pass
			con.println(" Nice Try! You did not pass the quiz. ");
			con.println(" Quiz: " + strQuizChoice); // Show the selected quiz name
			con.println(" Score: " + intCorrect + "/" + intAmount); // Show the number of correct answers out of total questions
			con.println(" Percentage: " + dblPercentage + "%"); // Show the percentage score

			writeScore(strUserName, strQuizChoice, dblPercentage); // Write the score to the high scores file

    // Ask user to return to the main menu
			con.println(" Enter X to go back to Main Menu ");
			String strOption = con.readLine();

    // If the user enters 'X', return to the main menu
			if (strOption.equalsIgnoreCase("X")) {
			BufferedImage imgMainMenu = con.loadImage("MainMenu.jpg"); // Load the main menu image
			con.clear(); // Clear the screen of text
			con.drawImage(imgMainMenu, 0, 0); // Show the main menu image
			}
		}

		con.println();
		
	} 
// Read and show the recorded high scores
	public static void recordedHighScores(Console con){
		TextInputFile highScores=new TextInputFile("HighScores.txt");
		System.out.println(" Debug: Reading HighScores.txt");
    
		String strReadScore;
		con.println(" High Scores : "); // Header for high scores
		String strdata=""; // Gather the high scores data
		int intCount=0; // Count the number of lines
		
		while (highScores.eof() == false) {  // Read each line from the file until the end
			strReadScore = highScores.readLine();
			strdata=strdata + strReadScore.trim() + ":";
			intCount=intCount + 1; // to know how many rows there are
			System.out.println(" Debug: Read line from HighScores.txt: " + strReadScore);
		}	
		highScores.close();
		System.out.println(" Debug: Finished reading HighScores.txt");
	
	// Split the high scores data into rows
		String dataarr[]=strdata.split(":");
    
    // Sort the high scores in descending order
		int intCount2;
		int intCount3;
		for (intCount2 = 0; intCount2 < intCount; intCount2++) {
			for (intCount3 = intCount2 + 1; intCount3 < intCount; intCount3++) {
				String strrowData[] = dataarr[intCount2].split(" - ");
				String strp1=strrowData[2]; // Extract the percentage value
				strp1=strp1.substring(0,strp1.length()-2); // Remove the '%' sign
				double dblp1=Double.parseDouble(strp1); // Convert to a double
				
				String rowData2[]=dataarr[intCount3].split(" - ");
				String strp2=rowData2[2];
				strp2=strp2.substring(0,strp2.length()-2);
				double dblp2=Double.parseDouble(strp2);
				
				// Swap rows if the current score is less than the next score
				if (dblp1 < dblp2) {
					String strTemp=dataarr[intCount2];
					dataarr[intCount2]=dataarr[intCount3];
					dataarr[intCount3]=strTemp;
				}
			}
		}
    
    // Show the sorted high scores
		for (int i = 0; i < intCount; i++) {
			con.println(dataarr[i]);
		}
    
    // Ask user to return to the main menu
		con.println(" Enter X to go back to Main Menu ");
		String strOption = con.readLine();
		if (strOption.equalsIgnoreCase("X")) {
			BufferedImage imgMainMenu = con.loadImage("MainMenu.jpg"); // Load the main menu image
			con.clear(); // Clear the screen
			con.drawImage(imgMainMenu, 0, 0); // Show the main menu image
		}
	}
		
	public static void highScores(Console con, String strUserName, String strQuizChoice, int intCorrect, int intAmount, double dblPercentage) { // Show user's quiz progress
		con.println("User: " + strUserName);  
		con.println();
		con.println("Quiz: " + strQuizChoice);
		con.println();
		con.println("Score: " + intCorrect + "/" + intAmount + " (" + dblPercentage + "%)");
		con.println();
			
	}
	public static void writeScore(String strUserName, String strQuizChoice, double dblPercentage){ // Open the HighScores.txt file to save the score
		TextOutputFile highScores = new TextOutputFile("HighScores.txt", true);
		System.out.println(" Debug: Writing score to HighScores.txt for user:" + strUserName);
		highScores.println(strUserName + " - " + strQuizChoice + " - " + dblPercentage + "%"); // Write the user's name, quiz choice, and percentage score to the file
		highScores.close(); // Close the file after writing
		System.out.println(" Debug: Score written successfully");
	}
}



				
		 
