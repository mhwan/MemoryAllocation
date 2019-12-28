import java.util.LinkedList;

public class MemoryBlock {
    private int processId;
    private int memory;
    private LinkedList<Integer> allocatedList;

    public MemoryBlock(int processId, int memory) {
        this.processId = processId;
        this.memory = memory;
        allocatedList = new LinkedList<>();
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getMemory() {
        return memory;
    }

    public void allocateProcess(int size){
        memory-=size;
        allocatedList.add(size);
    }
    public void releaseAllProcess(){
        for (int i =0; allocatedList.size() != 0; i++){
            int p = allocatedList.remove();
            memory+=p;
        }
        allocatedList.clear();
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }
}