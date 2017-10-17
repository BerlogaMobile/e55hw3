package cscie55.hw2;

public class MyTestHw2 {
    public static void main(String[] args) {
        System.out.println("MyTestHw2 started");
        Building building = new Building();
        Elevator elevator = building.elevator();
        Floor floor = building.floor(1);
        floor.passengersWaiting();
        System.out.println("MyTestHw2 completed");
    }
}
