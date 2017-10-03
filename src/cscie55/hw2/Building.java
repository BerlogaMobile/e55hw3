package cscie55.hw2;

public class Building {
    public static final int FLOORS = 7;
    private Floor[] floors = new Floor[FLOORS];
    private Elevator elevator;

    public Building() {
        elevator = new Elevator(this);
        for (int i = 0; i < this.FLOORS; i++){
            floors[i] = new Floor();
        }
    }

    public Floor floor(int floorNumber){
        return this.floors[floorNumber - 1];
    }

    public Elevator elevator(){
        return this.elevator;
    }
}
