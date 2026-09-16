public class StudentLinkedList {
    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;

    public boolean containsId(String id) {
        return find(id) != null;
    }

    public Student find(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) return current.data;
            current = current.next;
        }
        return null;
    }

    public boolean add(Student student) {
        if (containsId(student.getStudentId())) return false;
        Node n = new Node(student);
        if (head == null) head = n;
        else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = n;
        }
        return true;
    }

    public boolean update(String id, String name, String programme, double marks) {
        Student s = find(id);
        if (s == null) return false;
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

    public Student delete(String id) {
        Node current = head, previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) {
                if (previous == null) head = current.next;
                else previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
