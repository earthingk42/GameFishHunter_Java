package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import static project.Control.gameover;
import static project.Project.xbase;
import static project.Project.ybase;
import static project.TabBar.control;
import static project.TabBar.ct;
import static project.TabBar.levelup;
import static project.TabBar.upper;

public class Gameplay1 extends JPanel implements MouseMotionListener, Runnable {

    public static boolean win = false;
    String name1, name2;
    private int xset;
    Thread thread = new Thread(this);
    private Image back;
    private Image fish, R, L;
    Fish myfish;
    int total;
    ArrayList<Prey> to = new <Prey>ArrayList();
    boolean gover = false, gpass = false;
    private int typest;

    Gameplay1(Fish p, String b, int t, int h) {
        myfish = p;
        R = p.profileR;
        L = p.profileL;
        fish = R;
        this.typest = h;
        this.xset = myfish.x;
        back = new ImageIcon(this.getClass().getResource(b)).getImage();
        total = t;
        myfish.live = true;
        setLayout(null);
        setVisible(true);
        setCursor(this.getToolkit().createCustomCursor(
                new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
                "null"));
        addMouseMotionListener(this);
        thread.start();
        startgame();
        while (checkfish()) {
            startgame();
        }
        for (int i = 0; i < to.size(); i++) {
            to.get(i).ru.start();
        }

    }

    public void startgame() {
        for (int i = 0; i < total; i++) {
            int r = (int) Math.floor(Math.random() * 3);
            if (this.typest == 1) {
                if (r == 0) {
                    to.add(new RBfish());
                }
                if (r == 1) {
                    to.add(new Catfish());
                }
                if (r == 2) {
                    to.add(new Snkfish());
                }
            } else if (this.typest == 2) {
                if (r == 0) {
                    to.add(new Cartoon());
                }
                if (r == 1) {
                    to.add(new Pompa());
                }
                if (r == 2) {
                    to.add(new Lionfish());
                }
            } else if (this.typest == 3) {
                if (r == 0) {
                    to.add(new Tuna());
                }
                if (r == 1) {
                    to.add(new Marine());
                }
                if (r == 2) {
                    to.add(new Sharke());
                }
            }
        }
    }

    public Prey addfish() {

        int r = (int) Math.floor(Math.random() * 3);
        if (this.typest == 1) {
            if (r == 0) {
                return new RBfish();
            }
            if (r == 1) {
                return new Catfish();
            }
            if (r == 2) {
                return new Snkfish();
            }
        } else if (this.typest == 2) {
            if (r == 0) {
                return new Cartoon();
            }
            if (r == 1) {
                return new Pompa();
            }
            if (r == 2) {
                return new Lionfish();
            }
        } else {
            if (r == 0) {
                return new Tuna();
            }
            if (r == 1) {
                return new Marine();
            }
            if (r == 2) {
                return new Sharke();
            }
        }
        return null;
    }

    public boolean checkfish() {
        for (int i = 0; i < to.size(); i++) {
            if (myfish.level == to.get(i).level) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(back, 0, -10, 1400, 900, this);
        if (gover) {
            g.setColor(Color.red);
            g.setFont(new Font("Castella", 10, 100));
            g.drawString("Game Over", xbase / 4, ybase / 4);
            this.setCursor(null);
            control = false;
            gameover = false;
        }
        if (!gover&&!win) {
            //-------------- Myfish ----------------------//
            g.drawImage(fish, myfish.x - 50, myfish.y - 50, myfish.w, myfish.h, this);
            //--------------- Prey ----------------------//
            for (int i = 0; i < to.size(); i++) {
                g.drawImage(to.get(i).stand, to.get(i).xmove, to.get(i).ymove, to.get(i).w, to.get(i).h, this);
            }
        }
        if (win) {
            g.setColor(Color.red);
            g.setFont(new Font("Castella", 10, 100));
            g.drawString("Victory", (xbase / 4) + 100, ybase / 4);
            this.setCursor(null);
            this.thread.stop();
            control = false;
            gameover = false;
            gover = false;
        }
    }

    public void delfish() {
        for (int i = 0; i < to.size(); i++) {
            if (to.get(i).live == false) {
                to.set(i, addfish());
                to.get(i).ru.start();
                if (checkfish()) {
                    if (myfish.level == 1) {
                        if (this.typest == 1) {
                            to.set(i, new RBfish());
                            to.get(i).ru.start();
                        } else if (this.typest == 2) {
                            to.set(i, new Cartoon());
                            to.get(i).ru.start();
                        } else if (this.typest == 3) {
                            to.set(i, new Tuna());
                            to.get(i);
                        }
                    } else if (myfish.level == 2) {
                        if (this.typest == 1) {
                            to.set(i, new Catfish());
                            to.get(i).ru.start();
                        } else if (this.typest == 2) {
                            to.set(i, new Pompa());
                            to.get(i).ru.start();
                        } else if (this.typest == 3) {
                            to.set(i, new Marine());
                            to.get(i);
                        }
                    } else {
                        if (this.typest == 1) {
                            to.set(i, new Snkfish());
                            to.get(i).ru.start();
                        } else if (this.typest == 2) {
                            to.set(i, new Lionfish());
                            to.get(i).ru.start();
                        } else if (this.typest == 3) {
                            to.set(i, new Sharke());
                            to.get(i);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        myfish.x = e.getX()-50;
        myfish.y = e.getY()-50;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (control) {
            myfish.x = e.getX()-50;
            myfish.y = e.getY()-50;
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            if (control) {
                for (int i = 0; i < to.size(); i++) {
                    myfish.Eat(to.get(i));
                }
            }
            delfish();
            try {
                if (myfish.x > this.xset) {
                    fish = R;
                    this.xset = myfish.x;
                } else if (myfish.x < this.xset) {
                    fish = L;
                    this.xset = myfish.x;
                }
                if (upper) {
                    myfish.up_level();
                    upper = false;
                }
                if (gameover && ct <= 0) {
                    gover = gameover;
                    thread.stop();
                } else if (!myfish.live) {
                    gover = true;
                    thread.stop();
                }
                if (win) {
                    levelup = 1;
                    thread.stop();
                }
                thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
    }
}
