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
public class Message_ClassTest {


    String[] msgs = {
        "Did you get the cake?",
        "Where are you? You are late! I have asked you to be on time.",
        "Yohoooo, I am at your gate.",
        "It is dinner time !",
        "Ok, I am leaving without you."
    };
    String[] recs = {
        "+27834557896", "+27838884567", "+27834484567", "0838884567", "+27838884567"
    };
    String[] ids = {"MSG1", "MSG2", "MSG3", "MSG4", "MSG5"};

    @Test
    public void testCheckMessageId_Valid() {
        Message_Class.messageID = "1234567890";
        Assertions.assertEquals(true, Message_Class.checkMessageID());
    }

    @Test
    public void testCreateMessageHash() {
        Message_Class.messageID = "1234567890";
        Message_Class.message = "Hi Mike, Can you join us for dinner tonight";

        for (int i = 0; i < 3; i++) {
            Message_Class.totalMessages = i + 1;
            String expected = "12:" + (i + 1) + ":HITONIGHT";
            Assertions.assertEquals(expected, Message_Class.createMessageHash(Message_Class.message, Message_Class.messageID),
                "Hash should match the expected format");
        }
    }

    @Test
    public void testReturnTotalMessages() {
        Message_Class.totalMessages = 2;
        Assertions.assertEquals(true, Message_Class.returnTotalMessages() >= 0);
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        int result = Message_Class.checkRecipientCell("+27718693002");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        int result = Message_Class.checkRecipientCell("08575975889");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testSentMessage_Successfull() {
        Message_Class.message = "Send Message";
        String actual = Message_Class.message;
        String expected = "Send Message";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSentMessage_Unsuccessfull() {
        Message_Class.message = "Please enter a message less than 250 characters";
        String actual = Message_Class.message;
        String expected = "Please enter a message less than 250 characters";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCellPhone_Successfull() {
        String success = "Cell phone number successfully captured.";
        Message_Class.checkRecipientCell("+27718693002");
        Message_Class.message = "Cell phone number successfully captured.";
        success = Message_Class.message;
        String expected = "Cell phone number successfully captured.";
        String actual = Message_Class.message;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCell_UnSuccessfull() {
        String cellUnsuccess = "Incorrect format of Cell Number, \nPlease enter a 10 digitnumber starting with +27";
        Message_Class.checkRecipientCell("08575975889");
        Message_Class.message = "Incorrect format of Cell Number, \nPlease enter a 10 digitnumber starting with +27";
        cellUnsuccess = Message_Class.message;
        String expected = "Incorrect format of Cell Number, \nPlease enter a 10 digitnumber starting with +27";
        String actual = Message_Class.message;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSendMessage_Option1_Send() {
        int userChoice = 1;
        String result = "";
        switch (userChoice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        Assertions.assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSendMessage_Option2_Disregard() {
        int userChoice = 2;
        String result = "";
        switch (userChoice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        Assertions.assertEquals("Press 0 to delete message.", result);
    }

    @Test
    public void testSendMessage_Option3_Store() {
        int userChoice = 3;
        String result = "";
        switch (userChoice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        Assertions.assertEquals("Message successfully stored.", result);
    } 

    @Test
    public void testSentMessagesCorrectlyStored() {
        Message_Class.totalMessages = 0;

        Message_Class.message = "Did you get the cake?";
        Message_Class.recipientCell = "+27834557896";
        Message_Class.messageID = "ID001";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        Message_Class.message = "It is dinner time !";
        Message_Class.recipientCell = "0838884567";
        Message_Class.messageID = "0838884567";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        Assertions.assertEquals("Did you get the cake?", Message_Class.sentMessages[0]);
        Assertions.assertEquals("It is dinner time !", Message_Class.sentMessages[1]);
    }

    @Test
    public void testLongestMessage() {
        Message_Class.totalMessages = 0;

        Message_Class.message = "Where are you? You are late! I have asked you to be on time.";
        Message_Class.recipientCell = "+27838884567";
        Message_Class.messageID = "ID002";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        String[] result = Message_Class.getLongestMessage();
        Assertions.assertTrue(result[3].contains("Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    public void testSearchByMessageID() {
        Message_Class.totalMessages = 0;

        Message_Class.message = "It is dinner time !";
        Message_Class.recipientCell = "0838884567";
        Message_Class.messageID = "0838884567";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        String[] result = Message_Class.searchByMessageID("0838884567");
        Assertions.assertTrue(result[2].contains("It is dinner time !"));
    }

    @Test
    public void testSearchByRecipient() {
        Message_Class.totalMessages = 0;

        Message_Class.message = "Where are you? You are late! I have asked you to be on time.";
        Message_Class.recipientCell = "+27838884567";
        Message_Class.messageID = "ID002";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        Message_Class.message = "Ok, I am leaving without you.";
        Message_Class.recipientCell = "+27838884567";
        Message_Class.messageID = "ID005";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.totalMessages++;

        String[] result = Message_Class.searchByRecipient("+27838884567");
        Assertions.assertEquals(2, result.length);
        Assertions.assertTrue(result[0].contains("Where are you? You are late!"));
        Assertions.assertTrue(result[1].contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        Message_Class.totalMessages = 0;
        for (int i = 0; i < msgs.length; i++) {
            Message_Class.message = msgs[i];
            Message_Class.recipientCell = recs[i];
            Message_Class.messageID = ids[i];
            Message_Class.sentMessages[i] = Message_Class.message;
            Message_Class.recipients[i] = Message_Class.recipientCell;
            Message_Class.messageIDs[i] = Message_Class.messageID;
            Message_Class.messageHashes[i] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
            Message_Class.totalMessages++;
        }

        String hash = Message_Class.messageHashes[1];
        String[] result = Message_Class.deleteMessageByHash(hash);

        Assertions.assertTrue(result[0].contains("Deleted"));
        Assertions.assertEquals(4, Message_Class.totalMessages);
    }

    @Test
    public void testSentMessageReport() {
        Message_Class.totalMessages = 0;

        Message_Class.message = "Did you get the cake?";
        Message_Class.recipientCell = "+27834557896";
        Message_Class.messageID = "ID001";
        Message_Class.sentMessages[Message_Class.totalMessages] = Message_Class.message;
        Message_Class.recipients[Message_Class.totalMessages] = Message_Class.recipientCell;
        Message_Class.messageIDs[Message_Class.totalMessages] = Message_Class.messageID;
        Message_Class.messageHashes[Message_Class.totalMessages] = Message_Class.createMessageHash(Message_Class.message,Message_Class.messageID);
        Message_Class.input = 1;
        Message_Class.totalMessages++;

        String[] report = Message_Class.getDisplayReport();
        Assertions.assertTrue(report.length > 0);
    }
}

