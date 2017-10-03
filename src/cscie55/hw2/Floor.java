package cscie55.hw2;

public class Floor {
    private int passengersWaiting = 0;
    public Floor() {

    }
    public void waitForElevator(){
        this.passengersWaiting ++;
    }

    void passengerBoarded() {
        if (passengersWaiting > 0) {
            passengersWaiting --;
        }
    }

    public int passengersWaiting() {
        return this.passengersWaiting;
    }
}