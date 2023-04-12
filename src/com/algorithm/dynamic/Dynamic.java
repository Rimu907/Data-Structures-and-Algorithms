package com.algorithm.dynamic;

import javax.swing.*;

/**
 * (业务实现)
 * 背包问题
 *
 * @author NANDI_GUO
 * @date 2023/4/11 14:28
 */
public class Dynamic {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000}; //val[i]就是当前物品的价值
        int m = 4; //背包容量
        int n = val.length; //物品个数

        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //创建动态规划表
        //v[i][j]
        int[][] v = new int[n + 1][m + 1];
        //初始化表第一行第一列的值
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //根据公式动态规划
        for (int i = 1; i < v.length; i++) { //不处理第一行第一列
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i - 1] > j) { //v作为表，下标用来调整位置不用-1，但是w和val存储有数据 需要-1来获取实际首数据
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放情况，不能简单的使用max来取值
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出一下表
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出最终的最优结果
        int i = path.length - 1; //行最大下标
        int j = path[0].length - 1; //列最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1];
            }
            i--;
        }
    }

}
