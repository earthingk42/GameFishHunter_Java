package project;

import javax.swing.*;

public class Stage3 extends JPanel {

    Orca or = new Orca();
    TabBar Tab = new TabBar();
    Gameplay1 ga = new Gameplay1(or, "Stage3.jpg", 10, 3);

    public Stage3() {
        setLayout(null);
        or.live = true;
        Tab.newset();
        ga.gover= false;
        or.reset();
        Tab.set("3", 15, 4, 40);
        Tab.setBounds(0, 0, 1500, 70);
        add(Tab);
        ga.setBounds(0, 70, 1500, 930);
        add(ga);
    }
}
