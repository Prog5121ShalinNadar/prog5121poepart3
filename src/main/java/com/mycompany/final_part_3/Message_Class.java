/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_part_3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class Message_Class {

    static String messageID;
    static String recipientCell;
    static String hash;
    static String content;
    static String result;
    static int totalMessages = 0;
    static int messagenum;
    static String message;
    static String messageLog;
    static int input;

    static String[] sentMessages = new String[250]; // max 250 messages
    static String[] recipients = new String[250];
    static String[] messageIDs = new String[250];
    static String[] messageHashes = new String[250];
    static String[] disregardedMessages = new String[250];

    static String hashToDelete;
    static int totalDisregarded = 0;
    public static final int MAX_MESSAGES = 100; // max messages capacity
    private static Object writer;

    // Constructor
    public Message_Class(String message) {

        this.message = message;
    }


// Generates a 10-digit numeric message ID
    public static boolean checkMessageID() {
    Random r = new Random();
    StringBuilder sb = new StringBuilder();

   // Append 10 random digits
    for (int i = 0; i < 10; i++) {

        sb.append(r.nextInt(10));
    }

    messageID = sb.toString();

   // Validate ID is numeric and 10 digits
    return messageID.length() == 10 ;






}
// Validates the recipient phone number
     public static int checkRecipientCell(String recipientCell) {


    if (recipientCell.length() == 12 && recipientCell.startsWith("+27")) {
    return 1;
    }else{
    return 0;
}}



   public static String[] sentMessage(){

   input = Integer.parseInt (JOptionPane.showInputDialog(null,
   "please Select one of the following options:\n\n "
+  "Option 01: Send Message \n"
+  "Option 02: Disregard Message \n"
+  "Option 03: Store Message to save later",
   "User Menu", JOptionPane.INFORMATION_MESSAGE));


   String result;
    // Menu options
   switch (input) {
case 1:

    // Notify user that they selected to send a message
    JOptionPane.showMessageDialog (null, "Send Message", "Send Message", JOptionPane. INFORMATION_MESSAGE);
    result = "Send Message";


    JOptionPane.showMessageDialog(null,"Proceed to send message","Send Message",  JOptionPane.INFORMATION_MESSAGE);

    break;

case 2:
    // Disregard a message by removing it from temporary storage
     JOptionPane.showMessageDialog(null,"Delete Message", "Disregard Message", JOptionPane.INFORMATION_MESSAGE);


    String messageToDisregard = JOptionPane.showInputDialog(null, "Please Enter the message you want to disregard:");


    if (messageToDisregard == null || messageToDisregard.trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "No message entered. Disregard cancelled.");

    break;
}

    // Save the disregarded message
    disregardedMessages[totalDisregarded] = messageToDisregard.trim();
    totalDisregarded++;

    // Display all disregarded messages in a StringBuilder
    StringBuilder disregardedInfo = new StringBuilder("All Disregarded Messages:\n\n");
    for (int i = 0; i < totalDisregarded; i++) {
    if (disregardedMessages[i] != null) {
    disregardedInfo.append("Message: ").append(disregardedMessages[i]).append("\n\n");
}
}

    JOptionPane.showMessageDialog(null, disregardedInfo.toString(), "Disregarded Messages", JOptionPane.INFORMATION_MESSAGE);
    result = "Disregard Message";

    break;


case 3:

    // Prompt user to store a message for later
    JOptionPane.showMessageDialog(null, "Store message to save later", "Store Message", JOptionPane.INFORMATION_MESSAGE);
    //Prompting user to enter recipient cell
    recipientCell = JOptionPane.showInputDialog("Enter recipient phone number ");
    //Prompting user to enter message
    message = JOptionPane.showInputDialog("Enter the message to store later:");
    //Displays the messageID
    messageID = String.format("%010d", new Random().nextInt(1_000_000_000));
    //Displays the hash created
    hash = createMessageHash(message, messageID);

    String[] storeResult = storeMessage(messageID, recipientCell, message, hash);

    JOptionPane.showMessageDialog(null, String.join("\n", storeResult));
    return storeResult;

    default:
    // Invalid menu selection
    JOptionPane.showMessageDialog(null, "Invalid selection. Try again.");

}   return new String[] { "Invalid option" }; }


  // Creates a unique hash for each message using ID and content
    public static String createMessageHash(String message, String messageID){
        // stringbuilder
    StringBuilder sb = new StringBuilder();


    String firstTwoLetters = messageID.length() >= 2 ? messageID.substring(0, 2).toUpperCase() : "00";
    String formattedNumber = "0";
    messagenum = totalMessages;
    String firstWord = "";
    String lastWord = "";

    if (message != null && !message.trim().isEmpty()) {
    message = message.trim();
    int firstSpace = message.indexOf(' ');
    int lastSpace = message.lastIndexOf(' ');

    if (firstSpace == -1) {

    firstWord = message.toUpperCase();
    lastWord = firstWord;
   }else {
    firstWord = message.substring(0, firstSpace).toUpperCase();
    lastWord = message.substring(lastSpace + 1).toUpperCase();
        }
    }





    sb.append(firstTwoLetters).append(":").append(messagenum).append(":").append(firstWord).append(lastWord);


    return sb.toString();
}

    // Builds a formatted string for a message log
    public static String printMessage(String message, String messageID) {
    StringBuilder sb = new StringBuilder("Message Log:\n\n");
    for (int i = 0; i < totalMessages; i++){



    sb.append("MessageID: ").append(messageID).append("\n");
    sb.append("Message: ").append(message).append("\n");
    sb.append("Message Hash: ").append(createMessageHash(message, messageID)).append("\n");
    sb.append("Recipient Number: ").append(recipientCell).append("\n");
}
    return sb.toString();
 }
    // Returns total number of stored messages
    public static int returnTotalMessages(){
    return totalMessages;
   }





  // Returns details of the most recently sent message
    public static String[] getRecentlySentMessage() {

    if (totalMessages == 0)

    return null;
     int lastIndex = totalMessages - 1;
    return new String[]
     //Returns the users messageID       
{    messageIDs[lastIndex],
     //Returns the users number   
     recipients[lastIndex],
     //Returns the users message
     sentMessages[lastIndex],
     //Returns the users message hash
     messageHashes[lastIndex] };
}


    // Returns a formatted list of all sent messages
    public static String[] getAllSentMessagesReport() {
    if (totalMessages == 0) {
        return new String[] { "No messages sent yet." };
    }

    String[] reports = new String[totalMessages];
    int count = 0;

    //Loop through all the messages and format their information
    for (int i = 0; i < totalMessages; i++) {
        if (sentMessages[i] != null && recipients[i] != null && messageIDs[i] != null && messageHashes[i] != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Sender and Recipient of All Sent Messages").append("\n");
            sb.append("sender: ").append(Final_part_3.cellPhoneNumber != null ? Final_part_3.cellPhoneNumber :"N/A").append("\n");
            sb.append("Message ID: ").append(messageIDs[i]).append("\n");
            sb.append("Recipient: ").append(recipients[i]).append("\n");
            sb.append("Message : ").append(messageHashes[i]).append("\n");
            sb.append("Message hash: ").append(sentMessages[i]).append("\n");
           reports[count] = sb.toString();
            count++;
        }
    }



    return reports;
}


    // Finds and returns the longest message sent
    public static String[] getLongestMessage() {
    if (totalMessages == 0)
        return new String[] { "No messages has been sent yet." };

    int longestIndex = 0;
    int maxLen = 0;
    for (int i = 0; i < totalMessages; i++) {
    if (sentMessages[i] != null && sentMessages[i].length() > maxLen) {
            maxLen = sentMessages[i].length();
            longestIndex = i;
        }
    }

    return new String[] {
        "Longest Message:",
        "Message ID: " + messageIDs[longestIndex],
        "Recipient: " + recipients[longestIndex],
        "Message Hash: " + sentMessages[longestIndex],
        "Message: " + messageHashes[longestIndex]
    };
}


   // Searches messages by ID and returns matched result
    public static String[] searchByMessageID(String searchID) {
    for (int i = 0; i < totalMessages; i++) {
    if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
    return new String[] {
                "Message ID: " + messageIDs[i],
                "Recipient: " + recipients[i],
                "Message: " + messageHashes[i],
                "Message Hash: "+ sentMessages[i]
            };
        }
    }
    return new String[] { "Message ID not found." };
}


    // Searches messages by recipient number and returns all matching entries
    public static String[] searchByRecipient(String recipientNumber) {
    int foundCount = 0;
    String[] tempResults = new String[totalMessages];  // max possible

    //Finds all messages that will match inserted number of recipient
    for (int i = 0; i < totalMessages; i++) {
    if (recipients[i] != null && recipients[i].equals(recipientNumber)) {
        //builds the message searchByRecipient using stringbuilder
            StringBuilder sb = new StringBuilder();
            sb.append("Message ID: ").append(messageIDs[i]).append("\n");
            sb.append("Message: ").append(messageHashes[i]).append("\n");
            sb.append("Message Hash: ").append(sentMessages[i]).append("\n");
            tempResults[foundCount] = sb.toString();
            foundCount++;
        }
    }
    //If nothing matches error message is sent to user
    if (foundCount == 0) {
    return new String[] { "No messages found for recipient: " + recipientNumber };
    }

    // Copy foundCount results into a trimmed array
    String[] results = new String[foundCount];
    System.arraycopy(tempResults, 0, results, 0, foundCount);

    return results;
}




   // Deletes a message by its hash value
    public static String[] deleteMessageByHash(String hashToDelete) {
    if (hashToDelete == null || hashToDelete.trim().isEmpty()) {
        return new String[] { "No hash entered. Deletion cancelled." };
    }

    hashToDelete = hashToDelete.trim();

    for (int i = 0; i < totalMessages; i++) {
        if (messageHashes[i] != null && messageHashes[i].equals(hashToDelete)) {
            String[] deletedInfo = new String[] {
                "Deleted Message:",
                "Message ID: " + messageIDs[i],
                "Recipient: " + recipients[i],
                "Message: " + sentMessages[i],
                "Hash: " + messageHashes[i]
            };

               // Shift remaining messages
            for (int j = i; j < totalMessages - 1; j++) {
                sentMessages[j] = sentMessages[j + 1];
                recipients[j] = recipients[j + 1];
                messageIDs[j] = messageIDs[j + 1];
                messageHashes[j] = messageHashes[j + 1];
            }

            // Clear the last entry
            sentMessages[totalMessages - 1] = null;
            recipients[totalMessages - 1] = null;
            messageIDs[totalMessages - 1] = null;
            messageHashes[totalMessages - 1] = null;

            totalMessages--;

            return deletedInfo;
        }
    }

    return new String[] { "No message found with the entered hash: " + hashToDelete };
}



// Generates a detailed report of all sent messages
    public static String[] getDisplayReport() {


    if (totalMessages == 0) {
        return new String[] { "No messages have been sent yet." };
    }

    String[] reportArray = new String[totalMessages];

    for (int i = 0; i < totalMessages; i++) {
        if (sentMessages[i] != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("Message ID: ").append(messageIDs[i]).append("\n");
            sb.append("Recipient: ").append(recipients[i]).append("\n");
            sb.append("Message Hash: ").append(sentMessages[i]).append("\n");
            sb.append("Message Content: ").append(messageHashes[i]);
            reportArray[i] = sb.toString();
        }
    }

    return reportArray;
}


    // Stores message details in a JSON file
    public static String[] storeMessage(String messageID, String recipientCell, String message, String hash) {
    if (totalMessages < MAX_MESSAGES) {
        sentMessages[totalMessages] = message;
        recipients[totalMessages] = recipientCell;
        messageIDs[totalMessages] = messageID;
        messageHashes[totalMessages] = hash;
        totalMessages++;
    }else {
        JOptionPane.showMessageDialog(null, "Message storage limit reached.", "Storage Full", JOptionPane.ERROR_MESSAGE);
    return new String[] {"Storage limit is reached"};
   }

    // Append to local JSON file
    try (FileWriter writer = new FileWriter("messages.json", true)) {
        String jsonString = String.format(
                "{\"messageID\":\"%s\",\"recipient\":\"%s\",\"messageContent\":\"%s\",\"messageHash\":\"%s\"}%n",
                messageID, recipientCell, message, hash);
        writer.write(jsonString);
    }catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
     return new String[] {"Error saving message"};
    }

     return new String[]{
        "Message Stored Successfully:",
        "Message ID:" + messageID,
        "Recipient Cell:" + recipientCell,
        "Message:" + message,
        "Hash:" + hash
    };
}}
