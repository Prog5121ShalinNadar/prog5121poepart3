/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_part_3;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class Login_Class {
     static String name;
    static String surname;
    static String registeredUserName;
    static String registeredPassword;
    static String loginUser;
   static String loginPassword;
   static String CellphoneNumber;

    //validates user name format
         public static boolean checkUserName(String username) {
     if     ( username.contains("_") && username.length() <= 5){
            JOptionPane.showMessageDialog(null, "Username Successfully Captured!", "Username", JOptionPane.INFORMATION_MESSAGE);
    return true;
  } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. It must contain an underscore and be at most 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
   }return false;
}

     //validates password complexity and requirements
     public static boolean checkPasswordComplexity(String PasswordComplexity) {
     if  (PasswordComplexity.length() >= 8 && PasswordComplexity.matches(".*\\d.*")
                && PasswordComplexity.matches(".*[!@#$%^&*()\\-+=<>?/].*")
                && PasswordComplexity.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(null, "Password Successfully Captured!", "Password", JOptionPane.INFORMATION_MESSAGE);
   return true;
     } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. It must contain at least 8 characters, a capital letter, a number, and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
    return false;
        }}


     //checks if the cell phone number is +27 AND has exactly 12 digits
     public static boolean checkCellPhoneNumber(String CellphoneNumber) {
     if  (CellphoneNumber.startsWith("+27") && CellphoneNumber.length() == 12)   {
          JOptionPane.showMessageDialog(null, "Cell phone number successfully added", "Number", JOptionPane.INFORMATION_MESSAGE);
     return true;
} else {
            JOptionPane.showMessageDialog(null, "Phone number format is incorrect. It must start with +27 and have exactly 12 digits.", "Error", JOptionPane.ERROR_MESSAGE);
   return false;
        }}



      // registers the user if all validations pass
      public static String registerUser(String userName, String password, String cellPhoneNumber) {

    if (checkUserName(userName) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellPhoneNumber)) {
     JOptionPane.showMessageDialog(null, "User Registered Successfully", "Registered", JOptionPane.INFORMATION_MESSAGE);


        // Store user details
        registeredUserName = userName;
        registeredPassword = password;

       return "Success";
    } else {
        JOptionPane.showMessageDialog(null, "Registration failed. Please ensure that all details meet the required format!", "Error", JOptionPane.ERROR_MESSAGE);
        return "Failure";
    }
}




   //promps user to enter name and surname after validating login
        public static boolean loginUser(String loginUser) {

    if (loginUser.equals(registeredUserName)) {
        name = JOptionPane.showInputDialog(null, "Please enter your Name:", "Name", JOptionPane.QUESTION_MESSAGE);
        surname = JOptionPane.showInputDialog(null, "Please enter your surname:", "Surname", JOptionPane.QUESTION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Welcome " + name + " " + surname, "Welcome Message", JOptionPane.INFORMATION_MESSAGE);
        return true;
    } else {
        JOptionPane.showMessageDialog(null, "User not registered or incorrect username!", "Login Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}




// returns login status message based on the stored username
   public static String returnLoginStatus(String returnLoginUser) {
    if (returnLoginUser != null && returnLoginUser.equals(registeredUserName)) {
        JOptionPane.showMessageDialog(null, "Welcome " + name + " " + surname + ", it is great to see you again", "Login", JOptionPane.INFORMATION_MESSAGE);
        return "true";
    } else {
        JOptionPane.showMessageDialog(null, "Username incorrect. Please try again.", "Login", JOptionPane.ERROR_MESSAGE);
        return "false";
    }
   }}

