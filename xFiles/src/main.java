public class main
{
    public static void main(String[] args)
    {
        String [][] xFiles = new String[7][7];
        xFiles[0][0] = "X";
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
        xFiles[0][1] = "";xFiles[0][2] = "";xFiles[0][3] = "";xFiles[0][4] = "";
        xFiles[0][5] = "";xFiles[1][2] = "";xFiles[1][3] = "";xFiles[1][4] = "";
        xFiles[6][5] = "";xFiles[1][6] = "";xFiles[2][0] = "";xFiles[2][1] = "";
        xFiles[2][3] = "";xFiles[2][5] = "";xFiles[2][6] = "";xFiles[3][0] = "";
        xFiles[3][1] = "";xFiles[3][2] = "";xFiles[3][4] = "";xFiles[3][5] = "";
        xFiles[3][6] = "";xFiles[4][0] = "";xFiles[1][0] = "";xFiles[4][1] = "";
        xFiles[4][3] = "";xFiles[4][5] = "";xFiles[4][6] = "";xFiles[5][0] = "";
        xFiles[5][2] = "";xFiles[5][3] = "";xFiles[5][4] = "";xFiles[5][6] = "";
        xFiles[6][1] = "";xFiles[6][2] = "";xFiles[6][3] = "";xFiles[6][4] = "";

        for (int i = 0; i < 7; i++) {  //идём по строкам
            for (int j = 0; j < 7; j++) {//идём по столбцам
                System.out.print(" " + xFiles[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
}
