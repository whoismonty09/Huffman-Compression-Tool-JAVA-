# 📦 Huffman File Compression Tool (Java)

A file compression and decompression tool built using Java and the Huffman Coding algorithm.  
This project demonstrates Greedy Algorithms, Priority Queues, Binary Trees, and File Handling.

---

## 🚀 Features

- Reads text from input.txt
- Builds frequency map
- Generates Huffman Tree
- Encodes text into compressed format
- Decodes compressed data
- Calculates compression ratio
- Generates:
  - compressed.bin
  - decompressed.txt

---

## 🧠 Concepts Used

- Greedy Algorithm
- Binary Tree
- Priority Queue
- File Handling
- OOP (Object-Oriented Programming)

---

## 📂 Project Structure

```
HuffmanCompression/
│
├── HuffmanCompression.java
├── input.txt
├── compressed.bin (generated)
├── decompressed.txt (generated)
```

---

## ▶️ How To Run

### 1️⃣ Create input.txt
Add some sample text inside:

```
This is my Huffman compression project
Java programming is powerful
```

### 2️⃣ Compile

```
javac HuffmanCompression.java
```

### 3️⃣ Run

```
java HuffmanCompression
```

---

## 📊 Sample Output

```
Original Size: 640 bits
Compressed Size: 412 bits
Compression Ratio: 0.64
Completed Successfully
```

---

## 📈 How Huffman Coding Works

1. Count frequency of each character
2. Build a priority queue
3. Create a binary tree by combining lowest frequency nodes
4. Assign binary codes (0 = left, 1 = right)
5. Encode the text using generated codes
6. Decode using the same tree

---

## 💡 Why This Project Is Important

This project demonstrates understanding of:

- Data Structures (Trees)
- Algorithms (Greedy)
- Java Collections Framework
- File processing
- Compression techniques

It is not a basic console app — it implements a real-world compression algorithm.

---

## 🛠 Tech Stack

- Java
- Collections Framework
- File I/O
- PriorityQueue

---

## 🔥 Future Improvements

- Store tree structure inside file
- Implement real binary writing instead of string bits
- Add CLI menu options
- Add GUI version
- Improve compression efficiency

---

## 👨‍💻 Author


