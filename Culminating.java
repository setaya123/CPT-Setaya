import arc.*; 
import java.util.Random;
public class Culminating{
	
	public static void main(String[]args){
		Console con=new Console();
		String strUserName;
		String strQuizChoice;
		
		con.println(" Welcome to the Multiple-Choice Game!");
		con.println(" Enter your name: ");
		strUserName=con.readLine();
		
		String strMenuChoice="";
		while(!strMenuChoice.equalsIgnoreCase("q")){
			con.println(" Main Menu: ");
			con.println(" Play quizzes (p)  ");
			con.println(" View High Score (v) ");
			con.println(" Quit (q) ");
			con.println(" Enter your choice" );
			strMenuChoice=con.readLine();
		
		if(strMenuChoice.equalsIgnoreCase("p")){
			showAvailableQuizzes(con);
			con.println(" Enter your choice of the quiz: ");
			strQuizChoice=con.readLine();
		
		if(strQuizChoice.equalsIgnoreCase("kia")||strQuizChoice.equalsIgnoreCase("toyoto") || strQuizChoice.equalsIgnoreCase("rolls-royce")){
			 playQuizzes(con, strUserName, strQuizChoice);
		}else{
			con.println(" Invalid quiz choice. Returning to main menu.");
		}
	} else if (strMenuChoice.equalsIgnoreCase("v")) {
    recordedHighScores(con);
	}else if(strMenuChoice.equalsIgnoreCase("q")){
		con.println(" Thank you for playing Multiple Choice Game! ");	
	}else{
		con.println("Invalid Choice. Please select one of three above options");
	}
}
}
public static void showAvailableQuizzes(Console con){		  
		String strLine;
		TextInputFile quizNames=new TextInputFile("QuizNames.txt");
	    con.println(" Avaliable Quizzes: ");
	    while(quizNames.eof()==false){
		strLine=quizNames.readLine();
		con.println(strLine);
		}
        quizNames.close();
    }
	  
    public static void playQuizzes(Console con, String strUserName, String strQuizChoice) {
		  TextInputFile quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt");
		  
		  int intLines=0;
		  while(quizFile.eof()==false){
			  quizFile.readLine();
			  intLines=intLines+1;
			}
			quizFile.close();
		
		int intAmount=intLines/6;
		
		String[][]quizParts=new String[intAmount][6];
		int[]randomNumbers=new int[intAmount];
		Random rand=new Random();
		
		quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt");
		for(int i=0;i<intAmount;i++){
			quizParts[i][0]=quizFile.readLine();
			quizParts[i][1]=quizFile.readLine();
			quizParts[i][2]=quizFile.readLine();
			quizParts[i][3]=quizFile.readLine();
			quizParts[i][4]=quizFile.readLine();
			quizParts[i][5]=quizFile.readLine();
			randomNumbers[i]=(int)(Math.random()*100)+1;

		}
		quizFile.close();
		
		
		String[] strTemp;
		int tempNum;
		for(int intRow2=0;intRow2<randomNumbers.length-1;intRow2++){
			for (int intRow = 0; intRow < randomNumbers.length - 1 - intRow2; intRow++) {
			if(randomNumbers[intRow]>randomNumbers[intRow+1]){
				tempNum=randomNumbers[intRow];
				randomNumbers[intRow]=randomNumbers[intRow+1];
				randomNumbers[intRow+1]=tempNum;
				
				strTemp=quizParts[intRow];
				quizParts[intRow]=quizParts[intRow+1];
				quizParts[intRow + 1]=strTemp;
			}
		}
	}
	        int intCorrect=0;
	        for(int i=0; i<intAmount; i++){
				con.println(" " + quizParts[i][0]);
				con.println(" a " + quizParts[i][1]);
				con.println(" b " + quizParts[i][2]);
				con.println(" c " + quizParts[i][3]);
				con.println(" d " + quizParts[i][4]);	
			    con.println(" Enter your answer(a,b,c,d): ");
			    String strAnswer=con.readLine();
			    
			int intuserAnswer=0;
			
			if(strAnswer.equalsIgnoreCase("a")){
				 intuserAnswer=1;
		    }else if(strAnswer.equalsIgnoreCase("b")){
				intuserAnswer=2;
			}else if(strAnswer.equalsIgnoreCase("c")){
				intuserAnswer=3;
			}else if(strAnswer.equalsIgnoreCase("d")){
				intuserAnswer=4;
			
			if(intuserAnswer==Integer.parseInt(quizParts[i][5])){
				 con.println(" Correct" );
				 intCorrect=intCorrect+1;
			}else{
				con.println("Wrong. The correct answer was: " + quizParts[i][5]);
		    }
		}
		
		double dblPercentage=(intCorrect/intAmount)*100;
        highScores(con, strUserName, strQuizChoice, intCorrect, intAmount, dblPercentage);
    }
}
    public static void recordedHighScores(Console con){
		TextInputFile highScores=new TextInputFile("HighScores.txt");
		String strReadScore;
		con.println(" High Scores : ");
		while(highScores.eof()==false){
			strReadScore=highScores.readLine();
			con.println(strReadScore);
		}
		highScores.close();
	}
		
    public static void highScores(Console con, String strUserName, String strQuizChoice, int intCorrect, int intAmount, double dblPercentage) {
        con.println("User: " + strUserName);  
        con.println("Quiz: " + strQuizChoice);
        con.println("Score: " + intCorrect + "/" + intAmount + " (" + dblPercentage + "%)");
        TextOutputFile highScores = new TextOutputFile("HighScores.txt", true);
        highScores.println(strUserName + " - " + strQuizChoice + " - " + dblPercentage + "%");
        highScores.close();
    }
}

				
		 
