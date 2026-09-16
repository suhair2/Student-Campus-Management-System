import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentLinkedList students = new StudentLinkedList();
    private static final ActionStack actions = new ActionStack();
    private static final ServiceQueue requests = new ServiceQueue();
    private static final BST bst = new BST();
    private static final HashTable hash = new HashTable();
    private static final CampusGraph graph = new CampusGraph();

    public static void main(String[] args) {
        seedDemoData();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ", 1, 16);
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> students.display();
                case 5 -> addRequest();
                case 6 -> processRequest();
                case 7 -> actions.display();
                case 8 -> bst.displayInOrder();
                case 9 -> searchHash();
                case 10 -> addLocation();
                case 11 -> removeLocation();
                case 12 -> addConnection();
                case 13 -> removeConnection();
                case 14 -> graph.display();
                case 15 -> traverseGraph();
                case 16 -> running = false;
            }
            System.out.println();
        }
        System.out.println("Program ended.");
    }

    private static void printMenu() {
        System.out.println("\n===== UNIVERSITY STUDENT & CAMPUS SYSTEM =====");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST/AVL");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS/DFS");
        System.out.println("16. Exit");
    }

    private static void addStudent() {
        String id = readText("Student ID: ");
        if (students.containsId(id)) {
            System.out.println("Duplicate Student ID.");
            return;
        }
        String name = readText("Name: ");
        String programme = readText("Programme: ");
        double marks = readDouble("Marks (0-100): ", 0, 100);
        Student s = new Student(id, name, programme, marks);
        students.add(s);
        bst.insert(s);
        hash.put(s);
        actions.push("Added student " + id);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readText("Student ID to update: ");
        Student s = students.find(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        String name = readText("New name: ");
        String programme = readText("New programme: ");
        double marks = readDouble("New marks (0-100): ", 0, 100);
        students.update(id, name, programme, marks);
        // The same Student object is referenced by BST/HashTable, so updates remain consistent.
        actions.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readText("Student ID to delete: ");
        Student removed = students.delete(id);
        if (removed == null) {
            System.out.println("Student not found.");
            return;
        }
        hash.remove(id);
        actions.push("Deleted student " + id);
        System.out.println("Student deleted successfully.");
        System.out.println("Note: BST keeps its original nodes in this simple implementation; use the linked list/hash as the authoritative current record store.");
    }

    private static void addRequest() {
        String id = readText("Student ID: ");
        if (students.find(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        String request = readText("Service request: ");
        requests.add(new ServiceRequest(id, request));
        actions.push("Added service request for " + id);
        System.out.println("Request added to queue.");
    }

    private static void processRequest() {
        ServiceRequest r = requests.processNext();
        if (r == null) System.out.println("Queue is empty.");
        else {
            System.out.println("Processed: " + r);
            actions.push("Processed a service request");
        }
    }

    private static void searchHash() {
        String id = readText("Student ID to search: ");
        Student s = hash.search(id);
        if (s == null) System.out.println("Student not found in hash table.");
        else System.out.println("Found: " + s);
    }

    private static void addLocation() {
        String location = readText("Campus location: ");
        System.out.println(graph.addLocation(location) ? "Location added." : "Duplicate location.");
    }

    private static void removeLocation() {
        String location = readText("Campus location to remove: ");
        System.out.println(graph.removeLocation(location) ? "Location removed." : "Location not found.");
    }

    private static void addConnection() {
        String a = readText("First location: ");
        String b = readText("Second location: ");
        System.out.println(graph.addConnection(a, b) ? "Connection added." : "Could not add connection.");
    }

    private static void removeConnection() {
        String a = readText("First location: ");
        String b = readText("Second location: ");
        System.out.println(graph.removeConnection(a, b) ? "Connection removed." : "Connection not found.");
    }

    private static void traverseGraph() {
        String start = readText("Starting location: ");
        System.out.println("1. BFS");
        System.out.println("2. DFS");
        int c = readInt("Choose traversal: ", 1, 2);
        if (c == 1) graph.bfs(start);
        else graph.dfs(start);
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.println("Please enter a value from " + min + " to " + max + ".");
        }
    }

    private static void seedDemoData() {
        Student a = new Student("0546", "A.B.M SUhair", "Computer Science", 76);
        Student b = new Student("0557", "M.M Manaseer", "Software Engineering", 77);
        Student c = new Student("0845", "A.p Sabras", "Information Technology", 78);
        Student d = new Student("0581", "T Apna", "Electrical Engineering", 79);
        students.add(a); students.add(b); students.add(c); students.add(d);
        bst.insert(a); bst.insert(b); bst.insert(c); bst.insert(d);
        hash.put(a); hash.put(b); hash.put(c); hash.put(d);
        graph.addLocation("Main Gate");
        graph.addLocation("Library");
        graph.addLocation("Engineering Block");
        graph.addLocation("Cafeteria");
        graph.addConnection("Main Gate", "Library");
        graph.addConnection("Library", "Engineering Block");
        graph.addConnection("Library", "Cafeteria");
    }
}
