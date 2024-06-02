import java.util.*;
public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        ArrayList<Item> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter the arrival time of process " + (i + 1) + ": ");
            int a = sc.nextInt();
            System.out.print("Enter the burst time of process " + (i + 1) + ": ");
            int b = sc.nextInt();
            arr.add(new Item(a, b));
        }

        // Sorting the processes based on arrival time
        Collections.sort(arr, Comparator.comparingInt(o -> o.arrival));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (!arr.isEmpty()) {
            int shortestIndex = -1;
            int shortestBurst = Integer.MAX_VALUE;

            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).arrival <= currentTime && arr.get(i).burst < shortestBurst) {
                    shortestBurst = arr.get(i).burst;
                    shortestIndex = i;
                }
            }

            if (shortestIndex == -1) {
                currentTime = arr.get(0).arrival;
                continue;
            }

            Item shortestJob = arr.get(shortestIndex);
            int waitingTime = currentTime - shortestJob.arrival;
            int turnaroundTime = waitingTime + shortestJob.burst;

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            // System.out.println("Executing process " + (shortestIndex + 1) + " from time " + currentTime +
            //         " to " + (currentTime + shortestJob.burst) +
            //         " (Waiting Time: " + waitingTime + ", Turnaround Time: " + turnaroundTime + ")");

            currentTime += shortestJob.burst;
            arr.remove(shortestIndex);
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

        sc.close();
    }
}
class Item {
    int arrival, burst;

    public Item(int a, int b) {
        arrival = a;
        burst = b;
    }
}
