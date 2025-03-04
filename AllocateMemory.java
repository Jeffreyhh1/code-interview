import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AllocateMemory {
    public static final int CAPACITY = 100;

    public static void printAllocated(List<List<Integer>> allocated) {
        for (int i = 0; i < allocated.size(); i++) {
            System.out.println(allocated.get(i));
        }
    }

    public static int allocateMemory(int expectedSize, List<List<Integer>> allocated) {
        allocated.sort(Comparator.comparingInt(a -> a.get(0)));
        for (int i = 1; i < allocated.size(); i++) {
            List<Integer> prev = allocated.get(i - 1);
            List<Integer> current = allocated.get(i);
            int prevOffset = prev.get(0);
            int prevSize = prev.get(1);
            int currentOffset = current.get(0);
            int currentSize = current.get(1);

            if (prevOffset < 0 || prevOffset + prevSize > 100) {
                return -1;
            }
            if (i == allocated.size() - 1) {
                if (currentOffset < 0 || currentOffset + currentSize > 100) {
                    return -1;
                }
            }
            if (prev.get(0) + prev.get(1) > current.get(0)) {
                return -1;
            }
        }
        int start = 0;
        for (List<Integer> block : allocated) {
            int offset = block.get(0);
            int size = block.get(1);
            if (start + expectedSize <= offset) {
                return start;
            }
            start = offset + size;
        }
        if (start + expectedSize <= CAPACITY) {
            return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int expectedSize = in.nextInt();
        System.out.println("expectedSize: " + expectedSize);
        in.nextLine();
        List<List<Integer>> allocated = new ArrayList<>();
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.isEmpty()) {
                break;
            }
            String[] parts = str.split(" ");
            int offset = Integer.parseInt(parts[0]);
            int size = Integer.parseInt(parts[1]);
            allocated.add(Arrays.asList(offset, size));
        }

        int result = allocateMemory(expectedSize, allocated);
        System.out.println(result);
    }
    
}
