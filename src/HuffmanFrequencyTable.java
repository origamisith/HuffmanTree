//------------------NOTE------------------------------------------------
//----------FOR UP-TO-DATE PROJECT ZIP, SEE LINK BELOW-----------------
// https://drive.google.com/drive/u/1/folders/1vR9kZc_ygT2nJVTnDTGHf1kLKlew-V4M

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
public class HuffmanFrequencyTable {
    private LinkedHashMap<Character, TableEntry> map;

    public HuffmanFrequencyTable(String input) {
        if(input.length() < 2) throw new IllegalArgumentException("String must be at least 2 characters long");
        map = new LinkedHashMap();
        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            put(curr);
        }
    }

    public void put(char c) {
        if(map.containsKey(c)) {
            map.get(c).freq++;
        }
        else {
            map.put(c, new TableEntry(1, ""));
        }

    }

    public TableEntry get(char c) {
        return map.get(c);
    }

    public boolean setCode(char c, String code) {
        TableEntry entry = get(c);
        if(entry == null) return false;
        entry.code = code;
        return true;
    }
    public int size() {
        return map.size();
    }

    public void fillHeap(ArrayHeap<HuffmanTreeNode> heap) {
        for(Map.Entry<Character, TableEntry> set: map.entrySet()) {
            heap.add(new HuffmanTreeNode(set.getKey(), set.getValue().freq));
        }

    }

    public String encode(String phrase) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < phrase.length(); i++) {
            sb.append(get(phrase.charAt(i)).code);
        }
        return sb.toString();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n======================================\n");
        sb.append("char     frequency    code\n");
        sb.append("--------------------------------------\n");
        for(Map.Entry<Character, TableEntry> set: map.entrySet()) {
            sb.append(String.format("%-10s%-12d%-5s\n", set.getKey(), set.getValue().freq, set.getValue().code));
        }
        sb.append("======================================\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanFrequencyTable that = (HuffmanFrequencyTable) o;
        return Objects.equals(map, that.map);
    }

}
