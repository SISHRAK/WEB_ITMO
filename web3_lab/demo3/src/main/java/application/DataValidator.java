package application;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Arrays;

@Named
@SessionScoped
public class DataValidator implements Serializable {
    Double[] allowedR = new Double[] {1.0, 1.5, 2.0, 2.5, 3.0};

    public boolean isDataCorrect(Double x, Double y, Double r) {
        return Arrays.asList(allowedR).contains(r) && y >=-3 && y <= 3 && x >= -3 && x <= 3;
    }
}