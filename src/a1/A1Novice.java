package a1;

import java.util.Scanner;

public class A1Novice
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		// Your code follows here.
		
		// Reads in an integer representing the number of customers and
		// creates an array of Customer objects with size as that integer
		Customer[] customers = new Customer[scan.nextInt()];
		
		/* Fills the array with new Customer objects, reading in fields
		 * firstName, lastName, and itemsBought
		 * 
		 * Creates and fills an array of new Item objects for each customer,
		 * with size the number of items bought,
		 * reading in fields amount, name, and price
		 */
		for (int i = 0; i < customers.length; i++)
		{
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());
			
			// Customer is a static class, so fields can be accessed without getter methods.
			Item[] items = new Item[customers[i].itemsBought];
			
			for (int j = 0; j < items.length; j++)
			{
				items[j] = new Item(scan.nextInt(), scan.next(), scan.nextDouble());
			}
			
			// Formats the total price to two decimal places
			String result = String.format("%.2f", getTotal(items));
			
			System.out.println(customers[i].firstName.charAt(0) + ". " + 
							   customers[i].lastName + ": " + result);
		}
		
		// Closes the scanner as it is no longer needed
		scan.close();
	}
	
	/* Calculates and returns the total cost of the items purchased by a customer
	 * by summing the product of each bought item's price and its amount.
	 * 
	 * Input: items, an array of this customer's Item objects
	 * Output: total cost of items purchased as a double
	 */
	private static double getTotal(Item[] items)
	{
		double total = 0.0;
		
		for (int i = 0; i < items.length; i++)
		{
			// Item is also a static class, so fields can be accessed without getter methods.
			total += items[i].price * items[i].amount;
		}
		
		return total;
	}
	
	// Represents a customer with a first name, last name, and the number of items bought.
	public static class Customer
	{
		private String firstName;
		
		private String lastName;
		
		private int itemsBought;
		
		public Customer(String first, String last, int items)
		{
			this.firstName = first;
			this.lastName = last;
			this.itemsBought = items;
		}
	}
	
	// Represents an item with an amount, name, and price.
	public static class Item
	{
		private int amount;
		
		private String name;
		
		private double price;
		
		public Item(int amt, String n, double p)
		{
			amount = amt;
			name = n;
			price = p;
		}
	}
}