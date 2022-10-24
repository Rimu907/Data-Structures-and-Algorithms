package com.nd.sparseArray;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/22 12:05
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建原始数组 11 * 11
        //0 没有 1 黑 2 蓝
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        //输出原始二维数组
        System.out.println("原始二维数组");
        for (int[] row : chessArray1){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //数组转稀疏数组
        int[][] sparseArray = arrayToSparseArray(chessArray1);
        //稀疏数组转数组
        int[][] reArray = sparseArrayToArray(sparseArray);
    }

    public static int[][] arrayToSparseArray(int[][] array){
        //一共有多少值
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0){
                    sum++;
                }
            }
        }

        //创建稀疏数组 行 | 列 | 值
        //sum+1行，有一行要存原数组大小和值
        int sparseArray[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历二维数组，存非0值
        int count = 1; //用于记录是第几个非0数据 从第二行开始
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0){ //举例 [1][2] = 1
                    sparseArray[count][0] = i; //第一行的[0] = 1
                    sparseArray[count][1] = j; //第一行的[1] = 2
                    sparseArray[count][2] = array[i][j]; //第一行的[2 = 值
                    count++; //到下一行
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();
        return sparseArray;
    }

    private static int[][] sparseArrayToArray(int[][] array) {
        //创建原始数组
        int[][] originArray = new int[array[0][0]][array[0][1]];
        //循环次数
        int cycle = array[0][2];
        //从第一行开始遍历
        for (int i = 1; i <= cycle; i++) {
            originArray[array[i][0]][array[i][1]] = array[i][2];
        }

        System.out.println("复原二维数组");
        for (int[] row : originArray){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        return originArray;
    }

}
