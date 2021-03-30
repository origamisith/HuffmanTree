//------------------NOTE------------------------------------------------
//----------FOR UP-TO-DATE PROJECT ZIP, SEE LINK BELOW-----------------
// https://drive.google.com/drive/u/1/folders/1vR9kZc_ygT2nJVTnDTGHf1kLKlew-V4M
import java.util.Objects;

public class TableEntry {
    public int freq;
    public String code;
    public TableEntry(int freq, String code) {
        this.freq = freq;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableEntry that = (TableEntry) o;
        return freq == that.freq && code == that.code;
    }

    @Override
    public String toString() {
        return "Freq: " + freq + ", code "  + code;
    }
}
