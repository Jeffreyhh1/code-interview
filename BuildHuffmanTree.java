import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class BuildHuffmanTree {
    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val + " ");
        inorder(root.right);
    }

    public static TreeNode buildHuffmanTree(int[] nums) {
        PriorityQueue<TreeNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (int num : nums) {
            queue.offer(new TreeNode(num));
        }

        while(queue.size() > 1) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            TreeNode parent = new TreeNode(left.val + right.val);
            if (left.val > right.val) {
                TreeNode temp = left;
                left = right;
                right = temp;
            }
            parent.left = left;
            parent.right = right;
            queue.offer(parent);
        }

        return queue.poll();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        TreeNode root = buildHuffmanTree(nums);
        inorder(root);
    }
    
}
