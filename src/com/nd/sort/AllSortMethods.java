package com.nd.sort;

import java.util.Arrays;

import static com.nd.sort.HeapSort.adjustHeap;

/**
 * 总结所有排序算法(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/18 19:33
 */
public class AllSortMethods {
    public static void main(String[] args) {
        int arr[] = {9,8,7,6,5,4,3,2,1};
        //bubbleSort(arr);
        //insertSort(arr);
        //shellsort(arr);
        //selectSort(arr);
        //quickSort(arr,0,arr.length-1);
        //mergeSort(arr,0,arr.length-1, new int[arr.length]);
        //redixsort(arr);
        heapSort(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    /**
     * @description: 冒泡排序，交换解决。双层for，第一层是比较轮数，第二层是比较次数
     * @param arr 传入数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 19:34
     */
    public static void bubbleSort(int[] arr){
        int helper; //此处定义，节省栈空间
        boolean flag = false; //优化冒泡
        for (int i = 0; i < arr.length-1; i++) { //比较数组长度-1轮
            for (int j = 0; j < arr.length-1-i; j++){ //每次最大的都冒到后面，所以后面-i不需要再比
                if (arr[j] > arr[j+1]){
                    flag = true;
                    helper = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = helper;
                }
            }
            if (!flag){ //优化冒泡性能，成了就不需要再来了
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * @description: 插入排序，扑克牌 无序表插有序表，待插入数插入被插数的前面, 假设待插数的下标是1
     * @param arr 传入数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 19:53
     */
    public static void insertSort(int[] arr){
        //无序表第一个数下标为0，待插入的数的下标为1，为方便循环起点为1
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;      //被插入下标，即无序表第一位
            int insertVal = arr[i]; //待插入下表，即有序表第一位

            //保证待插入数跟被插入数比较时不越界，待插入数比被插入数小证明还没找到位置，需进入循环
            while(index >= 0 && insertVal < arr[index]){
                //将被插入数右移挪位置
                arr[index+1] = arr[index];
                //被插入数下标--，继续与待插入数比较，一直到退出循环
                index--;
            }
            //退出循环证明index停留在被插位置的前一个位置，需要++
            arr[index+1] = insertVal;
        }
    }

    /**
     * @description: 希尔排序， 先确定步长，分治法，后用插入排序
     * @param arr 传入数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 20:51
     */
    public static void shellsort(int[] arr){
        //首先按照gap进行分组
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            //按组进行遍历, gap是中间数，i-gap就是第一个元素，gap往后+1就是换组，到最后一个元素就是最后一组
            for (int i = gap; i < arr.length ; i++) {
                //对每组中的元素进行插入排序
                int index = i - gap;      //被插入数的下标
                int insertVal = arr[i];   //待插入数为当前数
                //条件 index需要大于等于0, 待插入比被插入小就是没找到，进循环
                while(index >= 0 && insertVal < arr[index]){
                    //被插入数后移
                    arr[index+gap] = arr[index];
                    index -= gap;
                }
                //出循环就是找到位置
                arr[index+gap] = insertVal;
            }
        }
    }

    /**
     * @description: 快速排序 找基准数 双指针遍历 右左找小 左右找大 指针所指交换位置 基数复位 递归左右排序
     * @param arr 传入数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 21:18
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right){
            return;
        }
        int base = arr[left]; //定义基准数
        int i = left;    //定义左指针
        int j = right;   //定义右指针
        int temp;
        //左右不等时进入循环
        while(i!=j){
            //从右往左，找右边比base小的, 不满足条件就是小
            while(i<j && arr[j] >= base){
                j--;
            }
            //从左往右，找左边比base大的，不满足条件就是大
            while(i<j && arr[i] <= base){
                i++;
            }
            //换位
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        //交换基数到中间
        arr[left] = arr[j];
        arr[j] = base;
        //基数左边递归排序
        quickSort(arr, left ,i-1);
        quickSort(arr, j+1 , right);
    }

    /**
     * @description: 选择排序 默认第一位最小 遍历后面挑最小 最小换位 记录最小
     * @param
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 21:35
     */
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;      //最小数下标为第一个数
            int min = arr[i];      //最小数起始为第一个数
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j]; //重置最小数
                    minIndex = j; //重置坐标
                }
            }
            //找到比第一个数小的数, 换位
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * @description: 归并排序，先分再和，分用递归，和分层和
     * @param
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 21:50
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left >= right){
            return;
        }
        //先找中间数
        int mid = (left + right)/2;
        mergeSort(arr, left, mid, temp); //左分
        mergeSort(arr, mid + 1, right, temp); //右分
        //分解完，栈顶存的为散着的元素，开始合并
        mergeSort(arr, left, mid, right, temp);
    }
    private static void mergeSort(int[] arr, int left, int mid, int right, int[] temp){
        int i = left; //左边有序列表的初始索引
        int j = mid + 1;//右边有序列表的初始索引
        int t = 0; //辅助数组的初始索引

        //把左右两边列表数据比大小填充进temp, 谁小先填充谁
        while(i <= mid && j <= right){ //条件是直到左右两边的列表有一个处理完为止
            temp[t++] = arr[i] < arr[j]? arr[i++] : arr[j++];
        }
        //左边还没到mid，填充左边进去
        while(i <= mid){
            temp[t++] = arr[i++];
        }
        //右边还没到right，填充右边进去
        while(j <= right){
            temp[t++] = arr[j++];
        }

        //拷贝temp的所有元素到arr
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }

    /**
     * @description: 基数排序 创建十个桶 按最大位数遍历 存每个桶有效个数 清零每个桶
     * @param arr 数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 22:28
     */
    public static void redixsort(int[] arr){
        int max = arr[0];
        for (int n : arr){
            if (n > max){
                max = n;
            }
        }

        int maxDigit = String.valueOf(max).length(); //得出数组中最大值的位数
        int[][] buckets = new int[10][arr.length]; //桶子
        int[] bcounts = new int[10]; //存每个桶子中的有效数字

        //按最大位数来确定遍历次数 每次除的不一样来获取个位十位百位
        for (int i = 1, f = 1; i <= maxDigit; i++, f*=10) {
            //遍历数组的每个元素
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j]/f%10; //数组每个元素的某一位
                //buckets[分配到某个桶子][某个桶子的某个位置]
                buckets[digit][bcounts[digit]] = arr[j];
                bcounts[digit]++;
            }

            //取桶的元素回数组
            int index = 0;
            //遍历十个桶子
            for (int k = 0; k < bcounts.length; k++) {
                //判断桶子是不是空的
                if (bcounts[k] != 0){
                    //bcounts[k] 代表第k个桶中右多少有效个数
                    for (int l = 0; l < bcounts[k]; l++) {
                        arr[index++] = buckets[k][l];
                    }
                }
                bcounts[k] = 0;
            }
        }
    }

    /**
     * @description: 堆排序调用方法，获取堆，后排序
     * @param arr
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/25 12:51
     */
    public static void heapSort(int arr[]){
        int temp = 0;
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            getHeap(arr, i, arr.length);
        }
        for (int j = arr.length-1; j > 0; j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }

    /**
     * @description: 堆排序，变成大顶堆后，最大数沉底，进入循环
     * @param arr 数组
     * @param i 开始的节点，第一次初始化堆从1开始，后面是从0开始
     * @param length 对多少元素进行调整，后面会不断减小，因为大的数沉在了最下面
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/25 12:35
     */
    private static void getHeap(int[] arr, int i, int length){
        int temp = arr[i]; //最后一个非叶子节点
        //比较当前节点的左右两个节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1){
            //如果右节点比左节点大 将k指向右节点，如果没进判断就是k指向左子节点
            if (k+1<length && arr[k] < arr[k+1]){
                k++;
            }
            //比较子节点和当前节点
            if (arr[k] > temp){
                arr[i] = arr[k]; //将当前节点的值改为右子节点
                i = k; //再将i指向之前的右子节点
            } else {
                break;
            }
            arr[i] = temp;
        }

    }
}
