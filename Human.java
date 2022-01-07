import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 *  Class Human represents Human object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */
public class Human extends Animal implements KeyListener{
    /** Ability settings   */
    private int ability_turns;
    /** Ability status    */
    private boolean on;
    /** Move status    */
    private boolean moved;
    /** Move settings    */
    private int step;
    /** Reference to panel2    */
    private JPanel panel2;
    /** Flag for key pressing    */
    private final boolean[] flag = {true};


    /**
     * Human class constructor
     * @param world world reference
     * @param panel2 panel2 reference
     * @param frame frame reference
     */
    public Human(World world, JPanel panel2, JFrame frame) {
        super(world, 5, 4, "src/Resources/Human.png", 0);
        GenerateInitialPosition(world);
        this.name = "human";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this, 1);
        this.panel2 = panel2;
        this.ability_turns = 0;
        this.on = false;
        this.step = 1;
        frame.addKeyListener(this);
    }

    /**
     * Human class constructor for specific coordinates x,y
     * @param world world reference
     * @param panel2 panel2 reference
     * @param frame frame reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Human(World world, JPanel panel2, JFrame frame, int x, int y){
        super(world, 5, 4, "src/Resources/Human.png", 0);
        this.name = "human";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
        this.panel2 = panel2;
        this.ability_turns = 0;
        this.on = false;
        this.step = 1;
        frame.addKeyListener(this);
    }

    @Override
    public String Draw() {
        return this.GetImage();
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
    }

    @Override
    public void Action() {
        flag[0] = true;
        this.moved=false;
        step=1;

        if(ability_turns==0 && !on){
            JLabel ability_lab;
            if(world.GetTurn() == 1){
                ability_lab = new JLabel("Press '2' to turn on human special ability - antelope's speed", SwingConstants.CENTER);
            }else {
                ability_lab = new JLabel("Press '2' to turn on human special ability - antelope's speed", SwingConstants.LEFT);
            }
            ability_lab.setPreferredSize(new Dimension(400,20));
            panel2.add(ability_lab);
            panel2.validate();
        }

        if(!on && ability_turns>0){
            ability_turns--;
        }

        if(ability_turns>=0 && ability_turns <4 && on){
            ability_turns++;
            step = 2;
        } else if(ability_turns >3 && ability_turns <6 && on){
            Random r = new Random();
            int possibility = r.nextInt(100);
            if(possibility <= 50){
                step=2;
            }else{
                step=1;
            }
            if(ability_turns==5){
                on = false;
            } else {
                ability_turns++;
            }
        }

        while(flag[0]){
            try {
               Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag[0] = true;
    }

    /**
     * Method setting Human position in accordance to its movement
     * @param evt KeyEvent
     * @throws IOException input output exception
     */
    public void ActMove(KeyEvent evt) throws IOException {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if((this.GetPositionY() + step) <world.GetWidth()&&!this.moved) {
                    this.SetPositionY(this.GetPositionY()+step);
                    world.board[GetPositionY()-step][GetPositionX()] = null;
                    this.moved=true;
                }
                flag[0] = false;
                break;
            case KeyEvent.VK_UP:
                if((this.GetPositionY() - step) >= 0&&!this.moved) {
                    this.SetPositionY(this.GetPositionY()-step);
                    world.board[GetPositionY()+step][GetPositionX()] = null;
                }
                flag[0] = false;
                break;
            case KeyEvent.VK_LEFT:
                if((this.GetPositionX() - step) >= 0&&!this.moved) {
                    this.SetPositionX(this.GetPositionX()-step);
                    world.board[GetPositionY()][GetPositionX()+step] = null;
                    this.moved=true;
                }
                flag[0] = false;
                break;
            case KeyEvent.VK_RIGHT:
                if((this.GetPositionX() + step) <world.GetLength()&&!this.moved) {
                    this.SetPositionX(this.GetPositionX()+step);
                    world.board[GetPositionY()][GetPositionX()-step] = null;
                    this.moved=true;
                }
                flag[0] = false;
                break;
            case KeyEvent.VK_2:
                step = 2;
                if(ability_turns ==0) {
                    on = true;
                }
                ability_turns++;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            ActMove(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}