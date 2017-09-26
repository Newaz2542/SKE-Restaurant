package Compro;

import java.util.Scanner;
public class SKE{
	static int choice;static int quantity;
	static int PPizza;static int PChicken;static int PCoke;
	static int QPizza;static int QChicken;static int QCoke;
	static int save;static int save2;static int save3;

	static void menu() {
		System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
		System.out.printf("1.) Pizza\t\t %5d Baht%n", 250);
		System.out.printf("2.) Chicken\t\t %5d Baht%n", 120);
		System.out.printf("3.) Coke\t\t %5d Baht%n", 45);
		System.out.printf("4.) Total%n");
		System.out.printf("5.) Exit%n%n");
	}

	static int menu2(int choice, int quantity) {
		if (choice == 1) {QPizza = quantity;PPizza = 250 * QPizza;save = 1;} 
		else if (choice == 2) {QChicken = quantity;PChicken = 120 * QChicken;save2 = 1;} 
		else if (choice == 3) {QCoke = quantity;PCoke = 45 * QCoke;save3 = 1;}
		return save|save2|save3;
	}

	static void bill (int choice) {
		  if(choice==4)
          {System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
        if(save==1){System.out.printf("| Pizza            |  %5d  |    %5d  |\n",QPizza,PPizza);}
        if(save2==1){System.out.printf("| Chikens          |  %5d  |    %5d  |\n",QChicken,PChicken);}
        if(save3==1){System.out.printf("| Coke             |  %5d  |    %5d  |\n",QCoke,PCoke);}
            System.out.println("+------------------+---------+-----------+");
            System.out.printf("| Total                      |   %5d   |\n",(PPizza+PChicken+PCoke));
            System.out.println("+------------------+---------+-----------+");
            System.out.println("");
          }
	}
		  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		menu();
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.nextInt();if(choice ==5) {break;}
			if(choice != 4) {
			System.out.print("Enter Quantity: ");
			quantity =sc.nextInt();
			}
			menu2(choice,quantity);
			
			bill (choice);
		}while(choice != 5);
		System.out.print("===== Thank you =====");
	}
	}