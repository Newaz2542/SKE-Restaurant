package Compro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is used for reading files from menu.txt and then store it in each
 * variable.
 * 
 * @author Vichakorn Yotboonrueang
 */

public class RestaurantManager {

	private static ArrayList<String> isMenu = new ArrayList<String>();
	private static ArrayList<Double> isPrice = new ArrayList<Double>();
	private static ArrayList<Integer> isOrder = new ArrayList<Integer>();
	private static ArrayList<Double> isTotalPrice = new ArrayList<Double>();

	static void setMenu() {
		String menuFile = "data/menu.txt";
		ClassLoader loader = RestaurantManager.class.getClassLoader();

		InputStream in = loader.getResourceAsStream(menuFile);
		if (in == null) {
			System.out.println("Could not access file " + menuFile);
			return;
		}

		Scanner scanFile = new Scanner(in);

		while (scanFile.hasNextLine()) {
			String line = scanFile.nextLine();
			if (line.startsWith("#") || line.startsWith(" #") || line.equals("")) {
				continue;
			}

			String[] array = line.trim().split("; ");
			isMenu.add(array[0]);
			isPrice.add(Double.parseDouble(array[1]));
		}
		scanFile.close();
	}

	public static String[] getMenuItems() {
		String[] itemMenu = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}

	public static double[] getPrices() {
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x < priceMenu.length; x++) {
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}

	public static ArrayList<Integer> getOrder() {
		for (int i = 0; i < getMenuItems().length; i++) {
			isOrder.add(0);
		}
		return isOrder;
	}

	public static ArrayList<Double> getTotalPrice() {
		for (int i = 0; i < getMenuItems().length; i++) {
			isTotalPrice.add((double) 0);
		}
		return isTotalPrice;
	}

	public static void writeToFile(String allOrder) throws IOException {
		File createTxt = new File("src/data/RecordOrder.txt");
		FileOutputStream saveLog;
		try {
			saveLog = new FileOutputStream(createTxt, true);
			saveLog.write(allOrder.getBytes());

		} catch (FileNotFoundException ex) {
			System.out.println("Couldn't open file " + createTxt);
			return;
		}
		saveLog.close();

	}

	public static String recordAllOrder(ArrayList<Integer> itemOrder) {
		String[] menu = RestaurantManager.getMenuItems();
		String text = "";

		for (int i = 0; i < menu.length; i++) {
			if (itemOrder.get(i) != 0) {
				text = String.format(text + "%-15s%5d\n", menu[i], itemOrder.get(i));
			}

		}
		
		return String.format("\nDate: " + LocalDate.now() + "\nTime: " + LocalTime.now()
				+ "\nSales Log is \n%s", text);
	}

	static void init() {
		setMenu();
	}

}