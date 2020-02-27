package utils;

/**
 * 
 * February, 3 2020
 * 
 * @author guney
 *
 */

public final class Common {
	
	private Common () {}
	
	public static void sleep ( int seconds) {
		
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
        System.out.println(getRandomEmail("John", "Doe"));
    }
    
    /*
     * Create getRandomEmail ( String fName, String lName ); method
     * use Math.random method
     * 
     * Ex.: getRandomEmail ( "John", "Doe" ); return String
     * Output #1 john.doe@gmail.com
     * Output #2 john.doe@yahoo.com
     * Output #3 john.doe@hotmail.com
     */
    public static String getRandomEmail ( String fName, String lName ) {
        String [] emails = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@mail.ru"};
        return (fName.trim() + "." + lName.trim()
                + emails [ getRandomNumber(0, emails.length - 1) ]).toLowerCase();
    }
    
    public static int getRandomNumber ( int minNumber, int maxNumber ) {
        double randomNumber = (Math.random() * (( maxNumber - minNumber ) + 1) + minNumber);
        return (int) randomNumber;
    }
}
