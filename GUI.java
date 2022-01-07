import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Class GUI creating GUI components and performing main action
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class GUI {
    /** Setting frame    */
    private static JFrame frame;
    /** Setting panel    */
    public static JPanel panel;

    /**
     * GUI class constructor
     */
    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50,100,50,100));
        panel.setLayout(new GridLayout(0,10));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(300,300));
        frame.getContentPane().add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,800);
        frame.setTitle("World of animals and plants");
        frame.setVisible(true);
    }

    /**
     * Main method with main program loop
     * @param args passed string
     * @throws IOException input output exception
     */
    public static void main(String[] args) throws IOException {
        new GUI();
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(100, 250));
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);
        panel2.setLayout(new GridLayout(0,1));
        World w = new World(10,10);
        w.CreateBoard(panel);

        Wolf wolf = new Wolf(w);
        Wolf wolf2 = new Wolf(w);
        Sheep sheep = new Sheep(w);
        Sheep sheep2 = new Sheep(w);
        Fox fox = new Fox(w);
        Fox fox2 = new Fox(w);
        Antelope antelope = new Antelope(w);
        Antelope antelope2 = new Antelope(w);
        Turtle turtle = new Turtle(w);
        Turtle turtle2 = new Turtle(w);
        Grass grass = new Grass(w);
        Grass grass2 = new Grass(w);
        SowThistle sowthistle = new SowThistle(w);
        SowThistle sowthistle2 = new SowThistle(w);
        Guarana guarana = new Guarana(w);
        Guarana guarana2 = new Guarana(w);
        Belladonna belladonna = new Belladonna(w);
        Belladonna belladonna2 = new Belladonna(w);
        SosnowskyHogsweed hogsweed = new SosnowskyHogsweed(w);
        SosnowskyHogsweed hogsweed2 = new SosnowskyHogsweed(w);
        Human human = new Human(w,panel2, frame);
        CyberSheep cyber = new CyberSheep(w);
        CyberSheep cyber2 = new CyberSheep(w);

        final boolean[] flag = {true};

       KeyListener kl = new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               flag[0] = false;
           }
       };
        frame.addKeyListener(kl);


        int number_turns=20;
        while(w.GetTurn()<=number_turns) {
            w.DrawWorld(panel2, frame);
            panel.revalidate();
            w.AddDuplicates();
            w.MakeTurn();
            panel.repaint();
            panel2.repaint();
            panel.revalidate();
            panel2.revalidate();

            if(w.GetCheckHuman()==0) {
                while (flag[0]) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            w.RemoveOrganism();
            w.AddDuplicates();
            w.SetTurn();

            flag[0] = true;
        }
        w.DrawWorld(panel2, frame);
    }
}
