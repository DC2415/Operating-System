import java.util.*;
import java.util.*;

public class FcfsWithOutArrivalTime {
    public static void main(String args[]) {
        // FCFS
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> burst_time = new ArrayList<>();
        System.out.println("Enter the number of processes");
        int a = sc.nextInt();
        int waiting = 0, tt = 0;

        for (int i = 1; i <= a; i++) {
            System.out.print("Enter the burst time of p" + i + ": ");
            burst_time.add(sc.nextInt());
        }
        int[] wt = new int[a];
        wt[0] = 0;
        for (int i = 1; i < a; i++) {
            waiting += burst_time.get(i - 1);
            wt[i] = waiting;
        }
        int[] ttt = new int[a];
        for (int i = 0; i < a; i++) {
            tt += burst_time.get(i);
            ttt[i] = tt;
        }
        int twt = 0, tttt = 0;
        for (int j = 0; j < a; j++) {
            twt += wt[j];
            tttt+=ttt[j];
        }

        // for (int j = 0; j < a; j++) {
        //     tttt += ttt[j];
        // }
        System.out.println("Average Waiting Time: " + (double) twt / a);
        System.out.println("Average Turnaround Time: " + (double) tttt / a);
    }
}
