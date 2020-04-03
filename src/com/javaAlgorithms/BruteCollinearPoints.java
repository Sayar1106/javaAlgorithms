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

public class BruteCollinearPoints {
    private Point[] pointcopy;
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Null parameter.");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Null value encountered.");
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Repeated values");
                }

            }
        }
        this.pointcopy = Arrays.copyOf(points, points.length);
        final int n = points.length;
        this.lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(this.pointcopy);
        for (int i = 0; i < n - 3; i++)
            for (int j = i + 1; j < n - 2; j++)
                for (int k = j + 1; k < n - 1; k++)
                    for (int l = k + 1; l < n; l++) {
                        if ((this.pointcopy[i].slopeTo(this.pointcopy[j]) == this.pointcopy[i]
                                .slopeTo(this.pointcopy[k])) &&
                                (this.pointcopy[i].slopeTo(this.pointcopy[j]) == this.pointcopy[i]
                                        .slopeTo(this.pointcopy[l]))) {
                            this.lineSegments
                                    .add(new LineSegment(this.pointcopy[i], this.pointcopy[l]));
                        }
                    }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);

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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
