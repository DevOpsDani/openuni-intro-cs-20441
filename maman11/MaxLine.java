import java.util.Scanner;

// Daniel Ben Shushan 

// This program accepts three points and finds the greatest distance between two points

public class MaxLine
{
 public static void main (String [] args) {
    Scanner scan = new Scanner (System.in);
    System.out.println("Enter first point coordinates:");
    final int x1 = scan.nextInt();
    final int y1 = scan.nextInt();
    System.out.println ("Enter second point coordinates:");
    final int x2 = scan.nextInt();
    final int y2 = scan.nextInt();
    System.out.println ("Enter third point coordinates:");
    final int x3 = scan.nextInt();
    final int y3 = scan.nextInt();
    scan.close();

    // Calculate distance, try all possible combinations
    double distanceOne = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    double distanceTwo = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    double distanceThree = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

    if (distanceOne >= distanceTwo && distanceOne >= distanceThree)
        System.out.println("Max line created by the following points:(" + x1 + "," + y1 +")" + ",(" + x2 + "," + y2 + ")");

    else if (distanceTwo >= distanceOne && distanceTwo >= distanceThree)
        System.out.println("Max line created by the following points:(" + x2 + "," + y2 +")" + ",(" + x3 + "," + y3 + ")");
    else
        System.out.println("Max line created by the following points:(" + x1 + "," + y1 +")" + ",(" + x3 + "," + y3 + ")");
 } 
} 
