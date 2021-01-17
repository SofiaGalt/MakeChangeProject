package makechange;

import java.util.Scanner;
import java.util.LinkedList;

public class MakeChange {
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		checkout();
	}
	
	/**
	 *  Prompts for total cost and amount tendered, then calculates the change to be given back to the customer, if any. 
	 *  
	 *  If cost is greater than the amount tendered, a "funds not sufficient" message will be generated.
	 */
	public static void checkout() {
		
		System.out.println("What did the total come to? ");
		double bill = in.nextDouble();
		System.out.println();
		
		double amountPaid = 0.0;
		
		while(true) { 
			
			System.out.println("Press 1 to input the amount tendered.  Press 2 to manually input bills and coins.");
			int paymentMethod = in.nextInt();
			System.out.println();
			
			if(paymentMethod == 1) {
				
				amountPaid = enterAmountTenderedSum();
				break;
			} 
			else if ( paymentMethod == 2) {
				
				amountPaid = manualBillCoinInput();
				break;
			}
			else {
				System.out.println("That is not a recognized payment option.");
			}
		}
		
		if(amountPaid < bill ) {
			
			System.out.println("Funds not sufficient.");
			
		} else if( amountPaid == bill ) {
			
			System.out.println("Amount tendered exactly covers the cost.  Transaction complete.");
		} else {
			
			getChange(bill, amountPaid);
		}
	}
	
	/**
	 * User is prompted for the total amount tendered.
	 * 
	 * No prompt for currency denominations.
	 * To get a prompt for currency denominations in use for a given transaction, see manualBillCoinInput()
	 * 
	 * @return the amount tendered.
	 */
	public static double enterAmountTenderedSum() {
		
		System.out.println("Cash tendered sums to: ");
		double amountPaid = in.nextDouble();
		System.out.println();
		
		return amountPaid;
	}
	
	/**
	 * User will be prompted for the amount of each denomination of currency in use for a given transaction.
	 * The total amount tendered will be calculated using those input values.
	 * 
	 * To get a prompt for total amount tendered without a prompt for currency denominations in use, see enterAmountTenderedSum()
	 * 
	 * @return the amount tendered.
	 */
	public static double manualBillCoinInput() {
		
		System.out.println("Please enter the number of each denomination of currency in use for this transaction:");

		System.out.println("Twenty dollar bills: ");
		int twenties = in.nextInt();
		System.out.println("Ten dollar bills: ");
		int tens = in.nextInt();
		System.out.println("Five dollar bills: ");
		int fives = in.nextInt();
		System.out.println("One dollar bills: ");
		int ones = in.nextInt();
		System.out.println("Quarters: ");
		int quarters = in.nextInt();
		System.out.println("Dimes: ");
		int dimes = in.nextInt();
		System.out.println("Nickles: ");
		int nickels = in.nextInt();
		System.out.println("Pennies: ");
		int pennies = in.nextInt();
		
		return amountPaid(twenties, tens, fives, ones, quarters, dimes, nickels, pennies );
	}
	
	/**
	 * parameters:  The number of each denomination of currency in use for a single calculation.
	 * 
	 * @param twenties the number of twenty dollar bills.
	 * 
	 * @param tens the number of ten dollar bills.
	 * 
	 * @param fives the number of five dollar bills.
	 * 
	 * @param ones the number of one dollar bills.
	 * 
	 * @param quarters the number of quarters.
	 * 
	 * @param dimes the number of dimes.
	 * 
	 * @param nickles the number of nickels.
	 * 
	 * @param pennies the number of pennies.
	 * 
	 * @return the total monetary value of the given collection of bills and coins.
	 */
	public static double amountPaid(int twenties, int tens, int fives , int ones, int quarters, int dimes, int nickels, int pennies) {
		
		return twenties * 20 
				+ tens * 10
				+ fives * 5
				+ ones 
				+ (double) quarters * 0.25
				+ (double)dimes * 0.10
				+ (double)nickels * 0.05
				+ (double)pennies * 0.01;
	}
	
	/**
	 * 
	 * @param cost The cost in USD.
	 * 
	 * @param amountPaid The amount tendered in USD.
	 * 
	 * 	Sends message telling the amount of change to be given to the user.
	 */
	public static void getChange( double cost, double amountPaid ) {
		
		double difference = (double)Math.round( (amountPaid - cost) * 100d) / 100d;
				
		System.out.println("\nYou paid " + amountPaid + " and your purchase cost: " + cost + ".  Your change should sum to: " + difference + "\n");
		
		int twenties = (int)(difference / 20);
		difference -=  (twenties*20);
		int tens = (int)(difference / 10 );
		difference -= (tens*10);
		int fives = (int)(difference / 5 );
		difference -= (fives*5);
		int ones = (int)(difference);
		difference -= (ones);
		int change = (int) Math.round(difference*100);
		int quarters = (int)(change / 25 );
		change -= quarters*25;
		int dimes = (int)(change / 10 );
		change -= dimes*10;
		int nickels = (int)(change / 5 );
		change -= nickels * 5;
		int pennies = change;
		System.out.println("DEV change: " + change);
		
		change -= pennies;
		
		System.out.print("Your change is:");
		
		/*
		 * Display the output change. 
		 * If the number of bills or coins to be given as change for a distinct category (i.e twenties, fives, dimes, etc.) is only one, then print the singular noun.
		 * If the number of bills or coins to be given as change for a distinct category is greater than one, then print the plural noun.
		 * If the number of bills or coins to be given as change for a distinct category is zero, then don't print any info for that category.
		 * There not a case in which the number of bills or coins to be given as change for a distinct category is negative.
		 * 
		 * Each Node of the LinkedList stringsToBeConcatenated contains a String that will be added to the "Your change is: " primary output message.
		 */
		LinkedList<String> stringsToBeConcatenated = new LinkedList<String>();
		
		if(twenties ==  1) stringsToBeConcatenated.add("1 twenty dollar bill");
		else if(twenties > 1) stringsToBeConcatenated.add(twenties + " twenty dollar bills");
		
		if(tens ==  1) 	stringsToBeConcatenated.add("1 ten dollar bill");
		else if(tens > 1) stringsToBeConcatenated.add(tens + " ten dollar bills");
		
		if(fives ==  1) stringsToBeConcatenated.add("1 five dollar bill");
		else if(fives > 1) stringsToBeConcatenated.add(fives + " five dollar bills, ");
		
		if(ones ==  1) stringsToBeConcatenated.add("1 one dollar bill");
		else if(ones > 1) stringsToBeConcatenated.add(ones + " one dollar bills");
		
		if(quarters ==  1) stringsToBeConcatenated.add("1 quarter");
		else if(quarters > 1) stringsToBeConcatenated.add(quarters + " quarters"); 
		
		if(dimes ==  1) stringsToBeConcatenated.add("1 dime");
		else if(dimes > 1) stringsToBeConcatenated.add(dimes + " dimes");
		
		if(nickels ==  1) stringsToBeConcatenated.add("1 nickel");
		else if(nickels > 1) stringsToBeConcatenated.add( nickels + " nickels");
		
		if(pennies ==  1) stringsToBeConcatenated.add("1 penny");
		else if(pennies > 1) stringsToBeConcatenated.add(pennies + " pennies");
		
 		String userChangeMessage = "";
		
        int i = 0;
        for(;;) {
		   
		   if(i != stringsToBeConcatenated.size() -1 ) {
			   userChangeMessage += ", "+ stringsToBeConcatenated.get(i);
			   i++;
			   continue;
		   }
		   userChangeMessage += ", and "+ stringsToBeConcatenated.get(i) + ".";
		   break;
        }
		
		System.out.println(userChangeMessage.substring(1));
	}
	
}
