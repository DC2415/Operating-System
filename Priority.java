import java.util.Arrays;
import java.util.Scanner;



public class Priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time, burst time, and priority for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            int priority = sc.nextInt();
            processes[i] = new Process(i + 1, burstTime, arrivalTime, priority);
        }

        // Sort processes based on arrival time and priority (higher value means higher
        // priority)
        Arrays.sort(processes, (a, b) -> {
            if (a.arrivalTime != b.arrivalTime) {
                return a.arrivalTime - b.arrivalTime;
            } else {
                return b.priority - a.priority;
            }
        });

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (processes[i].arrivalTime > currentTime) {
                currentTime = processes[i].arrivalTime;
            }
            processes[i].waitingTime = currentTime - processes[i].arrivalTime;
            processes[i].turnaroundTime = processes[i].waitingTime + processes[i].burstTime;
            currentTime += processes[i].burstTime;
        }

        // Calculate and display average waiting time and average turnaround time
        double avgWaitingTime = 0;
        double avgTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            avgWaitingTime += processes[i].waitingTime;
            avgTurnaroundTime += processes[i].turnaroundTime;
        }
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;

        // System.out.println("\nProcess\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        // for (Process process : processes) {
        //     System.out.println(process.id + "\t\t" + process.arrivalTime + "\t\t\t" + process.burstTime +
        //             "\t\t\t" + process.priority + "\t\t\t" + process.waitingTime + "\t\t\t\t" +
        //             process.turnaroundTime);
        // }

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
class Process {
    int id;
    int burstTime;
    int arrivalTime;
    int priority;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime, int arrivalTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}