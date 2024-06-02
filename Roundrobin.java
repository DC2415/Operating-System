import java.util.Scanner;
public class Roundrobin {
    public static void main(String args[]) {
        int n, i, bt[], wt[], tat[], rem_bt[], at[], quantum;
        float awt = 0, atat = 0;
        bt = new int[10];
        wt = new int[10];
        tat = new int[10];
        rem_bt = new int[10];
        at = new int[10];
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes (maximum 10) = ");
        n = s.nextInt();
        System.out.print("Enter the burst time and arrival time of the processes\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + " Burst Time = ");
            bt[i] = s.nextInt();
            System.out.print("P" + i + " Arrival Time = ");
            at[i] = s.nextInt();
            rem_bt[i] = bt[i];
        }
        System.out.print("Enter the time quantum: ");
        quantum = s.nextInt();

        int currentTime = 0;
        boolean done = false;
        while (!done) {
            done = true;
            for (i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > quantum) {
                        currentTime += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        currentTime += rem_bt[i];
                        rem_bt[i] = 0;
                        tat[i] = currentTime - at[i];
                        wt[i] = tat[i] - bt[i];
                        awt += wt[i];
                        atat += tat[i];
                    }
                }
            }
        }

       
               awt = awt / n;
        atat = atat / n;
        System.out.println("\nAverage waiting Time = " + awt + "\n");
        System.out.println("Average turnaround time = " + atat);
    }
}
