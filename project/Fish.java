package project;

import java.awt.Image;
import javax.swing.*;
import static project.TabBar.ate;
import static project.TabBar.levelup;

public class Fish {

    boolean live = true;
    int level = 1;
    protected int x = -100, y = 100;
    Image profileL, profileR;
    int w, h;

    Fish() {
    }

    public void setx(int x1) {
        this.x = x1;
    }

    public void sety(int y1) {
        this.y = y1;
    }

    public void reset() {
        this.live = true;
        this.level = 1;
        levelup = 1;
        this.w = 150;
        this.h = 100;
    }

    public void Eat(Prey f) {
        int dis = (int) Math.sqrt((Math.pow(Math.abs((this.x + (this.level * 10)) - (f.xmove + f.level * 10)), 2) + (Math.pow(Math.abs((this.y + (this.level * 10)) - (f.ymove + f.level * 10)), 2))));
        if (this.level >= f.level) {
            if (dis >= 0 && dis <= 33 && f.live) {
                f.live = false;
                TabBar.set(f.upsc());
                ate++;
            }
        }
//        if (this.level >= f.level) {
//            if (((this.x - 50) - f.xmove + (f.level * 10) >= 0 && (this.x - 50) - f.xmove + (f.level * 10) <= 50) && ((this.y - 50) - f.ymove + (f.level * 10) >= 0 && (this.y - 50) - f.ymove + (f.level * 10) <= 50) && f.live) {
//                f.live = false;
//                TabBar.set(f.upsc());
//                ate++;
//            }
//        }
        if (this.level < f.level) {
            if (dis >= 0 && dis <= 33 && f.live && this.live) {
                this.live = false;
            }
        }
    }

    public void up_level() {
        this.level = levelup;
        this.w = 150 + (100 * (levelup - 1));
        this.h = 100 + (100 * (levelup - 1));
    }
}

abstract class Prey extends Fish implements Runnable {

    Thread ru = new Thread(this);
    int xmove = 0, ymove = 0;
    Image stand;
    boolean topoint = false;

    public Prey() {
        int c = (int) (Math.random() * 2);
        if (c == 0) {
            this.xmove = 0;
        } else {
            this.xmove = 1400;
        }
        this.ymove = (int) (Math.ceil(Math.random() * 700) + 100);
        move();
    }

    public void move() {
        setx((int) Math.floor(Math.random() * 1200));
        sety((int) (Math.floor(Math.random() * 700)) + 100);
    }

    public boolean overlap5() {
        if (((x - xmove >= 0) && (x - xmove) <= 5) && ((y - ymove >= 0) && (y - ymove) <= 5)) {
            return true;
        }
        return false;
    }

    public boolean overlap10() {
        if (((x - xmove >= 0) && (x - xmove) <= 10) && ((y - ymove >= 0) && (y - ymove) <= 10)) {
            return true;
        }
        return false;
    }

    public boolean overlap15() {
        if (((x - xmove >= 0) && (x - xmove) <= 15) && ((y - ymove >= 0) && (y - ymove) <= 15)) {
            return true;
        }
        return false;
    }

    abstract int upsc();
}

class Orca extends Fish {

    public Orca() {
        this.level = 1;
        this.w = 150;
        this.h = 100;
        profileR = new ImageIcon(this.getClass().getResource("3.png")).getImage();
        profileL = new ImageIcon(this.getClass().getResource("3-1.png")).getImage();
    }
}

class Shark extends Fish {

    public Shark() {
        this.level = 1;
        this.w = 150;
        this.h = 100;
        profileR = new ImageIcon(this.getClass().getResource("6.png")).getImage();
        profileL = new ImageIcon(this.getClass().getResource("6-1.png")).getImage();
    }
}

class Piranha extends Fish {

    public Piranha() {
        this.level = 1;
        this.w = 150;
        this.h = 100;
        profileR = new ImageIcon(this.getClass().getResource("5.png")).getImage();
        profileL = new ImageIcon(this.getClass().getResource("5-1.png")).getImage();
    }
}

class Sharke extends Prey {

    public Sharke() {
        this.level = 3;
        this.w = 250;
        this.h = 200;
        stand = new ImageIcon(this.getClass().getResource("6.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("6-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap15()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove += 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove -= 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        TabBar.addtime = 10;
        return 100;
    }

}

class Catfish extends Prey {

    public Catfish() {
        this.level = 2;
        this.w = 150;
        this.h = 100;
        stand = new ImageIcon(this.getClass().getResource("14.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("14-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap10()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 10) {
                        xmove += 10;
                    }
                    if (y - ymove < 0 || y - ymove > 10) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 8) {
                        xmove -= 10;
                    }
                    if (y - ymove < 0 || y - ymove > 8) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        TabBar.addtime = 5;
        return 0;
    }
}

class Pompa extends Prey {

    public Pompa() {
        this.level = 2;
        this.w = 150;
        this.h = 100;
        stand = new ImageIcon(this.getClass().getResource("7.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("7-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap10()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 10) {
                        xmove += 10;
                    }
                    if (y - ymove < 0 || y - ymove > 10) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 10) {
                        xmove -= 10;
                    }
                    if (y - ymove < 0 || y - ymove > 10) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 50;
    }
}

class Cartoon extends Prey {

    public Cartoon() {
        this.level = 1;
        this.w = 100;
        this.h = 80;
        stand = new ImageIcon(this.getClass().getResource("2.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("2-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap5()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove += 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove -= 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 10;
    }
}

class Tuna extends Prey {

    public Tuna() {
        this.level = 1;
        this.w = 150;
        this.h = 100;
        stand = new ImageIcon(this.getClass().getResource("1.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("1-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap5()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove += 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove -= 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 10;
    }
}

class Lionfish extends Prey {

    public Lionfish() {
        this.level = 3;
        this.w = 250;
        this.h = 200;
        stand = new ImageIcon(this.getClass().getResource("11.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("11-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap15()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove += 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove -= 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 100;
    }
}

class Marine extends Prey {

    public Marine() {
        this.level = 2;
        this.w = 250;
        this.h = 150;
        stand = new ImageIcon(this.getClass().getResource("12.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("12-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap10()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 10) {
                        xmove += 10;
                    }
                    if (y - ymove < 0 || y - ymove > 10) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 10) {
                        xmove -= 10;
                    }
                    if (y - ymove < 0 || y - ymove > 10) {
                        if (y > ymove) {
                            ymove += 10;
                        } else if (y < ymove) {
                            ymove -= 10;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 50;
    }
}

class Snkfish extends Prey {

    public Snkfish() {
        this.level = 3;
        this.w = 250;
        this.h = 200;
        stand = new ImageIcon(this.getClass().getResource("10.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("10-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap15()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove += 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 15) {
                        xmove -= 15;
                    }
                    if (y - ymove < 0 || y - ymove > 15) {
                        if (y > ymove) {
                            ymove += 15;
                        } else if (y < ymove) {
                            ymove -= 15;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        TabBar.addtime = 10;
        return 100;
    }
}

class RBfish extends Prey {

    public RBfish() {
        this.level = 1;
        this.w = 75;
        this.h = 50;
        stand = new ImageIcon(this.getClass().getResource("13.png")).getImage();
        profileR = stand;
        profileL = new ImageIcon(this.getClass().getResource("13-1.png")).getImage();
    }

    @Override
    public void run() {
        while (true) {
            if (overlap5()) {
                move();
            }
            if (x > xmove) {
                stand = profileR;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove += 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            } else {
                stand = profileL;
                try {
                    if (x - xmove < 0 || x - xmove > 5) {
                        xmove -= 5;
                    }
                    if (y - ymove < 0 || y - ymove > 5) {
                        if (y > ymove) {
                            ymove += 5;
                        } else if (y < ymove) {
                            ymove -= 5;
                        }
                    }
                    ru.sleep(90);
                } catch (InterruptedException ed) {
                    System.out.println("Error");
                }
            }
        }
    }

    @Override
    int upsc() {
        return 50;
    }
}
