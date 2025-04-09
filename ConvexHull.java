import java.util.*;

public class ConvexHull {

    public static List<int[]> convexHull(int[][] points) {
        if (points.length < 3) {
            return Arrays.asList(points);
        }
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return divideAndConquer(points, 0, points.length - 1);
    }

    private static List<int[]> divideAndConquer(int[][] points, int left, int right) {
        if (right - left + 1 <= 3) {
            List<int[]> hull = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                hull.add(points[i]);
            }
            return computeHull(hull);
        }
        int mid = (left + right) / 2;
        List<int[]> leftHull = divideAndConquer(points, left, mid);
        List<int[]> rightHull = divideAndConquer(points, mid + 1, right);
        return mergeHulls(leftHull, rightHull);
    }

    private static List<int[]> mergeHulls(List<int[]> leftHull, List<int[]> rightHull) {
        int n = leftHull.size(), m = rightHull.size();
        int rightMostLeftHull = 0;
        for (int i = 0; i < n; i++) {
            if (leftHull.get(i)[0] > leftHull.get(rightMostLeftHull)[0]) {
                rightMostLeftHull = i;
            }
        }
        int leftMostRightHull = 0;
        for (int j = 0; j < m; j++) {
            if (rightHull.get(j)[0] < rightHull.get(leftMostRightHull)[0]) {
                leftMostRightHull = j;
            }
        }
        int leftUpper = rightMostLeftHull, rightUpper = leftMostRightHull;
        boolean done = false;
        while (!done) {
            done = true;
            while (orientation(rightHull.get(rightUpper), leftHull.get(leftUpper), leftHull.get((leftUpper + 1) % n)) > 0) {
                leftUpper = (leftUpper + 1) % n;
                done = false;
            }
            while (orientation(leftHull.get(leftUpper), rightHull.get(rightUpper), rightHull.get((rightUpper - 1 + m) % m)) < 0) {
                rightUpper = (rightUpper - 1 + m) % m;
                done = false;
            }
        }
        int leftLower = rightMostLeftHull, rightLower = leftMostRightHull;
        done = false;
        while (!done) {
            done = true;
            while (orientation(rightHull.get(rightLower), leftHull.get(leftLower), leftHull.get((leftLower - 1 + n) % n)) < 0) {
                leftLower = (leftLower - 1 + n) % n;
                done = false;
            }
            while (orientation(leftHull.get(leftLower), rightHull.get(rightLower), rightHull.get((rightLower + 1) % m)) > 0) {
                rightLower = (rightLower + 1) % m;
                done = false;
            }
        }
        List<int[]> mergedHull = new ArrayList<>();
        int i = leftUpper;
        mergedHull.add(leftHull.get(i));
        while (i != leftLower) {
            i = (i + 1) % n;
            mergedHull.add(leftHull.get(i));
        }
        int j = rightLower;
        mergedHull.add(rightHull.get(j));
        while (j != rightUpper) {
            j = (j + 1) % m;
            mergedHull.add(rightHull.get(j));
        }
        return mergedHull;
    }

    private static int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    private static List<int[]> computeHull(List<int[]> points) {
        points.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        if (points.size() == 3) {
            if (orientation(points.get(0), points.get(1), points.get(2)) < 0) {
                return Arrays.asList(points.get(0), points.get(2), points.get(1));
            }
        }
        return points;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, -4}, {-1, -5}, {-5, -3}, {-3, -1}, {-1, -3}, {-2, -2}, {-1, -1}, {-2, -1}, {-1, 1}};
        List<int[]> hull = convexHull(points);
        System.out.println("Convex Hull:");
        for (int[] point : hull) {
            System.out.println(point[0] + " " + point[1]);
        }
    }
}