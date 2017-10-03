package cscie55.hw3;

import java.util.HashSet;
import java.util.Set;

public class Elevator {
    public static final int NUMBER_OF_FLOORS = 7;
    public static final int CAPACITY = 10;
    private int currentFloorIndex = 0;
    private Boolean isGoingUp = true;
    private int passengersToFloor[] = {0, 0, 0, 0, 0, 0, 0};
    private int numOfPassengers = 0;
    private Set<Passenger> boardedPassengers = new HashSet<>();
    private Building building;

    public Elevator (Building building) {
        this.building = building;
    }

    public int currentFloor() {
        return currentFloorIndex + 1;
    }
    public Set<Passenger> passengers() {
        return boardedPassengers;
    }
    public boolean goingUp() {return isGoingUp; }
    public boolean goingDown() {return !isGoingUp; }

    private void stop(int floorNumber, Floor floor){
        // unload passengers going to this floor
        Set<Passenger> remainingPassengers = new HashSet<>();
        for (Passenger p : boardedPassengers){
            if (p.destinationFloor() == currentFloorIndex + 1){
                //boardedPassengers.remove(p);
                p.arrive();
                floor.enterGroundFloor(p);
            } else {
                remainingPassengers.add(p);
            }
        }
        boardedPassengers = remainingPassengers;
        numOfPassengers = numOfPassengers - passengersToFloor[floorNumber - 1];
        passengersToFloor[floorNumber - 1] = 0;

        // board passengers waiting on this floor
        floor.boardPassengers(this);

        return;
    }

    public void move() {
        currentFloorIndex = isGoingUp ? currentFloorIndex + 1 : currentFloorIndex - 1;
        if (currentFloorIndex == 0) {
            isGoingUp = true;
        } else if (currentFloorIndex == NUMBER_OF_FLOORS - 1) {
            isGoingUp = false;
        }
        Floor floor = building.floor(this.currentFloor());
        if (passengersToFloor[currentFloorIndex] > 0 || floor.passengersWaiting(isGoingUp) > 0) {
            stop(this.currentFloor(), floor);
        }
    }

    public void boardPassenger(Passenger passenger) throws ElevatorFullException {
        if (this.passengers().size() < this.CAPACITY) {
            passengersToFloor[passenger.destinationFloor() - 1]++;
            numOfPassengers++;
            boardedPassengers.add(passenger);
        } else {
            throw new ElevatorFullException("test");
        }
    }

    public String toString(){
        return String.format("Floor %d: Passengers %d \n %s ", this.currentFloor(), this.passengers().size(), this.passengers());
    }
}
