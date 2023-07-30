package atm;

import java.util.*;

public class MainMenu {
	
	static Scanner inputGetter = new Scanner(System.in);
	private static Account currAccHolder;
	
	MainMenu(){
		currAccHolder=AtmMainClass.getCurrAccHolder();
	}
	public void mainInmenu() {
		
		int choice=0;
		boolean temp=true;
	while(temp){
		try {
		System.out.println("");
		System.out.println("1.Balance Enquiry \n2.Ministatement \n3.Withdraw \n4.Deposit \n5.Transfer \n6.Exit");
		choice=inputGetter.nextInt();
		if(choice>6 || choice<0) {
			System.out.println("Enter valid Input....");
			continue;
		}
		temp=false;
		}
		catch(Exception e){
			System.out.println("Enter proper Input....");
			System.out.println();
			inputGetter.nextLine();
		}
		
	}
		switch(choice) {
		case 1:
			this.checkBalance();
			break;
		case 2:
			this.miniStatement();
			break;
		case 3:
			this.withDraw();
			break;
		case 4:
			this.deposit();
			break;
		case 5:
			this.transfer();
			break;	
		case 6:
			System.exit(0);
			break;
		}
	}
	
	
	//give balance
	
	public void checkBalance() {
		System.out.println("Your current Balance is :"+ currAccHolder.getAccountBalance());
		this.mainInmenu();
	}
	
	//mini statement
	
	public void miniStatement() {
		if(currAccHolder.getStatement().size()!=0) {
			ArrayList<String> statementList=currAccHolder.getStatement();
			System.out.println("Operations       Date and time         Amount   ");
			for(int i=0;i<statementList.size();i++) {
				System.out.println(statementList.get(i));
			}
		}
		else {
			System.out.println("We don't have access to past statements");
		}
		this.mainInmenu();																																																																						
	}
	
	
	//withdraw
	public void withDraw() {
		boolean temp=true;
		double amount=0;
		while(temp){
			try {
				System.out.println("Enter the amount to withdraw : ");
				amount=inputGetter.nextDouble();
				temp=false;
			}
			catch(Exception e){
				System.out.println("Enter proper Input....");
				System.out.println();
				inputGetter.nextLine();
			}
			
		}
		if(amount<=0) {
			System.out.println("Enter proper Amount...");
		}
		else if(amount>0 && amount<=currAccHolder.getAccountBalance()) {
			System.out.println("CurrentBalance is : "+currAccHolder.withdraw(amount));
		}
		else {
			System.out.println("Insufficient Balance...");
		}
		this.mainInmenu();
	}
	
	
	//deposit
	public void deposit() {
		boolean temp=true;
		double amount=0;
		while(temp){
			try {
				System.out.println("Enter the amount to Deposit : ");
				amount=inputGetter.nextDouble();
				temp=false;
			}
			catch(Exception e){
				System.out.println("Enter proper Amount....");
				System.out.println();
				inputGetter.nextLine();
			}
			
		}
			System.out.println("CurrentBalance is : "+currAccHolder.deposit(amount));
		    this.mainInmenu();
	}
	
	
	//transfer
	public void transfer() {
		
		//to check another account number
		boolean temp=true;
		long accNum=0;
		Account secondAcc=null;
		
		boolean temp2=true;
		double amount=0;
		
		while(temp) {
			try {
				System.out.println("Enter the Account number to transfer : ");
				accNum=inputGetter.nextLong();
				temp=false;
		    }
			catch(Exception e) {
				System.out.println("Enter proper Account Number....");
				System.out.println();
				inputGetter.nextLine();
			}
	   }
		for(int i=0;i<AtmMainClass.accountHolders.size();i++) {
			Account tempAcc=AtmMainClass.accountHolders.get(i);
			if(tempAcc.getAccNum()==accNum) {
				secondAcc=tempAcc;
			}
		}
		if(secondAcc==null) {
			System.out.println("Enter proper account number of this bank.");
			this.transfer();
		}
		else {
			//to transfer amount
			
			while(temp2){
				try {
					System.out.println("Enter the amount to transfer : ");
					amount=inputGetter.nextDouble();
					temp2=false;
				}
				catch(Exception e){
					System.out.println("Enter proper Input....");
					System.out.println();
					inputGetter.nextLine();
				}
				
			}
			if(amount<=0) {
				System.out.println("Enter proper Amount...");
			}
			else if(amount>0 && amount<=currAccHolder.getAccountBalance()) {
				System.out.println("CurrentBalance is : "+currAccHolder.transferAmount(secondAcc, amount));
				this.mainInmenu();
			}
			else {
				System.out.println("Insufficient Balance...");
				this.mainInmenu();
			}
		}
		
	
	}
	
}
