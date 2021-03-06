package com.deligkarisk;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    static Scanner inputScanner = new Scanner(System.in);
    private static Bank mainBank;


    public static void main(String[] args) {


        System.out.println("Welcome to the banking application.");

        mainBank = new Bank();

        int selection = 0;
        while (selection != -1) {
            System.out.println("Please make your selection below: ");
            int input = inputScanner.nextInt();
            inputScanner.nextLine();

            switch (input) {
                case 1:
                    addBranchToBank();
                    break;
                case 2:
                    addCustomerToBranch();
                    break;
                case 3:
                    addTransaction();
                    break;
                case 4:
                    showCustomers();
                    break;
                case 5:
                    selection = -1;
                    System.out.println("Exiting.");
                    break;

            }
        }
    }

   private static void addBranchToBank() {
       System.out.println("Enter branch name:");
       String branchName = inputScanner.nextLine();
       mainBank.addBranch(branchName);
   }

   private static void addCustomerToBranch() {

       System.out.println("Enter the branch name: ");
       String branchName = inputScanner.nextLine();
       Optional<Branch> optionalBranch = mainBank.findBranch(branchName);

       if (optionalBranch.isEmpty()) {
           System.out.println("No such branch.");
           return ;
       }

       System.out.println("Enter new customer's name:");
       String newCustomerName = inputScanner.nextLine();
       Customer newCustomer = new Customer(newCustomerName);

       System.out.println("Enter initial transaction amount:");
       Double initialTransaction = inputScanner.nextDouble();
       inputScanner.nextLine();

       mainBank.addNewCustomerToBranch(newCustomer, optionalBranch.get(), initialTransaction);
   }

   private static void addTransaction() {
       System.out.println("Enter the branch name: ");
       String branchName = inputScanner.nextLine();
       Optional<Branch> optionalBranch = mainBank.findBranch(branchName);

       if (optionalBranch.isEmpty()) {
           System.out.println("No such branch.");
           return ;
       }

       Branch branch = optionalBranch.get();

       System.out.println("Enter customer's name:");
       String customerName = inputScanner.nextLine();
       Optional<Customer> optionalCustomer =  branch.findCustomer(customerName);

       if (optionalCustomer.isEmpty()) {
           System.out.println("No such customer.");
           return ;
       }

       Customer customer = optionalCustomer.get();

       System.out.println("Enter transaction amount:");
       double transaction = inputScanner.nextDouble();
       inputScanner.nextLine();

       mainBank.addTransaction(customer, branch,transaction );

   }

   public static void showCustomers() {
       System.out.println("Enter the branch name: ");
       String branchName = inputScanner.nextLine();
       Optional<Branch> optionalBranch = mainBank.findBranch(branchName);

       if (optionalBranch.isEmpty()) {
           System.out.println("No such branch.");
           return ;
       }

       Branch branch = optionalBranch.get();

       mainBank.showCustomers(branch, true);
   }
}
