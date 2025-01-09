import arc.*;
public class Culminating1{
	public static void main(String[]args){
		Console con=new Console();
		String strUserName;
		int intChoice;
		
		con.println("Welcome to the Car Quiz Game!");
        con.print("Enter your name: ");
        strUserName = con.readLine();

        con.println(" Would you like to play a quiz? (1 for Yes, any other number for No):");
        String strChoice = con.readLine();

		if (strChoice.equals("1")) {
		CarTools.showAvaliableQuizzes(con);
    con.print("Enter your choice of quiz: ");
    strChoice = con.readLine();

    if (strChoice.equalsIgnoreCase("kia") || strChoice.equalsIgnoreCase("toyota") || strChoice.equalsIgnoreCase("rolls-royce")) {
        CarTools.playQuizzes(con, strUserName, strChoice);
    } else {
        con.println("Invalid choice");
    }
} else {
    con.println("Thank you for visiting the Car Quiz Game!");
}
}
}

 for(int i=0; i<10;i++){
			 con.println(" Question: " + quizParts[i][0]);
			 con.println("a. " + quizParts[i][1]);
			 con.println("c. " + quizParts[i][2]);
			 con.println("d. " + quizParts[i][3]);
			 con.println("e. " + quizParts[i][4]);
			 con.println(" Enter your answer: a, b, c, d");
			 String strUserName=con.readLine();
			 if(strUserName.equalsIgnoreCase(quizParts[i][5])){
				 con.println(" Correct ");
				 intCorrect=intCorrect + 1; 
			 }else{
				 con.println(" Wrong, the answer is: " + quizParts[i][5]);
			}
		}
			  
