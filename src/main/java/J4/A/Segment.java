package J4.A;

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public void rotate(Point center, double angle) {
        double radians = Math.toRadians(angle) / 2;
        System.out.println(radians);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);

        double startX = start.getX() - center.getX();
        double startY = start.getY() - center.getY();
        double endX = end.getX() - center.getX();
        double endY = end.getY() - center.getY();

        // Новые координаты начала отрезка
        double rotatedStartX = center.getX() + (startX * cos - startY * sin);
        double rotatedStartY = center.getY() + (startX * sin + startY * cos);
        start.setX(round(rotatedStartX));
        start.setY(round(rotatedStartY));

        // Новые координаты конца отрезка
        double rotatedEndX = center.getX() + (endX * cos - endY * sin);
        double rotatedEndY = center.getY() + (endX * sin + endY * cos);
        end.setX(round(rotatedEndX));
        end.setY(round(rotatedEndY));
    }

    // Метод округления для повышения точности
    private double round(double value) {
        double scale = Math.pow(10, 6); // 6 знаков после запятой
        return Math.round(value * scale) / scale;
    }

}
