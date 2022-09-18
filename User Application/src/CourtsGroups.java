import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CourtsGroups implements ActionListener {
    JFrame frame = new JFrame();
    JLabel tbt = new JLabel("3v3");
    JLabel fbf = new JLabel("5v5");
    JLabel queue = new JLabel("Queue: ");
    JLabel queue1 = new JLabel("Queue: ");
    JButton button = new JButton("Select");
    JButton button1 = new JButton("Select");
    JButton leaveButton = new JButton("Leave");
    public static String groupSize = "";
    //image
    //Turns the JPEG of the logo into a BufferedImage object
    BufferedImage pic = null;
    JLabel label;
    //ask for group number
    JLabel askForGroupNum;
    JTextField groupNum;
    JButton submitGroupNum;
    BufferedImage newPic;
    Graphics2D graphics2D;

    CourtsGroups() {

        try {
            pic = ImageIO.read(new File("src/DribbLink.jpeg"));
            System.out.println("Yay");
        } catch(IOException e) {}
        // Resizes the logo to fit in the JFrame Window
        newPic = new BufferedImage(420, 420, BufferedImage.TYPE_INT_RGB);
        graphics2D = newPic.createGraphics();
        graphics2D.drawImage(pic, 0, 0, 435, 420, null);
        graphics2D.dispose();


        label = new JLabel(new ImageIcon(newPic));

        label.setBounds(0, 0, 420, 420);
        label.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);

        //ask for group size
        askForGroupNum = new JLabel("Enter Group Number: ");
        askForGroupNum.setBounds(50, 100, 150, 25);
        askForGroupNum.setBackground(Color.WHITE);
        askForGroupNum.setOpaque(true);

        //text field
        groupNum = new JTextField();
        groupNum.setBounds(200, 100, 150, 25);
        groupNum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Submit GroupNum
        submitGroupNum = new JButton("Submit");
        submitGroupNum.setBounds(190, 150, 50, 25);
        submitGroupNum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        leaveButton.setBounds(0, 350, 100, 25);
        leaveButton.setFocusable(false);  //this removes the borders around the buttons
        leaveButton.addActionListener(this);

        frame.add(askForGroupNum);
        frame.add(groupNum);
        frame.add(submitGroupNum);
        frame.add(leaveButton);
        frame.add(label);
        frame.setVisible(true); //so we can actually see it

        submitGroupNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I'm clicked");
                groupSize = groupNum.getText();
                try{
                    LoginPage.edit.write("\n" + groupSize);
                    //Default wanna play 5v5
                    LoginPage.edit.write("\n5");
                    LoginPage.edit.flush();
                    LoginPage.edit.close();
                } catch(IOException error){
                    error.printStackTrace();
                }
                SinglePage singplepage = new SinglePage(5);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            SinglePage singplepage = new SinglePage(3);
        } else if(e.getSource() == button1) {
            SinglePage singplepage = new SinglePage(5);
        } else if (e.getSource() == leaveButton) {
            frame.dispose();
        }
    }
}
