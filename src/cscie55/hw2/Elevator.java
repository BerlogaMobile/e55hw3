package cscie55.hw2;

public class Elevator {
    public static final int NUMBER_OF_FLOORS = 7;
    public static final int CAPACITY = 10;
    private int currentFloorIndex = 0;
    private Boolean isGoingUp = true;
    private int passengersToFloor[] = {0, 0, 0, 0, 0, 0, 0};
    private int numOfPassengers = 0;
    private Building building;

    public Elevator (Building building) {
        this.building = building;
    }

    public int currentFloor() {
        return currentFloorIndex + 1;
    }
    public int passengers() {
        return numOfPassengers;
    }

    private void stop(int floorNumber, Floor floor){
        // unload passengers going to this floor
        numOfPassengers = numOfPassengers - passengersToFloor[floorNumber - 1];
        passengersToFloor[floorNumber - 1] = 0;
        // board passengers waiting on this floor
        while (floor.passengersWaiting() > 0) {
            try {
                this.boardPassenger(this.currentFloor() > 1 ? 1 : NUMBER_OF_FLOORS);
                floor.passengerBoarded();
            } catch (ElevatorFullException e) {
                //System.out.println(String.format("Elevator is full! %d passenger(s) will be picked up from floor %d on the next stop.", floor.passengersWaiting(), floorNumber));
                break;
            }
        }
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
        if (passengersToFloor[currentFloorIndex] > 0 || floor.passengersWaiting() > 0) {
            stop(this.currentFloor(), floor);
        }
    }

    public void boardPassenger(int destinationFloor) throws ElevatorFullException {
        if (this.passengers() < this.CAPACITY) {
            passengersToFloor[destinationFloor - 1]++;
            numOfPassengers++;
        } else {
            throw new ElevatorFullException();
        }
    }

    public String toString(){
        return String.format("Floor %d: Passengers %d ", this.currentFloor(), this.passengers());
    }
}
