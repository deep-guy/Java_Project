package sample;

import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;
import java.io.PrintStream;
import java.lang.Math;

public class BirdX extends Bird
{
    private double birdSpeed = 9;
    private boolean isLeader;


    public String getName()
    {
        return "1";
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

    protected void updatePos()
    {
        double dx;
        double dy;
        Position currPos = this.getPos();
        int x = currPos.getX();
        int y = currPos.getY();
        if (!this.isLeader) {
            Position lpos = this.getFlock().getLeader().getPos();
            this.setTarget(lpos.getX(), lpos.getY());
        }
        int xt = this.getTarget().getX();
        int yt = this.getTarget().getY();
        if (xt == x && yt == y) {
            dx = 0.0;
            dy = 0.0;
        } else if (xt == x) {
            dy = yt > y ? 1.0 : -1.0;
            dx = 0.0;
        } else if (yt == y) {
            dx = xt > x ? 1.0 : -1.0;
            dy = 0.0;
        } else {
            double m = (float)(yt - y) / (float)(xt - x);
            System.out.println(String.valueOf(xt) + "," + yt + "  " + (Object)currPos + " m = " + m);
            dx = xt > x ? 1.0 : -1.0;
            dy = m * (dx *= this.getSpeed());
        }
        

        for (int i = 0; i < this.getFlock().getBirds().size(); i++)
        {
            int tempx = 0, tempy = 0;
            tempx = this.getFlock().getBirds().get(i).getPos().getX();
            tempy = this.getFlock().getBirds().get(i).getPos().getY();            
            if (x + (int)dx >= tempx - 7 && x + (int)dx <= tempx + 7)
            {
                if (y + (int)dy >= tempy - 7 && y + (int)dy <= tempy + 7)
                {
                    if (this.isLeader())
                    {
                        this.getFlock().getBirds().get(i).setPos(this.getFlock().getBirds().get(i).getPos().getX() + 20, this.getFlock().getBirds().get(i).getPos().getY() + 20);
                    }
                    else
                    {
                        dx = 0;
                        dy = 0;
                    }
                }
            }
        }

        System.out.println("dx dy" + dx + "-" + dy);
        this.setPos(x + (int)dx, y + (int)dy);
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
