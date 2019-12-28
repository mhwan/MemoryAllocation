import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MemoryAllocator {
    public void bestFit(LinkedList<MemoryBlock> bList, ArrayList<Integer> pList, int m, int n) {
        int allocation[] = new int[n];
        Arrays.fill(allocation, -1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                count++;
                if (bList.get(j).getMemory() >= pList.get(i)) {
                    if (bestIdx == -1)
                        bestIdx = j;
                    else if (bList.get(bestIdx).getMemory()> bList.get(j).getMemory())
                        bestIdx = j;
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                bList.get(bestIdx).allocateProcess(pList.get(i));
            }
        }
        System.out.println("\n-- BestFit");
        printAllocation(pList, bList, allocation, count);
    }

    public void nextFit(LinkedList<MemoryBlock> bList, ArrayList<Integer> pList, int m, int n) {
        int allocation[] = new int[n], j = 0;
        Arrays.fill(allocation, -1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (; j < m; j=(j+1)%m){
                count++;
                if (!(bList.get(j).getMemory() < pList.get(i))) {
                    allocation[i] = j;
                    bList.get(j).allocateProcess(pList.get(i));

                    break;
                }
            }
        }
        System.out.println("\n-- NextFit");
        printAllocation(pList, bList, allocation, count);
    }

    public void firstFit(LinkedList<MemoryBlock> bList, ArrayList<Integer> pList, int m, int n) {
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count++;
                if (!(bList.get(j).getMemory() < pList.get(i))) {
                    allocation[i] = j;
                    bList.get(j).allocateProcess(pList.get(i));

                    break;
                }
            }
        }
        System.out.println("-- FirstFit");
        printAllocation(pList, bList, allocation, count);
    }

    private void printAllocation(ArrayList<Integer> processList, LinkedList<MemoryBlock> blockList, int[] allocation, int count) {
        System.out.println("Process No.\tProcess Size\tBlock no.");
        for (int i = 0; i < processList.size(); i++) {
            System.out.print(" " + (i));
            System.out.print("\t\t "+processList.get(i));
            if (allocation[i] != -1)
                System.out.print("\t\t "+allocation[i]);
            else
                System.out.printf("\t\t Not Allocated");
            System.out.println();
        }
        System.out.println("Usable Block List");
        for (MemoryBlock block : blockList) {
            System.out.print(block.getMemory() + " ");
            block.releaseAllProcess();
        }
        System.out.println("\nTotal count of Comparison\n"+count);

        System.out.printf("Average count of Comparison\n%.1f\n", ((double)count/processList.size()));
    }
}