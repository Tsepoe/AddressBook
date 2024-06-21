

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

interface PersonTemplate {
    String name = "";
    int phone = 0;
    String address = "";
    String email = "";
}

class Person implements PersonTemplate {
    String name;
    int phone;
    String address;
    String email;

    public Person(String name, int phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public void addToAddressBook() {
        File addressBook = new File("addressBook.txt");
        
        try {
            addressBook.createNewFile();
            FileWriter addressFile = new FileWriter("addressBook.txt", true);
            addressFile.write(name + "\n" + phone + "\n" + address + "\n" + email + "\n");
            addressFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String searchAddressBook(String criteria) {
        File myFile = new File("addressBook.txt");
        Scanner addressBook = null;
        try {
            addressBook = new Scanner(myFile);
            while (addressBook.hasNextLine()) {
                String currentLine = addressBook.nextLine();
                if (criteria.equals(currentLine)) {
                    // Found the name, now get the phone, address, and email
                    String phone = addressBook.nextLine();
                    String address = addressBook.nextLine();
                    String email = addressBook.nextLine();
                    return "Name: " + currentLine + "\nPhone: " + phone + "\nAddress: " + address + "\nEmail: " + email;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (addressBook != null) {
                addressBook.close();
            }
        }
        return null;
    }
}

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JTextField searchTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        super("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1266, 603);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnEnterInfo = new JButton("Enter User Information");
        btnEnterInfo.setBounds(277, 442, 210, 55);
        contentPane.add(btnEnterInfo);
        
        JButton btnSearchForUser = new JButton("Search For User");
        btnSearchForUser.setBounds(647, 442, 210, 55);
        contentPane.add(btnSearchForUser);
        
        nameTextField = new JTextField();
        nameTextField.setEnabled(false);
        nameTextField.setBounds(625, 50, 232, 19);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);
        nameTextField.setVisible(false);
        
        phoneTextField = new JTextField();
        phoneTextField.setEnabled(false);
        phoneTextField.setColumns(10);
        phoneTextField.setBounds(625, 98, 232, 19);
        contentPane.add(phoneTextField);
        phoneTextField.setVisible(false);
        
        addressTextField = new JTextField();
        addressTextField.setEnabled(false);
        addressTextField.setColumns(10);
        addressTextField.setBounds(625, 146, 232, 19);
        contentPane.add(addressTextField);
        addressTextField.setVisible(false);

        emailTextField = new JTextField();
        emailTextField.setEnabled(false);
        emailTextField.setColumns(10);
        emailTextField.setBounds(625, 194, 232, 19);
        contentPane.add(emailTextField);
        emailTextField.setVisible(false);
        
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblName.setBounds(408, 38, 145, 31);
        contentPane.add(lblName);
        lblName.setVisible(false);
        
        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblPhoneNumber.setBounds(408, 86, 224, 31);
        contentPane.add(lblPhoneNumber);
        lblPhoneNumber.setVisible(false);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblAddress.setBounds(408, 134, 145, 31);
        contentPane.add(lblAddress);
        lblAddress.setVisible(false);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblEmail.setBounds(408, 182, 145, 31);
        contentPane.add(lblEmail);
        lblEmail.setVisible(false);
        
        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.setEnabled(false);
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String phoneString = phoneTextField.getText();
                String address = addressTextField.getText();
                String email = emailTextField.getText();
                
                // Validate input fields
                if (name.isEmpty() || phoneString.isEmpty() || address.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int phone;
                try {
                    phone = Integer.parseInt(phoneString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Phone number must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Person user = new Person(name, phone, address, email);
                user.addToAddressBook();
                JOptionPane.showMessageDialog(null, "User added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                nameTextField.setVisible(false);
                phoneTextField.setVisible(false);
                addressTextField.setVisible(false);
                emailTextField.setVisible(false);
                lblName.setVisible(false);
                lblPhoneNumber.setVisible(false);
                lblAddress.setVisible(false);
                lblEmail.setVisible(false);
                btnSubmit.setVisible(false);
                nameTextField.setEnabled(false);
                phoneTextField.setEnabled(false);
                addressTextField.setEnabled(false);
                emailTextField.setEnabled(false);
                lblName.setEnabled(false);
                lblPhoneNumber.setEnabled(false);
                lblAddress.setEnabled(false);
                lblEmail.setEnabled(false);
                btnSubmit.setEnabled(false);
                btnEnterInfo.setEnabled(true);
                btnEnterInfo.setVisible(true);
                btnSearchForUser.setEnabled(true);
                btnSearchForUser.setVisible(true);
            }
        });
        btnSubmit.setBounds(503, 242, 210, 55);
        btnSubmit.setVisible(false);
        contentPane.add(btnSubmit);
        
        JLabel lblSearch = new JLabel("Search ");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblSearch.setBounds(425, 303, 232, 49);
        contentPane.add(lblSearch);
        lblSearch.setVisible(false);
        
        searchTextField = new JTextField();
        searchTextField.setEnabled(false);
        searchTextField.setBounds(596, 324, 261, 19);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);
        searchTextField.setVisible(false);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setEnabled(false);
        btnSearch.setVisible(false);
        btnSearch.setBounds(513, 400, 139, 32);
        contentPane.add(btnSearch);
        
        JLabel lblFound = new JLabel("");
        lblFound.setHorizontalAlignment(SwingConstants.CENTER);
        lblFound.setFont(new Font("Tahoma", Font.PLAIN, 44));
        lblFound.setBounds(229, 169, 743, 104);
        contentPane.add(lblFound);
        
        btnEnterInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameTextField.setVisible(true);
                phoneTextField.setVisible(true);
                addressTextField.setVisible(true);
                emailTextField.setVisible(true);
                lblName.setVisible(true);
                lblPhoneNumber.setVisible(true);
                lblAddress.setVisible(true);
                lblEmail.setVisible(true);
                btnSubmit.setVisible(true);
                nameTextField.setEnabled(true);
                phoneTextField.setEnabled(true);
                addressTextField.setEnabled(true);
                emailTextField.setEnabled(true);
                lblName.setEnabled(true);
                lblPhoneNumber.setEnabled(true);
                lblAddress.setEnabled(true);
                lblEmail.setEnabled(true);
                btnSubmit.setEnabled(true);
                btnEnterInfo.setEnabled(false);
                btnEnterInfo.setVisible(false);
                btnSearchForUser.setEnabled(false);
                btnSearchForUser.setVisible(false);
                btnSearch.setVisible(false);
                btnSearch.setEnabled(false);
                lblSearch.setVisible(false);
                searchTextField.setEnabled(false);
                searchTextField.setVisible(false);
                lblFound.setText("");
            }
        });
        
        btnSearchForUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTextField.setVisible(true);
                searchTextField.setEnabled(true);
                nameTextField.setVisible(false);
                phoneTextField.setVisible(false);
                addressTextField.setVisible(false);
                emailTextField.setVisible(false);
                lblName.setVisible(false);
                lblPhoneNumber.setVisible(false);
                lblAddress.setVisible(false);
                lblEmail.setVisible(false);
                btnSubmit.setVisible(false);
                nameTextField.setEnabled(false);
                phoneTextField.setEnabled(false);
                addressTextField.setEnabled(false);
                emailTextField.setEnabled(false);
                lblName.setEnabled(false);
                lblPhoneNumber.setEnabled(false);
                lblAddress.setEnabled(false);
                lblEmail.setEnabled(false);
                btnSubmit.setEnabled(false);
                btnEnterInfo.setEnabled(false);
                btnEnterInfo.setVisible(false);
                btnSearchForUser.setEnabled(false);
                btnSearchForUser.setVisible(false);
                lblSearch.setVisible(true);
                btnSearch.setVisible(true);
                btnSearch.setEnabled(true);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = Person.searchAddressBook(searchTextField.getText());
                if (result != null) {
                    lblFound.setText("Person Found In Address Book");
                    JOptionPane.showMessageDialog(null, result, "Person Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    lblFound.setText("Not Found In Address Book");
                    JOptionPane.showMessageDialog(null, "Person not found in the address book", "Error", JOptionPane.ERROR_MESSAGE);
                }
                btnEnterInfo.setVisible(true);
                btnEnterInfo.setEnabled(true);
            }
        });
    }
}
