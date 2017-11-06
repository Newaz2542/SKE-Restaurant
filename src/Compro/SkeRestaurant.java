package Compro;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for SKE Restaurarnt order taking application. It displays a menu
 * and accepts items to order. When done, it prints a receipt including the
 * order total, and have a little game for user.
 * 
 * @author Vichakorn Yotboonrueang
 */
public class SkeRestaurant {

	// all Variable
	static Random rand = new Random();
	static int r = rand.nextInt(2) + 1;
	static Scanner sc = new Scanner(System.in);

	private static String[] menu;
	private static double[] price;

	private static ArrayList<Double> totalPrice = new ArrayList<Double>();
	private static ArrayList<Integer> totalQuantity = new ArrayList<Integer>();

	// The main process
	static void askChoice(int choice) {

		if (choice > menu.length + 1 || choice <= 0) {
			System.out.print("Don't have on the list,Try again\n");

		} else {
			System.out.print("Enter Quantity: ");
			int quantity = sc.nextInt();
			select(choice, quantity);
		}

	}

	// show the menu
	static void menu() {
		menu = RestaurantManager.getMenuItems();
		price = RestaurantManager.getPrices();
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

	// pick menu here
	static int select(int choice, int quantity) {
		for (int index = 0; index < menu.length; index++) {
			if (choice == index + 1) {
				totalPrice.add(price[index] * quantity);
				totalQuantity.add(quantity);
			}
		}
		return choice;
	}

	// print total price
	static double totalBill() {
		double sumPrice = 0;
		for (double x : totalPrice) {
			sumPrice += x;
		}
		return sumPrice;
	}

	// show bill that total has been discount
//	static void billWingame() {
//		dateAndtime();
//		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
//		if (save == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[0], QPizza, PPizza);
//		}
//		if (save2 == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[1], QChicken, PChicken);
//		}
//		if (save3 == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[2], QCoke, PCoke);
//		}
//		System.out.println("| Game             |         |       50  |");
//		System.out.println("+------------------+---------+-----------+");
//		System.out.println("|              Discount 10 %             |");
//		System.out.println("+------------------+---------+-----------+");
//		totalBill(((PPizza + PChicken + PCoke + 50) * 90) / 100);
//		System.out.println("+------------------+---------+-----------+\n");
//	}

	// show bill that add game price
//	static void billPaypromo() {
//		dateAndtime();
//		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
//		if (save == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[0], QPizza, PPizza);
//		}
//		if (save2 == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[1], QChicken, PChicken);
//		}
//		if (save3 == 1) {
//			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[2], QCoke, PCoke);
//		}
//		System.out.println("| Game             |         |       50  |");
//		System.out.println("+------------------+---------+-----------+");
//		totalBill();
//		System.out.println("+------------------+---------+-----------+\n");
//	}

	// show bill when you select choice p
	static void bill() {
		dateAndtime();
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		for (int i = 0; i < totalQuantity.size(); i++) {
			if (totalQuantity.get(i) < 1) {
				continue;
			} else {
				System.out.printf("| %-17s|%7d  |%9.2f  |\n", menu[i], quantityIfelse(i), totalPrice.get(i));
			}
		}

		System.out.println("+------------------+---------+-----------+");
		System.out.printf(" Total  %.2f  \n", totalBill());
		System.out.println("+------------------+---------+-----------+\n");

	}
	
	static int quantityIfelse(int i) {
		
		if (totalQuantity.get(i) < 1 ) {
			return 0;
		}
		else {
			return totalQuantity.get(i);
		}
	}
	static void dateAndtime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		System.out.printf("\t      %s ", "SKE Restaurant");
		System.out.println("");
		System.out.println("   Date: " + date + "  Time: " + time);
	}

	// said no
//	static void noInput() {
//		System.out.print("\n===== Thank you come again =====");
//	}

	// said yes
//	static void yesInput() {
//		System.out.println("----- Promotion is to play a game -----");
//		System.out.println("  ----- Which hand is the coin in ? -----");
//		System.out.println("(If you win you get discount for 10%)");
//		System.out.println("    ----- Pay 50 bath to play -----");
//		System.out.print("(P)lay or (Q)uit : ");
//		String PorQ = sc.next();
//		if (PorQ.equals("p") || PorQ.equals("P")) {
//			billPaypromo();
//			gameLuncher();
//		} else if (PorQ.equals("q") || PorQ.equals("Q")) {
//			System.out.print("\n===== Thank you come again =====");
//		}
//	}

	// Run game
//	static void gameLuncher() {
//		System.out.println("      ----- Start -----");
//		System.out.println("(1)Left hand or Right hand(2)");
//		System.out.print("Select your hand : ");
//		int game = sc.nextInt();
//		if (game == r) {
//			System.out.println("Correct! You earn discount");
//			System.out.println("      -----------------");
//			billWingame();
//			System.out.print("\n===== Thank you come again =====");
//
//		} else {
//			System.out.println("Ow Try again next time");
//			System.out.println("      -----------------");
//			System.out.print("\n===== Thank you come again =====");
//		}
//
//	}

	// add Promotion of SKE Restaurant
//	static void promotionRunner() {
//		System.out.print("But wait we have a Promotion do you want to know (y/n) : ");
//		ans = sc.next();
//		System.out.println("");
//		if (ans.equals("n") || ans.equals("N")) {
//			noInput();
//		} else if (ans.equals("y") || ans.equals("Y")) {
//			yesInput();
//		}
//	}

	public static void main(String[] args) {
		RestaurantManager.init();
		menu();
		int choice = 0;
		do {
			System.out.print("Enter your Choice: ");
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				askChoice(choice);
			} else {
				String inChoice = sc.next();
				if (inChoice.equalsIgnoreCase("p")) {
					bill();
				}
				if (inChoice.equalsIgnoreCase("q")) {
					System.out.print("Thank you");
					break;
				}

			}
		} while (true);
		//promotionRunner();
	}
}
