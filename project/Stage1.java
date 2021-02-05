package project;

import javax.swing.*;
import static project.Control.gameover;

public class Stage1 extends JPanel {

    Piranha ph = new Piranha();
    TabBar Tab = new TabBar();
    Gameplay1 ga = new Gameplay1(ph, "Stage1.jpg", 5,1);
    

    public Stage1() {
        gameover = false;
        ga.gover= false;
        ph.reset();
        Tab.newset();
        Tab.set("1", 8, 2, 60);
        setLayout(null);
        Tab.setBounds(0, 0, 1500, 70);
        add(Tab);
        ga.setBounds(0, 70, 1500, 930);
        add(ga);
    }
}
