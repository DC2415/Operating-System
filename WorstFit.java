import java.util.Scanner;

public class WorstFit {
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

        allocateWorstFit(memoryBlocks, processSizes);

        scanner.close();
    }

    private static void allocateWorstFit(int[] memoryBlocks, int[] processSizes) {
        int[] allocation = new int[processSizes.length];

        for (int i = 0; i < processSizes.length; i++) {
            int worstFitIndex = -1;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    if (worstFitIndex == -1 || memoryBlocks[j] > memoryBlocks[worstFitIndex]) {
                        worstFitIndex = j;
                    }
                }
            }

            if (worstFitIndex != -1) {
                allocation[i] = worstFitIndex + 1;
                memoryBlocks[worstFitIndex] -= processSizes[i];
            }
        }

        displayAllocation("Worst Fit", allocation);
    }

    private static void displayAllocation(String algorithm, int[] allocation) {
        System.out.println("\nMemory Allocation using " + algorithm + " Algorithm:");
        for (int i = 0; i < allocation.length; i++) {
            System.out.println("Process " + (i + 1) + " is allocated to Block " + allocation[i]);
        }
    }
}
