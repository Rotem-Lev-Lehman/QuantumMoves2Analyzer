public class TwoIntAndOneDoubleTuple {
    private int first;
    private int second;
    private double third;

    public TwoIntAndOneDoubleTuple(int first, int second, double third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public double getThird() {
        return third;
    }

    public void setThird(double third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return first + "," + second + "," + third;
    }
}
