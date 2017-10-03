package cscie55.hw3;

public class Passenger {
    final static int UNDEFINED_FLOOR = -1;
    private int id;
    private int currentFloor = 1;
    private int destinationFloor = UNDEFINED_FLOOR;

    public Passenger(int id) {
    }

    public int currentFloor() {
        return currentFloor;
    }

    public int destinationFloor() {
        return destinationFloor;
    }

    void waitForElevator(int newDestinationFloor){
        destinationFloor = newDestinationFloor;
    }

    public void boardElevator(){
        currentFloor = UNDEFINED_FLOOR;
    }

    public void arrive(){
        currentFloor = destinationFloor;
        destinationFloor = UNDEFINED_FLOOR;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", currentFloor=" + currentFloor +
                ", destinationFloor=" + destinationFloor +
                '}';
    }
}
