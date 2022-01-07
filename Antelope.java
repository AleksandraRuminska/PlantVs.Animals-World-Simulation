import java.io.*;
import java.util.*;

/**
 *  Class Antelope represents Antelope object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public class Antelope extends Animal {

    /**
     * Antelope class constructor
     * @param world world reference
     */
    public Antelope(World world){
        super(world, 4, 4, "src/Resources/Antelope.png", 0);
        GenerateInitialPosition(world);
        this.name = "antelope";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * Antelope class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public Antelope(World world, int x, int y){
        super(world, 4, 4, "src/Resources/Antelope.png", 0);
        this.name = "antelope";
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
        Organism org = new Antelope(world, x, y);
    }
    @Override
    public void Action() {
        Random r = new Random();
        int number = r.nextInt(4);

        switch(number){
            case 0:
                if((this.GetPositionX() + 2) < world.GetLength()) {
                    this.SetPositionX(this.GetPositionX() + 2);
                }
                break;
            case 1:
                if((this.GetPositionX() - 2) >= 0) {
                    this.SetPositionX(this.GetPositionX() - 2);
                }
                break;
            case 2:
                if((this.GetPositionY() + 2) < world.GetWidth()) {
                    this.SetPositionY(this.GetPositionY() + 2);
                }
                break;
            case 3:
                if((this.GetPositionY() - 2) >= 0) {
                    this.SetPositionY(this.GetPositionY() - 2);
                }
                break;
            default:
                break;
        }
    }
    @Override
    public int Collision(int x, int y) throws IOException {
        Random r = new Random();
        int chance_to_escape = r.nextInt(100);

        if(chance_to_escape <= 50){
            FindPlace(x, y,2,0);
            FileWriter fw = new FileWriter("animalplants.txt", true);
            fw.write("\n");
            fw.write("antelope escaped from attack");
            fw.close();
            return 3;
        } else {
            if (world.board[this.GetPositionY()][this.GetPositionX()].name.equals(this.name)) {
                if (FindPlace(x, y, 1, 1) == 0) {
                    FindPlace(this.GetPositionX(), this.GetPositionY(), 1, 1);
                }
                FileWriter fw = new FileWriter("animalplants.txt", true);
                fw.write("\n");
                fw.write("new ");
                fw.write(this.name);
                fw.write(" was born");
                fw.close();
                return 1;
            } else if (world.board[this.GetPositionY()][this.GetPositionX()].GetReflect() > 0) {
                if (world.board[this.GetPositionY()][this.GetPositionX()].GetReflect() > this.GetStrength()) {
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("turtle reflected: ");
                    fw.write(this.name);
                    fw.write("'s attack.");
                    fw.close();
                    return 1;
                } else {
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("turtle was defeated by: ");
                    fw.write(this.name);
                    fw.close();
                    return 3;
                }
            } else {
                if (world.board[this.GetPositionY()][this.GetPositionX()].GetStrength() > this.GetStrength()) {
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write(this.name);
                    fw.write(" was defeated by: ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.close();
                    return 2;
                } else if (world.board[this.GetPositionY()][this.GetPositionX()].GetStrength() < this.GetStrength()) {
                    if(world.board[this.GetPositionY()][this.GetPositionX()].name.equals("guarana")){
                        this.SetStrength(this.GetStrength()+3);
                    }
                    FileWriter fw = new FileWriter("animalplants.txt",true);
                    fw.write("\n");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was defeated by: ");
                    fw.write(this.name);
                    fw.close();
                    return 3;
                } else {
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was defeated by: ");
                    fw.write(this.name);
                    fw.close();
                    return 3;
                }
            }
        }
    }
}
