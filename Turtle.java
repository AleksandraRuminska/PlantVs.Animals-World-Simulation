import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *  Class Turtle represents Turtle object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Turtle extends Animal{

    /**
     * Turtle class constructor
     * @param world world reference
     */
    public Turtle(World world){
        super(world, 2, 1, "src/Resources/Turtle.png", 5);
        GenerateInitialPosition(world);
        this.name = "turtle";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Turtle class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Turtle(World world, int x, int y){
        super(world, 2, 1, "src/Resources/Turtle.png", 5);
        this.name = "turtle";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void Action(){
        Random r = new Random();
        int probability_moving = r.nextInt(100);

        if(probability_moving > 75) {
            Random r2 = new Random();
            int number = r2.nextInt(4);

            switch (number) {
                case 0:
                    if ((this.GetPositionX() + 1) < world.GetLength()) {
                        this.SetPositionX(this.GetPositionX() + 1);
                    }
                    break;
                case 1:
                    if ((this.GetPositionX() - 1) >= 0) {
                        this.SetPositionX(this.GetPositionX() - 1);
                    }
                    break;
                case 2:
                    if ((this.GetPositionY() + 1) < world.GetWidth()) {
                        this.SetPositionY(this.GetPositionY() + 1);
                    }
                    break;
                case 3:
                    if ((this.GetPositionY() - 1) >= 0) {
                        this.SetPositionY(this.GetPositionY() - 1);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new Turtle(world, x, y);
    }

    @Override
    public String Draw() {
        return this.GetImage();
    }
}
