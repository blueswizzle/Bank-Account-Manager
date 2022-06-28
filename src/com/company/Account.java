package com.company;


import java.util.*;


public class Account {

    private String firstName;
    private String lastName;
    private double balance;
    private String phoneNumber;
    private String gender;
    private final Random rand = new Random();
    StringBuilder str = new StringBuilder();
    private final String ID;
    private String userName;
    private String passWord;



    public Account(String fn, String ln, String pn){
        this.firstName = fn;
        this.lastName = ln;
        this.phoneNumber = pn;
        this.balance = rand.nextDouble(9999) + 100.00;
        gender = randomGender();
        this.userName = firstName.toLowerCase(Locale.ROOT) + "_" + lastName.toLowerCase(Locale.ROOT);
        this.passWord = String.valueOf(firstName.toLowerCase(Locale.ROOT).charAt(0)) + String.valueOf(lastName.toLowerCase(Locale.ROOT).charAt(0))
                + String.valueOf(rand.nextInt(1000) + 100);
        ID = createID();
    }
    public Account(String fn, String ln, String pn, double balance){
        this.firstName = fn;
        this.lastName = ln;
        this.phoneNumber = pn;
        this.balance = balance;
        gender = randomGender();
        this.userName = firstName.toLowerCase(Locale.ROOT) + "_" + lastName.toLowerCase(Locale.ROOT);
        this.passWord = String.valueOf(firstName.toLowerCase(Locale.ROOT).charAt(0)) + String.valueOf(lastName.toLowerCase(Locale.ROOT).charAt(0))
                + String.valueOf(rand.nextInt(1000) + 100);
        ID = createID();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void addBalance(double amount){ //   Negative values for amount also work
        this.balance += amount;
    }
    public String randomGender(){
        if(rand.nextBoolean()){
            return "MALE";
        }else{
            return "FEMALE";
        }
    }
    public void setGender(String newGender){
        gender = newGender;
    }
    public String getGender(){
        return gender;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return  passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String createID(){
        for(int i=0; i <2; i++){
            char letter = (char) (rand.nextInt(26) + 'A');
            str.append(letter);
        }
        for(int i =0; i < 7; i++){
            str.append(rand.nextInt(10));
        }
        return str.toString();
    }
    public String getAccountID(){
        return ID;
    }

    public static Comparator<Account> COMPARE_BY_FIRSTNAME = new Comparator<Account>() {
        @Override
        public int compare(Account one, Account other) {
            return one.firstName.compareTo(other.firstName);
        }
    };


    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
