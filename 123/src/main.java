public class main {
    public static void main(String[] args) {
        String[] letters = {"C", "M", "T", "B", "A", "P", "O", "H", "E", "Y"};
        for (int i = 0; letters.length > i; i++){
            for (int j = 0; j < 10; j++){
                for(int k = 0; k < 199; k++){
                    String letter = letters[i];
                    String number = String.format("%s%d%d%d%s%s%d", letter, j, j, j, letter, letter, k);
                    System.out.println(number);
                }
            }
        }

    }
}
