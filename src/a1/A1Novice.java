package a1;

import java.util.Scanner;

public class A1Novice
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		// Your code follows here.
		
		int numCustomers = scan.nextInt();
		
		Customer[] customers = new Customer[numCustomers];
		
		for (int i = 0; i < customers.length; i++)
		{
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());
			
			Item[] items = new Item[customers[i].getItemsBought()];
			
			for (int j = 0; j < items.length; j++)
			{
				items[j] = new Item(scan.nextInt(), scan.next(), scan.nextDouble());
			}
			
			String result = String.format("%.2f", getTotal(customers[i], items));
			
			System.out.println(customers[i].getFirstName().charAt(0) + ". " + 
							   customers[i].getLastName() + ": " + result);
		}
		scan.close();
	}
	private static double getTotal(Customer customer, Item[] items)
	{
		double total = 0.0;
		
		for (int i = 0; i < items.length; i++)
		{
			total += items[i].getPrice() * items[i].getAmount();
		}
		return total;
	}
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
		public String getFirstName()
		{
			return firstName;
		}
		public String getLastName()
		{
			return lastName;
		}
		public int getItemsBought()
		{
			return itemsBought;
		}
	}
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
		public int getAmount()
		{
			return amount;
		}
		public String getName()
		{
			return name;
		}
		public double getPrice()
		{
			return price;
		}
	}
}