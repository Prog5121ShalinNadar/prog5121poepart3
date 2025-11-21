/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.final_part_3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class Login_ClassTest {


         @Test
    public void testCheckUserName_True() {
        //WORKS!!!!!!
        //LoginClass LC = new LoginClass("Java Programming",2,0,3);

         //boolean condition = LB.isAvailable();

        // Assertions.assertTrue(condition);
       /* String userName = "kyl_1";
        boolean expected = true;
        boolean actual = LoginClass.checkUserName(userName);
        Assertions.assertEquals(expected, actual);*/
       boolean condition = Login_Class.checkUserName( "kyl_1");
        Assertions.assertTrue(condition);
    }

    @Test
    public void testCheckUserName_False() {
        //WORKS!!!!!!
       /* String userName = "kyle";
        boolean expected = false;
        boolean actual = LoginClass.checkUserName(userName);
        Assertions.assertEquals(expected, actual);*/
        boolean condition = Login_Class.checkUserName("kyle!!!!!!");
        Assertions.assertFalse(condition);
    }

    @Test
    public void testCheckPasswordComplexity_True() {
        /*String password = "Ch&&sec@ke99!";
        boolean expected = true;
        boolean actual = LoginClass.checkPasswordComplexity(password);
        Assertions.assertEquals(expected, actual);*/
       boolean condition = Login_Class.checkPasswordComplexity("Ch&&sec@ke99!");
       Assertions.assertTrue(condition);


    }

    @Test
    public void testCheckPasswordComplexity_False() {
        //WORKS!!!!!!
        /*String password = "ch&&sec@ke99!";
        boolean expected = false;
        boolean actual = LoginClass.checkPasswordComplexity(password);
        Assertions.assertEquals(expected, actual);*/
         boolean condition = Login_Class.checkPasswordComplexity("password");
        Assertions.assertFalse(condition);
    }

    @Test
    public void testCheckCellPhoneNumber_True() {
        //WORKS!!!!!!
       /* String number = "+27838968976";
        boolean expected = true;
        boolean actual = LoginClass.checkCellPhoneNumber(number);
        Assertions.assertEquals(expected, actual);*/
        boolean condition = Login_Class.checkCellPhoneNumber("+27838968976");
        Assertions.assertTrue(condition);
    }

    @Test
    public void testCheckCellPhoneNumber_False() {
        //WORKS!!!!!!
        /*String number = "08966553";
        boolean expected = false;
        boolean actual = LoginClass.checkCellPhoneNumber(number);
        Assertions.assertEquals(expected, actual);*/
       boolean condition = Login_Class.checkCellPhoneNumber("08966553");
        Assertions.assertFalse(condition); 
    }

    @Test
    public void testRegisterUser() {
     // Valid input
    String userName = "kyl_1";
    String password = "Ch&&sec@ke99!";
    String cellPhoneNumber = "+27838968976";

    String expected = "true";
    String actual = Login_Class.registerUser(userName, password, cellPhoneNumber);

    Assertions.assertEquals(expected, actual); 
    }



     @Test
    public void testLoginUser_True() {
        String registeredUser = "kyl_1";
        String loginUser = "kyl_1";
        Login_Class.name = "Kyle";
        Login_Class.surname = "Smith";

        // Assuming loginUser method returns boolean
        boolean expected = true;
        boolean actual = Login_Class.loginUser(loginUser);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLoginUser_False() {
        String registeredUser = "kyl_1";
        String loginUser = "kyle!!!!!!";
        Login_Class.name = "Kyle";
        Login_Class.surname = "Smith";


    boolean expected = true;
    boolean actual = Login_Class.loginUser(loginUser);


        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatus_Success() {
        String loginUser = "kyl_1";
        String returnLoginUser = "kyl_1";

        Login_Class.registeredPassword = "Ch&&sec@ke99!";
        Login_Class.name = "Kyle";
        Login_Class.surname = "Smith";

        String expected = "Welcome Kyle Smith, it is great to see you again";
        String actual = Login_Class.returnLoginStatus(Login_Class.loginUser);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatus_Fail() {
    String loginUser = "kyl_1";
    String returnLoginUser = "kyl_1";

    Login_Class.registeredPassword = "Ch&&sec@ke99!";
    String enteredPassword = "wrong_password";   

    Login_Class.name = "Kyle";
    Login_Class.surname = "Smith";

    String expected = "User not registered or incorrect username/password!";
    String actual = Login_Class.returnLoginStatus(Login_Class.loginUser);

    Assertions.assertEquals(expected,actual);

 }
}
