package com.paic.array;

/**
 * 稀疏数组：对于二维数组，数据如果太少，就会浪费大量的空间。
 * 举个简单的例子，比如围棋的存盘操作，我们初始化一个n * n的二维数组当棋盘，对于下的黑棋，我们将A[i][j]=1，对于白棋我们将A[i][j]=2处理，
 * 我们知道围棋下到中后期还没分出胜负的可能性很小，换句话说将整个二维数组填充满的可能性很小，因此使用这种办法在做存盘时会造成大量的空间浪费。
 *
 * 这里就使用到了稀疏数组，他是个二维数组。稀疏数组的列是固定的为3,行不固定，它取决于你的棋子个数
 * a[0][0]、a[0][1]、a[0][2]构成第一行，用于存储使用行、列以及棋子个数的总数
 * 除去第一行的其他行，用于存储每个棋子在棋盘上的状态
 *
 */
public class SparseArray {

    // 底层的数据结构,是一个二维数组
    int[][] arr;

    /**
     * 棋盘
     * @param chessArr
     */
    SparseArray(int[][] chessArr){

        // 初始化arr，首先知道有多少行、多少列、多少数据
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0)
                    sum++;
            }
        }
        int n = sum + 1;
        arr = new int[n][3];

        // 设置第一行值
        arr[0][0] = chessArr.length;
        arr[0][1] = chessArr[0].length;
        arr[0][2] = sum;
        
        // 设置除第一行的值
        // 用于记录放到第几行了
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0){
                    count ++;
                    arr[count][0] = i;
                    arr[count][1] = j;
                    arr[count][2] = chessArr[i][j];
                }
            }
        }

    }
    
    public static void print(int[][] arr){
        for (int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        
    }


    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        print(chessArr1);
        
        SparseArray sparseArray = new SparseArray(chessArr1);
        print(sparseArray.arr);
    }

}
