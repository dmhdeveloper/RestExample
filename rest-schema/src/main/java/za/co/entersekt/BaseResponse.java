package za.co.entersekt;

public class BaseResponse {

    private Status status;
    private String message;

    public Status getStatus() {
        return status;
    }

    public BaseResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
