import flockbase.Bird;
import flockbase.Flock;
import java.util.ArrayList;

public class FlockY
extends Flock {
    private ArrayList<Bird> birds = new ArrayList();
    Bird lead = null;

    public void addBird(Bird b) {
        this.birds.add(b);
        b.setFlock((Flock)this);
    }

    public void setLeader(Bird leader) {
        if (this.lead != null) {
            this.lead.retireLead();
        }
        this.lead = leader;
        this.lead.becomeLeader();
    }

    public ArrayList<Bird> getBirds() {
        return this.birds;
    }

    public Bird getLeader() {
        return this.lead;
    }

    public Flock split(int pos) {
        return null;
    }

    public void joinFlock(Flock f) {
    }
}