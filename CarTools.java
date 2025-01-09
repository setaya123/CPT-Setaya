import arc.*;
public class CarTools{
public static void showAvaliableQuizzes(Console con){
		con.println("Avaliable Quizzes: ");
		con.println(" Kia ");
		con.println(" Toyota ");
        con.println(" Rolls-Royce ");
}
public static void playQuizzes(Console con, String strUsername, String strQuizName){
        TextInputFile kiaFile = new TextInputFile("kia.txt");
        TextInputFile toyotaFile = new TextInputFile("toyoto.txt");
        TextInputFile rollsRoyceFile = new TextInputFile("rollsroyce.txt");

    TextInputFile quizFile;
    
	if (strQuizName.equalsIgnoreCase("kia")) {
    quizFile = new TextInputFile("kia.txt");
	}else if (strQuizName.equalsIgnoreCase("toyota")) {
    quizFile = new TextInputFile("toyoto.txt");
	}else if (strQuizName.equalsIgnoreCase("rolls-royce")) {
    quizFile = new TextInputFile("rollsroyce.txt");
	}else{
    con.println("Quiz not found.");
    return;
}
    
    //random Numbers array assigns a random number to each Row(ie, each question) in the quizParts array
    String[][]quizParts=new String[10][6];
    int[]randomnumbers=new int[10];
    Random rand=new Random();
    
    

    for(int i=0;i<10;i++){
		quizParts[i][0]=quizFile.readLine();
		quizParts[i][1]=quizFile.readLine();
		quizParts[i][2]=quizFile.readLine();
		quizParts[i][3]=quizFile.readLine();
		quizParts[i][4]=quizFile.readLine();
		quizParts[i][5]=quizFile.readLine();
	    randomnumbers[i]=rand.nextInt(100)+1;
	}
        quizFile.close();
     }   
     public static void bubbleSortQuiz(String[] quizParts,int[]randomNumbers){
		for(int i=0; i<randomNumbers.length;i++){
			for(int j=0; j<randomNumbers.length-1-i;j++){
				if(randomNumbers[j]>randomNumbers[j+1]){
					int tempNum=randomNumbers[j];
					randomNumbers[j]=randomNumbers[j+1];
					randomNumbers[j+1]=tempNum;
		    String[] tempQuiz=quizParts[j];
		    quizParts[j]=quizParts[j+1];
		    quizParts[j+1]=tempQuiz;
		   
		    
		  public static void HighScore(String strUsername, String strQuizName, double dblPercentage);
		  TextOutputFile HighScore=new TextOutputFile("Highscore.txt", true);
		  double dblPercentage=(intCorrect/10.0)*100;
		  con.println( " User " + strUserName);
		  con.println("  Quiz " + strQuizName);
		  con.println("  Score " + intCorrect + "/10: " + (dblPercentage) + "%");
		  
		  HighScore.println(strUsername+ "  " + strQuizName+ "  " + dblPercentage+ "  " );
		  HighScore.close();
		    
}
}
}
}
}
