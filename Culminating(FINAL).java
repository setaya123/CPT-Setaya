import arc.*;
public class Culminating(FINAL){
	public static void main(String[]args){
		Console con=new Console();
		String strUserName;
		int intChoice;
		
		con.println(" Welcome to the Multiple Choice Game!");
		con.println("Main Menu:");
            con.println("1. Play Quizzes");
            con.println("2. View High Scores");
            con.println("3. Help Screen");
            con.println("4. Quit Game");
            con.println("5. Add Quiz");
            con.print("Enter your choice: ");
            intChoice=con.readInt();
     
            if(intChoice==1){
				 con.println("Show Available Quizzes");
				 con.println("1. Kia quiz ");
				 con.println("2. Rolls Royce quiz");
				 con.println("3. Toyoto quiz");
			int intQuizChoice;
		
		String strName;
		con.println(" Please enter name your name: ");
		strName=con.readLine();
		

		TextInputFile cars=new TextInputFile("cars.txt");
		String strA;
		String strB;
		String strC;
		String strD;
		String strCorrectAnswer;
		
		while (cars.eof()==false){
			strA=cars.readLine();
			strB=cars.readLine();
			strC=cars.readLine();
			strD=cars.readLine();
			strCorrectAnswer=cars.readLine();
			
	    	TextInputFile Toyoto=new TextInputFile("Toyoto.txt");
		while (Toyoto.eof()==false){
			strA=Toyoto.readLine();
			strB=Toyoto.readLine();
			strC=Toyoto.readLine();
			strD=Toyoto.readLine();
			strCorrectAnswer=Toyoto.readLine()
			
			TextInputFile rollsroyce=new TextInputFile("rollsroyce.txt");		
		while (rollsroyce.eof()==false){
			strA=rollsroyce.readLine();
			strB=rollsroyce.readLine();
			strC=rollsroyce.readLine();
			strD=rollsroyce.readLine();
			strCorrectAnswer=rollsroyce.readLine();
		}
		
		TextInputFile highscore=new TextInputFile("highscore.txt",true);
		
		

			
	
}
}
