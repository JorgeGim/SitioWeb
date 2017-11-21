package services;
public class EmailDemo {

	public static void main(String[] args) {
		String to [] = {"jorgegimenez_1996@live.com"};
		if(EmailSenderService.sendEmail
				("jorgehgimenez.1996@gmail.com", "jo020396", 
						"Probando mail desde java", 
						to))System.out.println("email sent successfully");
		else System.out.println("Some error occurred");

	}

}
