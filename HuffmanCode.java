
import java.util.PriorityQueue;

public class HuffmanCode {

    static class HuffmanNode {

        int freq;
        char c;

        HuffmanNode left;
        HuffmanNode right;
    }

    static void printCode(HuffmanNode root, String s) {
        if (root.left == null || root.right == null) {

            System.out.println(root.c + " | " + s);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {

        int n = 4;
        char[] charArr = {'A', 'B', 'C', 'D'};
        int[] freqArr = {5, 1, 6, 3};

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(n, ((x, y) -> x.freq - y.freq));

        for (int i = 0; i < n; i++) {

            HuffmanNode hn = new HuffmanNode();

            hn.c = charArr[i];
            hn.freq = freqArr[i];

            hn.left = null;
            hn.right = null;

            pq.add(hn);
        }

        HuffmanNode root = null;
        while (pq.size() > 1) {

            HuffmanNode x = pq.peek();
            pq.poll();

            HuffmanNode y = pq.peek();
            pq.poll();

            HuffmanNode f = new HuffmanNode();

            f.freq = x.freq + y.freq;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;

            pq.add(f);
        }
        System.out.println(" Char | Huffman code ");
        System.out.println("--------------------");
        printCode(root, "");
    }
}
