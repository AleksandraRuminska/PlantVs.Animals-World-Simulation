import java.io.*;

/**
 *  Class SosnowskyHogsweed represents SosnowskyHogsweed object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class SosnowskyHogsweed extends Plant {
    /**
     * SosnowskyHogsweed class constructor
     * @param world world reference
     */
    public SosnowskyHogsweed(World world){
        super(world, 10, 0, "src/Resources/Sosnowsky.png", 0);
        GenerateInitialPosition(world);
        this.name = "hogsweed";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * SosnowskyHogsweed class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public SosnowskyHogsweed(World world, int x, int y){
        super(world, 10, 0, "src/Resources/Sosnowsky.png", 0);
        this.name = "hogsweed";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void NewSameTypeOrganism(int x, int y) {
        Organism org = new SosnowskyHogsweed(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }

    @Override
    public void Action() throws IOException {
        if(this.GetPositionX()+1 < world.GetLength()) {
            if (!CheckIfFree(world, this.GetPositionX()+1, this.GetPositionY())) {
                if (!world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("guarana") &&
                        !world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("grass")
                        && !world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("sowthistle") &&
                        !world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("belladona")
                        && !world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("hogsweed")
                        && !world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("cybersheep")) {
                    FileWriter fw = new FileWriter("animalplants.txt",true);
                    fw.write("\n");
                    fw.write("hogsweed killed: ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()+1].name);
                    fw.close();
                    world.board[this.GetPositionY()][this.GetPositionX()+ 1].SetRemoved();
                    world.board[this.GetPositionY()][this.GetPositionX() + 1] = null;
                }
            }
        }
        if(this.GetPositionX() -1 >= 0) {
            if (!CheckIfFree(world, this.GetPositionX() -1, this.GetPositionY())) {
                if (!world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("guarana") &&
                        !world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("grass")
                        && !world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("sowthistle") &&
                        !world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("belladona")
                        && !world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("hogsweed")
                        && !world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("cybersheep")){
                    FileWriter fw = new FileWriter("animalplants.txt",true);
                    fw.write("\n");
                    fw.write("hogsweed killed: ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()-1].name);
                    fw.close();
                    world.board[this.GetPositionY()][this.GetPositionX()  - 1].SetRemoved();
                    world.board[this.GetPositionY()][this.GetPositionX()  - 1] = null;
                }
            }
        }
        if(this.GetPositionY()+1 < world.GetWidth()) {
            if (!CheckIfFree(world, this.GetPositionX() , this.GetPositionY() +1)) {
                if (!world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("guarana") &&
                        !world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("grass")
                        && !world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("sowthistle") &&
                        !world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("belladona")
                        && !world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("hogsweed")
                        && !world.board[this.GetPositionY()+1][this.GetPositionX()].name.equals("cybersheep")) {
                    FileWriter fw = new FileWriter("animalplants.txt",true);
                    fw.write("\n");
                    fw.write("hogsweed killed: ");
                    fw.write(world.board[this.GetPositionY()+1][this.GetPositionX()].name);
                    fw.close();
                    world.board[this.GetPositionY() + 1][this.GetPositionX()].SetRemoved();
                    world.board[this.GetPositionY() + 1][this.GetPositionX()] = null;
                }
            }
        }
        if(this.GetPositionY()-1 >= 0) {
            if (!CheckIfFree(world, this.GetPositionX(), this.GetPositionY()-1)) {
                if (!world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("guarana") &&
                        !world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("grass")
                        && !world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("sowthistle") &&
                        !world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("belladona")
                        && !world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("hogsweed")
                        && !world.board[this.GetPositionY()-1][this.GetPositionX()].name.equals("cybersheep")) {
                    FileWriter fw = new FileWriter("animalplants.txt",true);
                    fw.write("\n");
                    fw.write("hogsweed killed: ");
                    fw.write(world.board[this.GetPositionY()-1][this.GetPositionX()].name);
                    fw.close();
                    world.board[this.GetPositionY() - 1][this.GetPositionX()].SetRemoved();
                    world.board[this.GetPositionY() - 1][this.GetPositionX()] = null;
                }
            }
        }
    }

}
