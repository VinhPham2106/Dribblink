import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WelcomePage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Single or Group: ");
    JButton singleButton = new JButton("Single");
    JButton groupButton = new JButton("Group");
    JButton leaveButton = new JButton("Leave");

    //image
    //Turns the JPEG of the logo into a BufferedImage object
    BufferedImage pic = null;
    JLabel label;
    BufferedImage newPic;
    Graphics2D graphics2D;

    WelcomePage() {

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

        welcomeLabel.setBounds(135, 20, 2000, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 20));

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);  //I dont want a layout manager
        frame.setVisible(true);

        singleButton.setBounds(60, 100, 300, 50);
        singleButton.setFocusable(false);  //this removes the borders around the buttons
        singleButton.addActionListener(this);

        groupButton.setBounds(60, 200, 300, 50);
        groupButton.setFocusable(false);  //this removes the borders around the buttons
        groupButton.addActionListener(this);

        leaveButton.setBounds(0, 350, 100, 25);
        leaveButton.setFocusable(false);  //this removes the borders around the buttons
        leaveButton.addActionListener(this);


        frame.add(singleButton);
        frame.add(groupButton);
        frame.add(leaveButton);
        frame.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == singleButton) {
            Courts courts = new Courts();
            try{
                LoginPage.edit.write("\n1\n5");
                LoginPage.edit.flush();
                LoginPage.edit.close();
            } catch(IOException error){
                error.printStackTrace();
            }
        } else if (e.getSource() == groupButton) {
            CourtsGroups courtsgroups = new CourtsGroups();
        } else if (e.getSource() == leaveButton) {
            frame.dispose();
        }
    }
}
