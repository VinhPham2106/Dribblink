import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class SinglePage implements ActionListener {

    JButton leaveButton = new JButton("Leave");
    int holder = 0;
    Random random = new Random();
    int temp = random.nextInt(10) + 2;
    JFrame frame = new JFrame();
    JLabel queueLabel = new JLabel("");
    //JLabel waitingLabel = new JLabel(temp + " more people until your turn...");

    //image
    //Turns the JPEG of the logo into a BufferedImage object
    BufferedImage pic = null;
    JLabel label;
    BufferedImage newPic;
    Graphics2D graphics2D;
    SinglePage(int temp) {

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

        JButton submitRequest = new JButton("Submit Request");
        submitRequest.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        submitRequest.setBounds(180, 100, 80, 25);
        label = new JLabel(new ImageIcon(newPic));

        label.setBounds(0, 0, 420, 420);
        label.setVisible(true);

        leaveButton.setBounds(0, 350, 100, 25);
        leaveButton.setFocusable(false);  //this removes the borders around the buttons
        leaveButton.addActionListener(this);


        //waitingLabel.setBounds(60, 40, 2000, 35);
        //waitingLabel.setFont(new Font(null, Font.PLAIN,20));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);


        frame.add(submitRequest);
        frame.add(leaveButton);
        frame.add(label);
        frame.setVisible(true); //so we can actually see it
        //Function of the Button
        submitRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(true){
                    //Just testing if code reaches this point
                    System.out.println("I sent the file");
                    try {
                        //Magic to send the file
                        FileInputStream fileInputStream = new FileInputStream(LoginPage.fileToSend.getAbsolutePath());
                        //Establish connection
                        Socket socket = new Socket("localhost", 1234);

                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String fileName = LoginPage.fileToSend.getName();
                        byte[] fileNameBytes = fileName.getBytes();
                        byte[] contentBytes = new byte[(int) LoginPage.fileToSend.length()];
                        fileInputStream.read(contentBytes);
                        dataOutputStream.writeInt(fileNameBytes.length);
                        dataOutputStream.write(fileNameBytes);
                        dataOutputStream.writeInt(contentBytes.length);
                        dataOutputStream.write(contentBytes);
                    } catch(IOException error) {
                        error.printStackTrace();
                    }

                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leaveButton) {
            frame.dispose();
        }
    }
}
