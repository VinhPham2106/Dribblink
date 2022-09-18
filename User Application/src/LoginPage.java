import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class LoginPage implements ActionListener {
    public static File fileToSend;
    public static String userID;
    public static FileWriter edit;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login"); //Parameter what the button shows
    JButton resetButton = new JButton("Reset"); //Parameter what the button shows
    JButton leaveButton = new JButton("Leave");
    JTextField userIDField = new JTextField();  //user input
    JPasswordField userPasswordField = new JPasswordField();    //password input

    //creating the different labels that will be added later
    JLabel userIDLabel = new JLabel("userID: ");
    JLabel userPasswordLabel = new JLabel("password: ");
    JLabel messageLabel = new JLabel("");

    //image
    //Turns the JPEG of the logo into a BufferedImage object
    BufferedImage pic = null;
    JLabel label;
    BufferedImage newPic;
    Graphics2D graphics2D;

    HashMap<String, String> logininfo = new HashMap<String, String>();


    LoginPage(HashMap<String, String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;  //copy of hashmap that is globally available.

        //
        try {
            pic = ImageIO.read(new File("src/DribbLink.jpeg"));
            System.out.println("Yay");
        } catch(IOException e) {}
        // Resizes the logo to fit in the JFrame Window
        newPic = new BufferedImage(420, 420, BufferedImage.TYPE_INT_RGB);
        graphics2D = newPic.createGraphics();
        graphics2D.drawImage(pic, 0, 0, 455, 420, null);
        graphics2D.dispose();


        label = new JLabel(new ImageIcon(newPic));


        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);   //creating the bounds of the text field
        messageLabel.setBounds(105, 25, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        leaveButton.setBounds(0, 350, 100, 25);
        leaveButton.setFocusable(false);  //this removes the borders around the buttons
        leaveButton.addActionListener(this);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);  //this removes the borders around the buttons
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);  //this removes the borders around the buttons
        resetButton.addActionListener(this);

        label.setBounds(0, 0, 420, 420);
        label.setVisible(true);

        frame.add(messageLabel);
        frame.add(userIDLabel); //the add function actually creates the labels on the screen.
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(leaveButton);
        frame.add(label);

        //creating the login page
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(420,420));
        frame.setLayout(null);
        frame.setVisible(true); //so we can actually see it


    }

    @Override
    public void actionPerformed(ActionEvent e) {    //handle events with this
        if(e.getSource() == resetButton) {  //everytime the reset button gets clicked
            userIDField.setText("");    //reset the text field like we are clearing it
            userPasswordField.setText("");
            messageLabel.setText("");
        }

        if(e.getSource() == loginButton) {//when you cick the login button to try and sign in
            userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());//retrieve the passowrd, change to string, and store in a string called password

            if(logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    //Create the Request File
                    String requestFile = userID +"Request.txt";
                    //Create the request file to send
                    fileToSend = new File("src/" +requestFile);
                    try{
                        edit = new FileWriter(fileToSend);
                        edit.write(userID);
                    } catch(IOException error){
                        error.printStackTrace();
                    }
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Login Successful");
                    WelcomePage welcomePage = new WelcomePage();
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Incorrect Username");
            }
        } else if (e.getSource() == leaveButton) {
            frame.dispose();
        }
    }
}