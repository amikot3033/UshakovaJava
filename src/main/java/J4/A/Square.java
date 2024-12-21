package J4.A;

class Square {
    private Segment[] sides = new Segment[4];
    private String color;

    public Square(Point bottomLeft, double sideLength) {
        Point bottomRight = new Point(bottomLeft.getX() + sideLength, bottomLeft.getY());
        Point topRight = new Point(bottomLeft.getX() + sideLength, bottomLeft.getY() + sideLength);
        Point topLeft = new Point(bottomLeft.getX(), bottomLeft.getY() + sideLength);

        sides[0] = new Segment(bottomLeft, bottomRight);
        sides[1] = new Segment(bottomRight, topRight);
        sides[2] = new Segment(topRight, topLeft);
        sides[3] = new Segment(topLeft, bottomLeft);

        this.color = "Желтый"; // Цвет по умолчанию
    }

    public void setSideLength(double newSideLength) {
        Point bottomLeft = sides[0].getStart();
        Point bottomRight = new Point(bottomLeft.getX() + newSideLength, bottomLeft.getY());
        Point topRight = new Point(bottomLeft.getX() + newSideLength, bottomLeft.getY() + newSideLength);
        Point topLeft = new Point(bottomLeft.getX(), bottomLeft.getY() + newSideLength);

        sides[0] = new Segment(bottomLeft, bottomRight);
        sides[1] = new Segment(bottomRight, topRight);
        sides[2] = new Segment(topRight, topLeft);
        sides[3] = new Segment(topLeft, bottomLeft);
    }

    public void stretch(double factor) {
        setSideLength(getSideLength() * factor);
    }

    public void compress(double factor) {
        setSideLength(getSideLength() / factor);
    }

    public void rotate(double angle) {
        Point center = getCenter();
        for (Segment side : sides) {
            side.rotate(center, angle);
        }
    }

    public void changeColor(String newColor) {
        this.color = newColor;
    }

    public double getSideLength() {
        return sides[0].length();
    }

    public Point getCenter() {
        Point bottomLeft = sides[0].getStart();
        Point topRight = sides[2].getStart();
        double centerX = (bottomLeft.getX() + topRight.getX()) / 2;
        double centerY = (bottomLeft.getY() + topRight.getY()) / 2;
        return new Point(centerX, centerY);
    }

    public String getColor() {
        return color;
    }

    public void printInfo() {
        System.out.println("Квадрат:");
        System.out.println("Стороны:");
        for (int i = 0; i < sides.length; i++) {
            Segment side = sides[i];
            System.out.println("Сторона " + (i + 1) + ": Начало (" + side.getStart().getX() + ", " + side.getStart().getY() + ") -> Конец (" + side.getEnd().getX() + ", " + side.getEnd().getY() + ")");
        }
        System.out.println("Длина: " + getSideLength());
        System.out.println("Цвет: " + getColor());
    }


    public static void main(String[] args) {
        Point bottomLeft = new Point(0, 0);
        Square square = new Square(bottomLeft, 5);

        square.printInfo();

        System.out.println("\nРастяжение в 2 раза:");
        square.stretch(2);
        square.printInfo();

        System.out.println("\nРастяжение в 2 раза:");
        square.compress(5);
        square.printInfo();

        System.out.println("\nПоворот на 45 градусов:");
        square.rotate(90);
        square.printInfo();

        System.out.println("\nИзменение цвета на красный:");
        square.changeColor("Красный");
        square.printInfo();
    }
}

