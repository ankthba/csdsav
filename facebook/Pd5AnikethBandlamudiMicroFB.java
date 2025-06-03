/*

Name: Aniketh Bandlamudi
Period: 5
Name of the Lab: MicroFB

Purpose of the Program: To create a micro version of Facebook that allows users to create people, manage friendships, 
and query relationships using hash maps and linked lists.

The program works/partially work/does not compile/has run time errors and where ... 
N/A, No errors, works as expected.

What I learned (in bullet form):
- How to use hash maps to store objects and relationships efficiently.
- How to implement linked lists to manage friendships.
- How to create a simple command-line interface for user interaction.

How I feel about this lab: 
I feel that this lab was a great exercise in understanding data structures like hash maps and linked lists, 
and how they can be used to model real world relationships like friendships. It also helped me practice my Java 
programming skills, especially in object oriented design.

What I wonder:
How this implementation could be extended to include more features like groups.

What part of this program did you find most challenging?
The most challenging part of this program was ensuring that the friendship relationships were managed correctly,

Describe one bug you fixed — what was wrong, and how did you fix it?
One bug I fixed was ensuring that a person could not friend themselves. Initially, the program allowed this, but 
I added a check in the makeFriends and unfriend methods to prevent it.

What part are you proud of?
I am proud of the way I structured the code to separate concerns, with a clear `Person` class and methods in the `MicroFB` class

What advice would you give a student doing this next year?
Make sure to thoroughly test each command as you implement it, and pay attention to edge cases like self-friending,

*/


import java.util.*;
import java.io.*;


public class Pd5AnikethBandlamudiMicroFB {
    
    // person class to represent each person in the micro Facebook
    // person is implemented as a static inner class to maintain logic and cohesion with MicroFB
    // while avoiding memory overhead of a non static inner class and enhances encapsulation
    // and allows Person to be independently instantiated
    static class Person {
        private String name;
        // LinkedList is better to mainatain the order of friends and allow efficient insertion and deletion
        // in this program, friends are added and removed frequently so LinkedList is more suitable
        private LinkedList<Person> friends;
        
        // constructor for Person
        // pre: name is not null
        // post: creates a Person object with the given name and initializes an empty friends list
        public Person(String name) {
            this.name = name;
            this.friends = new LinkedList<>();
        }
        
        // pre: none
        // post: returns the name of the person
        public String getName() {
            return name;
        }
        
        // pre: none
        // post: returns the list of friends for this person
        public LinkedList<Person> getFriends() {
            return friends;
        }
        
        // pre: friend is not null and not equal to this person
        // post: adds the friend to this person's friends list if not already present
        public void addFriend(Person friend) {
            if (friend != null && !friend.equals(this)) {
                friends.addFirst(friend);
            }
        }
        
        // pre: friend is not null
        // post: removes the friend from this person's friends list if present
        public void removeFriend(Person friend) {
            if (friend != null) {
                friends.remove(friend);
            }
        }
        
        // pre: none
        // post: returns a string representation of the person
        // @Override is reccomended to override the default toString method and is standard practice in Java
        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null || getClass() != obj.getClass()){
                return false;
            }
            Person person = (Person) obj;
            return Objects.equals(name, person.name);
        }
        
        // pre: none
        // post: returns a hash code for the person based on their name
        // @Override is reccomended to override the default hashCode method and is standard practice in Java
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
    
    // hash map to store Person objects indexed by name
    private HashMap<String, Person> people;
    // hash map to store friendship relationships using combined names as keys
    private HashMap<String, Boolean> friendships;
    private Scanner scanner;
    
    // constructor for MicroFB
    public Pd5AnikethBandlamudiMicroFB() {
        people = new HashMap<>();
        friendships = new HashMap<>();
        scanner = new Scanner(System.in);
    }
    
    // pre: names are not null and not already existing
    // post: creates a unique key for the friendship based on the names
    private String createFriendshipKey(String name1, String name2) {
        // ensure names are in a consistent order to avoid duplicates
        if (name1.compareTo(name2) <= 0) {
            return name1 + "*" + name2;
        } else {
            return name2 + "*" + name1;
        }
    }
    
    // pre: name is not null and not already existing
    // post: creates a new Person object and adds it to the people map
    public void createPerson(String name) {
        if (!people.containsKey(name)) {
            Person person = new Person(name);
            people.put(name, person);
        }
    }
    
    // pre: both names are not null and exist in the system
    // post: creates a friendship between two people if they are not already friends
    public void makeFriends(String name1, String name2) {
        if (name1.equals(name2)) {
            return;
        }
        
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        
        if (person1 != null && person2 != null) {
            String friendshipKey = createFriendshipKey(name1, name2);
            if (!friendships.containsKey(friendshipKey)) {
                person1.addFriend(person2);
                person2.addFriend(person1);
                friendships.put(friendshipKey, true);
            }
        }
    }
    
    // pre: both names are not null and exist in the system
    // post: removes the friendship between two people if they are friends
    public void unfriend(String name1, String name2) {
        if (name1.equals(name2)) {
            return;
        }
        
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        
        if (person1 != null && person2 != null) {
            String friendshipKey = createFriendshipKey(name1, name2);
            if (friendships.containsKey(friendshipKey)) {
                person1.removeFriend(person2);
                person2.removeFriend(person1);
                friendships.remove(friendshipKey);
            }
        }
    }
    
    // pre: name is not null and exists in the system
    // post: lists all friends of the person with the given name
    public void listFriends(String name) {
        Person person = people.get(name);
        if (person != null) {
            LinkedList<Person> friends = person.getFriends();
            if (friends.isEmpty()) {
                System.out.println("Friend(s): ");
            } else {
                System.out.print("Friend(s): ");
                for (int i = 0; i < friends.size(); i++) {
                    System.out.print(friends.get(i).getName());
                    if (i < friends.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    // pre: both names are not null and exist in the system
    // post: checks if two people are friends and prints the result
    public void checkFriendship(String name1, String name2) {
        String friendshipKey = createFriendshipKey(name1, name2);
        if (friendships.containsKey(friendshipKey) && friendships.get(friendshipKey)) {
            System.out.println("Yes, they are friends");
        } else {
            System.out.println("No, they are not friends");
        }
    }
    
    // pre: commandLine is a valid command string
    // post: processes the command and performs the corresponding action
    private boolean processCommand(String commandLine) {
        String[] parts = commandLine.trim().split("\\s+");
        String command = parts[0].toUpperCase();
        
        // swith case to handle different commands
        // best practice is to use switch case for better readability and maintainability
        // and is more efficient than multiple if-else statements
        // extremely fast for small number of cases
        switch (command) {
            case "P":
                if (parts.length >= 2) {
                    createPerson(parts[1]);
                }
                break;
            case "F":
                if (parts.length >= 3) {
                    makeFriends(parts[1], parts[2]);
                }
                break;
            case "U":
                if (parts.length >= 3) {
                    unfriend(parts[1], parts[2]);
                }
                break;
            case "L":
                if (parts.length >= 2) {
                    listFriends(parts[1]);
                }
                break;
            case "Q":
                if (parts.length >= 3) {
                    checkFriendship(parts[1], parts[2]);
                }
                break;
            case "X":
                return false; // terminate program
            default:
                System.out.println("Invalid command");
                break;
        }
        return true;
    }
    
    // pre: none
    // post: runs the MicroFB program, processes commands, and interacts with the user
    // using run method to encapsulate the main logic beacause it allows for better organization
    // by offloading the main logic from the main method
    public void run() {
        System.out.println("MicroFB - Mini Facebook Program");
        System.out.println("Commands: P <name>, F <name1> <name2>, U <name1> <name2>, L <name>, Q <name1> <name2>, X");
        
        while (true) {
            String commandLine = scanner.nextLine();
            if (!processCommand(commandLine)) {
                break;
            }
        }
        
        scanner.close();
    }
    
    // pre: none
    public static void main(String[] args) {
        Pd5AnikethBandlamudiMicroFB microFB = new Pd5AnikethBandlamudiMicroFB();
        // run method to allow for better handling of the main logic
        microFB.run();
    }
}

// end of program