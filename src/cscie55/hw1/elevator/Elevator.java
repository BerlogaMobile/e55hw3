package cscie55.hw1.elevator;

public class Elevator {
    static final int NUMBER_OF_FLOORS = 7;
    private int currentFloor = 0;
    private Boolean isGoingUp = true;
    private int passengersToFloor[] = {0, 0, 0, 0, 0, 0, 0};
    private int numOfPassengers = 0;

    public Elevator () {
    }

    public int getCurrentFloor() {
        return currentFloor + 1;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    private void stop(int floor){
        numOfPassengers = numOfPassengers - passengersToFloor[floor];
        passengersToFloor[floor] = 0;
    }

    public void move() {
        currentFloor = isGoingUp ? currentFloor + 1 : currentFloor - 1;
        if (currentFloor == 0) {
            isGoingUp = true;
        } else if (currentFloor == NUMBER_OF_FLOORS - 1) {
            isGoingUp = false;
        }
        if (passengersToFloor[currentFloor] > 0) {
            stop(currentFloor);
        }
    }

    public void boardPassenger(int destinationFloor) {
        passengersToFloor[destinationFloor]++;
        numOfPassengers++;
    }

    public String toString(){
        return String.format("Floor %d: Passengers %d ", this.getCurrentFloor(), this.getNumOfPassengers());
    }
}
