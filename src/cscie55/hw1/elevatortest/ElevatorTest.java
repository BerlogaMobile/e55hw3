package cscie55.hw1.elevatortest;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ElevatorTest {

    public static void main(String[] args) {

        Queue q = new PriorityQueue();
        q.add(new Patient(new Person("John"), 3));
        q.add(new Patient(new Person("Nathan"), 5));
        q.add(new Patient(new Person("Jane"), 1));
        q.add(new Patient(new Person("Bob"), 4));
        q.add(new Patient(new Person("Alex"), 8));
        System.out.println(q);
        q.poll();
        System.out.println(q);

        Set s = new HashSet();

        String name1 = "Charlie";
        String name2 = "Fred";
        String name3 = "Greg";
        String name4 = "Guy";
        String name5 = "Sergei";

        System.out.println(name1+":"+name1.hashCode());
        System.out.println(name2+":"+name2.hashCode());
        System.out.println(name3+":"+name3.hashCode());
        System.out.println(name4+":"+name4.hashCode());
        System.out.println(name5+":"+name5.hashCode());

//        Elevator jetFlyElevator = new Elevator();
//        jetFlyElevator.boardPassenger(3);
//        jetFlyElevator.boardPassenger(3);
//        jetFlyElevator.boardPassenger(5);
//        System.out.println(jetFlyElevator);
//        do {
//            jetFlyElevator.move();
//            System.out.println(jetFlyElevator);
//        } while (jetFlyElevator.getCurrentFloor() > 1);
    }



}

class Person{
    private String name;
    public Person (String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

class Patient implements Comparable<Patient>{
    private int urgencyIndex;
    private Person person;
    public Patient (Person person, int urgencyIndex){
        this.person = person;
        this.urgencyIndex = urgencyIndex;
    }

    public int compareTo(Patient p){
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        //this optimization is usually worthwhile, and can
        //always be added
        if (this == p) return EQUAL;

        //primitive numbers follow this form
        if (this.urgencyIndex < p.urgencyIndex) return BEFORE;
        if (this.urgencyIndex > p.urgencyIndex) return AFTER;

        return EQUAL;

    }

    @Override
    public String toString() {
        return this.person + "-"+this.urgencyIndex;
    }

    @Override
    public int hashCode()
    {
        return person.getName().hashCode() + urgencyIndex;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Patient other = (Patient) obj;
        if (this.person == null ? other.person != null : !this.person.getName().equals(other.person.getName()))
        {
            return false;
        }
        if (this.urgencyIndex != other.urgencyIndex)
        {
            return false;
        }
        return true;
    }

}
