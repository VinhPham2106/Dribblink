import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Courts implements ActionListener {
    JFrame frame = new JFrame();
    JLabel tbt = new JLabel("3v3");
    JLabel fbf = new JLabel("5v5");
    JLabel ovo = new JLabel("1v1");
    JButton button = new JButton("Queue");
    JButton button1 = new JButton("Queue");
    JButton button2 = new JButton("Queue");
    JButton leaveButton = new JButton("Leave");

    //image
    //Turns the JPEG of the logo into a BufferedImage object
    BufferedImage pic = null;
    JLabel label;
    BufferedImage newPic;
    Graphics2D graphics2D;

    Courts() {

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
        frame.setVisible(true); //so we can actually see it

        tbt.setBounds(10, 140, 100, 55);
        tbt.setFont(new Font(null, Font.PLAIN, 50));
        Border border = BorderFactory.createLineBorder(Color.BLACK,5);
        tbt.setBorder(border);

        fbf.setBounds(110, 10, 100, 55);
        fbf.setFont(new Font(null, Font.PLAIN, 50));
        Border border1 = BorderFactory.createLineBorder(Color.BLACK,5);
        fbf.setBorder(border);

        ovo.setBounds(315, 140, 100, 55);
        ovo.setFont(new Font(null, Font.PLAIN, 50));
        Border border2 = BorderFactory.createLineBorder(Color.BLACK,5);
        ovo.setBorder(border);

        button.setBounds(10, 200, 100, 30);
        button.setFocusable(false);  //this removes the borders around the buttons
        button.addActionListener(this);

        leaveButton.setBounds(0, 350, 100, 25);
        leaveButton.setFocusable(false);  //this removes the borders around the buttons
        leaveButton.addActionListener(this);

        button1.setBounds(210, 28, 100, 30);
        button1.setFocusable(false);  //this removes the borders around the buttons
        button1.addActionListener(this);

        button2.setBounds(315, 200, 100, 30);
        button2.setFocusable(false);  //this removes the borders around the buttons
        button2.addActionListener(this);

        frame.add(tbt);
        frame.add(fbf);
        frame.add(ovo);
        frame.add(button);
        frame.add(button1);
        frame.add(button2);
        frame.add(leaveButton);
        frame.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            SinglePage singplepage = new SinglePage(3);
        } else if(e.getSource() == button1) {
            SinglePage singplepage = new SinglePage(5);
        } else if (e.getSource() == button2) {
            SinglePage singplepage = new SinglePage(1);
        } else if (e.getSource() == leaveButton) {
            frame.dispose();
        }
    }
}
