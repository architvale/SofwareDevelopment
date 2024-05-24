/*Create a program that converts temperature between Celsius , Fahrenheit , and Kelvin scales. The program should prompt the user to input a temperature value and the original unit of measurement.
 It should convert the temperature into other two units and display the converted values to the user. For example , if the user enters a temperature of 25 degree Celsius , the program should convert it to 
 Fahrenheit and Kelvin , and present the converted values as outputs.*/

import java.util.Scanner;

public class Temperature {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5/9;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return kelvin * 9/5 - 459.67;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();
        System.out.print("Enter the original unit of measurement (Celsius, Fahrenheit, or Kelvin): ");
        String unit = scanner.next().toLowerCase();

        double celsius, fahrenheit, kelvin;

        switch (unit) {
            case "celsius":
                celsius = temperature;
                fahrenheit = celsiusToFahrenheit(celsius);
                kelvin = celsiusToKelvin(celsius);
                break;
            case "fahrenheit":
                fahrenheit = temperature;
                celsius = fahrenheitToCelsius(fahrenheit);
                kelvin = fahrenheitToKelvin(fahrenheit);
                break;
            case "kelvin":
                kelvin = temperature;
                celsius = kelvinToCelsius(kelvin);
                fahrenheit = kelvinToFahrenheit(kelvin);
                break;
            default:
                System.out.println("Invalid unit entered!");
                return;
        }

        System.out.println(temperature + " " + unit.substring(0, 1).toUpperCase() + unit.substring(1) + " is equal to:");
        System.out.printf("%.2f Fahrenheit%n", fahrenheit);
        System.out.printf("%.2f Celsius%n", celsius);
        System.out.printf("%.2f Kelvin%n", kelvin);

        scanner.close();
    }
}
