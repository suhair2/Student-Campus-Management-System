import java.util.HashMap;

public class HashTable {
    private final HashMap<String, Student> table = new HashMap<>();

    public void put(Student student) {
        table.put(student.getStudentId().toLowerCase(), student);
    }

    public Student search(String id) {
        return table.get(id.toLowerCase());
    }

    public void remove(String id) {
        table.remove(id.toLowerCase());
    }

    public void display() {
        if (table.isEmpty()) {
            System.out.println("Hash table is empty.");
            return;
        }
        for (Student s : table.values()) System.out.println(s);
    }
}
