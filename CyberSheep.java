import java.util.*;
import java.lang.Math.*;

/**
 *  Class CyberSheep represents CyberSheep object allowing the representation of its state and behaviours
 * @author Aleksandra Ruminska
 * @version 1.0
 */


public class CyberSheep extends Animal{
    /**
     * CyberSheep class constructor
     * @param world world reference
     */
    public CyberSheep(World world){
        super(world, 11, 4, "src/Resources/CyberSheep.png", 0);
        GenerateInitialPosition(world);
        this.name = "cybersheep";
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,1);
    }

    /**
     * CyberSheep class constructor for specific coordinates x,y
     * @param world world reference
     * @param x coordinate x
     * @param y coordinate y
     */
    public CyberSheep(World world, int x, int y) {
        super(world, 11, 4, "src/Resources/CyberSheep.png", 0);
        this.name = "cybersheep";
        this.SetPositionX(x);
        this.SetPositionY(y);
        world.board[this.GetPositionY()][this.GetPositionX()] = this;
        world.AddOrganism(this,0);
    }

    @Override
    public void Action(){
        if((this.GetPositionX() + 1) < world.GetLength()) {
            if(world.board[this.GetPositionY()][this.GetPositionX() + 1]!=null) {
                if (world.board[this.GetPositionY()][this.GetPositionX() + 1].name.equals("hogsweed")) {
                    this.SetPositionX(this.GetPositionX() + 1);
                    return;
                }
            }
        }
        if((this.GetPositionX() - 1) >= 0) {
            if(world.board[this.GetPositionY()][this.GetPositionX() - 1]!=null) {
                if (world.board[this.GetPositionY()][this.GetPositionX() - 1].name.equals("hogsweed")) {
                    this.SetPositionX(this.GetPositionX() - 1);
                    return;
                }
            }
        }
        if((this.GetPositionY() + 1) <world.GetWidth()) {
            if(world.board[this.GetPositionY()+1][this.GetPositionX()]!=null) {
                if (world.board[this.GetPositionY() + 1][this.GetPositionX()].name.equals("hogsweed")) {
                    this.SetPositionY(this.GetPositionY() + 1);
                    return;
                }
            }
        }
        if((this.GetPositionY() - 1) >= 0) {
            if(world.board[this.GetPositionY()-1][this.GetPositionX()]!=null) {
                if (world.board[this.GetPositionY() - 1][this.GetPositionX()].name.equals("hogsweed")) {
                    this.SetPositionY(this.GetPositionY() - 1);
                    return;
                }
            }
        }

        double diagonal = 1000;
        int hogs_x=-1;
        int hogs_y=-1;

        for(int jj=0; jj<world.GetWidth(); jj++){
            for(int ii=0; ii<world.GetLength(); ii++) {
                if (world.board[jj][ii]!=null) {
                    if (world.board[jj][ii].name.equals("hogsweed")) {
                        if (Math.sqrt(world.board[jj][ii].GetPositionX() * world.board[jj][ii].GetPositionX() + world.board[jj][ii].GetPositionY() * world.board[jj][ii].GetPositionY()) < diagonal) {
                            diagonal = Math.sqrt(world.board[jj][ii].GetPositionX() * world.board[jj][ii].GetPositionX() + world.board[jj][ii].GetPositionY() * world.board[jj][ii].GetPositionY());
                            hogs_x = world.board[jj][ii].GetPositionX();
                            hogs_y = world.board[jj][ii].GetPositionY();
                        }
                    }
                }
            }
        }

        if(diagonal == 1000) {
            Random r = new Random();
            int number = r.nextInt(4);
            switch (number) {
                case 0:
                    if ((this.GetPositionX() + 1) < world.GetLength()) {
                        this.SetPositionX(this.GetPositionX() + 1);
                    }
                    break;
                case 1:
                    if ((this.GetPositionX() - 1) >= 0) {
                        this.SetPositionX(this.GetPositionX() - 1);
                    }
                    break;
                case 2:
                    if ((this.GetPositionY() + 1) < world.GetWidth()) {
                        this.SetPositionY(this.GetPositionY() + 1);
                    }
                    break;
                case 3:
                    if ((this.GetPositionY() - 1) >= 0) {
                        this.SetPositionY(this.GetPositionY() - 1);
                    }
                    break;
                default:
                    break;
            }
        } else{
            if(this.GetPositionX() < hogs_x){
                if(Math.abs(this.GetPositionX() - hogs_x) < Math.abs(this.GetPositionY() - hogs_y)){
                    this.SetPositionX(this.GetPositionX() + 1);
                }else{
                    if(this.GetPositionY() < hogs_y){
                        this.SetPositionY(this.GetPositionY() + 1);
                    }else if(this.GetPositionY() > hogs_y) {
                        this.SetPositionY(this.GetPositionY() - 1);
                    } else{
                        this.SetPositionX(this.GetPositionX() + 1);
                    }
                }
            } else if(this.GetPositionX() > hogs_x){
                if(Math.abs(this.GetPositionX() - hogs_x) < Math.abs(this.GetPositionY() - hogs_y)){
                    this.SetPositionX(this.GetPositionX() - 1);
                }else{
                    if(this.GetPositionY() < hogs_y) {
                        this.SetPositionY(this.GetPositionY() + 1);
                    }else if(this.GetPositionY() > hogs_y) {
                        this.SetPositionY(this.GetPositionY() - 1);
                    } else{
                        this.SetPositionX(this.GetPositionX() - 1);
                    }
                }
            } else{
                if(this.GetPositionY() < hogs_y) {
                    this.SetPositionY(this.GetPositionY() + 1);
                }else if(this.GetPositionY() > hogs_y) {
                    this.SetPositionY(this.GetPositionY() - 1);
                }
            }
        }
    }

    @Override
    void NewSameTypeOrganism(int x, int y) {
        Organism org = new CyberSheep(world, x, y);
    }

    @Override
    String Draw() {
        return this.GetImage();
    }
}
