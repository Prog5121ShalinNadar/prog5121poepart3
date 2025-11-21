/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.final_part_3;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class Final_part_3 {


             // Static variables to store user data

    static String name;
    static String surname;
    static String registeredUserName;
    static String registeredPassword;
    static String recipientCell;
    static String messageCountInput ;
    static int messageCounter = 0;
    static String messageDetails;
    static String messageContent;
    static String message;
    static String hash;
    public static String cellPhoneNumber;
    public static void main(String[] args) {


        // Prompt user to enter credentials


 String username = JOptionPane.showInputDialog(null, "Please enter your username:", "Username", JOptionPane.QUESTION_MESSAGE);


  String PasswordComplexity = JOptionPane.showInputDialog(null, "Please enter your password:", "Password", JOptionPane.QUESTION_MESSAGE);


  String CellPhoneNumber = JOptionPane.showInputDialog(null, "Please enter your cell phone number:", "Number", JOptionPane.QUESTION_MESSAGE);

             // Register the user
  String registrationResult = Login_Class.registerUser(username, PasswordComplexity,CellPhoneNumber);

               // Prompt for username to login
      String enteredUsername = JOptionPane.showInputDialog(null, "Enter your username for login:", "Login", JOptionPane.QUESTION_MESSAGE);
     boolean loginSuccessful = Login_Class.loginUser(enteredUsername);

// Confirm login with another prompt
     String returnLoginUser = JOptionPane.showInputDialog(null, "Enter username again to confirm login:", "Login Confirmation", JOptionPane.QUESTION_MESSAGE);
     String loginStatus = Login_Class.returnLoginStatus(returnLoginUser);




    //code in part 2



     // Runs continuously until user exits
    while(true){
   JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");


              // Display menu options
   int userInput = Integer.parseInt (JOptionPane.showInputDialog(null,
  "please Select one of the following options: \n\n"
+ "Option 01: Send Messages \n"
+ "option 02: Recently sent message \n"
+ "Option 03: Exit",
"User Menu", JOptionPane.INFORMATION_MESSAGE));

            // Handle user menu selection
switch (userInput) {
case 1:

      // Generate and display a message ID
  Message_Class.checkMessageID();
JOptionPane.showMessageDialog(null, "Generated Message ID: " + Message_Class.messageID);



    // Ask how many messages the user wants to send
  int messageCountInput = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages are you sending?"));

   // Loop until the total sent messages matches the user input
  while (Message_Class.totalMessages < messageCountInput) {




                           // Prompt for recipient cell number and validate it
     String recipientCell = JOptionPane.showInputDialog(null, "Please enter recipient cell phone number:", "Number", JOptionPane.QUESTION_MESSAGE);
      if (Message_Class.checkRecipientCell(recipientCell) == 1) {
           Message_Class.recipientCell = recipientCell;
            JOptionPane.showMessageDialog(null, "Recipient cellphone number added.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect format of number. Please enter a 12-digit number starting with +27.", "Error", JOptionPane.ERROR_MESSAGE);
      continue;
      }

                             // Call sentMessage method (e.g., option menu)
     Message_Class.sentMessage();



                                 // Prompt user to enter message (max 250 characters)
         String message = JOptionPane.showInputDialog(null, "Enter your message which should be less than 250 characters " ,"Message",JOptionPane.QUESTION_MESSAGE);

         // Validate the message input
         while (message == null || message.trim().isEmpty() || (message.length() > 250) ) {

             if (message == null || message.trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Message cannot be empty.");   
           }

       else if(message.length() > 250){
       JOptionPane.showMessageDialog(null, "Message cannot exceed 250 characters.","Error",JOptionPane.ERROR_MESSAGE);       
       }
      JOptionPane.showMessageDialog(null,"Message Sent","Message", JOptionPane.INFORMATION_MESSAGE);
         }


                           // Show and validate message ID again

    Message_Class.checkMessageID();
   JOptionPane.showMessageDialog(null,"Generated Message ID: " + Message_Class.checkMessageID());


      boolean isValid = Message_Class.checkMessageID();

      JOptionPane.showMessageDialog(null,"Is Message ID Valid? " + isValid);




                                 // Increment message count

       Message_Class.totalMessages++;





                            // Create hash from message
    String hash = Message_Class.createMessageHash(message, Message_Class.messageID);
    JOptionPane.showMessageDialog(null,"\nGenerated Hash:"+ hash); 






                            // Print formatted message details
       String outputs = Message_Class.printMessage( message,  Message_Class.messageID);

       JOptionPane.showMessageDialog(null,outputs);




   // Store message in JSON or equivalent storage


     Message_Class.storeMessage(Message_Class.messageID , Message_Class.recipientCell , hash ,message );


  }

    break;


case 2:
      // retrieve the most recienly sent message details
    String[] latest = Message_Class.getRecentlySentMessage();
    for (int i = 0; i < latest.length; i++) {
    if (latest[i] == null || latest[i].trim().isEmpty()) {
        latest[i] = "N/A";
    }
}
    //display the most recent message information
    StringBuilder recentMessage = new StringBuilder();
    recentMessage.append("Recently Sent Message:\n\n");
    recentMessage.append("Message Hash : ").append(latest[2]).append("\n");
    recentMessage.append("Recipient: ").append(latest[1]).append("\n");
    recentMessage.append("Message ID: ").append(latest[0]).append("\n");
    recentMessage.append("Message: ").append(latest.length > 3 && latest[3] !=null ? latest[3] : "N/A").append("\n");

    JOptionPane.showMessageDialog(null, recentMessage.toString(),"Recently Sent Message", JOptionPane.INFORMATION_MESSAGE);

    //displays additional message actions
    String subOption = JOptionPane.showInputDialog(null,
            "Select an action:\n" +
            "1: Display sender & recipient of all sent messages\n" +
            "2: Display longest sent message\n" +
            "3: Search by message ID\n" +
            "4: Search for message sent by particular recipient\n" +
            "5: Delete Message using Hash\n" +
            "6: Display report with full details",
            "Message Actions", JOptionPane.QUESTION_MESSAGE);

        if (subOption == null) {
            JOptionPane.showMessageDialog(null, "No option selected. Returning user to menu.");
            break;
        }

        switch (subOption) {
            case "1":
                   // displays all messages sent

              String[] reports = Message_Class.getAllSentMessagesReport();
              StringBuilder sb = new StringBuilder();
              for (String report : reports) {
               if (report != null) { 
        sb.append(report).append("\n\n");
    }
}     
          //display   
         JOptionPane.showMessageDialog(null, sb.toString(), "All Sent Messages", JOptionPane.INFORMATION_MESSAGE);



           break;


            case "2":
                // display longest message sent
             String[] longest = Message_Class.getLongestMessage(); JOptionPane.showMessageDialog(null, String.join("\n", longest),
                "Longest Message\n", JOptionPane.INFORMATION_MESSAGE);
           break;


            case "3":
                // search for a message by ID
                  String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
            if (searchID != null && !searchID.trim().isEmpty()) {
                String[] foundByID = Message_Class.searchByMessageID(searchID.trim());
                JOptionPane.showMessageDialog(null, String.join("\n", foundByID),
                    "Search by Message ID\n", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No Message ID has been entered.");
            }
           break;


            case "4":
                 // Search for messages by Recipient phone number
            String recipient = JOptionPane.showInputDialog("Please Enter Recipient Phone Number:");
            if (recipient != null && !recipient.trim().isEmpty()) {
                String[] foundByRecipient = Message_Class.searchByRecipient(recipient.trim());
                JOptionPane.showMessageDialog(null, String.join("\n\n", foundByRecipient),
                    "Search by Recipient Number", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No Recipient Number entered.");
            }
                break;


            case "5":
              // delete a message using its hash
             String inputHash = JOptionPane.showInputDialog(null, "Enter the message hash to delete a message:", "Delete Message", JOptionPane.QUESTION_MESSAGE);

    if (inputHash != null) {
        String[] deletedResult = Message_Class.deleteMessageByHash(inputHash);

        StringBuilder bb = new StringBuilder();
        for (String line : deletedResult) {
            bb.append(line).append("\n");
        }

        JOptionPane.showMessageDialog(null, bb.toString(), "Deletion Result:", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "No input provided. Deletion is being cancelled.");
    }
    break;


            case "6":
                //display a report of all sent messages 
               String[] display = Message_Class.getDisplayReport();
              StringBuilder db = new StringBuilder();
              for (String report : display) {
               if (report != null) { 
        db.append(report).append("\n\n");
    }
}     

         JOptionPane.showMessageDialog(null, db.toString(), "All Sent Messages", JOptionPane.INFORMATION_MESSAGE);

                 break;

            default:
                // displays error message for invalid entry
                JOptionPane.showMessageDialog(null, "Invalid option selected.");
                break;
        }
        break;

       case 3:
                //exiting the program with a farewell message
            JOptionPane.showMessageDialog(null,"Thank you for using QuickChat, Exiting the Program",
                    "GoodBye",  JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
                break;

            default:
               // displays error message for invalid entry
            JOptionPane.showMessageDialog(null,"InValid Selection....Please Try Again");
                break;
       }}}}






