public class NumberAndNameOfLine {
    private String number;
    private String name;

    public NumberAndNameOfLine(String number,String name) {
        this.number = number;
        this.name = name;
    }
    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  "number = " + number +
                ", name = '" + name + '\'' +
                '}';
    }
}
