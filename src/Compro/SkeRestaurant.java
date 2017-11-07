package Compro;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Main class for SKE Restaurarnt order taking application. It displays a menu
 * and accepts items to order. When done, it prints a receipt including the
 * order total.
 * 
 * @author Vichakorn Yotboonrueang
 */
public class SkeRestaurant {

	// all Variable
	static Scanner sc = new Scanner(System.in);
	private static RestaurantManager rm;
	private static ArrayList<Integer> itemOrder = new ArrayList<Integer>();
	private static ArrayList<Double> itemPrice = new ArrayList<Double>();

	// show the menu
	@SuppressWarnings("static-access")
	public static void menu() {
		rm = new RestaurantManager();
		String[] menu = rm.getMenuItems();
		double[] price = rm.getPrices();
		System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
		int index = 0;
		for (int round = 0; round < menu.length; round++) {
			System.out.printf("[%d] %s\t\t ", round + 1, menu[round]);
			if (index < menu.length) {
				System.out.printf("%.2f Baht", price[index]);
			}
			System.out.println("");
			index++;
		}
		System.out.println("");
		System.out.printf("[p] %s\t\t \n", "Print Menu");
		System.out.printf("[q] %s\t\t \n", "Exit");

	}

	// The main process
	@SuppressWarnings("static-access")
	static int askChoice(int choiceC) {

		if (choiceC > rm.getMenuItems().length + 1 || choiceC <= 0) {
			System.out.print("Don't have on the list,Try again\n");
		} else {
			System.out.print("Enter Quantity: ");
			int choiceQ = sc.nextInt();
			addOrder(choiceC,choiceQ);
			addPrice(choiceC,choiceQ);
		}
		return choiceC;
	}

	static int addOrder(int choiceC,int choiceQ) {
		int order;
		double[] price = RestaurantManager.getPrices();
		for (int i = 0; i < price.length; i++) {
			if (choiceC == i + 1) {
				order = itemOrder.get(i) + choiceQ;
				itemOrder.add(i, order);
				itemOrder.remove(i + 1);
			}

		}
		return choiceQ;
	}

	static int addPrice(int choiceC,int choiceQ) {
		double totalPrice;
		double[] price = RestaurantManager.getPrices();
		for (int i = 0; i < price.length; i++) {
			if (choiceC == i+1) {
				totalPrice = (price[i]+itemPrice.get(i)) * choiceQ;
				itemPrice.add(i, totalPrice);
				itemPrice.remove(i + 1);
			}
		}
		return choiceQ;
	}

	// show bill when you select choice p
	@SuppressWarnings("static-access")
	static void bill(int choice) {
		String[] menu = rm.getMenuItems();
		double[] price = rm.getPrices();
		dateAndtime();
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		for (int i = 0; i < rm.getMenuItems().length; i++) {
			if (price[i] * itemOrder.get(i) != 0 ) {
				System.out.printf("|%10s   \t|%2d  \t     |%6.2f\t |\n", menu[i], itemOrder.get(i), itemPrice.get(i));
			}
			
			
		}

		System.out.println("+------------------+---------+-----------+");
		System.out.printf(" Total  %.2f  \n", totalBill());
		System.out.println("+------------------+---------+-----------+\n");

	}

	static void dateAndtime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		System.out.printf("\t      %s ", "SKE Restaurant");
		System.out.println("");
		System.out.println("   Date: " + date + "  Time: " + time);
	}

	// print total price
	static double totalBill() {
		double sumPrice = 0;
		for (double x : itemPrice) {
			sumPrice += x;
		}
		return sumPrice;
	}

	static void createList() {
		setOrder();
		setTotalPrice();
	}

	@SuppressWarnings("static-access")
	static ArrayList<Integer> setOrder() {
		itemOrder = rm.getOrder();
		return itemOrder;
	}

	@SuppressWarnings("static-access")
	static ArrayList<Double> setTotalPrice() {
		itemPrice = rm.getTotalPrice();
		return itemPrice;
	}

	public static void main(String[] args) {
		RestaurantManager.init();
		menu();
		createList();
		int choiceC = 0;
		do {
			System.out.print("Enter your Choice: ");
			if (sc.hasNextInt()) {
				choiceC = sc.nextInt();
				askChoice(choiceC);
			} else {
				String inChoice = sc.next();
				if (inChoice.equalsIgnoreCase("p")) {
					bill(choiceC);
				}
				if (inChoice.equalsIgnoreCase("q")) {
					System.out.print("Thank you");
					break;
				}

			}
		} while (true);

	}
}
