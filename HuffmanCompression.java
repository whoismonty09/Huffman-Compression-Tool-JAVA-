// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompression {
   private static Map<Character, String> huffmanCodes = new HashMap();

   public HuffmanCompression() {
   }

   public static Map<Character, Integer> buildFrequencyMap(String var0) {
      HashMap var1 = new HashMap();

      for(char var5 : var0.toCharArray()) {
         var1.put(var5, (Integer)var1.getOrDefault(var5, 0) + 1);
      }

      return var1;
   }

   public static HuffmanNode buildHuffmanTree(Map<Character, Integer> var0) {
      PriorityQueue var1 = new PriorityQueue();

      for(Map.Entry var3 : var0.entrySet()) {
         var1.add(new HuffmanNode((Character)var3.getKey(), (Integer)var3.getValue()));
      }

      while(var1.size() > 1) {
         HuffmanNode var5 = (HuffmanNode)var1.poll();
         HuffmanNode var6 = (HuffmanNode)var1.poll();
         HuffmanNode var4 = new HuffmanNode(var5.frequency + var6.frequency, var5, var6);
         var1.add(var4);
      }

      return (HuffmanNode)var1.poll();
   }

   public static void generateCodes(HuffmanNode var0, String var1) {
      if (var0 != null) {
         if (var0.left == null && var0.right == null) {
            huffmanCodes.put(var0.character, var1);
         }

         generateCodes(var0.left, var1 + "0");
         generateCodes(var0.right, var1 + "1");
      }
   }

   public static String compress(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(char var5 : var0.toCharArray()) {
         var1.append((String)huffmanCodes.get(var5));
      }

      return var1.toString();
   }

   public static String decompress(String var0, HuffmanNode var1) {
      StringBuilder var2 = new StringBuilder();
      HuffmanNode var3 = var1;

      for(char var7 : var0.toCharArray()) {
         var3 = var7 == '0' ? var3.left : var3.right;
         if (var3.left == null && var3.right == null) {
            var2.append(var3.character);
            var3 = var1;
         }
      }

      return var2.toString();
   }

   public static void main(String[] var0) {
      try {
         String var1 = new String(Files.readAllBytes(Paths.get("input.txt")));
         Map var2 = buildFrequencyMap(var1);
         HuffmanNode var3 = buildHuffmanTree(var2);
         generateCodes(var3, "");
         String var4 = compress(var1);
         FileWriter var5 = new FileWriter("compressed.bin");
         var5.write(var4);
         var5.close();
         String var6 = decompress(var4, var3);
         FileWriter var7 = new FileWriter("decompressed.txt");
         var7.write(var6);
         var7.close();
         PrintStream var10000 = System.out;
         int var10001 = var1.length();
         var10000.println("Original Size: " + var10001 * 8 + " bits");
         System.out.println("Compressed Size: " + var4.length() + " bits");
         var10000 = System.out;
         double var10 = (double)var4.length();
         int var10002 = var1.length();
         var10000.println("Compression Ratio: " + var10 / (double)(var10002 * 8));
         System.out.println("Completed Successfully and developed by Monty");
      } catch (IOException var8) {
         var8.printStackTrace();
      }

   }
}

