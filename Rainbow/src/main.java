public class main
{
    public static void main(String[] args) {
        String text = "Красный Оранжевый Желтый Зеленый Голубой Синий Фиолетовый";
        String [] colors = text.split("\\s+");
        for (int i = colors.length - 1; i >= 0; i--)
        {
            System.out.println(colors[i]);
        }
    }
}
