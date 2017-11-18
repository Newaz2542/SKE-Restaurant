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
 * This class is for RestaurantManager
 * 
 * @author Vichakorn Yotboonrueang
 */

public class RestaurantManager {

	private static ArrayList<String> isMenu = new ArrayList<String>();

	private static ArrayList<Double> isPrice = new ArrayList<Double>();

	/**
	 * Read text form menu.txt
	 */

	public static void init() {
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

	/**
	 * Make ArrayList to Array than return.
	 * 
	 * @return
	 */

	public static String[] getMenuItems() {
		String[] itemMenu = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}

	/**
	 * Make ArrayList to Array than return.
	 * 
	 * @return
	 */

	public static double[] getPrices() {
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x < priceMenu.length; x++) {
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}

	/**
	 * create RecordOrder.txt for save log of ske-restaurant.
	 * 
	 * @param allOrder
	 * @throws IOException
	 */

	public static void writeToFile(String allOrder) throws IOException {
		File createTxt = new File("data/RecordOrder.txt");
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

	/**
	 * save the String of sales log before put to RecordOrder.txt
	 * 
	 * @param itemOrder
	 * @return
	 */

	public static String recordOrder(ArrayList<Integer> itemOrder) {
		String[] menu = RestaurantManager.getMenuItems();
		String text = "";

		for (int i = 0; i < menu.length; i++) {
			if (itemOrder.get(i) != 0) {
				text = String.format(text + "%-15s%5d\n", menu[i], itemOrder.get(i));
			}

		}

		return String.format("\nDate: " + LocalDate.now() + "\nTime: " + LocalTime.now() + "\nSales Log is \n%s", text);
	}

}