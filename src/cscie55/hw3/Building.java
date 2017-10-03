package cscie55.hw3;

import java.util.Arrays;

public class Building {
    public static final int FLOORS = 7;
    private Floor[] floors = new Floor[FLOORS];
    private Elevator elevator;

    public Building() {
        elevator = new Elevator(this);
        for (int i = 0; i < this.FLOORS; i++){
            floors[i] = new Floor(i+1);
        }
        return;
    }

    public Floor floor(int floorNumber){
        return this.floors[floorNumber - 1];
    }

    public Elevator elevator(){
        return this.elevator;
    }

    public void enter(Passenger passenger) {
        floors[0].enterGroundFloor(passenger);
    }

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + Arrays.toString(floors) +
                ", \nelevator=" + elevator +
                '}';
    }
}
