package org.example;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        PolyProcessor processor = new PolyProcessor();
        processor.processExpress(line);
        processor.printDerivative();
    }
}
