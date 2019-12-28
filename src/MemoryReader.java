import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MemoryReader {
    public static HashMap getMemoryBlockFromFile(String path){
        HashMap<String, List> lists = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            DataInputStream dataStream = new DataInputStream(fileInputStream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dataStream));
            String line = null;
            ArrayList<Integer> processList = new ArrayList<>();
            LinkedList<MemoryBlock> blockList = new LinkedList<>();
            int l_num = 0;
            while ((line = buffer.readLine()) !=null) {
                if (!line.isEmpty()) {
                    String[] block = line.split(" ");

                    for (int j =1; j<block.length; j++) {
                        int a = Integer.parseInt(block[j]);
                        if (l_num ==0)
                            blockList.add(new MemoryBlock(j-1, a));
                        else
                            processList.add(a);
                    }
                    l_num++;
                }
            }
            lists.put("block", blockList);
            lists.put("process", processList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists;
    }
}