import arc.*; 
import java.util.Random;
public class Culminating{
	public static void main(String[]args){
       Console con=new Console("Multiple-Choice Game", 1280, 720);
       String strUserName;
       String strQuizChoice;
		
		con.println(" Welcome to the Multiple-Choice Game!");
		con.println(" Enter your name: ");
		strUserName=con.readLine();
		con.println();
		
		char chrMenuChoice=' ';
		while(chrMenuChoice!='q'){ //loop ends if q is entered (will only run once) 
			con.println(" Main Menu: ");
			con.println(" Play quizzes (p)  ");
			con.println(" View High Score (v) ");
			con.println(" Help (h) ");
			con.println(" Quit Game (q) ");
			con.println(" Enter your choice" );
			chrMenuChoice=con.readChar();
			con.println();
		
		if(chrMenuChoice=='p') 
			showAvailableQuizzes(con);
			con.println();
			con.println(" Enter your choice of the quiz: ");
			strQuizChoice=con.readLine();
			con.println();
		
		if(strQuizChoice.equalsIgnoreCase("kia")||strQuizChoice.equalsIgnoreCase("toyoto") || strQuizChoice.equalsIgnoreCase("rolls-royce")){
			 playQuizzes(con, strUserName, strQuizChoice);
	}else if(chrMenuChoice=='v'){ 
    recordedHighScores(con);
    }else if(chrMenuChoice=='h'){
		 HelpOption(con);
    }else if(chrMenuChoice=='s'){
		SecretMenu(con);
	}else if(chrMenuChoice=='q'){
		con.println(" Thank you for playing Multiple Choice Game! ");	
	    con.println();
		
	}else{
		con.println("Invalid Choice. Please select one of three above options");
	    con.println();
	 }
}
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
		con.println(" You are given three quiz choices: Kia,Toyoto, Rolls-Royce");
		 con.println(" Each question has 4 answers. Enter the corresponding letter(a,b,c,d) for your answer");
		 con.println(" User name, quiz, and score will be shown at the front of each question ");
		 con.println(" Final score and result of the quiz will be shown at the end ");
	}
	public static void SecretMenu(Console con){
		con.println(" What do you call a fly without wings? A walk ");
		con.println(" Why do you call a cold puppy? A chili dog ");
		con.println(" Two fish are in a tank. One turns to the other and says, “Any idea how to drive this thing? ");
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
		
		String[][]quizParts=new String[intAmount][7];
		int[]randomNumbers=new int[intAmount];
		Random rand=new Random();
		
		quizFile=new TextInputFile(strQuizChoice.toLowerCase()+".txt");
		int intCount;
		for(intCount=0;intCount<intAmount;intCount++){
			quizParts[intCount][0]=quizFile.readLine();
			quizParts[intCount][1]=quizFile.readLine();
			quizParts[intCount][2]=quizFile.readLine();
			quizParts[intCount][3]=quizFile.readLine();
			quizParts[intCount][4]=quizFile.readLine();
			quizParts[intCount][5]=quizFile.readLine();
			randomNumbers[intCount]=(int)(Math.random()*100)+1;
			quizParts[intCount][6]=""+randomNumbers[intCount];

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
	        double dblPercentage=0;
            
	        for(intCount=0; intCount<intAmount; intCount++){
			    highScores(con, strUserName, strQuizChoice, intCorrect, intAmount, dblPercentage);
				con.println(" Question: " + quizParts[intCount][0]);
				con.println(" a) " + quizParts[intCount][1]);
				con.println(" b) " + quizParts[intCount][2]);
				con.println(" c) " + quizParts[intCount][3]);
				con.println(" d) " + quizParts[intCount][4]);	
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
			
			if(quizParts[intCount][intuserAnswer].equals(quizParts[intCount][5])){
				 con.println(" Correct" );
				 con.println();
				 intCorrect=intCorrect+1;
			}else{
				con.println("Wrong. The correct answer was: " + quizParts[intCount][5]);
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
		}else{
			con.println(" Nice Try! You did not pass the quiz. ");
			con.println(" Quiz: " + strQuizChoice);
			con.println(" Score: " + intCorrect + "/" + intAmount);
			con.println("Percentage: " + dblPercentage + "%");			
			writeScore(strUserName, strQuizChoice,dblPercentage);

		}
	     con.println();
		
}
    public static void recordedHighScores(Console con){
		TextInputFile highScores=new TextInputFile("HighScores.txt");
		String strReadScore;
		con.println(" High Scores : ");
		String strdata="";
		int intCount=0;
		
		while(highScores.eof()==false){
			strReadScore=highScores.readLine();
		    strdata=strdata + strReadScore + ":";
		    intCount=intCount + 1; // to know how many rows there are
		}
		highScores.close();
		String dataarr[]=strdata.split(":"); //put in one line because 
		for(int i=0;i<intCount;i++){
			String strrowData[]=dataarr[i].split(" - ");
			String strp1=strrowData[2];
			strp1=strp1.substring(0,strp1.length()-2);
			double dblp1=Double.parseDouble(strp1);
			for(int j=i+1;j<intCount;j++){
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
        highScores.println(strUserName + " - " + strQuizChoice + " - " + dblPercentage + "%");
        highScores.close();
	}
		
}



				
		 
