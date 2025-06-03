// I didn't learn gui development last year in my classes, so I took the initiative to learn it myself
// and created this application as a bonus for the mini facebook project.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gui {

    private Pd5AnikethBandlamudiMicroFB microFB = new Pd5AnikethBandlamudiMicroFB();
    private JTextField nameField1, nameField2;
    private JTextArea outputArea;

    // constructor for the gui class that sets up the JFrame and its components
    // Swing/JFrame for gui is part of the standard Java library with no external dependencies
    // built in event handling system as well
    public gui() {

        // create a new JFrame with a title and set its size and default close operation
        JFrame frame = new JFrame("MicroFB GUI");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a JPanel with a GridLayout to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        // create text fields for input and a text area for output
        nameField1 = new JTextField(10);
        nameField2 = new JTextField(10);
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // add labels and text fields to the panel
        panel.add(new JLabel("Name 1:"));
        panel.add(nameField1);
        panel.add(new JLabel("Name 2 (if needed):"));
        panel.add(nameField2);

        // create buttons for various actions
        JButton createBtn = new JButton("Create Person");
        JButton friendBtn = new JButton("Make Friends");
        JButton unfriendBtn = new JButton("Unfriend");
        JButton listBtn = new JButton("List Friends");
        JButton checkBtn = new JButton("Check Friendship");

        // add all the buttons to the panel
        panel.add(createBtn);
        panel.add(friendBtn);
        panel.add(unfriendBtn);
        panel.add(listBtn);
        panel.add(checkBtn);
        panel.add(scrollPane);

        // action listener for the create button, creates a new person with the name from the first text field
        createBtn.addActionListener(e -> {
            String name = nameField1.getText().trim();
            microFB.createPerson(name);
            outputArea.append("Created person: " + name + "\n");
        });

        // action listener for the friend button, makes two people friends using names from both text fields
        friendBtn.addActionListener(e -> {
            String name1 = nameField1.getText().trim();
            String name2 = nameField2.getText().trim();
            microFB.makeFriends(name1, name2);
            outputArea.append(name1 + " and " + name2 + " are now friends\n");
        });

        // action listener for the unfriend button, removes friendship between two people
        unfriendBtn.addActionListener(e -> {
            String n1 = nameField1.getText().trim();
            String n2 = nameField2.getText().trim();
            microFB.unfriend(n1, n2);
            outputArea.append("Unfriended: " + n1 + " & " + n2 + "\n");
        });

        // action listener for the list button, displays all friends of a person
        listBtn.addActionListener(e -> {
            String name = nameField1.getText().trim();
            outputArea.append("Listing friends of: " + name + "\n");
            microFB.listFriends(name);
        });

        // action listener for the check button, checks if two people are friends
        checkBtn.addActionListener(e -> {
            String n1 = nameField1.getText().trim();
            String n2 = nameField2.getText().trim();
            outputArea.append("Checking friendship between: " + n1 + " & " + n2 + "\n");
            microFB.checkFriendship(n1, n2);
        });

        // add the panel to the frame and make it visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // main method that creates a new instance of the gui class
    public static void main(String[] args) {
        new gui();
    }
}

// end of program