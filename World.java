import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 *  Class World represents created space in which all actions take place
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class World {
    /**  Turn status   */
    private int turn;
    /**  Length of world   */
    private final int length;
    /**  Width of world   */
    private final int width;
    /**  Human existence in the world   */
    private int check_human;
    /**  Organisms currently in the world  */
    private ArrayList<Organism> organisms = new ArrayList<Organism>();
    /**  New born organisms to be added to world  */
    private ArrayList<Organism> duplicated = new ArrayList<Organism>();
    /**  Arrangement of organisms in the world  */
    public Organism[][] board;
    /**  Representation of the world  */
    public JButton[][] buttons;

    /**
     * World class constructor
     * @param length world's length
     * @param width world's width
     */
    public World(int length, int width) {
        this.turn = 1;
        this.length = length;
        this.width = width;
        this.check_human = 0;

        board = new Organism [this.width][this.length];

        for(int i=0; i<this.width; i++){
            for(int j=0; j<this.length; j++){
                board[i][j] = null;
            }
        }
        buttons = new JButton[this.length][this.width];
    }

    /**
     * Method returning current turn
     * @return returns number of turn
     */
    public int GetTurn() {
        return this.turn;
    }

    /**
     * Method returning length of the world
     * @return returns length
     */
    public int GetLength(){
        return this.length;
    }

    /**
     * Method returning width of the world
     * @return returns width of the world
     */
    public int GetWidth(){
        return this.width;
    }

    /**
     * Method returning variable that checks human existence in the world
     * @return returns 1 if human exists, 0 otherwise
     */
    public int GetCheckHuman(){
        return this.check_human;
    }

    /**
     * Method setting turn to the next one
     */
    public void SetTurn() {
        this.turn = this.turn + 1;
    }

    /**
     * Method adding new organism to organisms in the world or duplicated - organisms to be added
     * @param new_organism new organism to be added
     * @param where place to add organism 1- organism, else duplicated
     */
    public void AddOrganism(Organism new_organism, int where) {
        if(where == 1) {
            organisms.add(new_organism);
        }else{
            duplicated.add(new_organism);
        }
    }

    /**
     * Method removing organism from organisms in the world
     */
    public void RemoveOrganism() {
        int i=0;
        for(i=0; i<organisms.size(); i++){
            if(organisms.get(i).GetRemoved() == 1){
                Organism temp = organisms.get(i);
                organisms.set(i,organisms.get(organisms.size()-1));
                organisms.set(organisms.size()-1,temp);
                organisms.remove(organisms.size()-1);
                i--;
            }
        }
    }

    /**
     * Method adding duplicated (new born organisms) to organisms in the world
     */
    public void AddDuplicates() {
        if(!duplicated.isEmpty()) {
            int ii=0;
            for(Organism i : duplicated){
                organisms.add(i);
                ii++;
            }
            duplicated.clear();
        }
    }

    /**
     * Method creating representation of the world
     * @param panel panel reference
     */
    public void CreateBoard(JPanel panel) {
        for(int i=0; i<this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                buttons[i][j] = new JButton(new ImageIcon("src/Resources/Empty.png"));
                buttons[i][j].setFocusable(false);
                panel.add(buttons[i][j]);
            }
        }
    }

    /**
     * Method responsible for triggering actions of all organisms in the world
     * @throws IOException input output exception
     */
    public void MakeTurn() throws IOException {
        Organism[] array = new Organism[organisms.size()];
        int ii=0;
        for(Organism i : organisms){
            if(ii==0) {
                array[ii] = i;
            } else {
                int j;
                for(j=0; j<ii; j++){
                    if(i.GetInitiative() > array[j].GetInitiative()){
                        for(int k=ii; k>j; k--){
                            array[k] = array[k-1];
                        }
                        array[j] = i;
                        break;
                    }
                }
                if(j==ii){
                    array[ii] = i;
                }
            }
            ii++;
        }
        for(int jj=0; jj<organisms.size(); jj++){
            if(array[jj].GetRemoved() != 1) {
                array[jj].Move();
            }
        }
    }

    /**
     * Method responsible for drawing world state
     * @param panel2 panel2 reference
     * @param frame frame reference
     * @throws IOException input output exception
     */
    public void DrawWorld(JPanel panel2, JFrame frame) throws IOException {
        this.check_human=0;
        panel2.removeAll();
        for(int i=0; i<this.width; i++){
            for(int j=0; j<this.length; j++){
                if(board[i][j]!=null){
                    if(board[i][j].name.equals("human")){
                        this.check_human=1;
                    }
                    buttons[i][j].setIcon(new ImageIcon(board[i][j].Draw()));
                }else{
                    buttons[i][j].setIcon(new ImageIcon("src/Resources/Empty.png"));
                    final JPopupMenu menu = new JPopupMenu();
                    final int[] listed = {0};

                    int finalI = i;
                    int finalI1 = j;
                    World w= this;

                    buttons[i][j].addMouseListener(new MouseAdapter() {
                        public void mouseReleased(MouseEvent e){
                        if (e.getButton() == 1){
                            JMenuItem item1 = new JMenuItem(new AbstractAction("Antelope") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Antelope aa = new Antelope(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item2 = new JMenuItem(new AbstractAction("Fox") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        if (board[finalI][finalI1] == null) {
                                            Fox ff = new Fox(w, finalI1, finalI);
                                            buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                        }
                                    }
                                }
                            });
                            JMenuItem item3 = new JMenuItem(new AbstractAction("Sheep") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Sheep ss = new Sheep(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item4 = new JMenuItem(new AbstractAction("Wolf") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Wolf ww = new Wolf(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item5 = new JMenuItem(new AbstractAction("Cyber Sheep") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        CyberSheep cs = new CyberSheep(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item6 = new JMenuItem(new AbstractAction("Turtle") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Turtle tt = new Turtle(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item7 = new JMenuItem(new AbstractAction("Grass") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Grass gg = new Grass(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item8 = new JMenuItem(new AbstractAction("Sow Thistle") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        SowThistle st = new SowThistle(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item9 = new JMenuItem(new AbstractAction("Guarana") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Guarana gg = new Guarana(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item10 = new JMenuItem(new AbstractAction("Belladonna") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        Belladonna bb = new Belladonna(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });
                            JMenuItem item11 = new JMenuItem(new AbstractAction("Sosnowsky's Hogsweed") {
                                public void actionPerformed(ActionEvent e) {
                                    if(board[finalI][finalI1]==null) {
                                        SosnowskyHogsweed sh = new SosnowskyHogsweed(w, finalI1, finalI);
                                        buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                    }
                                }
                            });

                            if(w.check_human==0){
                                JMenuItem item12 = new JMenuItem(new AbstractAction("Human") {
                                    public void actionPerformed(ActionEvent e) {
                                        if(board[finalI][finalI1]==null) {
                                            Human sh = new Human(w, panel2, frame, finalI1, finalI);
                                            buttons[finalI][finalI1].setIcon(new ImageIcon(board[finalI][finalI1].Draw()));
                                        }
                                    }
                                });
                                if (listed[0] == 0) {
                                    menu.add(item12);
                                }
                            }

                            if(listed[0] == 0) {
                                menu.add(item1);
                                menu.add(item2);
                                menu.add(item3);
                                menu.add(item4);
                                menu.add(item5);
                                menu.add(item6);
                                menu.add(item7);
                                menu.add(item8);
                                menu.add(item9);
                                menu.add(item10);
                                menu.add(item11);
                                listed[0] = 1;
                            }

                            if(board[finalI][finalI1]==null) {
                                menu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }
                        }
                    });

                    buttons[i][j].setFocusable(false);
                }
            }
        }
        if(turn==1){
            if (this.check_human == 1) {
                JLabel arrows = new JLabel("Press arrow key to choose the way for the human", SwingConstants.CENTER);
                arrows.setPreferredSize(new Dimension(400, 20));
                panel2.add(arrows);
            } else {
                JLabel author_lab = new JLabel("Press a key on the keyboard to start the game", SwingConstants.CENTER);
                author_lab.setPreferredSize(new Dimension(200, 20));
                panel2.add(author_lab);
            }
        }else {

            JLabel author_lab = new JLabel("Aleksandra Ruminska 179953", SwingConstants.RIGHT);
            author_lab.setPreferredSize(new Dimension(400, 20));
            panel2.add(author_lab);

            if (this.check_human == 1) {
                JLabel arrows = new JLabel("Press arrow key to choose the way for the human", SwingConstants.LEFT);
                arrows.setPreferredSize(new Dimension(400, 20));
                panel2.add(arrows);
            } else {
                JLabel key = new JLabel("Press a key on the keyboard to go to the next round", SwingConstants.LEFT);
                key.setPreferredSize(new Dimension(400, 20));
                panel2.add(key);
            }

            FlowLayout fll = new FlowLayout();
            String events;
            BufferedReader reader = new BufferedReader(new FileReader("animalplants.txt"));
            try {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    builder.append(line);
                    builder.append("\n");
                    line = reader.readLine();
                }
                events = builder.toString();
            } finally {
                reader.close();
            }

            JTextArea txta = new JTextArea();
            txta.setText(events);
            txta.setPreferredSize(new Dimension(300,100));
            panel2.setLayout(fll);
            panel2.add(txta);
            panel2.validate();
        }

        String str = "TURN: ";
        FileWriter fw = new FileWriter("animalplants.txt", false);
        fw.write(str);
        fw.write(String.valueOf(this.GetTurn()));
        fw.close();

    }
}
