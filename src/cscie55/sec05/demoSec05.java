package cscie55.sec05;

import java.util.*;

public class demoSec05 {

    public static void main(String[] args) {

        // Priority Queue Example
        Queue<Patient> q = new PriorityQueue<>();
        q.add(new Patient(new Person("John"), 3));
        q.add(new Patient(new Person("Nathan"), 5));
        q.add(new Patient(new Person("Jane"), 1));
        q.add(new Patient(new Person("Bob"), 4));
        q.add(new Patient(new Person("Alex"), 8));
        q.add(new Patient(new Person("Mary"), 1));

        System.out.println(q);
        q.poll();
        System.out.println(q);

        // HashMap Example

        String text = "Imagine there's no heaven\n" +
                "It's easy if you try\n" +
                "No hell below us\n" +
                "Above us only sky\n" +
                "Imagine all the people living for today\n" +
                "Imagine there's no countries\n" +
                "It isn't hard to do\n" +
                "Nothing to kill or die for\n" +
                "And no religion too\n" +
                "Imagine all the people living life in peace, you\n" +
                "You may say I'm a dreamer\n" +
                "But I'm not the only one\n" +
                "I hope some day you'll join us\n" +
                "And the world will be as one\n" +
                "Imagine no possessions\n" +
                "I wonder if you can\n" +
                "No need for greed or hunger\n" +
                "A brotherhood of man\n" +
                "Imagine all the people sharing all the world, you\n" +
                "You may say I'm a dreamer\n" +
                "But I'm not the only one\n" +
                "I hope some day you'll join us\n" +
                "And the world will be as one";

        String[] words = text.toLowerCase().split("[\\p{Punct}\\s]+");

        Map<String, Integer> myMap = new HashMap<>();

        for ( String word : words) {
            System.out.println(word);
            int count = myMap.containsKey(word) ? myMap.get(word) : 0;
            count ++;
            myMap.put(word, count);
        }

        System.out.println("Total number of words: " + words.length);
        for (String key : myMap.keySet()) {
            System.out.println(key + ": "+myMap.get(key));
        }



        // Hash Code example
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

        // Collections Class utilities example
          List<Patient> myList = new LinkedList<>();
        Patient myPatient = new Patient(new Person("Bob"), 4);
        myList.add(new Patient(new Person("John"), 3));
        myList.add(new Patient(new Person("Nathan"), 5));
        myList.add(new Patient(new Person("Jane"), 1));
        myList.add(new Patient(new Person("Bob"), 4));
        myList.add(new Patient(new Person("Bob"), 4));
        myList.add(new Patient(new Person("Alex"), 8));
        System.out.println("Found Bob at: "+Collections.binarySearch(myList, myPatient));

        System.out.println("Frequency of Bobs: "+Collections.frequency(myList, myPatient));

        Collections.sort(myList);
        System.out.println("Found Bob after sorting at: "+Collections.binarySearch(myList, myPatient));

        System.out.println("Max Urgency: "+Collections.max(myList));
        System.out.println("Min Urgency: "+Collections.min(myList));



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
        final int HASH_BASE = 11;
        int asciiValue = (int)this.person.getName().toCharArray()[0];
        int hashCode = asciiValue %  HASH_BASE;
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
