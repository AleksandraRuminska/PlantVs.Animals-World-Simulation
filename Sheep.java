/**
 *  Class Sheep represents Sheep object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Sheep extends Animal{
    /**
     * Sheep class constructor
     * @param world world reference
     */
    public Sheep(World world){
        super(world, 4, 4, "src/Resources/Sheep.png", 0);
        GenerateInitialPosition(world);
        this.name = "sheep";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Sheep class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Sheep(World world, int x, int y) {
        super(world, 4, 4, "src/Resources/Sheep.png", 0);
        this.name = "sheep";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    void NewSameTypeOrganism(int x, int y) {
        Organism org = new Sheep(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
