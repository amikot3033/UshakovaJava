package J3;// 5. Класс Интервал

import java.util.Arrays;
import java.util.List;

class Interval {
    private double start;
    private double end;
    private boolean startInclusive;
    private boolean endInclusive;

    public Interval(double start, double end, boolean startInclusive, boolean endInclusive) {
        this.start = start;
        this.end = end;
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    public Interval intersect(Interval other) {
        double newStart = Math.max(this.start, other.start);
        double newEnd = Math.min(this.end, other.end);
        boolean newStartInclusive = (this.start == newStart ? this.startInclusive : other.startInclusive) && newStart == this.start;
        boolean newEndInclusive = (this.end == newEnd ? this.endInclusive : other.endInclusive) && newEnd == this.end;

        if (newStart > newEnd || (newStart == newEnd && (!newStartInclusive || !newEndInclusive))) {
            return null;
        }
        return new Interval(newStart, newEnd, newStartInclusive, newEndInclusive);
    }

    public Interval union(Interval other) {
        if (this.end < other.start || other.end < this.start) {
            return null; // No union possible if intervals don't overlap
        }

        double newStart = Math.min(this.start, other.start);
        double newEnd = Math.max(this.end, other.end);
        boolean newStartInclusive = (this.start == newStart ? this.startInclusive : other.startInclusive);
        boolean newEndInclusive = (this.end == newEnd ? this.endInclusive : other.endInclusive);

        return new Interval(newStart, newEnd, newStartInclusive, newEndInclusive);
    }

    public double length() {
        return this.end - this.start;
    }

    @Override
    public String toString() {
        return (startInclusive ? "[" : "(") + start + ", " + end + (endInclusive ? "]" : ")");
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(1, 5, true, false),
                new Interval(4, 10, false, true),
                new Interval(8, 12, true, true),
                new Interval(0, 3, false, true)
        );

        System.out.println("Интервалы:");
        for (Interval interval : intervals) {
            System.out.println(interval);
        }


        double minStart = intervals.stream().mapToDouble(i -> i.start).min().orElse(0);
        double maxEnd = intervals.stream().mapToDouble(i -> i.end).max().orElse(0);

        System.out.println("Максимальное расстояние: " + (maxEnd - minStart));

        Interval resultIntersect = intervals.get(0).intersect(intervals.get(1));
        Interval resultUnion = intervals.get(0).union(intervals.get(1));

        System.out.println("Пересечение интервалов: " + intervals.get(0) + " и "+ intervals.get(1) + ": " + (resultIntersect != null ? resultIntersect : "Нет пересечения"));
        System.out.println("Объединение интервалов: " + intervals.get(0) + " и "+ intervals.get(1) + ": " + (resultUnion != null ? resultUnion : "Нельзя обхединить"));
    }
}