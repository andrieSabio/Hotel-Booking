import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Date;

public class Main extends JFrame implements ActionListener {

    private JLabel labelName, labelId;
    private JTextField textName, textId;
    private JButton buttonSubmit;
    private HashMap<String, User> users = new HashMap<>();
    private JPanel panelMain, panelSub;
    private JLabel labelUserChoice;
    private JComboBox<String> userChoice;
    private String[] userOptions = {"Reserve a Room", "Book a Room", "Search the User"};
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
        panelMain.setLayout(new GridLayout(7, 1));

        labelName = new JLabel("Name: ");
        labelId = new JLabel("ID: ");
        textName = new JTextField();
        textId = new JTextField();
        buttonSubmit = new JButton("Submit");
        buttonSubmit.addActionListener(this);

        panelMain.add(labelName);
        panelMain.add(textName);
        panelMain.add(labelId);
        panelMain.add(textId);
        panelMain.add(buttonSubmit);

        add(panelMain, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        }

        if (e.getSource() == buttonConfirm) {
            String date = textDate.getText();
            String until = textUntil.getText();
            String roomNumber = (String) this.roomNumber.getSelectedItem();
            String id = (String) this.userChoice.getSelectedItem();
            if (userChoice.getSelectedItem().equals("Reserve a Room")) {
                // code to reserve a room
            } else if (userChoice.getSelectedItem().equals("Book a Room")) {
                // code to book a room
            } else if (userChoice.getSelectedItem().equals("Search the User")) {
                // code to search for a user
            }
        }

        if (e.getSource() == buttonBack) {
            panelSub.setVisible(false);
            panelMain.setVisible(true);
        }
    }
}

class User {
    private String name;
    private String id;
    private String roomNumber;
    private Date startDate;
    private Date endDate;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }
}