package J3;

import java.util.ArrayList;
import java.util.List;

class Point {
    private final List<Double> xHistory;
    private final List<Double> yHistory;
    private final List<Double> zHistory;
    private final List<Double> timeHistory;

    public Point(double x, double y, double z, double time) {
        this.xHistory = new ArrayList<>();
        this.yHistory = new ArrayList<>();
        this.zHistory = new ArrayList<>();
        this.timeHistory = new ArrayList<>();

        this.xHistory.add(x);
        this.yHistory.add(y);
        this.zHistory.add(z);
        this.timeHistory.add(time);
    }

    public void move(double dx, double dy, double dz, double dt) {
        double currentX = getCurrentX();
        double currentY = getCurrentY();
        double currentZ = getCurrentZ();
        double currentTime = getCurrentTime();

        xHistory.add(currentX + dx);
        yHistory.add(currentY + dy);
        zHistory.add(currentZ + dz);
        timeHistory.add(currentTime + dt);
    }

    public double distanceTo(Point other) {
        double dx = getCurrentX() - other.getCurrentX();
        double dy = getCurrentY() - other.getCurrentY();
        double dz = getCurrentZ() - other.getCurrentZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double velocityTo(Point other) {
        double distance = this.distanceTo(other);
        double timeDiff = other.getCurrentTime() - this.getCurrentTime();
        if (timeDiff == 0) throw new IllegalArgumentException("Time difference cannot be zero.");
        return distance / timeDiff;
    }

    public double accelerationTo(Point prev, Point next) {
        double v1 = prev.velocityTo(this);
        double v2 = this.velocityTo(next);
        double timeDiff = next.getCurrentTime() - prev.getCurrentTime();
        return (v2 - v1) / timeDiff;
    }

    public boolean willIntersect(Point other, double velocityX, double velocityY, double velocityZ) {
        if (this.getCurrentTime() == other.getCurrentTime()) return false;

        double relativeX = other.getCurrentX() - this.getCurrentX();
        double relativeY = other.getCurrentY() - this.getCurrentY();
        double relativeZ = other.getCurrentZ() - this.getCurrentZ();

        double relativeVelocityX = velocityX;
        double relativeVelocityY = velocityY;
        double relativeVelocityZ = velocityZ;

        double tX = relativeVelocityX != 0 ? relativeX / relativeVelocityX : Double.MAX_VALUE;
        double tY = relativeVelocityY != 0 ? relativeY / relativeVelocityY : Double.MAX_VALUE;
        double tZ = relativeVelocityZ != 0 ? relativeZ / relativeVelocityZ : Double.MAX_VALUE;

        return Double.compare(tX, tY) == 0 && Double.compare(tY, tZ) == 0 && tX > 0;
    }

    public double getCurrentX() {
        return xHistory.get(xHistory.size() - 1);
    }

    public double getCurrentY() {
        return yHistory.get(yHistory.size() - 1);
    }

    public double getCurrentZ() {
        return zHistory.get(zHistory.size() - 1);
    }

    public double getCurrentTime() {
        return timeHistory.get(timeHistory.size() - 1);
    }

    @Override
    public String toString() {
        return "(" + getCurrentX() + ", " + getCurrentY() + ", " + getCurrentZ() + ", t=" + getCurrentTime() + ")";
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0, 0, 0);
        Point p2 = new Point(3, 4, 0, 1);
        Point p3 = new Point(6, 8, 0, 2);

        System.out.println("Начальные позиции:");
        System.out.println("Точка 1: " + p1);
        System.out.println("Точка 2: " + p2);
        System.out.println("Расстояние между точками: " + p1.distanceTo(p2));

        p1.move(1, 1, 1, 1);
        p2.move(-1, -1, -1, 1);

        System.out.println("\nПосле движения:");
        System.out.println("Точка 1: " + p1);
        System.out.println("Точка 2: " + p2);
        System.out.println("Расстояние между точками: " + p1.distanceTo(p2));

        System.out.println("Скорость точки 1 относительно точки 2: " + p1.velocityTo(p2));

        double acceleration = p1.accelerationTo(p2, p3);
        System.out.println("Ускорение точки: " + acceleration);

        boolean intersect = p1.willIntersect(p2, 3, 4, 0);
        System.out.println("\nПроизойдет ли пересечение траекторий: " + (intersect ? "Да" : "Нет"));
    }
}
