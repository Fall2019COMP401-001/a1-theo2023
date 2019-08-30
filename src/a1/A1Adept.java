package a1;

import java.util.Scanner;

public class A1Adept
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
		
		// Stores the biggest and smallest spenders into respective Customer variables
		Customer biggest = getBiggestSpender(customers, itemsInStore);
		Customer smallest = getSmallestSpender(customers, itemsInStore);
		
		// Formats the largest, smallest, and average amounts spent to two decimal places
		String bigString = String.format("%.2f", biggest.getAmountSpent(itemsInStore));
		String smallString = String.format("%.2f", smallest.getAmountSpent(itemsInStore));
		String avgString = String.format("%.2f", getAverageAmountSpent(customers, itemsInStore));
		
		System.out.println("Biggest: " + biggest.firstName + " " + biggest.lastName + " (" + bigString + ")");
		System.out.println("Smallest: " + smallest.firstName + " " + smallest.lastName + " (" + smallString + ")");
		System.out.println("Average: " + avgString);
	}

	/* Determines and returns the customer who spent the most amount of
	 * money in the store by comparing the amount spent by each customer.
	 * 
	 * Initially assumes that the first customer spent the most.
	 * 
	 * Input: customers, an array of all the Customer objects
	 * 		  itemsInStore, an array of all Item objects present in the store
	 * Output: the customer who was the biggest spender as a Customer object
	 */
	private static Customer getBiggestSpender(Customer[] customers, Item[] itemsInStore)
	{
		Customer biggest = customers[0];

		for (int i = 1; i < customers.length; i++)
		{
			if (customers[i].getAmountSpent(itemsInStore) > 
				biggest.getAmountSpent(itemsInStore))
			{
				biggest = customers[i];
			}
		}
		return biggest;
	}
	
	/* Determines and returns the customer who spent the least amount of
	 * money in the store by comparing the amount spent by each customer.
	 * 
	 * Initially assumes that the first customer spent the least.
	 * 
	 * Input: customers, an array of all customers in the store as Customer objects
	 * 		  itemsInStore, an array of all items in the store as Item objects
	 * Output: the customer who was the smallest spender as a Customer object
	 */
	private static Customer getSmallestSpender(Customer[] customers, Item[] itemsInStore)
	{
		Customer smallest = customers[0];

		for (int i = 1; i < customers.length; i++)
		{
			if (customers[i].getAmountSpent(itemsInStore) < 
				smallest.getAmountSpent(itemsInStore))
			{
				smallest = customers[i];
			}
		}
		return smallest;
	}
	
	/* Calculates and returns the average amount spent in the store by summing
	 * the total amount of money spent and dividing by the number of customers.
	 * 
	 * Input: customers, an array of all customers in the store as Customer objects
	 * 		  itemsInStore, an array of all items in the store as Item objects
	 * Output: the average amount of money spent in the store as a double
	 */
	private static double getAverageAmountSpent(Customer[] customers, Item[] itemsInStore)
	{
		double total = 0.0;
		
		for (int i = 0; i < customers.length; i++)
		{
			total += customers[i].getAmountSpent(itemsInStore);
		}
		return total / customers.length;
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

		/* Calculates the returns the amount of money spent by this customer by
		 * summing the product of each bought item's amount and its price.
		 * 
		 * Input: itemsInStore, an array of all items in the store as Item objects
		 * Output: the amount of money spent by this customer as a double
		 */
		public double getAmountSpent(Item[] itemsInStore)
		{
			double total = 0.0;
			
			// A nested loop is necessary here because it has to look through
			// both the array of available items and that of bought items
			for (int i = 0; i < itemsInStore.length; i++)
			{
				for (int j = 0; j < itemsBought.length; j++)
				{
					/* Item is also a static class, so fields can be accessed without getter methods.
					 * 
					 * Name of item bought must match that of item available
					 * in order to get the correct price and amount
					 */
					if (itemsInStore[i].name.equals(itemsBought[j].name))
					{
						total += itemsInStore[i].price * itemsBought[j].amount;
					}
				}
			}
			return total;
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
	}
}