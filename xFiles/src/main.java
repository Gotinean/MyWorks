import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        System.out.println("Введите длину строки");
        int valueOfX;
        Scanner scanner = new Scanner(System.in);
        valueOfX = scanner.nextInt();
        String [][] xFiles = new String[valueOfX][valueOfX];
        int a;
        int b;
        for (a = 0; a <= xFiles.length -1; a++)
            for (b = 0; b <= xFiles.length -1; b++)
                if (a == b)
                {
                xFiles[a][b] = "X";
                }
        else if (b - a == xFiles.length)
                {
                    xFiles[a][b] = "X";
                }
        else if (b - a == valueOfX - valueOfX%2)
                {xFiles[a][b] = "X";}
        else if (a - b == valueOfX - valueOfX%2)
                {xFiles[a][b] = "X";}
        for(a = 0; a <= xFiles.length-1; a++)
            for (b = 0; b <= xFiles.length-1; b++)
                if (xFiles[a][b] != "X")
                {
                    xFiles[a][b] = " ";
                }
        for (int i = 0; i < xFiles.length; i++) {  //идём по строкам
            for (int j = 0; j < xFiles[i].length; j++) {//идём по столбцам
                System.out.print(" " + xFiles[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
}
