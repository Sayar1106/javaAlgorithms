/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] pointcopy;
    private ArrayList<LineSegment> lineSegments;
    private final LineSegment[] result;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Null parameter.");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Null value.");
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Repeated values");
                }
            }

        }
        this.pointcopy = points.clone();
        Arrays.sort(pointcopy);
        final int n = points.length;
        this.lineSegments = new ArrayList<LineSegment>();
        for (int i = 0; i < n - 3; i++) {
            Point[] copy = pointcopy.clone();
            Point currentPoint = pointcopy[i];
            Arrays.sort(copy, currentPoint.slopeOrder());
            int count = 0;
            int j = 0;
            for (; j < n; count = 1) {
                boolean issmaller = false;
                double currentSlope;
                do {
                    currentSlope = currentPoint.slopeTo(copy[j]);
                    issmaller = issmaller || (copy[j].compareTo(currentPoint) < 0);
                    count++;
                    j++;
                } while (j < n
                        && Double.compare(currentPoint.slopeTo(copy[j]), currentSlope) == 0);
                if (count >= 4 && !issmaller) {
                    lineSegments.add(new LineSegment(currentPoint, copy[j - 1]));
                }
            }
        }
        result = lineSegments.toArray(new LineSegment[0]);


    }

    public int numberOfSegments() {
        return result.length;
    }

    public LineSegment[] segments() {
        return result.clone();
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

