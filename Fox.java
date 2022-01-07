import java.io.*;
/**
 *  Class Fox represents Fox object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Fox extends Animal {
    /**
     * Fox class constructor
     * @param world world reference
     */
    public Fox(World world){
        super(world, 3, 7, "src/Resources/Fox.png", 0);
        GenerateInitialPosition(world);
        this.name = "fox";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Fox class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Fox(World world, int x, int y){
        super(world, 3, 7, "src/Resources/Fox.png", 0);
        this.name = "fox";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void Move() throws IOException {
        int position_before_x = this.GetPositionX();
        int position_before_y = this.GetPositionY();

        world.board[position_before_y][position_before_x] = null;
        Action();
        if (CheckIfFree(this.world, this.GetPositionX(), this.GetPositionY())){
            world.board[this.GetPositionY()][this.GetPositionX()] = this;
        } else {
            switch(Collision(position_before_x, position_before_y)){
                case 1:
                case 2:
                    this.SetPositionX(position_before_x);
                    this.SetPositionY(position_before_y);
                    world.board[this.GetPositionY()][this.GetPositionX()] = this;
                    break;
                case 3:
                    world.board[this.GetPositionY()][this.GetPositionX()].SetRemoved();
                    world.board[this.GetPositionY()][this.GetPositionX()] = null;
                    world.board[this.GetPositionY()][this.GetPositionX()] = this;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    void NewSameTypeOrganism(int x, int y) {
        Organism org = new Fox(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
