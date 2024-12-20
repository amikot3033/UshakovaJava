package J4.A;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = round(x);
    }

    public void setY(double y) {
        this.y = round(y);
    }

    private double round(double value) {
        double scale = Math.pow(10, 6); // Округление до 6 знаков после запятой
        return Math.round(value * scale) / scale;
    }
}
