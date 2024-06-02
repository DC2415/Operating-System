import java.util.Scanner;

public class NextFit {
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

        allocateNextFit(memoryBlocks, processSizes);

        scanner.close();
    }

    private static void allocateNextFit(int[] memoryBlocks, int[] processSizes) {
        int[] allocation = new int[processSizes.length];
        int currentBlockIndex = 0;

        for (int i = 0; i < processSizes.length; i++) {
            while (currentBlockIndex < memoryBlocks.length) {
                if (memoryBlocks[currentBlockIndex] >= processSizes[i]) {
                    allocation[i] = currentBlockIndex + 1;
                    memoryBlocks[currentBlockIndex] -= processSizes[i];
                    break;
                }
                currentBlockIndex = (currentBlockIndex + 1) % memoryBlocks.length;
            }
        }

        displayAllocation("Next Fit", allocation);
    }

    private static void displayAllocation(String algorithm, int[] allocation) {
        System.out.println("\nMemory Allocation using " + algorithm + " Algorithm:");
        for (int i = 0; i < allocation.length; i++) {
            System.out.println("Process " + (i + 1) + " is allocated to Block " + allocation[i]);
        }
    }
}
