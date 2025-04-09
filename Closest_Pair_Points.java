
import java.util.*;

public class Closest_Pair_Points {

    public static void main(String[] args) {

        ArrayList<Pair> setOfPoints = new ArrayList<>();

        setOfPoints.add(new Pair(1, 7));
        setOfPoints.add(new Pair(5, 9));
        setOfPoints.add(new Pair(5, 6));
        setOfPoints.add(new Pair(6, 3));
        setOfPoints.add(new Pair(2, 4));
        setOfPoints.add(new Pair(8, 6));
        setOfPoints.add(new Pair(3, 2));

        // Step0: Sort based on X coordinates, if same, consider Y coordinates:
        sortPoints_X(setOfPoints);

        // Step1: Divide into halves
        // Find Median:
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < setOfPoints.size(); i++) {
            ls.add(setOfPoints.get(i).x);
        }

        int Xmed = FindMedian(ls);

        List<Integer> Xl = new ArrayList<>();
        List<Integer> Xr = new ArrayList<>();
        List<Integer> Yl = new ArrayList<>();
        List<Integer> Yr = new ArrayList<>();

        for (int i = 0; i < setOfPoints.size(); i++) {
            if (setOfPoints.get(i).x <= Xmed) {
                Xl.add(setOfPoints.get(i).x);
                Yl.add(setOfPoints.get(i).y);
            } else {
                Xr.add(setOfPoints.get(i).x);
                Yr.add(setOfPoints.get(i).y);
            }
        }

        // Step2: Recurse on both
        double dl = shortestDistance_recurse(Xl, Yl, 0, Xl.size());
        double dr = shortestDistance_recurse(Xr, Yr, 0, Xr.size());

        double d = Math.min(dl, dr);

        // Step3: Put the Solution Together:
        ArrayList<Pair> newSetOfPoints1 = new ArrayList<>();
        ArrayList<Pair> newSetOfPoints2 = new ArrayList<>();

        for (int i = 0; i < Xl.size(); i++) {
            if (Xl.get(i) >= Xmed - d) {
                newSetOfPoints1.add(new Pair(Xl.get(i), Yl.get(i)));
            }
        }

        for (int i = 0; i < Xr.size(); i++) {
            if (Xr.get(i) <= Xmed + d) {
                newSetOfPoints2.add(new Pair(Xr.get(i), Yr.get(i)));
            }
        }

        sortPoints_Y(newSetOfPoints1);
        sortPoints_Y(newSetOfPoints2);

        double delta = computeDelta(newSetOfPoints1, newSetOfPoints2, d);

        System.out.println("The shortest distance is: " + Math.min(d, delta));

    }

    public static class Pair {

        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Function for sorting points (x,y) based on x-coordinate
    public static void sortPoints_X(List<Pair> points) {
        Collections.sort(points, (a, b) -> {
            if (a.x != b.x) {
                return Integer.compare(a.x, b.x);
            } else {
                return Integer.compare(a.y, b.y);
            }
        });
    }

    // Function for sorting points (x,y) based on y-coordinate
    public static void sortPoints_Y(List<Pair> points) {
        Collections.sort(points, (a, b) -> {
            if (a.y != b.y) {
                return Integer.compare(a.y, b.y);
            } else {
                return Integer.compare(a.x, b.x);
            }
        });
    }

    // Function to find Median in a list using Sorting:
    public static int FindMedian(List<Integer> ls) {
        Collections.sort(ls);
        return ls.get(ls.size() / 2);
    }

    // Function for finding shortest distance between points recursively:
    public static double shortestDistance_recurse(List<Integer> X, List<Integer> Y, int start, int end) {

        if (X.size() == 1) {
            return Integer.MAX_VALUE;
        }

        if (X.size() == 2) {
            return DistanceBetweenTwoPoints(X.get(0), Y.get(0), X.get(1), Y.get(1));
        }

        if (X.size() == 3) {
            double d1 = DistanceBetweenTwoPoints(X.get(0), Y.get(0), X.get(1), Y.get(1));
            double d2 = DistanceBetweenTwoPoints(X.get(1), Y.get(1), X.get(2), Y.get(2));

            return Math.min(d1, d2);
        }

        if (X.size() == 4) {
            double d1 = DistanceBetweenTwoPoints(X.get(0), Y.get(0), X.get(1), Y.get(1));
            double d2 = DistanceBetweenTwoPoints(X.get(2), Y.get(2), X.get(3), Y.get(3));

            return Math.min(d1, d2);
        }

        int mid = start + (end - start) / 2;
        List<Integer> X1 = new ArrayList<>();
        List<Integer> Y1 = new ArrayList<>();
        for (int i = start; i <= mid; i++) {
            X1.add(X.get(i));
            Y1.add(Y.get(i));
        }

        List<Integer> X2 = new ArrayList<>();
        List<Integer> Y2 = new ArrayList<>();
        for (int i = mid + 1; i < end; i++) {
            X2.add(X.get(i));
            Y2.add(Y.get(i));
        }

        double d1 = shortestDistance_recurse(X1, Y1, start, mid);
        double d2 = shortestDistance_recurse(X2, Y2, mid + 1, end);

        return Math.min(d1, d2);
    }

    // Function to compute distance between two points:
    public static double DistanceBetweenTwoPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    // Function to compute minimum distance across boundary
    public static double computeDelta(List<Pair> Yl_, List<Pair> Yr_, double d) {

        double delta = d;

        // Maintain three pointers p, q, r
        int p = 0, q = 0, r = 0;

        // Loop through Yl_ and check points in Yr_
        for (p = 0; p < Yl_.size(); p++) {
            while (q < Yr_.size() && Yr_.get(q).y < Yl_.get(p).y - d) {
                q++;
            }

            while (r < Yr_.size() && Yr_.get(r).y <= Yl_.get(p).y + d) {
                r++;
            }

            // Check at most 8 points between q and r
            for (int i = q; i < r && i < Yr_.size(); i++) {
                double dist = DistanceBetweenTwoPoints(Yl_.get(p).x, Yl_.get(p).y, Yr_.get(i).x, Yr_.get(i).y);
                delta = Math.min(delta, dist);
            }
        }

        return delta;
    }
}
