package utils;

public class TriangleUtils {

    public static Double getArea(Double a, Double b, Double c) {
        Double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static Double getPerimeter(Double a, Double b, Double c) {
        return a + b + c;
    }

}
