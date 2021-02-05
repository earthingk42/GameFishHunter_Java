package project;

import javax.swing.*;

public class Project extends JFrame {

    public final static int xbase = 1400;
    public final static int ybase = 1000;
    ImageIcon im = new ImageIcon(this.getClass().getResource("Head.png"));

    Project() {
        setTitle("Hunter fish or Prey fish");
        setSize(xbase, ybase);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(im.getImage());
        setVisible(true);
        add(new Control());

    }

    public static void main(String[] args){
        new Project();
    }

}
