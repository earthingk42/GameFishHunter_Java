package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static project.Control.gameover;

public class TabBar extends JPanel implements ActionListener {

    private ImageIcon Title = new ImageIcon(this.getClass().getResource("Tabtitle.jpg"));
    public static int Score = 0;
    public static int ate, gg = 100, levelup, ct = 1000;
    public static boolean upper = false;
    private int eated = 0, last;
    private int Prey = 0;
    private int Preyl;
    private int Time = 1000;
    private int lv2, lv3;
    private boolean g2 = true, g3 = true;
    public static boolean control = false;
    public static int addtime = 0;
    private String s = "";
    private JLabel Status = new JLabel("Score: " + Score);
    private JLabel Pr = new JLabel("Hunt for " + Preyl + " prey. Will grow up.");
    private JLabel Pe = new JLabel("Have to hunt " + Prey + " more prey to goal");
    private JLabel st = new JLabel("Stage " + s);
    private JButton Pause = new JButton(new ImageIcon(this.getClass().getResource("puse.jpg")));
    private JButton resume = new JButton(new ImageIcon(this.getClass().getResource("resum.jpg")));
    private JLabel TimeL = new JLabel("Time: " + Time);
    public JButton Back = new JButton(new ImageIcon(this.getClass().getResource("back.jpg")));
    Timer count = new Timer(1000, new Count());

    TabBar() {
        setSize(1500, 70);
        setLayout(null);
        Pr.setBounds(100, 15, 300, 25);
        Pr.setFont(new Font("Castella", Pr.getFont().getStyle(), 15));
        Pr.setForeground(Color.PINK);
        add(Pr);
        Pe.setBounds(100, 35, 300, 25);
        Pe.setFont(new Font("Castella", Pe.getFont().getStyle(), 15));
        Pe.setForeground(Color.yellow);
        add(Pe);
        Status.setBounds(600, 20, 150, 25);
        Status.setFont(new Font("Castella", Status.getFont().getStyle(), 20));
        Status.setForeground(Color.MAGENTA);
        add(Status);
        st.setBounds(450, 20, 100, 25);
        st.setFont(new Font("Castella", st.getFont().getStyle(), 20));
        st.setForeground(Color.black);
        add(st);
        TimeL.setBounds(800, 20, 100, 25);
        TimeL.setForeground(Color.WHITE);
        TimeL.setFont(new Font("Castella", st.getFont().getStyle(), 20));
        add(TimeL);
        Pause.setBounds(1000, 10, 50, 50);
        Pause.addActionListener(this);
        add(Pause);
        resume.setBounds(1100, 10, 50, 50);
        resume.addActionListener(this);
        add(resume);
        Back.setBounds(1200, 10, 50, 50);
        Back.addActionListener(this);
        add(Back);
    }

    public void set(String a, int b, int c, int d) {
        this.s = a;
        this.Prey = b;
        this.last = b;
        this.Time = d;
        this.Preyl = c;
        this.lv2 = c;
        this.lv3 = (c * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Title.getImage(), 0, 0, 1370, 70, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Pause) {
            control = false;
            count.stop();
        } else if (e.getSource() == resume) {
            control = true;
            count.start();
        }
    }

    public static void set(int s) {
        Score += s;
    }

    public void newset() {
        this.g2 = true;
        this.g3 = true;
        gg = 100;
        upper = false;
        gameover = false;
        Score = 0;
        levelup = 1;
    }

    class Count implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Time += addtime;
            addtime = 0;
            Time--;
            Prey -= ate;
            eated = last - Prey;
            if (eated == lv2 && g2 == true) {
                levelup = 2;
                upper = true;
                g2 = false;
            } else if (eated == lv3 && g3 == true) {
                levelup = 3;
                upper = true;
                g3 = false;
            }
            gg = Prey;
            ate = 0;
            TimeL.setText("Time: " + Time);
            st.setText("Stage " + s);
            Status.setText("Score: " + Score);
            Pe.setText("Have to hunt " + Prey + " more prey to goal");
            Pr.setText("Hunt for " + Preyl + " prey. Will grow up.");
            if (gg <= 0) {
                Gameplay1.win = true;
                levelup = 1;
                eated = 0;
                count.stop();
            }
            if (Time <= 10) {
                TimeL.setForeground(Color.red);
                if (Time <= 0) {
                    Time = 0;
                    TimeL.setText("Time: " + Time);
                    gameover = true;
                    levelup = 1;
                    eated = 0;
                    count.stop();
                }

            }
        }

    }
}
