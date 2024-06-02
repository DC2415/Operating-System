import java.util.Scanner;

public class FirstFit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();

        int[] memoryBlocks = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter the size of memory block " + (i + 1) + ": ");
            memoryBlocks[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        int[] processSizes = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter the size of process " + (i + 1) + ": ");
            processSizes[i] = scanner.nextInt();
        }

        allocateFirstFit(memoryBlocks, processSizes);

        scanner.close();
    }

    private static void allocateFirstFit(int[] memoryBlocks, int[] processSizes) {
        int[] allocation = new int[processSizes.length];

        for (int i = 0; i < processSizes.length; i++) {
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    allocation[i] = j + 1;
                    memoryBlocks[j] -= processSizes[i];
                    break;
                }
            }
        }

        displayAllocation("First Fit", allocation);
    }

    private static void displayAllocation(String algorithm, int[] allocation) {
        System.out.println("\nMemory Allocation using " + algorithm + " Algorithm:");
        for (int i = 0; i < allocation.length; i++) {
            System.out.println("Process " + (i + 1) + " is allocated to Block " + allocation[i]);
        }
    }
}
