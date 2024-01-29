package data;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user_requests")
public class UserRequest {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user_requests_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "r", nullable = false)
    private Double r;

    @Column(name = "result", nullable = false)
    private Boolean result;

    @Column(name = "request_time", nullable = false)
    private Timestamp requestTime;

    @Column(name = "executeion_time", nullable = false)
    private Double executionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }
}