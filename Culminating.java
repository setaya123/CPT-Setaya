import arc.*;
import java.util.Random;
public class Culminating{
	
	public static void main(String[]args){
		Console con=new Console();
		con.println("Test");
		String strUserName;
		String intChoie;
		String strQuizChoice;
		
		con.println("Welcome to the Car Quiz Game!");
        con.print("Enter your name: ");
        strUserName=con.readLine();
        
        con.println(" Would you like to play a quiz? (1 for Yes, any other number for No):");
        int intChoice =con.readInt();
        
        if (intChoice==1) {
		showAvaliableQuizzes(con);
		
        con.println("Enter your choice of quiz: ");
        strQuizChoice = con.readLine();
        
        if (strQuizChoice.equalsIgnoreCase("kia") || strQuizChoice.equalsIgnoreCase("toyota") || strQuizChoice.equalsIgnoreCase("rolls-royce")) {
        playQuizzes(con, strUserName, strQuizChoice);
     } else {
        con.println("Invalid choice");
} 
     }else{
		 con.println(" Thank you for participating ");
	}
}
	 public static void showAvaliableQuizzes(Console con){
		con.println("Avaliable Quizzes: ");
		con.println(" Kia ");
		con.println(" Toyota ");
        con.println(" Rolls-Royce ");
      }  
     public static void playQuizzes(Console con, String strUsername, String strQuizChoice){
		TextInputFile kiaFile = new TextInputFile("kia.txt");
        TextInputFile toyotaFile = new TextInputFile("toyota.txt");
        TextInputFile RollsRoyceFile = new TextInputFile("rollsroyce.txt");
        
        TextInputFile quizFile;
		
		if(strQuizChoice.equalsIgnoreCase("kia")){
			quizFile=kiaFile;
		}else if(strQuizChoice.equalsIgnoreCase("toyoto")){
			quizFile=toyotaFile;
		}else if(strQuizChoice.equalsIgnoreCase("Rolls-Royce")){
			quizFile=RollsRoyceFile;
		}else{
			con.println("Quiz not found.");
			return;
		}

        String[][]quizParts=new String[10][6];
		int[]randomNumbers=new int[10];
		Random rand=new Random();
		
		//reading data from the selected file
		for(int i=0; i<10;i++){
			quizParts[i][0]=quizFile.readLine();
			quizParts[i][1]=quizFile.readLine();
			quizParts[i][2]=quizFile.readLine();
			quizParts[i][3]=quizFile.readLine();
			quizParts[i][4]=quizFile.readLine();
			quizParts[i][5]=quizFile.readLine();
		    randomNumbers[i] = (int)(Math.random() * 100) + 1;
		}
		quizFile.close();
		

		int intCorrect=0;
		for(int i=0;i<10;i++){
			con.println(" Question: " + quizParts[i][0]);
			con.println("a. " + quizParts[i][1]);
			con.println("b. " + quizParts[i][2]);
			con.println("c. " + quizParts[i][3]);
			con.println("d. " + quizParts[i][4]);
			con.println(" Enter your answer: a, b, c, d");
			String strUserName=con.readLine();
		
		if(strUserName.equalsIgnoreCase(quizParts[i][5])){
			con.println("Correct");
			intCorrect=intCorrect+1;
		}else{
			con.println(" Wrong. The answer is: " + quizParts[i][5]);			
		}
		double dblPercentage=(intCorrect/10)*100;
		con.println("User: " + strUserName);
		con.println("Quiz: " + strQuizChoice);
		con.println("Score: " + dblPercentage + "%");


      }
 }
}
