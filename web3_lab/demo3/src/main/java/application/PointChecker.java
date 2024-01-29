package application;

import data.UserRequest;
import integration.ActiveSessions;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.sql.Timestamp;

import java.util.List;

@Named
@SessionScoped
public class PointChecker implements Serializable {


    private Double x;
    private Double y;
    private Double r;

    private final Integer xMin = -3;
    private final Integer yMin = -3;

    private final Integer xMax = 3;
    private final Integer yMax = 3;

    private Timestamp requestTime;
    private long requestStartedTime;

    @Inject
    ActiveSessions activeSessionsDao;

    public String processRequest() {
        startTimer();

        boolean result = isPointInArea(x, y, r);

        UserRequest request = new UserRequest();
        request.setRequestTime(requestTime);
        request.setX(x);
        request.setY(y);
        request.setR(r);
        request.setResult(result);
        double duration = requestStartedTime - System.currentTimeMillis();
        request.setExecutionTime(duration);

        activeSessionsDao.saveRequest(request);

        return "main?faces-redirect=true";
    }

    public List<UserRequest> getRequests() {
        return activeSessionsDao.getRequests();
    }

    private void startTimer() {
        requestStartedTime = System.currentTimeMillis();
        requestTime = new Timestamp(requestStartedTime);
    }

    private boolean isPointInArea(Double x, Double y, Double r) {
        return checkRectangle(x, y, r) || checkTriangle(x, y, r) || checkArc(x, y, r);
    }

    private boolean checkRectangle(Double x, Double y, Double r) {
        return x >= 0 && x <= (r / 2.0) && y <= r && y >= 0;
    }

    private boolean checkTriangle(Double x, Double y, Double r) {
        return x <= 0 && y >= 0 && y <= (r + 2.0 * x);
    }

    private boolean checkArc(Double x, Double y, Double r) {
        return x <= 0 && y <= 0 && ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2));
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

    public Integer getxMin() {
        return xMin;
    }

    public Integer getyMin() {
        return yMin;
    }


    public Integer getxMax() {
        return xMax;
    }

    public Integer getyMax() {
        return yMax;
    }

}
