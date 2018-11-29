package sample;

import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;
import java.io.PrintStream;
import java.lang.Math;

public class BirdX extends Bird
{
    private double birdSpeed = Bird.getMaxSpeed();
    private boolean isLeader;


    public String getName()
    {
        if (isLeader)
            return "Leader_IMT2017013";
        else
            return "Bird_IMT2017013";
    }

    public double getSpeed()
    {
        return birdSpeed;
    }

    public void setSpeed(double spd)
    {
        birdSpeed = spd;
    }

    public boolean isLeader()
    {
        return isLeader;
    }

    public void controlSpeed()
    {
        Bird leader = this.getFlock().getLeader();
        int leaderposx = leader.getPos().getX();
        int leaderposy = leader.getPos().getY();
        if ((this.getPos().getX() < leaderposx + 75 && this.getPos().getX() > leaderposx - 75) 
        || (this.getPos().getY() < leaderposy + 75 && this.getPos().getY() > leaderposy - 75))  
            {this.setSpeed(Bird.getMaxSpeed());}
        else    
            this.setSpeed(Bird.getMaxSpeed() + 10);                    
    }

    protected void updatePos()
    {
        controlSpeed();
        double change_x = 0;
        double change_y = 0;
        Position currPos = this.getPos();
        int x = currPos.getX();
        int y = currPos.getY();
        if (this.isLeader == false)   
        {
            Position lpos = getFlock().getLeader().getPos();
            setTarget(lpos.getX(), lpos.getY());
        }
        int x_target = this.getTarget().getX();
        int y_target = this.getTarget().getY();
        if (x_target == x && y_target == y) 
        {
            change_x = 0;
            change_y = 0;
            setPos(x, y);
        } 
        else if (x_target == x) 
        {
            change_x = 0;
            if (y_target > y)
                change_y = birdSpeed;
            else
                change_y = -birdSpeed;
        } 
        else if (y_target == y) 
        {
            change_y = 0.0;
            if (x_target > x)
                change_x = birdSpeed;
            else
                change_x = -birdSpeed;
        } 
        else 
        {
            //Calculating the slope of the straight line joining the points
            double slope = (float)(y_target - y) / (float)(x_target - x);
            if (x_target > x)
                change_x = birdSpeed;
            else
                change_x = -birdSpeed;

            change_y = (slope * change_x);
            if (change_y > 5)
                change_y = 5;
            else if (change_y < (-5))
                change_y = -5;
        }
        

        for (int i = 0; i < this.getFlock().getBirds().size(); i++)
        {
            int temp_x = 0, temp_y = 0;
            temp_x = this.getFlock().getBirds().get(i).getPos().getX();
            temp_y = this.getFlock().getBirds().get(i).getPos().getY();            
            if (x + (int)change_x >= temp_x - 7 && x + (int)change_x <= temp_x + 7)
            {
                if (y + (int)change_y >= temp_y - 7 && y + (int)change_y <= temp_y + 7)
                {
                    if (this.isLeader())
                    {
                        this.getFlock().getBirds().get(i).setPos(this.getFlock().getBirds().get(i).getPos().getX() + 20, this.getFlock().getBirds().get(i).getPos().getY() + 20);
                    }
                    else
                    {
                        change_x = -change_x;
                        change_y = -change_y;
                    }
                }
            }
        }

        this.setPos(x + (int)change_x, y + (int)change_y);
    }

    public void becomeLeader() 
    {
        this.isLeader = true;
    }

    public void retireLead() 
    {
        this.isLeader = false;
    }
}
