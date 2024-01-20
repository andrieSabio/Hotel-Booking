import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Date;

public class Main extends JFrame implements ActionListener {

    private JLabel labelName, labelId, labelSearchName;
    private JTextField textName, textId, textSearchName;
    private JButton buttonSubmit, buttonSearch;
    private HashMap<String, User> users = new HashMap<>();
    private JPanel panelMain, panelSub;
    private JLabel labelUserChoice;
    private JComboBox<String> userChoice;
    private String[] userOptions = {"Reserve a Room", "Book a Room"};
    private JLabel labelDate, labelUntil;
    private JTextField textDate, textUntil;
    private JLabel labelRoomNumber;
    private JComboBox<String> roomNumber;
    private String[] roomNumbers = {"101", "102", "103", "104", "105", "106", "107", "108", "109", "110"};
    private JButton buttonConfirm;
    private JLabel labelUserInfo;
    private JButton buttonBack;

    public Main() {

        setLayout(new BorderLayout());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(8, 1));

        labelName = new JLabel("Name: ");
        labelId = new JLabel("ID: ");
        labelSearchName = new JLabel("Search Name: ");
        textName = new JTextField();
        textId = new JTextField();
        textSearchName = new JTextField();
        buttonSubmit = new JButton("Submit");
        buttonSearch = new JButton("Search");
        buttonSubmit.addActionListener(this);
        buttonSearch.addActionListener(this);

        panelMain.add(labelName);
        panelMain.add(textName);
        panelMain.add(labelId);
        panelMain.add(textId);
        panelMain.add(labelSearchName);
        panelMain.add(textSearchName);
        panelMain.add(buttonSubmit);
        panelMain.add(buttonSearch);

        add(panelMain, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean booked = false;
        if (e.getSource() == buttonSubmit) {
            String name = textName.getText();
            String id = textId.getText();
            users.put(id, new User(name, id));
    
            panelMain.setVisible(false);
    
            panelSub = new JPanel();
            panelSub.setLayout(new GridLayout(8, 1));
    
            labelUserChoice = new JLabel("Choose an option: ");
            userChoice = new JComboBox<>(userOptions);
            userChoice.addActionListener(this);
    
            labelDate = new JLabel("Date: ");
            textDate = new JTextField();
            labelUntil = new JLabel("Until: ");
            textUntil = new JTextField();
    
            labelRoomNumber = new JLabel("Room Number: ");
            roomNumber = new JComboBox<>(roomNumbers);
    
            buttonConfirm = new JButton("Confirm");
            buttonConfirm.addActionListener(this);
    
            labelUserInfo = new JLabel("User Info");
    
            buttonBack = new JButton("Back");
            buttonBack.addActionListener(this);
    
            panelSub.add(labelUserChoice);
            panelSub.add(userChoice);
            panelSub.add(labelDate);
            panelSub.add(textDate);
            panelSub.add(labelUntil);
            panelSub.add(textUntil);
            panelSub.add(labelRoomNumber);
            panelSub.add(roomNumber);
            panelSub.add(buttonConfirm);
            panelSub.add(labelUserInfo);
            panelSub.add(buttonBack);
    
            add(panelSub, BorderLayout.CENTER);
    
            setVisible(true);
        } else if (e.getSource() == userChoice) {
            if (userChoice.getSelectedIndex() == 0) { // Reserve a Room
                labelDate.setVisible(true);
                textDate.setVisible(true);
                labelUntil.setVisible(true);
                textUntil.setVisible(true);
                labelRoomNumber.setVisible(true);
                roomNumber.setVisible(true);
                buttonConfirm.setVisible(true);
                labelUserInfo.setVisible(false);
                buttonBack.setVisible(true);
            } else if (userChoice.getSelectedIndex() == 1) { // Book a Room
                labelDate.setVisible(false);
                textDate.setVisible(false);
                labelUntil.setVisible(false);
                textUntil.setVisible(false);
                labelRoomNumber.setVisible(true);
                roomNumber.setVisible(true);
                buttonConfirm.setVisible(true);
                labelUserInfo.setVisible(false);
                buttonBack.setVisible(true);
            } else if (userChoice.getSelectedIndex() == 2) { // Search the User
                labelDate.setVisible(false);
                textDate.setVisible(false);
                labelUntil.setVisible(false);
                textUntil.setVisible(false);
                labelRoomNumber.setVisible(false);
                roomNumber.setVisible(false);
                buttonConfirm.setVisible(false);
                labelUserInfo.setVisible(true);
                buttonBack.setVisible(true);
            }
        } else if (e.getSource() == buttonConfirm) {
            if (userChoice.getSelectedIndex() == 0) { // Reserve a Room
                String id = textId.getText();
                String date = textDate.getText();
                String until = textUntil.getText();
                String roomNumberText = (String) roomNumber.getSelectedItem();
                // Save the reservation information
                JOptionPane.showMessageDialog(this,"Reservation made by " + users.get(id).name + " for room " + roomNumberText + " from " + date + " to " + until);
            } else if (userChoice.getSelectedIndex() == 1) { // Book a Room
                String id = textId.getText();
                String roomNumberText = (String) roomNumber.getSelectedItem();
                // Save the booking information
                JOptionPane.showMessageDialog(this,"Room " + roomNumberText + " booked by " + users.get(id).name);
                 booked = true;
                
            }
        } else if (e.getSource() == buttonSearch) {
            String searchName = textSearchName.getText().trim();
            
            if (searchName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name to search for.");
                return;
            }
            boolean found = false;
            
            for (User user : users.values()) {
                
                if (user.name.equals(searchName)) {
                    
                    labelUserInfo.setText("User Info: " + user.name + ", " + user.id);
                    if (booked = true) {
                       
                        labelUserInfo.setText(labelUserInfo.getText() + ", Room: " + user.room + ", Price: " + user.price);
                        JOptionPane.showMessageDialog(this,labelUserInfo.getText());
                        throw new NullPointerException();
                    } else {
                        labelUserInfo.setText(labelUserInfo.getText() + ", Room: Not Booked");
                        JOptionPane.showMessageDialog(this,labelUserInfo.getText());
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "No user found with name " + searchName);
            }
        } else if (e.getSource() == buttonBack) {
            panelSub.setVisible(false);
            panelMain.setVisible(true);
        }
    }

    class User {
        
        String name;
        String id;
        String room;
        int price;
     
    
        User(String name, String id) {
            this.name = name;
            this.id = id;
            this.room = null;
            this.price = 1000;
        }
    }
}
