package a1;

import java.util.Scanner;

public class A1Jedi
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		// Your code follows here.

		// Reads in an integer representing the number of items in the store
		// and creates an array of Item objects with size as that integer
		Item[] itemsInStore = new Item[scan.nextInt()];

		// Fills the array with new Item objects, reading in fields name and price
		for (int i = 0; i < itemsInStore.length; i++)
		{
			itemsInStore[i] = new Item(scan.next(), scan.nextDouble());
		}

		// Reads in an integer representing the number of customers and
		// creates an array of Customer objects with size as that integer
		Customer[] customers = new Customer[scan.nextInt()];

		/* Fills the array with new Customer objects, reading in fields
		 * firstName, lastName, and numItemsBought
		 * 
		 * Creates and fills an array of new Item objects for each customer,
		 * with size the number of items bought,
		 * reading in fields amount and name
		 */
		for (int i = 0; i < customers.length; i++)
		{
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());

			// Customer is a static class, so fields can be accessed without getter methods.
			Item[] itemsBought = customers[i].itemsBought;

			for (int j = 0; j < itemsBought.length; j++)
			{
				itemsBought[j] = new Item(scan.nextInt(), scan.next());
			}
		}

		// Closes the scanner as it is no longer needed
		scan.close();
		
		/* Goes through every item in the store and prints out
		 * how many different customers bought the item as well as
		 * the total quantity of the item bought by all customers.
		 */
		for (int i = 0; i < itemsInStore.length; i++)
		{
			int numCustomers = itemsInStore[i].getNumCustomers(customers);
			
			if (numCustomers == 0)
			{
				System.out.println("No customers bought " + itemsInStore[i].name);
			}
			else
			{
				// Item is a static class, so fields can be accessed without getter methods.
				System.out.println(numCustomers + " customers bought " + 
								   itemsInStore[i].getTotalBought(customers)
								   + " " + itemsInStore[i].name);
			}
		}
	}
	
	// Represents a customer with a first name, last name, the number of items bought,
	// and an array of Item objects representing the items bought by the customer.
	public static class Customer
	{
		private String firstName;

		private String lastName;

		private int numItemsBought;

		private Item[] itemsBought;

		public Customer(String first, String last, int items)
		{
			firstName = first;
			lastName = last;
			numItemsBought = items;
			itemsBought = new Item[numItemsBought];
		}
	}

	// Represents an item with a name and price or an item with a quantity and name.
	public static class Item
	{
		private int amount;

		private String name;

		private double price;

		public Item(String n, double p)
		{
			name = n;
			price = p;
		}

		public Item(int amt, String n)
		{
			amount = amt;
			name = n;
		}
		
		/* Calculates and returns the number of customers who bought this item
		 * by going through each customer's bought items, checking for a match
		 * and a non-zero amount of the item, and adding to a counter.
		 * 
		 * Input: customers, an array of all the Customer objects
		 * Output: the number of customers who bought this item as an integer
		 */
		public int getNumCustomers(Customer[] customers)
		{
			int numCustomers = 0;
			
			for (int i = 0; i < customers.length; i++)
			{
				Item[] itemsBought = customers[i].itemsBought;
				
				for (int j = 0; j < itemsBought.length; j++)
				{
					if (itemsBought[j].name.equals(this.name)
						&& itemsBought[j].amount > 0)
					{
						numCustomers++;
					}
				}
			}
			return numCustomers;
		}
		
		/* Calculates and returns the total quantity of this item bought by all
		 * customers by going through each customer's bought items, checking for
		 * a match, and adding the quantity of the bought item to the total.
		 * 
		 * Input: customers, an array of all the Customer objects
		 * Output: the total quantity of this item bought by all customers as an integer
		 */
		public int getTotalBought(Customer[] customers)
		{
			int totalBought = 0;

			for (int i = 0; i < customers.length; i++)
			{
				Item[] itemsBought = customers[i].itemsBought;
				
				for (int j = 0; j < itemsBought.length; j++)
				{
					if (itemsBought[j].name.equals(this.name))
					{
						totalBought += itemsBought[j].amount;
					}
				}
			}
			return totalBought;
		}
	}
}