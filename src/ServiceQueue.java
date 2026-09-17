import java.util.LinkedList;
import java.util.Queue;

public class ServiceQueue {
    private final Queue<ServiceRequest> queue = new LinkedList<>();

    public void add(ServiceRequest request) { queue.offer(request); }

    public ServiceRequest processNext() { return queue.poll(); }

    public void display() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("Pending Service Requests:");
        for (ServiceRequest r : queue) System.out.println("- " + r);
    }
}
