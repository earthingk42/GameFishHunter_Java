package project;

import java.awt.Graphics;
import javax.swing.*;
import static project.Project.xbase;

public class HomePage extends JPanel implements Runnable {

    private int x = xbase, y = 50, s = -100, t = 150;
    private int r = 300, re = 450, x1 = Project.xbase, x2 = -100;
    JButton start = new JButton(new ImageIcon(this.getClass().getResource("Start.png")));
    JButton Exit = new JButton(new ImageIcon(this.getClass().getResource("Exit.png")));
    Thread thread = new Thread(this);

    HomePage() {
        setVisible(true);
        setLayout(null);
        thread.start();
        start.setBounds(300, 450, 100, 50);
        add(start);
        Exit.setBounds(1000, 450, 100, 50);
        add(Exit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("background.jpg")).getImage(), 0, -100, 1400, 1100, this);
        g.drawImage(new ImageIcon(this.getClass().getResource("1-1.png")).getImage(), x, y, 300, 200, this);
        g.drawImage(new ImageIcon(this.getClass().getResource("2.png")).getImage(), s, t, 250, 200, this);
        g.drawImage(new ImageIcon(this.getClass().getResource("6.png")).getImage(), x1, re, 400, 200, this);
        g.drawImage(new ImageIcon(this.getClass().getResource("8-1.png")).getImage(), x2, r, 150, 100, this);
        g.drawImage(new ImageIcon(this.getClass().getResource("Head.png")).getImage(), Project.xbase / 5, 20, 800, 400, this);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                if (x2 < -100) {
                    x2 = Project.xbase + 300;
                    r = (int) (Math.random() * 900)+200;
                } else {
                    x2 -= 20;
                }
                if (x < -100) {
                    x = Project.xbase + 300;
                    y = (int) (Math.random() * 800)+200;
                } else {
                    x -= 10;
                }
                if (s > Project.xbase) {
                    s = -100;
                    t = (int) (Math.random() * 800)+200;
                } else {
                    s += 15;
                }
                if (x1 > Project.xbase) {
                    x1 = -100;
                    re = (int) (Math.random() * 900)+200;
                } else {
                    x1 += 25;
                }
                thread.sleep(90);
            } catch (Exception c) {
                System.out.println("Error");
            }
        }
    }
}
