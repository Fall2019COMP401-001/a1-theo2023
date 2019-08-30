package a1;

import java.util.Scanner;

public class A1Adept
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		// Your code follows here.

		Item[] itemsInStore = new Item[scan.nextInt()];

		for (int i = 0; i < itemsInStore.length; i++)
		{
			itemsInStore[i] = new Item(scan.next(), scan.nextDouble());
		}

		Customer[] customers = new Customer[scan.nextInt()];

		for (int i = 0; i < customers.length; i++)
		{
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());

			Item[] itemsBought = customers[i].itemsBought;

			for (int j = 0; j < itemsBought.length; j++)
			{
				itemsBought[j] = new Item(scan.nextInt(), scan.next());
			}
		}

		// Closes the scanner as it is no longer needed
		scan.close();
		
		Customer biggest = getBiggestSpender(customers, itemsInStore);
		Customer smallest = getSmallestSpender(customers, itemsInStore);
		
		String bigString = String.format("%.2f", biggest.getAmountSpent(itemsInStore));
		
		// Customer is a static class, so fields can be accessed without getter methods.
		System.out.println("Biggest: " + biggest.firstName + " " + biggest.lastName + " (" + bigString + ")");
		
		String smallString = String.format("%.2f", smallest.getAmountSpent(itemsInStore));
		
		System.out.println("Smallest: " + smallest.firstName + " " + smallest.lastName + " (" + smallString + ")");
		
		String avgString = String.format("%.2f", getAverageAmountSpent(customers, itemsInStore));
		
		System.out.println("Average: " + avgString);
	}

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

	private static double getAverageAmountSpent(Customer[] customers, Item[] itemsInStore)
	{
		double total = 0.0;
		
		for (int i = 0; i < customers.length; i++)
		{
			total += customers[i].getAmountSpent(itemsInStore);
		}
		return total / customers.length;
	}

	// Represents a customer with a first name, last name, and the number of items bought.
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

		public double getAmountSpent(Item[] itemsInStore)
		{
			double total = 0.0;

			for (int i = 0; i < itemsInStore.length; i++)
			{
				for (int j = 0; j < itemsBought.length; j++)
				{
					if (itemsInStore[i].name.equals(itemsBought[j].name))
					{
						total += itemsBought[j].amount * itemsInStore[i].price;
					}
				}
			}
			return total;
		}
	}

	// Represents an item with a name and price, or an item with a quantity and name.
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