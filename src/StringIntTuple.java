import java.util.Objects;

public class StringIntTuple {
    public String str;
    public int num;

    public StringIntTuple(String str, int num) {
        this.str = str;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringIntTuple that = (StringIntTuple) o;
        return num == that.num &&
                Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, num);
    }
}
