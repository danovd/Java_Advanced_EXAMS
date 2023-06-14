package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Person> roster;
    private String name;
    private int capacity;


    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        roster = new ArrayList<>();
    }

    public void add(Person person){
        if(capacity > roster.size()){
            roster.add(person);
        }
    }
    public boolean remove (String name){
        int indexToRemove = -1;
        for (int i = 0; i < roster.size(); i++) {
            Person person = roster.get(i);
            if(person.getName().equals(name)){
                indexToRemove = i;
                break;
            }
        }
        if(indexToRemove != -1){
            roster.remove(indexToRemove);
            return true;
        }
        return false;
    }
    public Person getPerson(String name, String hometown){
        Person person = null;
        for(Person p : roster){
            if(p.getName().equals(name) && p.getHometown().equals(hometown)){
                person = p;
            }
        }
        return person;
    }
    public int getCount(){
        return roster.size();
    }
    public String getStatistics(){
        String stats = "The people in the hotel " + this.name + " are:\n";
       for(Person p : roster){
           stats += p.toString() + "\n";
       }
        return stats;
    }
}
