import java.io.*;
import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanCompression {

    private static Map<Character, String> huffmanCodes = new HashMap<>();

    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.add(newNode);
        }

        return pq.poll();
    }

    public static void generateCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    public static String compress(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }
        return encoded.toString();
    }

    public static String decompress(String encoded, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }

        return decoded.toString();
    }

    public static void main(String[] args) {
        try {
            String text = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get("input.txt")));

            Map<Character, Integer> freqMap = buildFrequencyMap(text);
            HuffmanNode root = buildHuffmanTree(freqMap);
            generateCodes(root, "");

            String compressed = compress(text);

            FileWriter fw = new FileWriter("compressed.bin");
            fw.write(compressed);
            fw.close();

            String decompressed = decompress(compressed, root);

            FileWriter fw2 = new FileWriter("decompressed.txt");
            fw2.write(decompressed);
            fw2.close();

            System.out.println("Original Size: " + text.length() * 8 + " bits");
            System.out.println("Compressed Size: " + compressed.length() + " bits");
            System.out.println("Compression Ratio: " +
                    ((double) compressed.length() / (text.length() * 8)));

            System.out.println("Completed Successfully and developed by Monty");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
