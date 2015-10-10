import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MapParser {
	
	
	/**
	 * Takes in a filename and returns a 2d array of objects from the file
	 * @param filename
	 * @return
	 */
	public static int[][] parseMap(String filename) {
		FileReader reader = null;
		
		ArrayList<int[]> array =  new ArrayList<int[]>();
		
		try {
			reader = new FileReader("res/maps/"+filename);
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate map file: \""+filename+"\"");
			System.exit(0);
		}
		
		Scanner in = new Scanner(reader);
		ArrayList<String> lines = null;
		
		while(in.hasNext()) {
			lines.add(in.nextLine());
		}
		
		int y = 0;
		
		for(String s: lines) {
			int[] row = new int[s.toCharArray().length];
			for(char num: s.toCharArray()) {
				Integer.parseInt(num);
			}
			array.add(s.toCharArray());
			
			y ++;
		}
		
		return null;
	}
	
	
}
