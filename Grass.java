/**
 *  Class Grass represents Grass object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Grass extends Plant {
    /**
     * Grass class constructor
     * @param world world reference
     */
    public Grass(World world){
        super(world, 0, 0, "src/Resources/Grass.png", 0);
        GenerateInitialPosition(world);
        this.name = "grass";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Grass class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Grass(World world, int x, int y){
        super(world, 0, 0, "src/Resources/Grass.png", 0);
        this.name = "grass";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new Grass(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
