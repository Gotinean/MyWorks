public class NumberAndNameOfLine {
    private int number;
    private String name;

    public NumberAndNameOfLine(int number,String name) {
        this.number = number;
        this.name = name;
    }
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
