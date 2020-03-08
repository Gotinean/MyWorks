public class main
{
    public static void main(String[] args)
    {
        String [][] xFiles = new String[7][7];
        int a;
        int b;
        for(a = 0; a <= 6; a++)
            for (b = 0; b <= 6; b++)
                if (xFiles[a][b] != "Х")
                {
                    xFiles[a][b] = " ";
                }
        xFiles[0][0] = "Х";
        xFiles[1][1] = "X";
        xFiles[2][2] = "X";
        xFiles[3][3] = "X";
        xFiles[4][4] = "X";
        xFiles[5][5] = "X";
        xFiles[6][6] = "X";
        xFiles[0][6] = "X";
        xFiles[1][5] = "X";
        xFiles[2][4] = "X";
        xFiles[4][2] = "X";
        xFiles[5][1] = "X";
        xFiles[6][0] = "X";
        for (int i = 0; i < xFiles.length; i++) {  //идём по строкам
            for (int j = 0; j < xFiles[i].length; j++) {//идём по столбцам
                System.out.print(" " + xFiles[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
}
