public class ServiceRequest {
    private final String studentId;
    private final String request;

    public ServiceRequest(String studentId, String request) {
        this.studentId = studentId;
        this.request = request;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + " | Request: " + request;
    }
}
