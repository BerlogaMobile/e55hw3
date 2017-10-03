package cscie55.hw3;

import java.util.*;

public class Floor {
    private int passengersWaiting = 0;
    int myFloorNumber = 0;

    List<Passenger> upwardBound  = new LinkedList<>();
    List<Passenger> downwardBound  = new LinkedList<>();
    List<Passenger> residents = new LinkedList<>();


    public Floor(int myFloorNumber) {
        this.myFloorNumber = myFloorNumber;
    }

    public void waitForElevator(Passenger passenger, int destinationFloor){
        passenger.waitForElevator(destinationFloor);
        this.passengersWaiting ++;
        if (destinationFloor < myFloorNumber) {
            downwardBound.add(passenger);
            residents.remove(passenger);
        } else if (destinationFloor > myFloorNumber){
            upwardBound.add(passenger);
            residents.remove(passenger);
        }
    }

    void passengerBoarded() {
        if (passengersWaiting > 0) {
            passengersWaiting --;
        }
    }

    void boardPassengers(Elevator elevator) {
        List <Passenger> passengersToBoard = elevator.goingUp() ? upwardBound : downwardBound;
        int countOfBoardedPassengers = 0;
        for (Passenger p : passengersToBoard){
            try {
                elevator.boardPassenger(p);
                residents.remove(p);
                countOfBoardedPassengers ++;
                p.boardElevator();
            } catch (ElevatorFullException e) {
                break;
            }
        }
        for (int i = 0; i < countOfBoardedPassengers; i++){
            passengersToBoard.remove(0);
        }
    }

    public boolean isResident(Passenger passenger) {
        return residents.contains(passenger);
    }

    public void enterGroundFloor(Passenger passenger) {
        residents.add(passenger);
    }

    int passengersWaiting() {
        return this.passengersWaiting;
    }

    int passengersWaiting(boolean isGoingUp) {
        return isGoingUp ? upwardBound.size() : downwardBound.size();
    }

    @Override
    public String toString() {
        return "\nFloor{" +
                "myFloorNumber=" + myFloorNumber +
                ", passengersWaiting=" + passengersWaiting +
                ", \nupwardBound=" + upwardBound +
                ", \ndownwardBound=" + downwardBound +
                ", \nresidents=" + residents +
                '}';
    }
}