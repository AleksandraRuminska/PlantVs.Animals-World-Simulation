import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *  Abstract class Animal extending Organism
 * @author Aleksandra Ruminska
 * @version 1.0
 */

public abstract class Animal extends Organism{
    /** Reference to world    */
    protected World world;

    /**
     * Animal class constructor
     * @param world world reference
     * @param strength strength of organism
     * @param initiative initiative of organism
     * @param picture path to icon
     * @param reflect ability to reflect attack
     */
    public Animal(World world, int strength, int initiative, String picture, int reflect) {
        super(strength, 0, initiative, picture, reflect);
        this.world = world;
    }

    /**
     * Method checking if given place in the world is empty
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
    public void GenerateInitialPosition(World w) {
        Random r = new Random();
        int num_x = r.nextInt(world.GetLength());
        int num_y = r.nextInt(world.GetWidth());

        while(w.board[num_y][num_x] != null) {
            num_x = r.nextInt(world.GetLength());
            num_y = r.nextInt(world.GetWidth());
        }

        this.SetPositionX(num_x);
        this.SetPositionY(num_y);
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
                    this.SetPositionX(position_before_x);
                    this.SetPositionY(position_before_y);
                    world.board[this.GetPositionY()][this.GetPositionX()] = this;
                    break;
                case 2:
                    this.SetRemoved();
                    break;
                case 3:
                    if(world.board[this.GetPositionY()][this.GetPositionX()] != null) {
                        world.board[this.GetPositionY()][this.GetPositionX()].SetRemoved();
                        world.board[this.GetPositionY()][this.GetPositionX()] = null;
                    }
                    world.board[this.GetPositionY()][this.GetPositionX()] = this;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void Action(){
        Random r = new Random();
        int number = r.nextInt(4);
        switch(number){
            case 0:
                if((this.GetPositionX() + 1) <world.GetLength()) {
                    this.SetPositionX(this.GetPositionX()+1);
                }
                break;
            case 1:
                if((this.GetPositionX() - 1) >= 0) {
                    this.SetPositionX(this.GetPositionX()-1);
                }
                break;
            case 2:
                if((this.GetPositionY() + 1) <world.GetWidth()) {
                    this.SetPositionY(this.GetPositionY()+1);
                }
                break;
            case 3:
                if((this.GetPositionY() - 1) >= 0) {
                    this.SetPositionY(this.GetPositionY()-1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int Collision(int x, int y) throws IOException {
        if(world.board[this.GetPositionY()][this.GetPositionX()].name.equals(this.name)){
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
        } else if(world.board[this.GetPositionY()][this.GetPositionX()].GetReflect() > 0){
            if(world.board[this.GetPositionY()][this.GetPositionX()].GetReflect() > this.GetStrength()){
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
            if(world.board[this.GetPositionY()][this.GetPositionX()].GetStrength() > this.GetStrength()){
                FileWriter fw = new FileWriter("animalplants.txt", true);
                fw.write("\n");
                fw.write(this.name);
                fw.write(" was defeated by: ");
                fw.write(world.board[this.GetPositionY()][this.GetPositionX()].name);
                fw.close();
                return 2;
            } else if(world.board[this.GetPositionY()][this.GetPositionX()].GetStrength() < this.GetStrength()){
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

    /**
     * Method finding empty space in the world in the neighbouring cells
     * @param x1 coordinate x
     * @param y1 coordinate y
     * @param step distance to add/subtract
     * @param make_new flag to create new organism or not
     * @return returns 1 if some action was performed, 0 otherwise
     */
    public int FindPlace(int x1, int y1, int step, int make_new){
        if(x1+step < world.GetLength()) {
            if (CheckIfFree(world, x1+step, y1)) {
                if(make_new == 1) {
                    NewSameTypeOrganism(x1 + step, y1);
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
                } else {
                    this.SetPositionX(x1);
                    this.SetPositionY(y1- step);
                }
                return 1;
            }
        }
        return 0;
    }

    /**
     * Abstract method that in derived classes will be responsible for creating new object of a given class
     * @param x coordinate x
     * @param y coordinate y
     */
    abstract void NewSameTypeOrganism(int x, int y);

}
