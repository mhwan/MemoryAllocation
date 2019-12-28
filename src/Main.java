import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please input Memory file");
            System.exit(1);
        }
        HashMap<String, List> lists = MemoryReader.getMemoryBlockFromFile(args[0]);
        LinkedList<MemoryBlock> blockList = new LinkedList<>();
        ArrayList<Integer> processList = new ArrayList<>();
        for (String key : lists.keySet()) {
            for (Object o : lists.get(key)) {
                if (key.equals("block"))
                    blockList.add((MemoryBlock) o);
                else
                    processList.add((Integer) o);
            }
        }
        int b_size = blockList.size();
        int p_size = processList.size();

        MemoryAllocator memoryAllocator = new MemoryAllocator();
        memoryAllocator.firstFit(blockList, processList, b_size, p_size);
        memoryAllocator.nextFit(blockList, processList, b_size, p_size);
        memoryAllocator.bestFit(blockList, processList, b_size, p_size);
    }
}