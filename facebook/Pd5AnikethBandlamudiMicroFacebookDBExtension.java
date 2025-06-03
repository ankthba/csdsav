/*

Name: Aniketh Bandlamudi
Period: 5
Name of the Lab: MicroFacebookDBExtension

Purpose of the Program:
To extend the MicroFB functionality by generating random names, storing them in a file, 
and allowing users to manage friendships through a command-line interface.

The program works/partially work/does not compile/has run time errors and where ... 
N/A, No errors, works as expected.

What I learned (in bullet form):
- How to generate random names and ensure uniqueness.
- How to read from and write to files in Java.
- How to extend existing functionality by adding new features like file operations.

How I feel about this lab: 
I feel that this lab was a great exercise in extending existing code to add new features,

What I wonder:
- How to handle larger datasets efficiently.

What part of this program did you find most challenging?
The most challenging part was ensuring that the random names generated were unique
and correctly formatted when written to the file. I implemented a check to avoid duplicates.

Describe one bug you fixed — what was wrong, and how did you fix it?
One bug I fixed was related to reading names from the file. Initially, it did not handle
empty lines or improperly formatted lines correctly. I added checks to ensure that only valid lines were processed.


What part are you proud of?
I am proud of the way I structured the code to separate concerns,
and how I implemented file operations to enhance the functionality of the MicroFB system.

What advice would you give a student doing this next year?
I would advise students to focus on understanding how to manage data structures effectively,
and to practice reading from and writing to files, as these are essential skills in many programming tasks.

*/


import java.util.*;
import java.io.*;
import java.net.URL;

public class Pd5AnikethBandlamudiMicroFacebookDBExtension {
    
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
        public Person(String name) {
            this.name = name;
            this.friends = new LinkedList<>();
        }
        
        // pre: name is not null
        // post: creates a Person object with the given name and initializes an empty friends list
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
            // check if friend is not null and not equal to this person
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
        public boolean equals(Object obj) {
            // check if obj is the same instance
            if (this == obj){
                return true;
            }
            // check if obj is an instance of Person
            if (obj == null || getClass() != obj.getClass()){
                return false;
            }
            Person person = (Person) obj;
            // check if names are equal
            return ((name == person.name) || (name != null && name.equals(person.name)));
        }
        
        // pre: none
        // post: returns a hash code for the person based on their name
        public int hashCode() {
            return Objects.hash(name);
        }
        
        // pre: allPeople is a map of all Person objects indexed by name
        // post: returns a list of suggested friends based on mutual friends
        public List<Person> getSuggestedFriends(Map<String, Person> allPeople) {
            Set<Person> suggestions = new HashSet<>();
            Set<Person> alreadyFriends = new HashSet<>(friends);
            alreadyFriends.add(this); // dont suggest self
            
            // find friends of friends
            // use for each loop to iterate through friends and their friends
            for (Person friend : friends) {
                // use for each loop to iterate through each friend's friends
                for (Person friendOfFriend : friend.friends) {
                    // dont add if already friends
                    if (!alreadyFriends.contains(friendOfFriend)) {
                        suggestions.add(friendOfFriend);
                    }
                }
            }
            
            return new ArrayList<>(suggestions);
        }
    }
    
    // hash map to store Person objects indexed by name
    private HashMap<String, Person> people;
    // hash map to store friendship relationships using combined names as keys
    private HashMap<String, Boolean> friendships;
    private Scanner scanner;
    private Random random;
    
    // constructor for Pd5AnikethBandlamudiMicroFacebookDBExtension
    public Pd5AnikethBandlamudiMicroFacebookDBExtension() {
        people = new HashMap<>();
        friendships = new HashMap<>();
        scanner = new Scanner(System.in);
        random = new Random();
    }
    
    // pre: length > 0
    // post: returns a random name of specified length consisting of lowercase letters
    private String generateRandomName(int length) {
        // stringBuilder is used to efficiently build the random name
        // instead of using String concatenation in a loop
        StringBuilder name = new StringBuilder();
        // use StringBuilder for efficient string concatenation
        for (int i = 0; i < length; i++) {
            // generate a random character from 'a' to 'z'
            // works by adding a random integer between 0 and 25 to 'a'
            // uses a + ... because 'a' is the first letter in the alphabet
            char c = (char) ('a' + random.nextInt(26));
            name.append(c);
        }
        return name.toString();
    }
    
    // pre: numNames > 0, filename is a valid file path
    // post: generates numNames random names and writes them to the specified file
    private void generateNamesToFile(int numNames, String filename) {
        // try-catch block to handle IO exceptions such as FileNotFoundException
        // PrintWriter is used to write to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            Set<String> generatedNames = new HashSet<>();
            
            int i = 0;
            // use a while loop to ensure we generate exactly numNames unique names
            while (i < numNames) {
                String name = generateRandomName(5);
                if (!generatedNames.contains(name)) {
                    generatedNames.add(name);
                    writer.println("P " + name);
                    i++;
                }
            }

            // for (int i = 0; i < numNames; i++) {
            //     String name;
            //     // use the do while loop to ensure unique names
            //     do {
            //         name = generateRandomName(5);
            //     } while (generatedNames.contains(name)); // ensure unique names, self note to double check this logic
                
            //     generatedNames.add(name);
            //     writer.println("P " + name);
            // } - might not work so commented out and replaced with working code
            
            System.out.println("Generated " + numNames + " random names to " + filename);
        } catch (IOException e) { // catch block to handle IO exceptions such as FileNotFoundException
            // err stream is used to print error messages
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    // pre: filename is a valid file path
    // post: reads names from the specified file and creates Person objects
    private void readNamesFromFile(String filename) {
        // try catch block to handle IO exceptions such as FileNotFoundException
        // BufferedReader is used to read the file line by line and is better than reader
        // in that it can handle larger files more efficiently
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Reading names from " + filename + ":");
            
            // read each line from the file
            while ((line = reader.readLine()) != null) {
                // trim the line to remove leading and trailing whitespace
                String[] parts = line.trim().split("\\s+");
                // check if the line starts with "P" and has at least one more part
                if (parts.length >= 2 && parts[0].equals("P")) {
                    String name = parts[1];
                    createPerson(name);
                    System.out.println("Created person: " + name);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    
    // pre: none
    // post: displays all people in the system
    private void displayAllPeople() {
        // print a header for the list of people
        System.out.println("\nPeople in the system:");
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
        } else {
            for (String name : people.keySet()) {
                System.out.println("- " + name);
            }
        }
        System.out.println();
    }
    
    // pre: name1 and name2 are not null
    // post: returns a unique key for the friendship between name1 and name2
    private String createFriendshipKey(String name1, String name2) {
        // ensure names are compared in a consistent order to avoid duplicates
        if (name1.compareTo(name2) <= 0) {
            return name1 + "*" + name2;
        } else {
            return name2 + "*" + name1;
        }
    }
    
    // pre: name is not null
    // post: creates a Person object with the given name if it does not already exist
    private void createPerson(String name) {
        // if name is not null and does not already exist in the system
        if (!people.containsKey(name)) {
            Person person = new Person(name);
            people.put(name, person);
        }
    }
    
    // pre: name1 and name2 are not null and exist in the system
    // post: creates a friendship between two people if they are not already friends
    private void makeFriends(String name1, String name2) {
        if (name1.equals(name2)) {
            return;
        }
        
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        
        // check if both people exist in the system
        if (person1 != null && person2 != null) {
            // they key will always be unique irrespective of the order of names
            String friendshipKey = createFriendshipKey(name1, name2);
            if (!friendships.containsKey(friendshipKey)) {
                // add the friendship to both people
                person1.addFriend(person2);
                person2.addFriend(person1);
                // add the friendship to the friendships map so it is easier to check against the map
                friendships.put(friendshipKey, true);
            }
        }
    }
    
    // pre: name1 and name2 are not null and exist in the system
    // post: removes the friendship between two people if they are friends
    private void unfriend(String name1, String name2) {
        if (name1.equals(name2)) {
            return; // person cant unfriend themselves
        }
        
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        
        // check if both people exist in the system
        if (person1 != null && person2 != null) {
            // create a unique key for the friendship irrespective of the order of names
            String friendshipKey = createFriendshipKey(name1, name2);
            if (friendships.containsKey(friendshipKey)) {
                // since we are adding to both, we remove the friendship from both people
                person1.removeFriend(person2);
                person2.removeFriend(person1);
                // remove the friendship from the friendships map
                friendships.remove(friendshipKey);
            }
        }
    }
    
    // pre: name is not null and exists in the system
    // post: lists all friends of the person with the given name
    private void listFriends(String name) {
        Person person = people.get(name);
        if (person != null) {
            // check if the person has friends
            // use LinkedList to store friends for efficient iteration
            LinkedList<Person> friends = person.getFriends();
            if (friends.isEmpty()) {
                System.out.println(name + " has no friends.");
            } else {
                System.out.println(name + "'s friends:");
                for (Person friend : friends) {
                    System.out.println("- " + friend.getName());
                }
            }
        } else {
            System.out.println("Person not found: " + name);
        }
    }
    
    // pre: name is not null and exists in the system
    // post: lists all friends and their connections (extended friend list)
    // works by iterating through each friend's friends and displaying them
    private void listAllFriends(String name) {
        Person person = people.get(name);
        // check if the person exists in the system
        if (person != null) {
            LinkedList<Person> friends = person.getFriends();
            // check if the person has friends
            if (friends.isEmpty()) {
                System.out.println(name + " has no friends.");

            } else {
                // print the extended friend network
                System.out.println(name + "'s extended friend network:");
                // iterate through each friend and their friends
                for (Person friend : friends) {
                    System.out.println("- " + friend.getName());
                    LinkedList<Person> friendsOfFriend = friend.getFriends();
                    // iterate through each friend's friends
                    for (Person fof : friendsOfFriend) {
                        // print the friend's friend if it's not the original person
                        if (!fof.getName().equals(name)) { // don't include the original person
                            System.out.println("  └─ " + fof.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("Person not found: " + name);
        }
    }
    
    // pre: name is not null and exists in the system
    // post: suggests potential friends based on mutual connections
    // works by finding friends of friends who are not already friends with the person
    private void suggestFriends(String name) {
        Person person = people.get(name);
        if (person != null) {
            // set to track suggested friends and avoid duplicates
            Set<Person> suggestions = new HashSet<>();
            Set<Person> currentFriends = new HashSet<>(person.getFriends());
            currentFriends.add(person); // Add self to exclude from suggestions
            
            // find friends of friends who aren't already friends
            for (Person friend : person.getFriends()) {
                // iterate through each friend's friends
                for (Person friendOfFriend : friend.getFriends()) {
                    if (!currentFriends.contains(friendOfFriend)) {
                        suggestions.add(friendOfFriend);
                    }
                }
            }
            
            // display suggestions
            if (suggestions.isEmpty()) {
                System.out.println("No friend suggestions available for " + name);
            } else {
                // sort suggestions by name for better readability
                System.out.println("Friend suggestions for " + name + ":");
                for (Person suggestion : suggestions) {
                    // count mutual friends for each suggestion
                    System.out.println("- " + suggestion.getName() + " (mutual friends: " + countMutualFriends(person, suggestion) + ")");
                }
            }
        } else {
            System.out.println("Person not found: " + name);
        }
    }
    
    // pre: person1 and person2 are not null and exist in the system
    // post: counts the number of mutual friends between two people
    private int countMutualFriends(Person person1, Person person2) {
        int count = 0;
        // iterate through each friend of person1 and check if they are also a friend of person2
        for (Person friend : person1.getFriends()) {
            if (person2.getFriends().contains(friend)) {
                count++;
            }
        }
        return count;
    }
    
    // pre: none
    // post: displays the available commands to the user
    private void displayCommands() {
        System.out.println("\n=== Available Commands ===");
        System.out.println("C <name> - Create a person");
        System.out.println("F <name1> <name2> - Make two people friends");
        System.out.println("U <name1> <name2> - Unfriend two people");
        System.out.println("LF <name> - List friends of a person");
        System.out.println("LF_ <name> - List all friends and their connections");
        System.out.println("SF <name> - Suggest friends for a person");
        System.out.println("HELP - Display this list of commands");
        System.out.println("Q - Quit the application");
        System.out.println("=========================\n");
    }
    
    // pre: command is a valid command string
    // post: processes the command and performs the corresponding action
    private void processSimpleCommand(String command) {
        String[] parts = command.split("\\s+");
        String cmd = parts[0].toUpperCase();
        
        // swith case to handle different commands
        // best practice is to use switch case for better readability and maintainability
        // and is more efficient than multiple if-else statements
        switch (cmd) {
            case "C":
                if (parts.length > 1) createPerson(parts[1]);
                break;
            case "F":
                if (parts.length > 2) makeFriends(parts[1], parts[2]);
                break;
            case "U":
                if (parts.length > 2) unfriend(parts[1], parts[2]);
                break;
            case "LF":
                if (parts.length > 1) listFriends(parts[1]);
                break;
            case "LF_":
                if (parts.length > 1) listAllFriends(parts[1]);
                break;
            case "SF":
                if (parts.length > 1) suggestFriends(parts[1]);
                break;
            case "HELP":
                displayCommands();
                break;
            case "Q":
                System.out.println("Exiting application...");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command. Type HELP for a list of commands.");
        }
    }
    
    // pre: urlString is a valid URL pointing to a CSV file with names
    // post: fetches names from the URL and returns them as a list
    private static List<String> fetchNamesFromURL(String urlString) {
        List<String> names = new ArrayList<>();
        // try-catch block to handle IO exceptions such as MalformedURLException and IOException
        try {
            // create a URL object from the string
            URL url = new URL(urlString);
            // open a stream to read from the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            
            while ((line = reader.readLine()) != null) {
                // assuming CSV format with names in the first column
                String[] parts = line.split(",");
                if (parts.length > 0 && !parts[0].trim().isEmpty()) {
                    names.add(parts[0].trim());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error fetching names: " + e.getMessage());
        }
        return names;
    }
    
    // pre: scanner is a valid Scanner object and people is a map of Person objects
    // post: imports names from a URL and adds them to the people map
    private static void importNamesFromURL(Scanner scanner, Map<String, Person> people) {
        System.out.print("Enter URL for names CSV file: ");
        String url = scanner.nextLine();
        
        System.out.println("getting names from " + url);
        List<String> names = fetchNamesFromURL(url);
        
        if (names.isEmpty()) {
            System.out.println("No names found or error reading from URL.");
            return;
        }
        
        
        int added = 0;
        // iterate through the list of names and add them to the people map
        for (String name : names) {
            if (!people.containsKey(name)) {
                Person newPerson = new Person(name);
                people.put(name, newPerson);
                added++;
            }
        }
        
        System.out.println("Successfully imported " + added + " new names.");
    }
    
    // pre: scanner is a valid Scanner object and people is a map of Person objects
    // post: shows suggested friends for a given person and allows adding them as friends
    private static void showSuggestedFriends(Scanner scanner, Map<String, Person> people) {
        System.out.print("Enter name to get friend suggestions: ");
        String name = scanner.nextLine();
        
        Person person = people.get(name);
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }
        
        List<Person> suggestions = person.getSuggestedFriends(people);
        
        if (suggestions.isEmpty()) {
            System.out.println("No friend suggestions available for " + name);
            return;
        }
        
        System.out.println("Suggested friends for " + name + ":");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i+1) + ". " + suggestions.get(i).getName());
        }
        
        System.out.print("Enter number to add as friend (or 0 to cancel): ");
        // try-catch block to handle NumberFormatException
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            // check if the choice is valid
            if (choice > 0 && choice <= suggestions.size()) {
                Person newFriend = suggestions.get(choice - 1);
                person.addFriend(newFriend);
                newFriend.addFriend(person);
                System.out.println("Added " + newFriend.getName() + " as a friend.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    
    // pre: scanner is a valid Scanner object and people is a map of Person objects
    // post: handles the suggested friends feature, allowing users to get suggestions or import names
    private void handleSuggestedFriendsFeature() {
        while (true) {
            System.out.println("\n=== Friend Suggestions ===");
            System.out.println("1. Get friend suggestions for a person");
            System.out.println("2. Import names from online source");
            System.out.println("3. Return to main menu");
            System.out.print("Enter your choice: ");
            
            // try-catch block to handle NumberFormatException
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                // switch case to handle different choices for suggested friends feature
                switch (choice) {
                    case 1:
                        showSuggestedFriends(scanner, people);
                        break;
                    case 2:
                        importNamesFromURL(scanner, people);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    // Check if two people are friends
    private void checkFriendship(String name1, String name2) {
        // check if names are the same
        // friendship key will be the same irrespective of the order of names
        String friendshipKey = createFriendshipKey(name1, name2);
        if (friendships.containsKey(friendshipKey)) {
            System.out.println(name1 + " and " + name2 + " are friends.");
        } else {
            System.out.println(name1 + " and " + name2 + " are not friends.");
        }
    }
    
    // pre: commandLine is a valid command string
    // post: processes the command and returns true if the program should continue, false if it should terminate
    private boolean processCommand(String commandLine) {
        String[] parts = commandLine.trim().split("\\s+");
        String command = parts[0].toUpperCase();
        
        // switch case to handle different commands and perform corresponding actions
        switch (command) {
            case "P":
                if (parts.length >= 2) {
                    createPerson(parts[1]);
                    System.out.println("Created person: " + parts[1]);
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
            case "S":
                handleSuggestedFriendsFeature();
                break;
            case "HELP":
                displayCommands();
                break;
            case "LF_":
                if (parts.length >= 2) {
                    listAllFriends(parts[1]);
                }
                break;
            case "SF":
                if (parts.length >= 2) {
                    suggestFriends(parts[1]);
                }
                break;
            case "X":
                return false; // Terminate program
            default:
                System.out.println("Invalid command");
                break;
        }
        return true;
    }
    
    // pre: none
    // post: runs the MicroFacebookDBExtension program, allowing user interaction
    // using run method to encapsulate the main logic beacause it allows for better organization
    // by offloading the main logic from the main method
    public void run() {
        System.out.println("MicroFacebookDBExtension - Mini Facebook with Database Extension");
        
        // ask user for number of names to generate
        System.out.print("How many random names do you want to generate? ");
        int numNames = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // generate filename (you can modify this according to your naming convention)
        String filename = "person.txt";
        
        // generate random names to file
        generateNamesToFile(numNames, filename);
        
        // read names from file and create Person objects
        readNamesFromFile(filename);
        
        // display all people
        displayAllPeople();
        
        // continue with normal MicroFB operations
        System.out.println("You can now continue with MicroFB commands:");
        displayCommands();
        
        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine();
            if (!processCommand(commandLine)) {
                break;
            }
        }
        
        scanner.close();
    }
    
    // pre: none
    // post: main method to start the MicroFacebookDBExtension program
    public static void main(String[] args) {
        Pd5AnikethBandlamudiMicroFacebookDBExtension extension = new Pd5AnikethBandlamudiMicroFacebookDBExtension();
        // run method to allow for better handling of the main logic
        extension.run();
    }
}

// end of program