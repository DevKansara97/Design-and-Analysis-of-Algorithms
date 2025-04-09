
import java.util.*;

public class Job_Scheduling {

    public static void main(String[] args) {

        List<Pair> jobs = new ArrayList<>();

        jobs.add(new Pair(1, 6));
        jobs.add(new Pair(2, 4));
        jobs.add(new Pair(1, 2));
        jobs.add(new Pair(4, 7));
        jobs.add(new Pair(9, 13));
        jobs.add(new Pair(14, 19));
        jobs.add(new Pair(3, 15));
        jobs.add(new Pair(10, 16));
        // jobs.add(new Pair(11, 12));
        // jobs.add(new Pair(11, 21));
        // jobs.add(new Pair(8, 9));
        // jobs.add(new Pair(16, 17));

        printJobs(scheduleJobs(jobs));
    }

    public static List<Pair> scheduleJobs(List<Pair> jobs) {

        // Number of intervals
        int n = jobs.size();

        // Sort based on finishing time of job:
        sortPoints_Y(jobs);

        List<Pair> schedule = new ArrayList<>();
        int scheduleIdx = 0;
        schedule.add(jobs.get(0));
        for (int i = 1; i < n; i++) {
            if (jobs.get(i).start >= schedule.get(scheduleIdx).end) {
                schedule.add(jobs.get(i));
                scheduleIdx++;
            }
        }

        return schedule;
    }

    public static void printJobs(List<Pair> jobs) {
        for (int i = 0; i < jobs.size(); i++) {
            System.out.print("[" + jobs.get(i).start + ", " + jobs.get(i).end + "], ");
        }
    }

    // Function for sorting points (x,y) based on y-coordinate
    public static void sortPoints_Y(List<Pair> points) {
        Collections.sort(points, (a, b) -> {
            if (a.end != b.end) {
                return Integer.compare(a.end, b.end);
            } else {
                return Integer.compare(a.start, b.start);
            }
        });
    }

    public static class Pair {

        int start;
        int end;

        public Pair(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
}
