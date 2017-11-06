package Compro;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for reading files from menu.txt
 * and then store it in each variable.
 * 
 * @author Vichakorn Yotboonrueang
 */

public class RestaurantManager {
	
	private static ArrayList<String> isMenu = new ArrayList<String>();
	private static ArrayList<Double> isPrice = new ArrayList<Double>();
	
	static void setMenu(){
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
	
	public static String[] getMenuItems(){
		String[] itemMenu  = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}
	
	public static double[] getPrices(){
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x<priceMenu.length; x++){
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}
	static void init() {
		setMenu();
	}
	


}