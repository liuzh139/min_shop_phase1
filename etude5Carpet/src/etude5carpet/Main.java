/**
 * A class which uses an instance of the class java.util.HashMap to hold capet
 * strips and number of stock and produces a Carpet matching aesthestics
 * criteria specifed.
 *
 * @author Daisy, Lencho, Lennox, Nikhil.
 */
package etude5carpet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author daisy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("Start of main:");
        Map<String, Integer> stock = new HashMap<String, Integer>(); //data field created to store the "stock"

        //Uncomment this section when taking inputs from the command line
       /* int sizeRequired = Integer.parseInt(args[0]);
         Scanner in = new Scanner(System.in);
         while (in.hasNextLine()) {
         if (stock.containsKey(in.nextLine())) {
         stock.put(in.nextLine(), stock.get(in.nextLine()) + 1);
         } else {
         stock.put(in.nextLine(), 1);
         }
         }
         */
        //Uncomment this section when testing inputs from file
        int sizeRequired = 10;
        BufferedReader in = new BufferedReader(new FileReader("/Users/daisy/NetBeansProjects/project-liuzh139/etude5Carpet/src/etude5carpet/t0.txt"));

        while (in.ready()) {
            String l = in.readLine();
            System.out.println(l);
            if (stock.containsKey(l)) {
                stock.put(l, stock.get(l) + 1);
            } else {
                stock.put(l, 1);
            }
            System.out.println("One iteration!");
        }

        //sorting the map according to the storage
        ValueComparator bvc = new ValueComparator(stock);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);

        System.out.println(makeNoMatchCarpet(sizeRequired, (TreeMap<String, Integer>) sorted_map));
        
        System.out.println("End of excution!");
        /*switch (args[1]) {
         case "-n":
         makeNoMatchCarpet(sizeRequired, (TreeMap<String, Integer>) sorted_map);
         break;
         case "-m":
         makeMaxMatchCarpet(sizeRequired, (HashMap<String, Integer>) stock);
         break;
         case "-b":
         makeBalancedCarpet(sizeRequired, (HashMap<String, Integer>) stock);
         break;
         default:
         throw new IllegalArgumentException("Bad argument");
         }
         */
    }

    private static String makeNoMatchCarpet(int sizeRequired, TreeMap<String, Integer> stock) {
        int sizeMade = sizeRequired;
        String output = "";
        ArrayList<String> possibleComb = new ArrayList<String>();
        TreeMap<String, Integer> temp = stock;

        while (sizeMade > 0) {
            for (int i = 0; i < temp.size(); i++) {
                String currentKey = temp.firstKey(); //get the first key

                for (int j = 0; j < currentKey.length(); j++) {
                    if (currentKey.charAt(j) == temp.lowerKey(currentKey).charAt(j)) {
                        currentKey = temp.lowerKey(currentKey);
                    }
                }

                while (temp.lowerEntry(currentKey).getValue() > 0) { //make sure there's still storage of that piece
                    temp.put(currentKey, temp.get(currentKey) - 1);//update the 1st carpet stock
                    output += currentKey;
                    sizeMade -= 1;
                    temp.put(temp.lowerKey(currentKey), temp.get(temp.lowerKey(currentKey)) - 1); //update the 2nd carpet stock
                    output += temp.lowerKey(currentKey);
                    sizeMade -= 1;
                }
                temp.remove(temp.lowerKey(currentKey));

                if (temp.get(currentKey) == 0) {
                    temp.remove(currentKey);
                }

            }
        }
        return output;
    }

    private static void makeMaxMatchCarpet(int sizeRequired, HashMap<String, Integer> stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void makeBalancedCarpet(int sizeRequired, HashMap<String, Integer> stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private static class ValueComparator implements Comparator<String> {

        Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> stock) {
            this.base = stock;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
