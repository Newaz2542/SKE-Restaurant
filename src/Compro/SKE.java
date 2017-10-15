package Compro;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

/**
 * Main class for SKE Restaurarnt order taking application. It displays a menu
 * and accepts items to order. When done, it prints a receipt including the
 * order total, and have a little game for user.
 * 
 * @author Vichakorn Yotboonrueang
 */
public class SKE {

	// all Variable
	static Random rand = new Random();
	static int r = rand.nextInt(2) + 1;
	static Scanner sc = new Scanner(System.in);
	static LocalDate date = LocalDate.now();
	static LocalTime time = LocalTime.now();
	static int choice;
	static int quantity;
	static int PPizza;
	static int PChicken;
	static int PCoke;
	static int QPizza;
	static int QChicken;
	static int QCoke;
	static int save;
	static int save2;
	static int save3;
	static String ans;
	static String[] menu = { "Pizza", "Chicken", "Coke", "Total", "Exit" };
	static int[] price = { 250, 120, 45 };

	// The main process
	static void askChoice() {
		if (choice != 4) {
			System.out.print("Enter Quantity: ");
			quantity = sc.nextInt();
		}
		select(choice, quantity);
	}

	// show the menu
	static void menu() {
		System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
		int index = 0;
		for (int round = 0; round < price.length; round++) {
			System.out.printf("[%d] %s\t\t ", round + 1, menu[round]);
			if (index < price.length) {
				System.out.printf("%5d Baht", price[round]);
			}
			System.out.println("");
			index++;
		}
		System.out.println("");
		System.out.printf("[p] %s\t\t \n", menu[3]);
		System.out.printf("[q] %s\t\t \n", menu[4]);

	}

	// pick menu here
	static int select(int choice, int quantity) {
		if (choice == 1) {
			QPizza += quantity;
			PPizza = price[0] * QPizza;
			save = 1;
		} else if (choice == 2) {
			QChicken += quantity;
			PChicken = price[1] * QChicken;
			save2 = 1;
		} else if (choice == 3) {
			QCoke += quantity;
			PCoke = price[2] * QCoke;
			save3 = 1;
		} else if (choice > 5 || choice < 0) {
			System.out.println("Invalid select!!\n");

		}
		return save | save2 | save3;
	}

	// print total price
	static void totalBill(int total) {
		System.out.printf("| Total                      |   %5d   |\n", (total));
	}

	// show bill that total has been discount
	static void billWingame() {
		dateAndtime();	
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		if (save == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[0], QPizza, PPizza);
		}
		if (save2 == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[1], QChicken, PChicken);
		}
		if (save3 == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[2], QCoke, PCoke);
		}
		System.out.println("| Game             |         |       50  |");
		System.out.println("+------------------+---------+-----------+");
		System.out.println("|              Discount 10 %             |");
		System.out.println("+------------------+---------+-----------+");
		totalBill(((PPizza + PChicken + PCoke + 50) * 90) / 100);
		System.out.println("+------------------+---------+-----------+\n");
	}

	// show bill that add game price
	static void billPaypromo() {
		dateAndtime();	
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		if (save == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[0], QPizza, PPizza);
		}
		if (save2 == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[1], QChicken, PChicken);
		}
		if (save3 == 1) {
			System.out.printf("| %-17s|%7d  |%9d  |\n", menu[2], QCoke, PCoke);
		}
		System.out.println("| Game             |         |       50  |");
		System.out.println("+------------------+---------+-----------+");
		totalBill(PPizza + PChicken + PCoke + 50);
		System.out.println("+------------------+---------+-----------+\n");
	}

	// show bill when you select choice p
	static void bill(String choice) {
		if (choice.equals("p")) {
			dateAndtime();	
			System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
			if (save == 1) {
				System.out.printf("| %-17s|%7d  |%9d  |\n", menu[0], QPizza, PPizza);
			}
			if (save2 == 1) {
				System.out.printf("| %-17s|%7d  |%9d  |\n", menu[1], QChicken, PChicken);
			}
			if (save3 == 1) {
				System.out.printf("| %-17s|%7d  |%9d  |\n", menu[2], QCoke, PCoke);
			}
			System.out.println("+------------------+---------+-----------+");
			totalBill(PPizza + PChicken + PCoke);
			System.out.println("+------------------+---------+-----------+\n");
		}
	}
	
	static void dateAndtime() {
		System.out.printf("\t      %s ","SKE Restaurant");
		System.out.println("");
		System.out.println("   Date: " + date + "  Time: " + time);	
	}
	// said no
	static void noInput() {
		System.out.print("\n===== Thank you come again =====");
	}

	// said yes
	static void yesInput() {
		System.out.println("----- Promotion is to play a game -----");
		System.out.println("  ----- Which hand is the coin in ? -----");
		System.out.println("(If you win you get discount for 10%)");
		System.out.println("    ----- Pay 50 bath to play -----");
		System.out.print("(P)lay or (Q)uit : ");
		String PorQ = sc.next();
		if (PorQ.equals("p") || PorQ.equals("P")) {
			billPaypromo();
			gameLuncher();
		} else if (PorQ.equals("q") || PorQ.equals("Q")) {
			System.out.print("\n===== Thank you come again =====");
		}
	}

	// Run game
	static void gameLuncher() {
		System.out.println("      ----- Start -----");
		System.out.println("(1)Left hand or Right hand(2)");
		System.out.print("Select your hand : ");
		int game = sc.nextInt();
		if (game == r) {
			System.out.println("Correct! You earn discount");
			System.out.println("      -----------------");
			billWingame();
			System.out.print("\n===== Thank you come again =====");

		} else {
			System.out.println("Ow Try again next time");
			System.out.println("      -----------------");
			System.out.print("\n===== Thank you come again =====");
		}

	}

	// add Promotion of SKE Restaurant
	static void promotionRunner() {
		System.out.print("But wait we have a Promotion do you want to know (y/n) : ");
		ans = sc.next();
		System.out.println("");
		if (ans.equals("n") || ans.equals("N")) {
			noInput();
		} else if (ans.equals("y") || ans.equals("Y")) {
			yesInput();
		}
	}

	public static void main(String[] args) {
		menu();
		do {
			System.out.print("Enter your Choice: ");
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				askChoice();
			} else {
				String inChoice = sc.next();
				if (inChoice.equals("p")) {
					bill("p");
				}
				if (inChoice.equals("q")) {
					break;
				}

			}
		} while (true);
		promotionRunner();
	}
}
