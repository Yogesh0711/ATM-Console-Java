package atm;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Account {
	private long cardNum;
	private int pinNum;
	private long accNum;
	private double accountBalance;
	private String accHolderName;
	private ArrayList<String> statement=new ArrayList<>();
	static Account accoObj;
	
	public long getCardNum() {
		return cardNum;
	}
	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}
	public long getAccNum() {
		return accNum;
	}
	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}
	public int getPinNum() {
		return pinNum;
	}
	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccHolderName() {
		return accHolderName;
	}
	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}
	public ArrayList<String> getStatement() {
		return statement;
	}
	
	
	
	//to check card number 
    boolean checkCardNum(String card) {

    		if(Pattern.matches("[0-9]{10}", card)) {
    			long cardNumber=Long.parseLong(card);
    			for(int i=0;i<AtmMainClass.accountHolders.size();i++) {
    				accoObj=AtmMainClass.accountHolders.get(i);
    				if(cardNumber==accoObj.getCardNum()) {
    					System.out.println("Welcome Back "+accoObj.accHolderName+" ....");
    					return true;
    				}
    			}
    			return false;
    		}
    	return false;
    }
    
    //to check pin
    boolean checkPinNum(String pin) {

		if(Pattern.matches("[0-9]{6}", pin)) {
			long pinNumber=Integer.parseInt(pin);
			for(int i=0;i<AtmMainClass.accountHolders.size();i++) {
				accoObj=AtmMainClass.accountHolders.get(i);
				if(pinNumber==accoObj.getPinNum()) {
					this.setAccouitObject(accoObj);
					return true;
				}
			}
			return false;
		}
	return false;
   }
   private void setAccouitObject(Account currAccoutn) {
	   AtmMainClass.setCurrAccHolder(currAccoutn);
   }
    
   
   public double withdraw(double amount) {
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	   Date date = new Date();  
	   this.accountBalance-=amount;
	   statement.add("Withdraw    "+formatter.format(date)+"      Rs."+ amount);
	   return this.accountBalance;
	   
   }
   public double deposit(double amount) {
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	   Date date = new Date(); 
	   this.accountBalance+=amount;
	   statement.add("Deposit     "+formatter.format(date)+"      Rs."+ amount);
	   return this.getAccountBalance();
   }
   public double transferAmount(Account second,double amount) {
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	   Date date = new Date(); 
	   
	  //for reduce current customer balance
	   double tempAmount=this.getAccountBalance()-amount;
	   this.setAccountBalance(tempAmount);
	   
	   
	   //for setting second persons balance
	   double secondBal=second.getAccountBalance();
	   secondBal+=amount;
	   second.setAccountBalance(secondBal);
	   
	   
	   statement.add("Transfer    "+formatter.format(date)+"      Rs."+ amount);
	   return tempAmount;
   }

}
