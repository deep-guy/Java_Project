package sample;
import flockbase.Bird;
import flockbase.Flock;
import sample.BirdX;

import java.util.ArrayList;

public class FlockY extends Flock
{
    private ArrayList<Bird> birds = new ArrayList();
    Bird leader = null;

    public void addBird(Bird brd) 
    {
        birds.add(brd);
        brd.setFlock((Flock)this);
    }

    public void setLeader(Bird lead) 
    {
        if (leader != null) {
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
        Bird newLeader = birds.get(pos);
        newFlock.addBird(newLeader);
        birds.remove(pos);
        int size = birds.size();

        for (int i = 0; i < size; i+=2)
        {
            Bird temp =  birds.get(i);
            newFlock.addBird(temp);
            birds.remove(i);
        }

        newLeader.setTarget(800, 600);
        newFlock.setLeader(newLeader);
        return newFlock;
    }

    public void joinFlock(Flock f) 
    {
        for (int i = 0; i < birds.size(); i++)
        {
            Bird temp = birds.get(i);
            // if (temp.isLeader())
            // {
            //     temp.retireLead();
            // }
            birds.remove(i);
            f.addBird(temp);
        }
        this.leader = f.getLeader();
        this.birds = f.getBirds();
        f = null;
    }
}