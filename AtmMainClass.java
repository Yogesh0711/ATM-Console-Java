package atm;

import java.util.*;
import java.util.regex.Pattern;

public class AtmMainClass {
	public static ArrayList<Account> accountHolders=new ArrayList<> ();
	static Scanner inputGetter = new Scanner(System.in);
	static AtmMainClass atmMain=new AtmMainClass();
	static Account accoObj=new Account();
	private static Account currAccHolder;
	private static MainMenu mainMenu;
	
	
	
	
    public static Account createAccountObject(String accHolderName,long accNum,long cardNum,int pinNum,double accountBalance) {
    	Account newAccounter=new Account();
    	newAccounter.setAccHolderName(accHolderName);
    	newAccounter.setAccNum(accNum);
    	newAccounter.setCardNum(cardNum);
    	newAccounter.setPinNum(pinNum);
    	newAccounter.setAccountBalance(accountBalance);
    	return newAccounter;	
    }
    public static void createReadyMadeObjects() {
    	Account yogesh=AtmMainClass.createAccountObject("Yogeshwari", 3245678, 1234782323, 234533, 50000);
    	Account ramya=AtmMainClass.createAccountObject("Ramya",3245679,1234782423,129203,40000);
    	Account jayam=AtmMainClass.createAccountObject("Jaya Haritha", 3245680, 1234782423, 128924, 20000);
    	accountHolders.add(yogesh);
    	accountHolders.add(jayam);
    	accountHolders.add(ramya);
    }
    
    //main method
    public static void main(String args[]) {
    	createReadyMadeObjects();
    	System.out.println("------Welcome to Ramya ATM------");
    	atmMain.mainStart();
    }
    
    //to start
    
    void mainStart() {
    	atmMain.insertCard();
    }
    
    //to insert card
    public void insertCard() {
    	boolean answer=false;
    	System.out.println("Enter the card number : ");
        String cardNum=inputGetter.nextLine();
        answer=accoObj.checkCardNum(cardNum);
        if(!answer) {
        	System.out.println("Enter the proper card Number....");
        	System.out.println("");
        	atmMain.insertCard();
        }
        else {
        	
        	atmMain.enterPin();
        }
    }
      
        public static Account getCurrAccHolder() {
        	return currAccHolder;
	    }
    
        //to set current account holder in variable
    
  		public static void setCurrAccHolder(Account currAccHolder) {
  			AtmMainClass.currAccHolder = currAccHolder;
  		}
    
	
	//to check pin
    
     public void enterPin() {
    	 boolean answer=false;
    	 System.out.println("Enter the pin number : ");
         String pinNum=inputGetter.nextLine();
         answer=accoObj.checkPinNum(pinNum);
         if(!answer) {
         	atmMain.enterPin();
         }
         else {
        	 System.out.println("");
        	 
        	 // main menu 
        	 mainMenu=new MainMenu();
        	 mainMenu.mainInmenu();
         }
     }
     
    
     
     
}
