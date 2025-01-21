import arc.*;  
import java.awt.Color;
import java.awt.image.BufferedImage;
public class tryingpictures{
	public static void main(String[]args){
		Console con=new Console("Multiple-Choice Game", 1280, 720);
        
		BufferedImage imgBackground = con.loadImage("Begin.jpg");
		con.drawImage(imgBackground, 0, 0);
		System.out.println(" Debug: Displayed 'Begin.jpg'");
		
		MouseClick(con);
	
		BufferedImage imgMainMenu=con.loadImage("MainMenu.jpg");
		con.drawImage(imgMainMenu,0,0);
		System.out.println(" Debug: Displayed 'MainMenu.jpg' ");
		
		con.println(" Enter your name: ");
		String strUserName=con.readLine();

		System.out.println(" Debug: User entered name: " + strUserName);
		
		char chrMenuChoice=' ';
		while(chrMenuChoice!='q'){
			con.println(" Enter Menu Choice: ");
			chrMenuChoice=con.readChar();
			System.out.println( " Debug: User selected menu option: " + chrMenuChoice);
			
		if(chrMenuChoice=='p' || chrMenuChoice=='P' ) {
			BufferedImage imgQuizzes=con.loadImage("quizzes.jpg");
			con.clear();
			con.drawImage(imgQuizzes,0,0);
			System.out.println(" Debug: Displayed 'quizzes.jpg'");
			
			con.println(" Enter your choice of the quiz: ");
			String strQuizChoice=con.readLine();
			System.out.println(" Debug: User selected quiz: " + strQuizChoice);
		
		if(strQuizChoice.equalsIgnoreCase("kia")
		    ||strQuizChoice.equalsIgnoreCase("toyoto") 
		    || strQuizChoice.equalsIgnoreCase("rolls-royce")){
				con.setBackgroundColor(Color.BLACK);
			 playQuizzes(con, strUserName, strQuizChoice); 
    }else{
		con.println(" Invalid Choice ");
	}
	}else if(chrMenuChoice=='v'){ 
    con.setBackgroundColor(Color.BLACK);
    recordedHighScores(con);
    }else if(chrMenuChoice=='h'){
	con.setBackgroundColor(Color.BLACK);
    HelpOption(con);
    }else if(chrMenuChoice=='s'){
	con.setBackgroundColor(Color.BLACK);
	SecretMenu(con);
	}else if(chrMenuChoice=='q'){
	con.setBackgroundColor(Color.BLACK);
	con.println(" Thank you for playing Multiple Choice Game! ");	
	con.println();
		
	}else{
		con.println("Invalid Choice. Please select one of three above options");
	    con.println();
	 }
}
}    public static void MouseClick(Console con){
	 System.out.println(" Debug: Waiting for mouse click");
	 while(con.currentMouseButton()!=1){
	 }
	 System.out.println(" Debug: Mouse clicked, redirecting to Main Menu");
	}
    public static void ClearScreen(Console con){
       con.clear();
   }

public static void showAvailableQuizzes(Console con){		  
		String strLine;
		TextInputFile quizNames=new TextInputFile("tests.txt");
	    con.println(" Avaliable Quizzes: ");
	    while(quizNames.eof()==false){
		strLine=quizNames.readLine();
		con.println(strLine);
		}
        quizNames.close();
    }
	public static void HelpOption(Console con){
		ClearScreen(con);
		con.println(" You are given three quiz choices: Kia,Toyoto, Rolls-Royce");
		 con.println(" Each question has 4 answers. Enter the corresponding letter(a,b,c,d) for your answer");
		 con.println(" User name, quiz, and score will be shown at the front of each question ");
		 con.println(" Final score and result of the quiz will be shown at the end ");
		 con.println();
	}
	public static void SecretMenu(Console con){
		ClearScreen(con);
		con.println(" What do you call a fly without wings? A walk ");
		con.println(" Why do you call a cold puppy? A chili dog ");
		con.println(" Two fish are in a tank. One turns to the other and says, “Any idea how to drive this thing? ");
		con.println();
	}
    public static void playQuizzes(Console con, String strUserName, String strQuizChoice) {
		  TextInputFile quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt");
		  System.out.println(" Debug: Opening quiz file: " + strQuizChoice.toLowerCase()+".txt");
		  int intLines=0;
		  while(quizFile.eof()==false){
			  quizFile.readLine();
			  intLines=intLines+1;
			}
			quizFile.close();
			System.out.println(" Debug: Total lines in quiz file: " + intLines);
		
		int intAmount=intLines/6;
		
		String[][]strquizParts=new String[intAmount][7];
		int[]randomNumbers=new int[intAmount];
		
		quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt");
		System.out.println(" Debug: Loading quiz data into array. ");
		int intCount;
		for(intCount=0;intCount<intAmount;intCount++){
			strquizParts[intCount][0]=quizFile.readLine();
			strquizParts[intCount][1]=quizFile.readLine();
			strquizParts[intCount][2]=quizFile.readLine();
			strquizParts[intCount][3]=quizFile.readLine();
			strquizParts[intCount][4]=quizFile.readLine();
			strquizParts[intCount][5]=quizFile.readLine();
			randomNumbers[intCount]=(int)(Math.random()*100)+1;
			strquizParts[intCount][6]=""+randomNumbers[intCount];
            System.out.println(" Debug: Loaded question: " + (intCount+1) );
		}
		quizFile.close();
		System.out.println(" Debug: Finished loading quiz data.");
		
		
		String[] strTemp;
		int tempNum;
		for(int intRow2=0;intRow2<randomNumbers.length-1;intRow2++){
			for (int intRow = 0; intRow < randomNumbers.length - 1 - intRow2; intRow++) {
			if(randomNumbers[intRow]>randomNumbers[intRow+1]){
				tempNum=randomNumbers[intRow];
				randomNumbers[intRow]=randomNumbers[intRow+1];
				randomNumbers[intRow+1]=tempNum;
				
				strTemp=strquizParts[intRow];
				strquizParts[intRow]=strquizParts[intRow+1];
				strquizParts[intRow + 1]=strTemp;
			}
		}
	}
	        int intCorrect=0;
	        double dblPercentage=0;
            
	        for(intCount=0; intCount<intAmount; intCount++){
				ClearScreen(con);
			    highScores(con, strUserName, strQuizChoice, intCorrect, intAmount, dblPercentage);
				con.println(" Question: " + strquizParts[intCount][0]);
				con.println(" a) " + strquizParts[intCount][1]);
				con.println(" b) " + strquizParts[intCount][2]);
				con.println(" c) " + strquizParts[intCount][3]);
				con.println(" d) " + strquizParts[intCount][4]);	
			    con.println(" Enter your answer(a,b,c,d): ");
			    String strAnswer=con.readLine();
			    con.println();
			   
			int intuserAnswer=0;
			
			if(strAnswer.equalsIgnoreCase("a")){
				 intuserAnswer=1;
		    }else if(strAnswer.equalsIgnoreCase("b")){
				intuserAnswer=2;
			}else if(strAnswer.equalsIgnoreCase("c")){
				intuserAnswer=3;
			}else if(strAnswer.equalsIgnoreCase("d")){
				intuserAnswer=4;
			}
			
			if(strquizParts[intCount][intuserAnswer].equals(strquizParts[intCount][5])){
				 con.println(" Correct" );
				 con.println();
				 intCorrect=intCorrect+1;
			}else{
				con.println("Wrong. The correct answer was: " + strquizParts[intCount][5]);
				con.println();
		    }
		    dblPercentage=((double)intCorrect/(double)intAmount)*100;

		}
		//print after quiz is done
		 dblPercentage=((double)intCorrect/(double)intAmount)*100; 
        if(dblPercentage>=80){
			con.println(" Congratulations, you passed the quiz! ");
			con.println(" Quiz: " + strQuizChoice);
			con.println(" Score:" + intCorrect + "/" + intAmount);
			con.println(" Percentage: " + dblPercentage + "%");
			writeScore(strUserName, strQuizChoice,dblPercentage);
		    con.println(" Enter X to go back to Main Menu ");
	         String strOption=con.readLine();
	         if(strOption.equalsIgnoreCase("X")){
            BufferedImage imgMainMenu=con.loadImage("MainMenu.jpg");
            con.clear(); // to clear screen of all the text; 
            con.drawImage(imgMainMenu,0,0);
           }
    
		}else{
			con.println(" Nice Try! You did not pass the quiz. ");
			con.println(" Quiz: " + strQuizChoice);
			con.println(" Score: " + intCorrect + "/" + intAmount);
			con.println("Percentage: " + dblPercentage + "%");			
			writeScore(strUserName, strQuizChoice,dblPercentage);
	        con.println(" Enter X to go back to Main Menu ");
	         String strOption=con.readLine();
	         if(strOption.equalsIgnoreCase("X")){
            BufferedImage imgMainMenu=con.loadImage("MainMenu.jpg");
            con.clear();
            con.drawImage(imgMainMenu,0,0);
           }

		}
	     con.println();
		
}
    public static void recordedHighScores(Console con){
		TextInputFile highScores=new TextInputFile("HighScores.txt");
		System.out.println(" Debug: Reading HighScores.txt");
		String strReadScore;
		con.println(" High Scores : ");
		String strdata="";
		int intCount=0;
		
		while(highScores.eof()==false){
			strReadScore=highScores.readLine();
		    strdata=strdata + strReadScore.trim() + ":";
		    intCount=intCount + 1; // to know how many rows there are
		    System.out.println(" Debug: Read line from HighScores.txt: " + strReadScore);
		}
		highScores.close();
		System.out.println(" Debug: Finished reading HighScores.txt");
		String dataarr[]=strdata.split(":"); //put in one line because (seperate it by a line)
		for(int i=0;i<intCount;i++){
			for(int j=i+1;j<intCount;j++){
				String strrowData[]=dataarr[i].split(" - ");
			String strp1=strrowData[2]; //score is at row 2;
			strp1=strp1.substring(0,strp1.length()-2); //extract the percentage sign
			double dblp1=Double.parseDouble(strp1); 
		        String rowData2[]=dataarr[j].split(" - ");
		    String strp2=rowData2[2];
			strp2=strp2.substring(0,strp2.length()-2);
			double dblp2=Double.parseDouble(strp2);
			if(dblp1<dblp2){
				String strTemp=dataarr[i];
	            dataarr[i]=dataarr[j];
	            dataarr[j]=strTemp;
	      }
	  }
  }
    for(int i=0;i<intCount;i++){
		con.println(dataarr[i]);
	}
	con.println(" Enter X to go back to Main Menu ");
	String strOption=con.readLine();
	if(strOption.equalsIgnoreCase("X")){
    BufferedImage imgMainMenu=con.loadImage("MainMenu.jpg");
    con.clear();
    con.drawImage(imgMainMenu,0,0);
}
		        
	}
		
    public static void highScores(Console con, String strUserName, String strQuizChoice, int intCorrect, int intAmount, double dblPercentage) {
        con.println("User: " + strUserName);  
        con.println();
        con.println("Quiz: " + strQuizChoice);
        con.println();
        con.println("Score: " + intCorrect + "/" + intAmount + " (" + dblPercentage + "%)");
        con.println();
        
    }
    public static void writeScore(String strUserName, String strQuizChoice, double dblPercentage){
		TextOutputFile highScores = new TextOutputFile("HighScores.txt", true);
        System.out.println(" Debug: Writing score to HighScores.txt for user:" + strUserName);
        highScores.println(strUserName + " - " + strQuizChoice + " - " + dblPercentage + "%");
        highScores.close();
        System.out.println(" Debug: Score written successfully");
	}
		
}



				
		 
