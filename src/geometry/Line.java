//318528171

package geometry;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * @author Itay Shwartz
 * This class represent line and the methods that we can apply on the line.
 */
public class Line implements Sprite {
    private final Point start;
    private final Point end;
    private Color lineColor = null;
    // m is the slop of the line.
    private double m;
    // b is the y rate of the point of intersection with the axis.
    private double b;
    private double xParallelLineValue;
    private boolean isPoint = false;

    /**
     * This is the main constructor of the class. it set up new line.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        // We calculate the slop of the line.
        if (start.getX() - end.getX() == 0) {
            // A line parallel to the y-axis:
            this.m = Double.POSITIVE_INFINITY;
            this.xParallelLineValue = start.getX();
            // If its a line with the same point of start and end.
            if (start.equals(end)) {
                this.isPoint = true;
            } else {
                this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
                // With the equation y=mx+b we get y-mx=b:
                this.b = start.getY() - m * (start.getX());
                // If its a line with the same point of start and end.
                if (start.equals(end)) {
                    this.isPoint = true;
                }
            }
        }
    }

    /**
     * This is the second constructor - if we qet the point separated to parts instead two points.
     *
     * @param x1 - the x value of the start point.
     * @param y1 - the y value of the end point.
     * @param x2 - the x value of the start point.
     * @param y2 - the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // We create two points and put them in to start and end.
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);
        this.start = point1;
        this.end = point2;
        // We calculate the slop of the line.
        if (start.getX() - end.getX() == 0) {
            // A line parallel to the y-axis:
            this.m = Double.POSITIVE_INFINITY;
            this.b = 0;
            this.xParallelLineValue = start.getX();
        } else {
            this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
            // With the equation y=mx+b we get y-mx=b:
            this.b = start.getY() - m * (start.getX());
        }
        // If its a line with the same point of start and end.
        if (start.equals(end)) {
            this.isPoint = true;
        }
    }

    /**
     * This method is another constructor for line (this one have color field).
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     * @param color - the color of the line.
     */
    public Line(Point start, Point end, Color color) {
        this(start, end);
        this.lineColor = color;
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        //Length of line is the distance between the start point to the end point
        return start.distance(end);
    }

    /**
     * This method return the middle point of line.
     *
     * @return middlePoint.
     */
    public Point middle() {
        // First we get the values of the start and end points.
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        // The middle of line is the formula ((x1 + x2)/2, (y1 + y2)/2)
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * This method returns the start point of the line.
     *
     * @return start.
     */
    public Point start() {
        return start;
    }

    /**
     * This method returns the end point of the line.
     *
     * @return end.
     */
    public Point end() {
        return end;
    }

    /**
     * This method returns the m (slope) of the line.
     *
     * @return end.
     */
    public double getM() {
        return this.m;
    }

    /**
     * This method return if true of the point is on the line. otherwise - false.
     *
     * @param point - point that we check
     * @return true / false
     */
    public boolean isPointOnTheLine(Point point) {
        // If m is infinity the line is parallel to x axis.
        if (m == Double.POSITIVE_INFINITY) {

            /*
            If the x value of the point make the equation this.x = point.x correct so the point is on the line.
            but our line is limited so we check if the y of the point is between the y value of start and end,
            */
            if ((point.getX() == this.xParallelLineValue) && (point.getY() >= start.getY()
                    && point.getY() <= end.getY()
                    || point.getY() <= start.getY() && point.getY() >= end.getY())) {
                return true;
            }
            return false;
        }

        /*
         According the equation y=mx+b point that make this equation be correct is on the line. but is not make sure
         that the point in on aur limited line. so we check if the x value of the point is between the x values of
         start and end.
         */
        if ((point.getY() == this.m * point.getX() + this.b) && ((point.getX() >= start.getX()
                && point.getX() <= end.getX()) || (point.getX() <= start.getX() && point.getX() >= end.getX()))) {
            return true;
        }
        // If we not enter to one of the terms then the point is not on the line.
        return false;
    }

    /**
     * Returns true if the lines are intersect, false otherwise.
     *
     * @param other - the other line.
     * @return true / false.
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = intersectionWith(other);

        /*
        If we get from intersectionPoint a specific point - there is intersecting point and we return true.
        if the point is null its mean that there is no intersection point and we return false.
        */
        if (intersectionPoint == null) {
            return false;
        }
        return true;
    }

    /**
     * This method get line and another line. if one line cover another and one point is equal it return true.
     * else it return false.
     *
     * @param other - the other line
     * @return - true / false
     */
    public boolean isLineCoverOtherLineEqualOnePoint(Line other) {
        if ((start.equals(other.start) && isPointOnTheLine(other.end)) || (start.equals(other.end)
                && isPointOnTheLine(other.start)) || (end.equals(other.start) && isPointOnTheLine(other.end))
                || (end.equals(other.end)) && isPointOnTheLine(other.start) || (start.equals(other.start)
                && other.isPointOnTheLine(end)) || (start.equals(other.end) && other.isPointOnTheLine(end))
                || (end.equals(other.start) && other.isPointOnTheLine(start)) || (end.equals(other.start))
                && other.isPointOnTheLine(start)) {
            return true;
        }
        return false;
    }

    /**
     * This method return true if one line cover all the other line.
     *
     * @param other - another line
     * @return true / false
     */
    public boolean isLineCoverAllOtherLine(Line other) {
        if ((isPointOnTheLine(other.start) && isPointOnTheLine(other.end)) || (other.isPointOnTheLine(start)
                && other.isPointOnTheLine(end))) {
            return true;
        }
        return false;
    }

    /**
     * This method return true the line cover the other line or the opposite. otherwise false.\
     *
     * @param other - another line
     * @return true false
     */
    public boolean isPartOfTheLineCoverOther(Line other) {
        if (m != other.m && !isPoint && !other.isPoint) {
            return false;
        }
        if ((isPointOnTheLine(other.start) && !start.equals(other.start) && !end.equals(other.start))
                || (isPointOnTheLine(other.end) && !start.equals(other.end) && !end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * @param other - the other line.
     * @return point - if theres intersection. otherwise null.
     */
    public Point intersectionWith(Line other) {
        // If both of the lines are points - we check if they are equal.
        if (isPoint && other.isPoint) {

            /*
            If both of the lines are point then the start and the end point are the same - do we compare one
            of them.
            */
            if (start.equals(other.start)) {
                return start;
            }
            // If the points not the same - there is no intersection and we return null.
            return null;
        } else if (isPoint) {
            // If the line is a point we check if it on the other line. if so we return the point
            if (other.isPointOnTheLine(start)) {
                return start;
            }
            return null;
        } else if (other.isPoint) {
            // If the other line is a point we check if it on the line. if so we return the point
            if (isPointOnTheLine(other.start)) {
                return other.start;
            }
            return null;
            // If two of the lines have infinity m. and the same x value.
        } else if (m == other.m && m == Double.POSITIVE_INFINITY && xParallelLineValue == xParallelLineValue) {

            /*
            If one of the lines equal in one point and cover the other line we return null. or if line cover
            all of the other line or if part of one line cover part of other line we return null. if the lines
            are equal we also return null.
            */
            if (isLineCoverOtherLineEqualOnePoint(other) || isLineCoverAllOtherLine(other)
                    || isPartOfTheLineCoverOther(other) || equals(other)) {
                return null;
            }

            /*
            Because we have ruled out the other cases where some or all of the line is covered by another
            line, if one of the start or end points is equal to another then this is the point of intersection.
            */
            if (start.equals(other.start) || start.equals(other.end)) {
                return start;
            }
            if (end.equals(other.start) || end.equals(other.end)) {
                return end;
            }
            // If the parallel lines not have the same x value.
            return null;
        }
        // If our line have infinity m and the other have a regular m.
        if (m == Double.POSITIVE_INFINITY) {

            /*
            According the equation of the other line y=mx+b and the line x=n we  will place x in the second
            line equation and see if the point that comes out is on the second line is the intersection.
            */
            double y = other.m * xParallelLineValue + other.b;
            Point point = new Point(xParallelLineValue, y);
            if (isPointOnTheLine(point) && other.isPointOnTheLine(point)) {
                return point;
            }
            return null;
        }
        // If the other line have infinity m and the line have a regular m.
        if (other.m == Double.POSITIVE_INFINITY) {

            /*
            According the equation of the other line y=mx+b and the line x=n we will place x in the second
            line equation and see if the point that comes out is on the second line is the intersection.
            */
            double y = m * other.xParallelLineValue + b;
            Point point = new Point(other.xParallelLineValue, y);
            if (other.isPointOnTheLine(point) && isPointOnTheLine(point)) {
                return point;
            }
            return null;
        }

        /*
        now if the lines are regular and don't have infinity m. if they have the same m and they cover one the
        other, they cover part of the other, they are equals, they have different b we return null.
        */
        if ((m == other.m) && (isLineCoverOtherLineEqualOnePoint(other) || isLineCoverAllOtherLine(other)
                || isPartOfTheLineCoverOther(other) || equals(other) || b != other.b)) {
            return null;
        }

        /*
        geometry.Line represent by y=mx+b. We compare those two lines: m1*x+b1 = m2*x+b2. We try to extract the x
        value - the x value of the intersection of the lines.
        */
        double x = (other.b - b) / (m - other.m);
        // After we get the x value of the intersection we put it into line equation to get the y value.
        double y = m * x + b;
        Point intersectionPoint = new Point(x, y);

        /*
        because the lines are not infinity we need to make sure that the point is in those lines. to make that sure
        we check if the point is between start and end of the lines. we check if x1<=x<=x2 or the opposite on the
        two lines
        */
        if (((other.start.getX() <= x && x <= other.end.getX()) || (other.start.getX() >= x
                && x >= other.end.getX())) && ((start.getX() <= x && x <= end.getX())
                || (start.getX() >= x && x >= end.getX()))) {
            return intersectionPoint;
        }
        // If the intersectionPoint not on the two lines - we return null.
        return null;
    }

    /**
     * The method check if the lines are equals - return true. otherwise return false.
     *
     * @param other - the other line.
     * @return true / false.
     */
    public boolean equals(Line other) {
        // We check if the start and the end points are the same.
        if (start.equals(other.start) && end.equals(other.end) || start.equals(other.end)
                && end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * This method return the closest intersection point between the line to a given rectangle.
     *
     * @param rect - the rectangle
     * @return point- the closest one if there intersection point. null - if there is not intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        // We get all the intersection point.
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        // We go through all the point in the intersectionPoints and search for the closest one to the start.
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (closestPoint == null) {
                closestPoint = intersectionPoints.get(i);
            } else {
                if (closestPoint.distance(this.start) > intersectionPoints.get(i).distance(this.start)) {
                    closestPoint = intersectionPoints.get(i);
                }
            }
        }
        // In the end we return the closest point (could be null if there is no intersection point)
        return closestPoint;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.lineColor);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {
    }
}


