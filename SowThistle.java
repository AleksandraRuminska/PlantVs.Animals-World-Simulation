import java.io.*;

/**
 *  Class SowThistle represents SowThistle object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class SowThistle extends Plant {
    /**
     * SowThistle class constructor
     * @param world world reference
     */
    public SowThistle(World world){
        super(world, 0, 0, "src/Resources/SowThistle.png", 0);
        GenerateInitialPosition(world);
        this.name = "sowthistle";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * SowThistle class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public SowThistle(World world, int x, int y){
        super(world, 0, 0, "src/Resources/SowThistle.png", 0);
        this.name = "sowthistle";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void Move() throws IOException {
        Action();
        Action();
        Action();
        Collision(this.GetPositionX(),this.GetPositionY());
    }


    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new SowThistle(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
