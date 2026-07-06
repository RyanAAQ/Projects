package politicalZones;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ZoneFinder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("What State Are You From: ");
        String state = input.next();

        GeoPoliticalZones zone = GeoPoliticalZones.findZone(state);

        if(zone == null){
            System.out.println("State not found!!");
        }
        else{
            System.out.print(state + " belongs to " + zone);
        }
    }
}
