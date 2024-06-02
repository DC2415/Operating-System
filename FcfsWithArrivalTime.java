import java.util.*;

public class FcfsWithArrivalTime {
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        int no_of_pro;
        no_of_pro = scn.nextInt();
        ArrayList<item> arr = new ArrayList<>();
        for (int i = 0; i < no_of_pro; i++) {
            System.out.print("enter arrival time P" + i + ":");
            int a = scn.nextInt();
            System.out.print("enter burst time P" + i + ":");
            int b = scn.nextInt();
            arr.add(new item(a, b));
        }
        Collections.sort(arr, new MyComparator());
        int[] weighting_time = new int[no_of_pro];
        int[] Turnaround_time = new int[no_of_pro];
        weighting_time[0] = 0;
        Turnaround_time[0] = arr.get(0).burst_time;
        for (int i = 1; i < no_of_pro; i++) {
            weighting_time[i] = Turnaround_time[i - 1] - arr.get(i).arrival_time;
            Turnaround_time[i] = Turnaround_time[i - 1] + arr.get(i).burst_time;
        }
        int total_weight = 0, total_turnaround = 0;
        for (int k : weighting_time) {
            total_weight += k;
        }
        for (int k : Turnaround_time) {
            total_turnaround += k;
        }
        System.out.println("avg waiting time:" + (double) (total_weight) / no_of_pro);
        System.out.println("\navg turn around time:" + (double) (total_turnaround) / no_of_pro);
    }
}

class item {
    int arrival_time, burst_time;

    public item(int x, int y) {
        arrival_time = x;
        burst_time = y;
    }
}

class MyComparator implements Comparator<item> {
    public int compare(item o1, item o2) {
        if (o1.arrival_time > o2.arrival_time) {
            return 1;
        }
        if (o1.arrival_time < o2.arrival_time) {
            return -1;
        }
        return 0;
    }
}