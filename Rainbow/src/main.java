public class main
{
    public static void main(String[] args) {
        String [] colors = {"Красный ", "Оранжевый ", "Желтый ", "Зеленый ", "Голубой ", "Синий ", "Фиолетовый "};

        for (int i = 0; i >= colors.length; i++) {
            System.out.println(colors[i]);
        }
        int n = colors.length;
        String temp;
        for (int i = 0; i < n / 2; i++) {
            temp = colors[n - i - 1];
            colors[n - i - 1] = colors[i];
            colors[i] = temp;
        }
        //Выводим конечный массив в консоль
        for (int i = 0; i < colors.length; i++) {
            System.out.print(colors[i]);
        }
    }
}
