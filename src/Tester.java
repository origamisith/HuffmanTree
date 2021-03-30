import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Tester {
    HuffmanFrequencyTable table;
    @BeforeEach
    void initialize() {
        table = new HuffmanFrequencyTable("Hello World");
    }

    @Test
    void put() {
        table.put('z');
        HuffmanFrequencyTable table2 = new HuffmanFrequencyTable("Hello Worldz");
        assertEquals(table2, table);

        HuffmanFrequencyTable table3 = new HuffmanFrequencyTable("Helllo Worlldz");
        table.put('l');
        table.put('l');
        assertEquals(table3, table);
    }

    @Test
    void get() {
        assertEquals(new TableEntry(3, ""), table.get('l'));
    }

    @Test
    void shouldConstructValidHeap() {
        String testString = "aaabccccddeffff";
        HuffmanFrequencyTable table = new HuffmanFrequencyTable(testString);
        ArrayHeap<HuffmanTreeNode> heap = new ArrayHeap<>(10);
        table.fillHeap(heap);
        assertTrue(heap.verifyInvariant(0));
        assertEquals(6, heap.lastNode+1);
    }

    @Test
    void shouldConstructPriorityQueue() {
        String testString = "aaabccccddeffff";
        HuffmanFrequencyTable table = new HuffmanFrequencyTable(testString);
        PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue<>(10);
        table.fillHeap(queue);
        HuffmanTreeNode old = queue.removeNext();
        boolean invariant =  true;
        while(!queue.isEmpty()) {
            HuffmanTreeNode next = queue.removeNext();
            if(next.compareTo(old) < 0) invariant = false;
            old = next;
        }
        assertTrue(invariant);
    }

    @Test
    void shouldEncodeDecode() {
        PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue(50);
        String s = "Eerie eyes seen near lake.";
//        String s = "This is a test of how well my program works or not I'm not really sure but it will be fun to see.";
        System.out.println(s);
        HuffmanFrequencyTable table = new HuffmanFrequencyTable(s);
        table.fillHeap(queue);
        HuffmanTree tree = new HuffmanTree(queue);
        tree.fillTable(table);
        System.out.println(table);
        String encoded  =  table.encode(s);
        System.out.println("Encoded bit stream:\n" + encoded);
        System.out.println("Total number of bits without Huffman coding (8-bits per character): " + s.length()*8);
        System.out.println("Total number of bits with Huffman coding: " + encoded.length());
        System.out.println("Compression ratio: " + 8.0 * s.length() / encoded.length());
        System.out.println("Decoded string: " + tree.decodeLong(encoded));
        assertEquals(s, tree.decodeLong(encoded));
    }
}
