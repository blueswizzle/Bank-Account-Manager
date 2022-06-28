package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Admin {
    public ArrayList<Account> accountList;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public Admin(){
        this.accountList = new ArrayList<>();
    }
    public void addAccount(Account account){
        accountList.add(account);
    }
    public void sortAccountsByFirstName(){
        accountList.sort(Account.COMPARE_BY_FIRSTNAME);
    }

    public void showAllAccounts(){
        int i =1;
        for(Account a: accountList){
            System.out.println("\t\t["+i+"] ---- " + a);
            i++;
        }
    }
    public void getByFirstName(String name){
        for(int i=0; i < accountList.size(); i++){
            if(accountList.get(i).getFirstName().startsWith(name.toUpperCase(Locale.ROOT))) {
                System.out.println("\t\t[" + (i+1) + "] ---- " + accountList.get(i));
                if( i +1 < accountList.size()){
                    if(!accountList.get(i + 1).getFirstName().startsWith(name.toUpperCase(Locale.ROOT))){
                        break;
                    }
                }
            }
        }
    }
    public void getByLastName(String name){
        for(Account a: accountList){
            if(a.getLastName().startsWith(name.toUpperCase(Locale.ROOT))){
                int index = accountList.indexOf(a) + 1;
                System.out.println("\t\t["+ index + "] ---- " + a);
            }
        }
    }
    public void getAccountInfo(int i){
        System.out.println("\t\tViewing " + accountList.get(i).getFirstName()+ " " + accountList.get(i).getLastName() + "'s Account[" + (i + 1)+ "]");
        System.out.println("\t\t-----------------------");
        System.out.println("\t\tAccount ID: " + accountList.get(i).getAccountID());
        System.out.println("\t\tFirst Name: " + accountList.get(i).getFirstName() + "\n\t\tLast Name: " + accountList.get(i).getLastName()
                + "\n\t\tGender: " + accountList.get(i).getGender() + "\n\t\tPhone Number: " + accountList.get(i).getPhoneNumber() +
                "\n\t\tRemaining Balance: $" + df.format(accountList.get(i).getBalance()));
        System.out.println("\t\tLogin Info: " + accountList.get(i).getUserName() + " || " + accountList.get(i).getPassWord());
    }
    public void changeFirstName(Integer i, String newName){
        accountList.get(i).setFirstName(newName.toUpperCase(Locale.ROOT));
    }
    public void changeLastName(Integer i, String newName){
        accountList.get(i).setLastName(newName.toUpperCase(Locale.ROOT));
    }
    public void changeAccountBalance(Integer i, double amount){
        accountList.get(i).addBalance(amount);
    }
    public void changeGender(Integer i, String gender){
        accountList.get(i).setGender(gender.toUpperCase(Locale.ROOT));
    }
    public void changeUserName(String userName, Integer i){
        accountList.get(i).setUserName(userName);
    }
    public void changePassword(String password, Integer i){
        accountList.get(i).setPassWord(password);
    }
    public void changePhoneNumber(String phoneNumber, Integer i){
        accountList.get(i).setPhoneNumber(phoneNumber);
    }
}
