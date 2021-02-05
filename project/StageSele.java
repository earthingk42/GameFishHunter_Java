package project;

import java.awt.*;
import javax.swing.*;
import static project.Project.xbase;
import static project.Project.ybase;

public class StageSele extends JPanel {

    JButton St1 = new JButton(new ImageIcon(this.getClass().getResource("F1But.jpg")));
    JButton St2 = new JButton(new ImageIcon(this.getClass().getResource("F2But.jpg")));
    JButton St3 = new JButton(new ImageIcon(this.getClass().getResource("F3But.jpg")));
    JButton Back = new JButton(new ImageIcon(this.getClass().getResource("back.jpg")));
    public StageSele() {
        setSize(xbase, ybase);
        setVisible(true);
        setLayout(null);
        St1.setBounds(100, 100, 300, 400);
        add(St1);
        St2.setBounds(500, 100, 300, 400);
        add(St2);
        St3.setBounds(900, 100, 300, 400);
        add(St3);
        Back.setBounds(1100, 600, 50, 50);
        add(Back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon(this.getClass().getResource("background.jpg")).getImage(), 0, -100, 1400, 1100, this);
        g.setColor(Color.red);
        g.setFont(new Font("Castella", 0, 30));
        g.drawString("Easy", 200, 550);
        g.drawString("Medium", 600, 550);
        g.drawString("Hard", 1025, 550);
    }

}
