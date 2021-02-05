package project;

import javax.swing.*;

public class Stage2 extends JPanel {

    Shark sh = new Shark();
    TabBar Tab = new TabBar();
    Gameplay1 ga = new Gameplay1(sh, "Stage2.jpg", 8,2);
    public Stage2() {
        setLayout(null);
        sh.live = true;
        Tab.newset();
        ga.gover= false;
        sh.reset();
        Tab.set("2", 10, 3, 50);      
        Tab.setBounds(0, 0, 1500, 70);
        add(Tab);
        ga.setBounds(0, 70, 1500, 930);
        add(ga);
    }
}
