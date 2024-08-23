package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;

public class Program {
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employees> emp = new ArrayList<>();

        System.out.print("Enter file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            
            String line = br.readLine();
            while(line != null) {
                
                String[] fields = line.split(",");
                emp.add(new Employees(fields[0], fields[1], Double.parseDouble(fields[2])));

                line = br.readLine();
            }

            System.out.print("Set value: ");
            Double value = sc.nextDouble();

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> email = emp.stream().filter(e -> e.getSalary() > value).map(p -> p.getEmail()).sorted(comp).collect(Collectors.toList());

            Double avg = emp.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x, y) -> x + y);

            System.out.println();
            System.out.println("People whose salary is more than " + String.format("%.2f", value) + ": ");
            email.forEach(System.out::println);
            System.out.println();

            System.out.println("/////////////////////////////");

            System.out.println();
            System.out.println("Sum of salary from people whose name starts with 'M': " + avg);

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        sc.close();
    }
}