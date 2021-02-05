package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class IDEGraph extends JFrame {

    Menu r = new Menu();
    GP gp = new GP();
    private ArrayList<String> FxSave = new <String>ArrayList();
    private static int co = 0;
    private int ss = 0, s;

    public IDEGraph() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 800);
        setResizable(false);
        setTitle("IDE Graph Calculator");
        setVisible(true);
        setLayout(null);
        r.setBounds(0, 0, 200, 800);
        add(r);
        gp.setBounds(200, 0, 500, 800);
        add(gp);
        this.validate();
        this.repaint();
    }

    class Menu extends JPanel implements ActionListener {

        JButton Com = new JButton("Add Fx to Combobox");
        JButton safx = new JButton("Save");
        JButton cre = new JButton("Create");
        JButton out = new JButton("Exit");
        JButton defx = new JButton("Delete Fx");
        JTextField Pl = new JTextField("Please enter title program");
        JLabel Pp = new JLabel();
        JTextField addfx = new JTextField();
        JButton del = new JButton("Delete");
        JLabel fu = new JLabel();
        JLabel fu1 = new JLabel();
        JLabel fu2 = new JLabel();
        JLabel fu3 = new JLabel();
        JLabel fu4 = new JLabel();
        JLabel fu5 = new JLabel();
        JLabel fu6 = new JLabel();
        JLabel fu7 = new JLabel();
        JLabel fu8 = new JLabel();
        JLabel fu9 = new JLabel();

        public Menu() {
            setSize(200, 800);
            setLayout(null);
            setBackground(Color.BLACK);
            Pl.setBounds(20, 20, 150, 20);
            add(Pl);
            Pl.addActionListener(this);
            Com.setBounds(20, 50, 150, 20);
            add(Com);
            Com.addActionListener(this);
            defx.setBounds(20, 80, 150, 20);
            add(defx);
            defx.addActionListener(this);
            cre.setBounds(15, 650, 75, 20);
            add(cre);
            cre.addActionListener(this);
            out.setBounds(95, 650, 75, 20);
            add(out);
            out.addActionListener(this);
            fu.setBounds(10, 200, 180, 20);
            add(fu);
            fu1.setBounds(10, 230, 180, 20);
            add(fu1);
            fu2.setBounds(10, 260, 180, 20);
            add(fu2);
            fu3.setBounds(10, 290, 180, 20);
            add(fu3);
            fu4.setBounds(10, 320, 180, 20);
            add(fu4);
            fu5.setBounds(10, 350, 180, 20);
            add(fu5);
            fu6.setBounds(10, 380, 180, 20);
            add(fu6);
            fu7.setBounds(10, 410, 180, 20);
            add(fu7);
            fu8.setBounds(10, 440, 180, 20);
            add(fu8);
            fu9.setBounds(10, 470, 180, 20);
            add(fu9);
            this.validate();
            this.repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Com) {
                Pp.setBounds(10, 110, 180, 25);
                Pp.setText("Please Enter You Funtion here");
                Pp.setForeground(Color.red);
                add(Pp);
                addfx.setBounds(10, 140, 180, 25);
                add(addfx);
                safx.setBounds(60, 170, 70, 20);
                add(safx);
                if (ss == 0) {
                    safx.addActionListener(this);
                }
                ss++;
                this.validate();
                this.repaint();
            } else if (e.getSource() == safx) {
                CastFx(addfx.getText());
                System.out.println(FxSave);
                remove(safx);
                remove(addfx);
                remove(Pp);
                re();
                settext();
                this.validate();
                this.repaint();
            } else if (e.getSource() == defx) {
                Pp.setBounds(10, 110, 180, 25);
                Pp.setText("Please Index that you want to delete");
                Pp.setForeground(Color.red);
                add(Pp);
                addfx.setBounds(10, 140, 180, 25);
                add(addfx);
                del.setBounds(60, 170, 70, 20);
                add(del);
                if (s > 0) {
                    del.addActionListener(this);
                }
                s++;
                this.validate();
                this.repaint();
            } else if (e.getSource() == del) {
                int i = Integer.valueOf(addfx.getText());
                FxSave.remove(i);
                re();
                settext();
                this.validate();
                this.repaint();
            } else if (e.getSource() == cre) {
                try {
                    write();
                } catch (IOException ex) {
                    Logger.getLogger(IDEGraph.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == out) {
                System.exit(0);
            }
        }

        private void CastFx(String a) {
            String fun = "";
            int rap = a.length();
            int split = 0;
            boolean p = true;
            for (int i = 0; i < rap; i++) {
                if (a.charAt(i) >= '0' && a.charAt(i) <= '9') {
                    fun += a.substring(i, i + 1);
                    if (i < rap - 1) {
                        if (a.charAt(i + 1) == 'x' || a.charAt(i + 1) == 'X') {
                            fun += "*";
                        }
                    }
                } else if (a.charAt(i) == '+') {
                    fun += "+";
                } else if (a.charAt(i) == '-') {
                    fun += "-";
                } else if (a.charAt(i) == '*') {
                    fun += "*";
                } else if (a.charAt(i) == '/') {
                    fun += "/";
                } else if (a.charAt(i) == '(') {
                    fun += "(";
                } else if (a.charAt(i) == ')') {
                    fun += ")";
                    if (i < rap - 1) {
                        if (a.charAt(i + 1) == '^' || a.charAt(i + 1) == '+' || a.charAt(i + 1) == '-' || a.charAt(i + 1) == '*' || a.charAt(i + 1) == '/' || a.charAt(i + 1) == ')') {
                            continue;
                        } else {
                            fun += "*";
                        }
                    }
                } else if (a.charAt(i) == 'x' || a.charAt(i) == 'X') {
                    if (i < rap - 1) {
                        if ((a.charAt(i + 1) == '+' || a.charAt(i + 1) == '-' || a.charAt(i + 1) == '^' || a.charAt(i + 1) == '/' || a.charAt(i + 1) == '*' || a.charAt(i + 1) == ')')) {
                            fun += a.substring(i, i + 1);
                        } else {
                            fun += a.substring(i, i + 1) + "*";
                        }
                    } else {
                        fun += "x";
                    }
                } else if (a.charAt(i) == 'c') {
                    if (a.charAt(i + 1) == 'o' && a.charAt(i + 2) == 's' && a.charAt(i + 3) == 'e' && a.charAt(i + 4) == 'c') {
                        fun += "(1/Math.sin" + hcast(i + 5, a) + ")";
                        i = co;
                        co = 0;
                    } else if (a.charAt(i + 1) == 'o' && a.charAt(i + 2) == 't') {
                        fun += "(1/Math.tan" + hcast(i + 3, a) + ")";
                        i = co;
                        co = 0;
                    } else if (a.charAt(i + 1) == 'o' && a.charAt(i + 2) == 's') {
                        fun += "Math.cos";
                        i += 2;
                    }
                } else if (a.charAt(i) == 's') {
                    if (a.charAt(i + 1) == 'i' && a.charAt(i + 2) == 'n') {
                        fun += "Math.sin";
                        i = i + 2;
                    } else if (a.charAt(i + 1) == 'e' && a.charAt(i + 2) == 'c') {
                        fun += "(1/Math.cos" + hcast(i + 3, a) + ")";
                        i = co;
                        co = 0;
                    } else if (a.charAt(i + 1) == 'q' && a.charAt(i + 2) == 'r' && a.charAt(i + 3) == 't') {
                        fun += "Math.sqrt(" + hcast(i + 4, a) + ")";
                        i = co;
                        co = 0;
                    }
                } else if (a.charAt(i) == 't' && a.charAt(i + 1) == 'a' && a.charAt(i + 2) == 'n') {
                    fun += "Math.tan";
                    i = i + 2;
                } else if (a.charAt(i) == 'e' || a.charAt(i) == 'E') {
                    fun += "Math.E";
                } else if (a.charAt(i) == '^') {
                    if (a.charAt(i + 1) == '(') {
                        for (int j = fun.length() - 1; j > 0; j--) {
                            if (fun.charAt(j) == ')') {
                                p = false;
                            } else if (!p) {
                                if (fun.charAt(j) == '(') {
                                    split = j + 1;
                                    break;
                                }
                            } else if (p) {
                                if (fun.charAt(j) == '+' || fun.charAt(j) == '-' || fun.charAt(j) == '*' || fun.charAt(j) == '/') {
                                    split = j + 1;
                                    break;
                                }
                            }
                            System.out.println(fun.charAt(j));
                        }
                        String k = fun.substring(split, fun.length());
                        fun = fun.substring(0, split);
                        fun += "Math.pow(" + k + "," + hcast(i + 1, a);
                        i = co;
                        co = 0;
                    } else {
                        for (int j = fun.length() - 1; j > 0; j--) {
                            if (fun.charAt(j) == ')') {
                                p = false;
                            } else if (!p) {
                                if (fun.charAt(j) == '(') {
                                    split = j + 1;
                                    break;
                                }
                            } else if (p) {
                                if (fun.charAt(j) == '+' || fun.charAt(j) == '-' || fun.charAt(j) == '*' || fun.charAt(j) == '/') {
                                    split = j + 1;
                                    break;
                                }
                            }
                        }
                        String k = fun.substring(split, fun.length());
                        fun = fun.substring(0, split);
                        fun += "Math.pow(" + k + "," + num(i + 1, a) + ")";
                        i = co;
                        co = 0;
                    }
                }
            }
            FxSave.add(fun);
        }

        private String hcast(int i, String a) {
            String h = "";
            int k = 0;
            for (int j = i; j < a.length(); j++) {
                if (a.charAt(j) >= '0' && a.charAt(j) <= '9') {
                    h += a.substring(j, j + 1);
                    if (j < a.length() - 1) {
                        if (a.charAt(j + 1) == 'x' || a.charAt(j + 1) == 'X') {
                            h += "*";
                        }
                    }
                } else if (a.charAt(j) >= '0' && a.charAt(j) <= '9') {
                    h += a.substring(j, j + 1);
                } else if (a.charAt(j) == '+') {
                    h += "+";
                } else if (a.charAt(j) == '-') {
                    h += "-";
                } else if (a.charAt(j) == '*') {
                    h += "*";
                } else if (a.charAt(j) == '/') {
                    h += "/";
                } else if (a.charAt(j) == '(') {
                    h += "(";
                    k++;
                } else if (a.charAt(j) == ')') {
                    h += ")";
                    k--;
                    if (j < a.length() - 1) {
                        if (a.charAt(j + 1) != '+' || a.charAt(j + 1) != '-' || a.charAt(j + 1) != '*' || a.charAt(j + 1) != '/') {
                            h += "*";
                        }
                    }
                    if (k == 0) {
                        co = j;
                        break;
                    }
                } else if (a.charAt(j) == 'x' || a.charAt(j) == 'X') {
                    if (j < a.length() - 1) {
                        if ((a.charAt(j + 1) == '+' || a.charAt(j + 1) == '-' || a.charAt(j + 1) == '^' || a.charAt(j + 1) == '/' || a.charAt(j + 1) == '*' || a.charAt(j + 1) == ')')) {
                            h += a.substring(j, j + 1);
                        } else {
                            h += a.substring(j, j + 1) + "*";
                        }
                    } else {
                        h += "x";
                    }
                }
            }
            return h;
        }

        private String num(int d, String a) {
            String u = "";
            for (int i = d; i < a.length(); i++) {
                if (a.charAt(i) >= '0' && a.charAt(i) <= '9') {
                    u += a.substring(i, i + 1);
                    co = i;
                } else {
                    co = i - 1;
                    break;
                }
            }
            return u;
        }

        private void re() {
            fu.setText("");
            fu1.setText("");
            fu2.setText("");
            fu3.setText("");
            fu4.setText("");
            fu5.setText("");
            fu6.setText("");
            fu7.setText("");
            fu8.setText("");
            fu9.setText("");
        }

        private void settext() {
            String all = "";
            for (int y = 0; y < FxSave.size(); y++) {
                if (y == 0) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu.setText(all);
                    fu.setForeground(Color.red);
                    all = "";
                } else if (y == 1) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu1.setText(all);
                    fu1.setForeground(Color.red);
                    all = "";
                } else if (y == 2) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu2.setText(all);
                    fu2.setForeground(Color.red);
                    all = "";
                } else if (y == 3) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu3.setText(all);
                    fu3.setForeground(Color.red);
                    all = "";
                } else if (y == 4) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu4.setText(all);
                    fu4.setForeground(Color.red);
                    all = "";
                } else if (y == 5) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu5.setText(all);
                    fu5.setForeground(Color.red);
                    all = "";
                } else if (y == 6) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu6.setText(all);
                    fu6.setForeground(Color.red);
                    all = "";
                } else if (y == 7) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu7.setText(all);
                    fu7.setForeground(Color.red);
                    all = "";
                } else if (y == 8) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu8.setText(all);
                    fu8.setForeground(Color.red);
                    all = "";
                } else if (y == 9) {
                    all += (y + 1) + ": " + FxSave.get(y);
                    fu9.setText(all);
                    fu9.setForeground(Color.red);
                    all = "";
                }
            }
        }

        private void write() throws IOException {
            FileWriter fw = new FileWriter(Pl.getText() + ".java");
            PrintWriter save = new PrintWriter(fw);
            save.println("package project;");
            save.println("import java.awt.event.ActionListener;");
            save.println("import java.awt.event.ActionEvent;");
            save.println("import javax.swing.*;");
            save.println("import java.awt.Color;");
            save.println("import java.awt.Graphics;");
            save.println("import java.awt.Polygon;");
            save.println("public class " + Pl.getText() + " extends JFrame implements ActionListener {");
            save.println("private JButton Exit = new JButton(\"Exit\");");
            save.println("private JButton Cal = new JButton(\"Calculate\");");
            save.println("static int Command;");
            String sas = "Object[] s = {";
            sas += "\"Select Method\"" + ",";
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    sas += "\"" + FxSave.get(i) + "\"" + "};";
                    break;
                }
                sas += "\"" + FxSave.get(i) + "\"" + ",";
            }
            System.out.println(sas);
            save.println(sas);
            save.println("private JComboBox ss = new JComboBox(s);");
            save.println("public " + Pl.getText() + "(){");
            save.println("\n"
                    + "        setDefaultCloseOperation(EXIT_ON_CLOSE);\n"
                    + "        setSize(500, 800);\n"
                    + "        setResizable(false);\n"
                    + "        setTitle(\"IDE Graph Calculator\");\n"
                    + "        setVisible(true);\n"
                    + "        setLayout(null);\n"
                    + "        ss.setBounds(40, 500, 200, 20);\n"
                    + "        add(ss);\n"
                    + "        Cal.setBounds(60, 600, 150, 20);\n"
                    + "        add(Cal);\n"
                    + "        Cal.addActionListener(this);\n"
                    + "        Exit.setBounds(300, 600, 150, 20);\n"
                    + "        add(Exit);\n"
                    + "        Exit.addActionListener(this);\n"
                    + "        this.validate();\n"
                    + "        this.repaint();\n"
                    + "    }");
            save.println("@Override\n"
                    + "    public void actionPerformed(ActionEvent e) {\n Graph gg;"
                    + "        if (e.getSource() == Cal) {\n"
                    + "            if (ss.getSelectedIndex() == 1) {\n"
                    + "                Command = 1;\n"
                    + "                gg = new Graph();\n"
                    + "                gg.setBounds(0, 0, 600, 400);\n"
                    + "                this.add(gg);\n"
                    + "                this.validate();\n"
                    + "                this.repaint();\n"
                    + "            } else if (ss.getSelectedIndex() == 2) {\n"
                    + "                Command = 2;\n"
                    + "                gg = new Graph();\n"
                    + "                gg.setBounds(0, 0, 600, 400);\n"
                    + "                this.add(gg);\n"
                    + "                this.validate();\n"
                    + "                this.repaint();\n"
                    + "            }else if (ss.getSelectedIndex() == 3) {\n"
                    + "                Command = 3;\n"
                    + "                gg = new Graph();\n"
                    + "                gg.setBounds(0, 0, 600, 400);\n"
                    + "                this.add(gg);\n"
                    + "                this.validate();\n"
                    + "                this.repaint();\n"
                    + "            }\n"
                    + "        }\nelse if(e.getSource()==Exit){\n"
                    + "            System.exit(0);\n"
                    + "        }"
                    + "    }");
            save.println("class Graph extends JPanel {\n"
                    + "\n"
                    + "        private Polygon p = new Polygon();\n"
                    + "\n"
                    + "        Graph() {\n"
                    + "            setBackground(Color.white);\n"
                    + "            drawFunction(Command);\n"
                    + "        }");
            save.println("public void drawFunction(int h) {\n"
                    + "            if (h == 1) {\n"
                    + "                for (int t = -100; t <= 100; t++) {\n"
                    + "                    p.addPoint(t + 200, (int) (200 - f1(t)));\n"
                    + "                }\n"
                    + "            } else if (h == 2) {\n"
                    + "                for (int t = -100; t <= 100; t++) {\n"
                    + "                    p.addPoint(t + 200, (int) (200 - f2(t)));\n"
                    + "                }\n"
                    + "            } else if (h == 3) {\n"
                    + "                for (int t = -100; t <= 100; t++) {\n"
                    + "                    p.addPoint(t + 200, (int) (200 - f3(t)));\n"
                    + "                }\n"
                    + "            }\n"
                    + "        }");
            save.println("public double f1(int x) {\n"
                    + "            return " + FxSave.get(0) + ";\n"
                    + "        }\n"
                    + "\n"
                    + "        public double f2(int x) {\n"
                    + "            return " + FxSave.get(1) + ";\n"
                    + "        }\n"
                    + "\n"
                    + "        public double f3(int x) {\n"
                    + "            return " + FxSave.get(2) + ";\n"
                    + "        }");
            save.println("public void paintComponent(Graphics g) {\n"
                    + "            g.drawLine(10, 200, 390, 200);\n"
                    + "            g.drawLine(200, 30, 200, 390);\n"
                    + "            g.drawLine(390, 200, 370, 190);\n"
                    + "            g.drawLine(390, 200, 370, 210);\n"
                    + "            g.drawLine(200, 30, 190, 50);\n"
                    + "            g.drawLine(200, 30, 210, 50);\n"
                    + "            g.drawString(\"X\", 370, 170);\n"
                    + "            g.drawString(\"Y\", 220, 40);\n"
                    + "            g.setColor(Color.red);\n"
                    + "            g.drawPolyline(p.xpoints, p.ypoints, p.npoints);\n"
                    + "        }");
            save.println("}");
            save.println("public static void main(String[] args) {\n"
                    + "        " + Pl.getText() + " n = new " + Pl.getText() + "();\n"
                    + "    }");
            save.println("}");
            save.close();
        }
    }

    class GP extends JPanel {

        public GP() {
            setBackground(Color.DARK_GRAY);
            setSize(500, 800);
            this.validate();
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawRect(20, 50, 450, 350);
            g.drawString("Show Graph Panel", 100, 80);
            g.setColor(Color.red);
            g.drawRect(40, 500, 200, 20);
            g.drawString("Combobox", 100, 515);
            g.setColor(Color.cyan);
            g.drawRect(300, 600, 150, 20);
            g.drawString("Exit BT", 350, 615);
            g.setColor(Color.BLUE);
            g.drawRect(60, 600, 150, 20);
            g.drawString("Calculate BT", 90, 615);
        }
    }

    public static void main(String[] args) {
        IDEGraph ide = new IDEGraph();
    }
}
