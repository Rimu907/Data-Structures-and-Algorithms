package com.nd.recursion;

/**
 * 迷宫问题(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/5 14:54
 */
public class Maze {
    public static void main(String[] args) {
        //创建一个二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部为墙
        for (int i = 0; i < 7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //上下为墙
        for (int i = 0; i < 8 ; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        setWay(map,1,1);

        System.out.println("输出新地图");
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @description: 1墙 2通路 3死路
     * @param map 地图
     * @param i 起始位置
     * @param j
     * @return boolean 找到map[6][5]
     * @author: NANDI_GUO
     * @date: 2022/11/5 15:01
     */
    //使用递归找到路
    public static boolean setWay(int[][] map, int i, int j){
        if (map[6][5] == 2){ //找到终点
            return true;
        } else {
            if (map[i][j] == 0){ //没走过
                //按照策略 下 右 上 左
                map[i][j] = 2;
                if (setWay(map,i+1,j)){ //向下
                    return true;
                } else if (setWay(map, i ,j+1)){//向右
                    return true;
                } else if (setWay(map, i-1, j)){//向上
                    return true;
                } else if (setWay(map, i, j-1)){//向左
                    return true;
                } else {
                    //该点死路，走不通
                    map[i][j] = 3;
                    return false;
                }
            } else { //可能是1，2，3
                return false;
            }
        }
    }
}
