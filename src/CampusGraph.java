import java.util.*;

public class CampusGraph {
    private final Map<String, Set<String>> graph = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        if (graph.containsKey(location)) return false;
        graph.put(location, new LinkedHashSet<>());
        return true;
    }

    public boolean removeLocation(String location) {
        if (!graph.containsKey(location)) return false;
        graph.remove(location);
        for (Set<String> neighbours : graph.values()) neighbours.remove(location);
        return true;
    }

    public boolean addConnection(String a, String b) {
        if (!graph.containsKey(a) || !graph.containsKey(b) || a.equalsIgnoreCase(b)) return false;
        graph.get(a).add(b);
        graph.get(b).add(a);
        return true;
    }

    public boolean removeConnection(String a, String b) {
        if (!graph.containsKey(a) || !graph.containsKey(b)) return false;
        boolean removed = graph.get(a).remove(b);
        graph.get(b).remove(a);
        return removed;
    }

    public void display() {
        if (graph.isEmpty()) {
            System.out.println("No campus locations.");
            return;
        }
        for (String location : graph.keySet()) {
            System.out.println(location + " -> " + graph.get(location));
        }
    }

    public void bfs(String start) {
        if (!graph.containsKey(start)) {
            System.out.println("Location not found.");
            return;
        }
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        visited.add(start);
        System.out.print("BFS: ");
        while (!q.isEmpty()) {
            String current = q.poll();
            System.out.print(current + " ");
            for (String next : graph.get(current)) {
                if (visited.add(next)) q.offer(next);
            }
        }
        System.out.println();
    }

    public void dfs(String start) {
        if (!graph.containsKey(start)) {
            System.out.println("Location not found.");
            return;
        }
        Set<String> visited = new LinkedHashSet<>();
        System.out.print("DFS: ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (String next : graph.get(current)) {
            if (!visited.contains(next)) dfsRecursive(next, visited);
        }
    }
}
