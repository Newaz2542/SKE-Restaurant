package Compro;

import java.util.Scanner;
import java.util.Random;

public class SKE {
	//all Variable
	static Random rand = new Random();
	static int r = rand.nextInt(2)+1 ;
	static Scanner sc = new Scanner(System.in);
	static int choice;static int quantity;
	static int PPizza;static int PChicken;static int PCoke;
	static int QPizza;static int QChicken;static int QCoke;
	static int save;static int save2;static int save3;
	static String ans;
	static String[] menu = {"Pizza","Chicken","Coke","Total","Exit"};
	static int [] price = {250,120,45};
	//The main process
	static void askChoice () {
		if(choice != 4) {
			System.out.print("Enter Quantity: ");
			quantity =sc.nextInt();
			}
			select(choice,quantity);
			bill (choice);
	}
	//show the menu
	static void menu() {
		System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
		int number = 0;
		for (int numMenu = 0 ;numMenu <= 2;numMenu++) {
			number++;
			for(int numPrice = numMenu;numPrice <= numMenu;numPrice++) {
		System.out.printf("%d.) %s\t\t %5d Baht%n",number,menu[numMenu], price[numPrice]);
			}
		}
		System.out.printf("4.) %s%n",menu[3]);
		System.out.printf("5.) %sn%n",menu[4]);
			
		
	}
	//pick menu here
	static int select(int choice, int quantity) {
		if (choice == 1) {QPizza += quantity;PPizza = price[0] * QPizza;save = 1;} 
		else if (choice == 2) {QChicken += quantity;PChicken = price[1] * QChicken;save2 = 1;} 
		else if (choice == 3) {QCoke += quantity;PCoke = price[2] * QCoke;save3 = 1;}
		else if (choice >5 || choice <0){
			System.out.println("Invalid select!!\n");
			
		}
		return save|save2|save3;
	}
	//print total price
	static void totalBill (int total) {
		System.out.printf("| Total                      |   %5d   |\n",(total));
	}
	//show bill that total has been discount
	static void billWingame() {
		  System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
	  if(save==1){System.out.printf("| %s            |  %5d  |    %5d  |\n",menu[0],QPizza,PPizza);}
	  if(save2==1){System.out.printf("| %s          |  %5d  |    %5d  |\n",menu[1],QChicken,PChicken);}
	  if(save3==1){System.out.printf("| %s             |  %5d  |    %5d  |\n",menu[2],QCoke,PCoke);}
		  System.out.println("| Game             |         |       50  |");
		  System.out.println("+------------------+---------+-----------+");
		  System.out.println("|              Discount 10 %             |");
		  System.out.println("+------------------+---------+-----------+");
		  totalBill (((PPizza+PChicken+PCoke+50)*90)/100);
		  System.out.println("+------------------+---------+-----------+\n");
	}
	//show bill that add game price
		  static void billPaypromo() {
			  System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
      if(save==1){System.out.printf("| %s            |  %5d  |    %5d  |\n",menu[0],QPizza,PPizza);}
      if(save2==1){System.out.printf("| %s          |  %5d  |    %5d  |\n",menu[1],QChicken,PChicken);}
      if(save3==1){System.out.printf("| %s             |  %5d  |    %5d  |\n",menu[2],QCoke,PCoke);}
      	  System.out.println("| Game             |         |       50  |");
      	  System.out.println("+------------------+---------+-----------+");
          totalBill (PPizza+PChicken+PCoke+50);
          System.out.println("+------------------+---------+-----------+\n");
	}
	// show bill when you select choice 4
		  static void bill (int choice) {
		  if(choice==4){
			  System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
        if(save ==1){System.out.printf("| %s            |  %5d  |    %5d  |\n",menu[0],QPizza,PPizza);}
        if(save2==1){System.out.printf("| %s          |  %5d  |    %5d  |\n",menu[1],QChicken,PChicken);}
        if(save3==1){System.out.printf("| %s             |  %5d  |    %5d  |\n",menu[2],QCoke,PCoke);}
            System.out.println("+------------------+---------+-----------+");
            totalBill (PPizza+PChicken+PCoke);
            System.out.println("+------------------+---------+-----------+\n");
          }
	}
	// said no
	static void noInput() {
		System.out.print("\n===== Thank you come again =====");
	}
	// said yes	
	static void yesInput() {
		int r;
		System.out.println("----- Promotion is to play a game -----");
		System.out.println("  ----- Which hand is the coin in ? -----");
		System.out.println("(If you win you get discount for 10%)");
		System.out.println("    ----- Pay 50 bath to play -----");
		System.out.print("(P)lay or (Q)uit : ");
		String PorQ = sc.next();
		if (PorQ.equals("p") || PorQ.equals("P")){
			billPaypromo();
			gameLuncher();
		}
		else if (PorQ.equals("q") || PorQ.equals("Q")){
			System.out.print("\n===== Thank you come again =====");
		}
	}
	//Run game
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
	//add Promotion of SKE Restaurant
	static void promotionRunner() {
		System.out.print("But wait we have a Promotion do you want to know (y/n) : ");
		ans =  sc.next();
		System.out.println("");
		if (ans.equals("n")|| ans.equals("N")){noInput();}
		else if (ans.equals("y")|| ans.equals("Y")){yesInput();}
		 }
	
	public static void main(String[] args) {
		menu();
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.nextInt();if(choice ==5) {break;}
			askChoice();
		}while(choice != 5);
		promotionRunner();
	}
	}
