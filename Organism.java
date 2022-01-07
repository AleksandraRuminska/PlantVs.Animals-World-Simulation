import javax.swing.*;
import java.io.*;
/**
 *  Abstract class Organism
 * @author Aleksandra Ruminska
 * @version 1.0
 */
public abstract class Organism {
    /**  Value of organism's strength   */
    private int strength;
    /**  Value of organism's initiative   */
    private final int initiative;
    /**  Image representing organism in the world   */
    private final String picture_path;
    /**  Reflecting ability existence   */
    private final int reflect;
    /**  Status of being removed from world   */
    private int removed;
    /**  Coordinate x of position status   */
    private int cord_x;
    /**  Coordinate y of position status   */
    private int cord_y;
    /**  Name of organism species   */
    public String name;

    /**
     * Organism class constructor
     * @param strength organism strength
     * @param removed flag if organism is to be removed
     * @param initiative organism initiative
     * @param picture organism path to icon
     * @param reflect ability to reflect attack
     */
    public Organism(int strength, int removed, int initiative, String picture, int reflect){
        this.strength=strength;
        this.removed=removed;
        this.initiative=initiative;
        this.picture_path=picture;
        this.reflect=reflect;
    }

    /**
     * Method returning organism's strength
     * @return returns organism's strength
     */
    public int GetStrength(){
        return this.strength;
    }

    /**
     * Method returning organism's initiative
     * @return returns organism's initiative
     */
    public int GetInitiative(){
        return this.initiative;
    }

    /**
     * Method returning organism's reflecting ability
     * @return returns ability to reflect - in case of turtle 5, otherwise 0
     */
    public int GetReflect(){
        return this.reflect;
    }

    /**
     * Method setting organism's strength
     * @param new_strength new strength of organism to be set
     */
    public void SetStrength(int new_strength){
        this.strength = new_strength;
    }

    /**
     * Method setting organism to be removed
     */
    public void SetRemoved(){
        this.removed = 1;
    }

    /**
     * Method returning if organism is to be removed
     * @return returns 1 if organism is to be removed, 0 otherwise
     */
    public int GetRemoved(){
        return this.removed;
    }

    /**
     * Method returning image of organism
     * @return returns path to icon
     */
    public String GetImage(){
        return this.picture_path;
    }

    /**
     * Method returning x coordinate
     * @return returns x coordinate
     */
    public int GetPositionX(){
        return this.cord_x;
    }

    /**
     * Method returning y coordinate
     * @return returns y coordinate
     */
    public int GetPositionY(){
        return this.cord_y;
    }

    /**
     * Method setting x coordinate
     * @param x coordinate x to be set
     */
    public void SetPositionX(int x){
        this.cord_x = x;
    }

    /**
     * Method setting y coordinate
     * @param y coordinate y to be set
     */
    public void SetPositionY(int y){
        this.cord_y = y;
    }

    /**
     * Abstract method that in derived classes will be responsible for organisms' movement, actions and collision
     * @throws IOException input output exception
     */
    abstract void Move() throws IOException;

    /**
     * Abstract method that in derived classes will be responsible for changing position of organism
     * @throws IOException input output exception
     */
    abstract void Action() throws IOException;

    /**
     * Abstract method that in derived classes will be responsible for collision of organisms
     * @param x coordinate x
     * @param y coordinate y
     * @return returns what collision happened so the correct operations could be performed next
     * @throws IOException input output exception
     */
    abstract int Collision(int x, int y) throws IOException;

    /**
     * Abstract method that in derived classes will be responsible for returning organism's symbol
     * @return returns path to file
     */
    abstract String Draw();
}
