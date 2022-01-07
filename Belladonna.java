import java.util.*;
/**
 *  Class Belladonna represents Belladonna object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */
public class Belladonna extends Plant{
    /**
     * Belladonna class constructor
     * @param world world reference
     */
    public Belladonna(World world){
        super(world, 99, 0, "src/Resources/Belladonna.png", 0);
        GenerateInitialPosition(world);
        this.name = "belladonna";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Belladonna class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Belladonna(World world, int x, int y){
        super(world, 99, 0, "src/Resources/Belladonna.png", 0);
        this.name = "belladonna";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new Belladonna(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
