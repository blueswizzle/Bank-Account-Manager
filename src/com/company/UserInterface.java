package com.company;



import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    Admin admin;
    private static final Path NAMES_FOLDER = Path.of(System.getProperty("user.dir"),"Names List");
    private final Scanner scanner;

    public UserInterface(){
        this.admin = new Admin();
        this.scanner = new Scanner(System.in);
    }
    public void start() throws FileNotFoundException {
        readNameFile();
        System.out.println("**Welcome!**");
        while(true){
            System.out.println("-------------------------------------------------");
            System.out.println("\nWhat would you like to do? Enter 0 to exit");
            printOptions();
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 0:
                    break;
                case 1:
                    printAllAccounts();
                    continue;
                case 2:
                    editAnAccount();
                    continue;
                case 3:
                    addNewAccount();
                    continue;
                case 4:
                    searchFirstName();
                    continue;
                case 5:
                    searchLastName();
                    continue;
                default:
                    System.out.println("Not a command!");
                    continue;
            }
            break;
        }

    }
    public void printOptions(){
        System.out.println("1.View All Accounts\n2.Make changes to an Account\n3.Add a new Account\n4.Look up an Account by first name\n5.Look up an Account by last name");
    }
    public void readNameFile() throws FileNotFoundException {
        File file = new File(NAMES_FOLDER + "//names");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String [] parts = sc.nextLine().split(" ");
            if(parts.length < 3){
                admin.addAccount(new Account(parts[1].toUpperCase(Locale.ROOT),"N/A", parts[0]));
            }else{
                admin.addAccount(new Account(parts[1].toUpperCase(Locale.ROOT),parts[2].toUpperCase(Locale.ROOT),parts[0]));
            }
        }
        admin.sortAccountsByFirstName();
    }

    public void printAllAccounts(){
        admin.showAllAccounts();
    }
    public void searchFirstName(){
        System.out.println("Enter the first name:");
        String name = scanner.nextLine();
        System.out.println("Showing all " + name +"\n--------------");
        admin.getByFirstName(name);
    }
    public void searchLastName(){
        System.out.println("Enter the last name:");
        String name = scanner.nextLine();
        System.out.println("Showing all " + name +"\n--------------");
        admin.getByLastName(name);
    }
    public void editAnAccount(){
        int index;
        System.out.println("\nEnter the index of the Account you want to view/make changes to (Range 1-" + admin.accountList.size() + "):");
        index = Integer.parseInt(scanner.nextLine()) -1;
        while(true){
            if(!(index <= admin.accountList.size()) || index < 0 ){
                System.out.println("Index is out of bounds!");
                System.out.println("Enter the index of the Account you want to view/make changes to (Range 1-" + (admin.accountList.size() + "):"));
                index = Integer.parseInt(scanner.nextLine()) -1;
            }else{
               break;
            }
        }
        while(true){
            admin.getAccountInfo(index);
            System.out.println("\nWhat would you like to edit:");
            System.out.println("1.First Name\n2.Last Name\n3.Remaining Balance\n4.Change Person's Gender\n5.Change phone number\n6.Change Login Info\n0.Back to Main Menu");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 0:
                    break;
                case 1:
                    System.out.println("Enter the new first name:");
                    String name = scanner.nextLine();
                    admin.changeFirstName(index,name);
                    continue;
                case 2:
                    System.out.println("Enter the new last name:");
                    String lastName = scanner.nextLine();
                    admin.changeLastName(index,lastName);
                    continue;
                case 3:
                    System.out.println("Enter the amount you want to add or subtract by:");
                    double amount = Double.parseDouble(scanner.nextLine());
                    admin.changeAccountBalance(index, amount);
                    continue;
                case 4:
                    System.out.println("Enter the person's new gender:");
                    String gender = scanner.nextLine();
                    admin.changeGender(index,gender);
                    continue;
                case 5:
                    changeAccountPhoneNumber(index);
                    continue;
                case 6:
                    changeLoginInfo(index);
                    continue;
            }
            admin.sortAccountsByFirstName();
            break;
        }

    }
    public void addNewAccount(){
        System.out.println("Enter the full name of the person with a space:");
        String [] name = scanner.nextLine().split(" ");
        System.out.println("Enter the phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Do you want to set a balance for this person? 1.Yes  2.No");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.println("Enter the balance:");
                double balance = Double.parseDouble(scanner.nextLine());
                admin.addAccount(new Account(name[0].toUpperCase(Locale.ROOT), name[1].toUpperCase(Locale.ROOT),phoneNumber, balance));
            }
            case 2 -> admin.addAccount(new Account(name[0].toUpperCase(Locale.ROOT), name[1].toUpperCase(Locale.ROOT),phoneNumber));
        }
        admin.sortAccountsByFirstName();
    }
    public void changeLoginInfo(Integer index){
        while(true){
            System.out.println("What would you like to change? 1.Username 2.Password (Enter 0 to go back to previous options)");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1:
                    System.out.println("Enter the new username:");
                    String username = scanner.nextLine();
                    admin.changeUserName(username,index);
                    continue;
                case 2:
                    System.out.println("Enter the new password:");
                    String password = scanner.nextLine();
                    admin.changePassword(password,index);
                    continue;
                case 0:
                    break;
                default:
                    System.out.println("Not a command!");
                    continue;
            }
            break;
        }


    }
    public void changeAccountPhoneNumber(Integer index){
        System.out.println("Enter the new phone number:");
        String phoneNumber = scanner.nextLine();
        admin.changePhoneNumber(phoneNumber,index);
    }
}
