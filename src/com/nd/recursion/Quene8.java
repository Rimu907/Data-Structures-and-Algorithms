package com.nd.recursion;

/**
 * 皇后(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/5 15:54
 */
public class Quene8 {
    //定义一个max表示共多少皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果
    int[] array = new int[max];
    public static void main(String[] args) {
//        Quene8 quene8 = new Quene8();
//        quene8.check(0);



    }

    //编写一个方法，放置第n皇后
    private void check(int n){
        if (n == max){//n=8, 其实8个皇后已经放好
            print();
            return;
        }
        //依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++){
            //先把当前皇后n，放到该行第一列
            array[n] = i;
            if (judge(n)){
                check(n+1);
            }
        }
    }

    //当我们放置第n皇后，就去检测该皇后是否和前面已经放置的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n ; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]) ){
                return false;
            }
        }
        return true;
    }

    //写一个方法，将皇后摆放的位置输出
    public void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        };
        System.out.println();
    }
}
