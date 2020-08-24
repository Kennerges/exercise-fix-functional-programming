package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String filePath = sc.nextLine();
        System.out.print("Enter salary: ");
        Double minimumSalary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                Double salary = Double.parseDouble(fields[2]);
                list.add(new Employee(name, email, salary));
                line = br.readLine();
            }

            List<Employee> newList = list.stream().filter(
                    emp -> emp.getSalary() > minimumSalary).sorted(
                            Comparator.comparing(emp -> emp.getName().toUpperCase())).collect(
                                    Collectors.toList());

            double sum = 0.0;
            System.out.println("Email of people whose salary is more than 2000.00:");
            for (Employee emp : newList) {
                System.out.println(emp.getEmail());
            } list = list.stream().filter(emp -> emp.getName().charAt(0) == 'M').collect(Collectors.toList());
            for (Employee emp : list) {
                sum = sum + emp.getSalary();
            } System.out.println("Sum of salary of people whose name starts with 'M': " + sum);


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        sc.close();

    }
}
