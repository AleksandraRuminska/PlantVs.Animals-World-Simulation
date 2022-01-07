/**
 *  Class Guarana represents Guarana object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Guarana extends Plant {
    /**
     * Guarana class constructor
     * @param world world reference
     */
    public Guarana(World world){
        super(world, 0, 0, "src/Resources/Guarana.png", 0);
        GenerateInitialPosition(world);
        this.name = "guarana";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Guarana class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Guarana(World world, int x, int y){
        super(world, 0, 0, "src/Resources/Guarana.png", 0);
        this.name = "guarana";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new Guarana(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
