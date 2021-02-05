package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static project.Project.xbase;
import static project.Project.ybase;

public class Control extends JPanel implements ActionListener {

    Stage1 s1 = new Stage1();
    Stage2 s2 = new Stage2();
    Stage3 s3 = new Stage3();
    HomePage h = new HomePage();
    StageSele s = new StageSele();
    public static boolean gameover = false;

    public Control() {
        setSize(xbase, ybase);
        setVisible(true);
        setLayout(null);
        h.setBounds(0, 0, xbase, ybase);
        add(h);
        //--------------------- HomePage -----------------------------//
        h.start.addActionListener(this);
        h.Exit.addActionListener(this);
        //--------------------- StageSelect -----------------------------//
        s.Back.addActionListener(this);
        s.St1.addActionListener(this);
        s.St2.addActionListener(this);
        s.St3.addActionListener(this);
        s1.Tab.Back.addActionListener(this);
        s2.Tab.Back.addActionListener(this);
        s3.Tab.Back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == h.start) {
            h.thread.suspend();
            this.remove(h);
            s.setBounds(0, 0, xbase, ybase);
            this.add(s);
            this.validate();
            this.repaint();
        } else if (e.getSource() == h.Exit) {
            System.exit(0);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s.St1) {
            this.remove(s);
            s1 = new Stage1();
            s1.Tab.Back.addActionListener(this);
            s1.setBounds(0, 0, xbase, ybase);
            this.add(s1);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s.St2) {
            this.remove(s);
            s2 = new Stage2();
            s2.Tab.Back.addActionListener(this);
            s2.setBounds(0, 0, xbase, ybase);
            this.add(s2);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s.St3) {
            this.remove(s);
            s3 = new Stage3();
            s3.Tab.Back.addActionListener(this);
            s3.setBounds(0, 0, xbase, ybase);
            this.add(s3);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s.Back) {
            this.remove(s);
            h.setBounds(0, 0, xbase, ybase);
            h.thread.resume();
            add(h);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s1.Tab.Back) {
            s1.ga.thread.stop();
            this.remove(s1);
            s.setBounds(0, 0, xbase, ybase);
            add(s);
            Gameplay1.win = false;
            TabBar.control = false;
            gameover = false;
            s1.Tab.Back.addActionListener(this);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s2.Tab.Back) {
            this.remove(s2);
            s2.ga.thread.stop();
            s.setBounds(0, 0, xbase, ybase);
            add(s);
            Gameplay1.win = false;
            TabBar.control = false;
            gameover = false;
            s2.Tab.Back.addActionListener(this);
            this.validate();
            this.repaint();
        } else if (e.getSource() == s3.Tab.Back) {
            this.remove(s3);
            s3.ga.thread.stop();
            s.setBounds(0, 0, xbase, ybase);
            add(s);
            Gameplay1.win = false;
            TabBar.control = false;
            gameover = false;
            s3.Tab.Back.addActionListener(this);
            this.validate();
            this.repaint();
        }

    }

}
