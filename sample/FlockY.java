package sample;
import flockbase.Bird;
import flockbase.Flock;
import sample.BirdX;

import java.util.ArrayList;

public class FlockY extends Flock
{
    private ArrayList<Bird> birds = new ArrayList<Bird>(0);
    Bird leader = null;

    public void addBird(Bird brd) 
    {
        birds.add(brd);
        brd.setFlock((Flock)this);
    }

    public void setLeader(Bird lead) 
    {
        if (leader != null) 
        {
            leader.retireLead();
        }
        leader = lead;
        leader.becomeLeader();
    }

    public ArrayList<Bird> getBirds() 
    {
        return birds;
    }

    public Bird getLeader() 
    {
        return leader;
    }

    public Flock split(int pos) 
    {
        FlockY newFlock = new FlockY();
        for (int i = pos; i < birds.size(); i++)
        {
            birds.get(i).setFlock(newFlock);
            newFlock.addBird(birds.get(i));
            birds.remove(i);
        }

        newFlock.setLeader(newFlock.getBirds().get(0));
        // System.out.println(newFlock.getLeader().getName() + "+++++++");
        int xtarget, ytarget = 0;
        xtarget = getLeader().getPos().getX();
        ytarget = getLeader().getPos().getY();
        if (xtarget + 100 < 1000)
            xtarget = xtarget + 100;
        else
            xtarget = 999;
        if (ytarget + 100 < 1000)
            ytarget = ytarget + 100;
        else
            ytarget = 999;
        newFlock.getLeader().setTarget(xtarget, ytarget);
        return newFlock;
    }

    public void joinFlock(Flock f) 
    {
        for (int i = 0; i < birds.size(); i++)
        {
            birds.get(i).retireLead();
            birds.get(i).setFlock(f);
            f.getBirds().add(this.getBirds().get(i));
        }
    }
}