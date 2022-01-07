import javax.swing.*;
import java.io.*;
import java.util.*;
/**
 *  Abstract class Plant extending Organism
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public abstract class Plant extends Organism{
    /** Reference to world    */
    protected World world;

    /**
     * Plant class constructor
     * @param world world reference
     * @param strength organism strength
     * @param initiative organism initiative
     * @param picture path to icon
     * @param reflect ability to reflect attack
     */
    public Plant(World world, int strength, int initiative, String picture, int reflect){
        super(strength, 0, initiative, picture, reflect);
        this.world = world;
    }

    /**
     *  Method checking if given place in the world is empty
     * @param w world reference
     * @param x coordinate x
     * @param y coordinate y
     * @return returns true if place s empty, false otherwise
     */
    public boolean CheckIfFree(World w, int x, int y){
        return w.board[y][x] == null;
    }

    /**
     * Method generating initial position for organism derived from Animal class
     * @param w world reference
     */
    public void GenerateInitialPosition(World w){
        Random rp = new Random();
        int num_x = rp.nextInt(world.GetLength());
        int num_y = rp.nextInt(world.GetWidth());

        while(w.board[num_y][num_x] != null) {
            num_x = rp.nextInt(world.GetLength());
            num_y = rp.nextInt(world.GetWidth());
        }

        this.SetPositionX(num_x);
        this.SetPositionY(num_y);
    }

    /**
     * Method finding empty space in the world in the neighbouring cells
     * @param x1 coordinate x
     * @param y1 coordinate y
     * @param step distance to add/subtract
     * @param make_new flag to create new organism or not
     * @return returns 1 if some action was performed, 0 otherwise
     * @throws IOException input output exception
     */
    public int FindPlace(int x1, int y1, int step,int make_new) throws IOException {
        if(x1+step < world.GetLength()) {
            if (CheckIfFree(world, x1+step, y1)) {
                if(make_new == 1) {
                    NewSameTypeOrganism(x1 + step, y1);
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("new ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was sown ");
                    fw.close();
                } else{
                    this.SetPositionX(x1 + step);
                    this.SetPositionY(y1);
                }
                return 1;
            }
        }
        if(x1-step >= 0) {
            if (CheckIfFree(world, x1-step, y1)) {
                if(make_new == 1) {
                    NewSameTypeOrganism(x1 - step, y1);
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("new ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was sown ");
                    fw.close();
                } else {
                    this.SetPositionX(x1 - step);
                    this.SetPositionY(y1);
                }
                return 1;
            }
        }
        if(y1+step < world.GetWidth()) {
            if (CheckIfFree(world, x1, y1+step)) {
                if(make_new == 1) {
                    NewSameTypeOrganism(x1, y1 + step);
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("new ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was sown ");
                    fw.close();
                } else {
                    this.SetPositionX(x1);
                    this.SetPositionY(y1+ step);
                }
                return 1;
            }
        }
        if(y1-step >= 0) {
            if (CheckIfFree(world, x1, y1-step)) {
                if(make_new == 1) {
                    NewSameTypeOrganism(x1, y1 - step);
                    FileWriter fw = new FileWriter("animalplants.txt", true);
                    fw.write("\n");
                    fw.write("new ");
                    fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                    fw.write(" was sown ");
                    fw.close();
                } else {
                    this.SetPositionX(x1);
                    this.SetPositionY(y1- step);
                }
                return 1;
            }
        }
        return 0;
    }
    @Override
    public void Move() throws IOException {
        Action();
        Collision(this.GetPositionX(),this.GetPositionY());
    }
    @Override
    public void Action() throws IOException {
        FindPlace(this.GetPositionX(), this.GetPositionY(),1,1);
    }
    @Override
    public int Collision(int x, int y){
        return 0;
    }

    /**
     * Abstract method that in derived classes will be responsible for creating new object of a given class
     * @param x coordinate x
     * @param y coordinate y
     */
    public abstract void NewSameTypeOrganism(int x, int y);
}
