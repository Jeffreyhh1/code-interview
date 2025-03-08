import java.util.*;


public class AllocateMemoryTest {
    public static void main(String[] args) {
        // normal
        int expectedSize = 1;
        List<Integer> list1 = Arrays.asList(0, 1);
        List<Integer> list2 = Arrays.asList(3, 2);
        List<List<Integer>> allocated = new ArrayList<>(Arrays.asList(list1, list2));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == 1) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

        // has overlap
        expectedSize = 1;
        allocated.clear();
        list1 = Arrays.asList(0, 5);
        list2 = Arrays.asList(3, 2);
        allocated = new ArrayList<>(Arrays.asList(list1, list2));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == -1) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }
        
        // no enough space left1
        expectedSize = 50;
        allocated.clear();
        list1 = Arrays.asList(0, 50);
        list2 = Arrays.asList(50, 2);
        allocated = new ArrayList<>(Arrays.asList(list1, list2));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == -1) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

        // no enough space left2
        expectedSize = 1;
        allocated.clear();
        list1 = Arrays.asList(0, 80);
        list2 = Arrays.asList(80, 20);
        allocated = new ArrayList<>(Arrays.asList(list1, list2));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == -1) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

        // success1
        expectedSize = 1;
        allocated.clear();
        list1 = Arrays.asList(0, 80);
        list2 = Arrays.asList(80, 19);
        allocated = new ArrayList<>(Arrays.asList(list1, list2));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == 99) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

        // success2
        expectedSize = 5;
        allocated.clear();
        list1 = Arrays.asList(0, 10);
        list2 = Arrays.asList(12, 20);
        List<Integer> list3 = Arrays.asList(37, 2);
        allocated = new ArrayList<>(Arrays.asList(list1, list2, list3));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == 32) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

        // success3
        expectedSize = 5;
        allocated.clear();
        list1 = Arrays.asList(0, 10);
        list2 = Arrays.asList(12, 20);
        list3 = Arrays.asList(36, 2);
        allocated = new ArrayList<>(Arrays.asList(list1, list2, list3));
        if (AllocateMemory.allocateMemory(expectedSize, allocated) == 38) {
            System.out.println("Congradulations!");
        } else {
            throw new RuntimeException("wrong!");
        }

    }

}
