/**
 *  Class Wolf represents Wolf object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */


public class Wolf extends Animal {
    /**
     * Wolf class constructor
     * @param world world reference
     */
    public Wolf(World world){
        super(world, 9, 5, "src/Resources/Wolf.png", 0);
        GenerateInitialPosition(world);
        this.name = "wolf";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Wolf class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Wolf(World world, int x, int y){
        super(world, 9, 5, "src/Resources/Wolf.png", 0);
        this.name = "wolf";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public String Draw(){
        return this.GetImage();
    }

    @Override
    public void NewSameTypeOrganism(int x, int y){
        Organism org = new Wolf(world, x, y);
    }
}
