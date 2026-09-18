public class BST {
    private static class Node {
        Student student;
        Node left, right;
        Node(Student student) { this.student = student; }
    }

    private Node root;

    public void insert(Student student) {
        root = insert(root, student);
    }

    private Node insert(Node node, Student student) {
        if (node == null) return new Node(student);
        int cmp = student.getStudentId().compareToIgnoreCase(node.student.getStudentId());
        if (cmp < 0) node.left = insert(node.left, student);
        else if (cmp > 0) node.right = insert(node.right, student);
        return node;
    }

    public Student search(String id) {
        Node current = root;
        while (current != null) {
            int cmp = id.compareToIgnoreCase(current.student.getStudentId());
            if (cmp == 0) return current.student;
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.student);
        inOrder(node.right);
    }
}
