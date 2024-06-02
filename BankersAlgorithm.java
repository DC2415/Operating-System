import java.util.Scanner;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        System.out.print("Enter the number of resources: ");
        int r = scanner.nextInt();

        int[][] alloc = new int[n][r];
        int[][] max = new int[n][r];
        int[] avail = new int[r];

        // Input Allocation Matrix
        System.out.println("Enter the Allocation Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                alloc[i][j] = scanner.nextInt();
            }
        }

        // Input Maximum Matrix
        System.out.println("Enter the Maximum Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        // Input Available Resources
        System.out.println("Enter the Available Resources:");
        for (int j = 0; j < r; j++) {
            avail[j] = scanner.nextInt();
        }

        // Rest of the code remains the same as the previous version

        int[] f = new int[n];
        int[] ans = new int[n];
        int ind = 0;

        int[][] need = new int[n][r];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++)
                need[i][j] = max[i][j] - alloc[i][j];
        }

        int y;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (f[i] == 0) {
                    int flag = 0;
                    for (int j = 0; j < r; j++) {
                        if (need[i][j] > avail[j]) {
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 0) {
                        ans[ind++] = i;
                        for (y = 0; y < r; y++)
                            avail[y] += alloc[i][y];
                        f[i] = 1;
                    }
                }
            }
        }

        System.out.print("The SAFE Sequence is as follows: ");
        for (int i = 0; i < n - 1; i++)
            System.out.print("P" + ans[i] + " -> ");
        System.out.println("P" + ans[n - 1]);

        scanner.close();
    }
}
