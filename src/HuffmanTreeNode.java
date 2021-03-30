public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
    private Character key;
    private int value;
    public HuffmanTreeNode left = null;
    public HuffmanTreeNode right = null;
    public HuffmanTreeNode(Character key, int value) {
        this.key = key;
        this.value = value;
    }

    public HuffmanTreeNode() {

    }

    public int compareTo(HuffmanTreeNode obj) {
        HuffmanTreeNode other =  obj;
        if(other == null) return 0;
        return Integer.compare(value, other.value);
    }

    public String toString() {
        return key + ":" + value;
    }

    public Character getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
