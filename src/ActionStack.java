import java.util.Stack;

public class ActionStack {
    private final Stack<String> actions = new Stack<>();

    public void push(String action) { actions.push(action); }

    public void display() {
        if (actions.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        System.out.println("Recent Actions (latest first):");
        for (int i = actions.size() - 1; i >= 0; i--) {
            System.out.println("- " + actions.get(i));
        }
    }
}
