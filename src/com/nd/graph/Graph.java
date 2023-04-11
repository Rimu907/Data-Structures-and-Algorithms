package com.nd.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 5;  //结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
//        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); //
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //

//        //更新边的关系
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
//        graph.insertEdge(3, 7, 1);
//        graph.insertEdge(4, 7, 1);
//        graph.insertEdge(2, 5, 1);
//        graph.insertEdge(2, 6, 1);
//        graph.insertEdge(5, 6, 1);

        //显示一把邻结矩阵
        graph.showGraph();

        //测试dfs
//        System.out.println("深度遍历");
//        graph.dfs();
//        System.out.println();
        System.out.println("广度优先");
        graph.bfs();

    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    //得到第一个邻接结点的下标 w
    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for(int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for(int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先DFS
    //i第一次就是0
    public void dfs(boolean[] isVisited, int i){
        //首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为访问过
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while(w != -1){//说明有
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w被访问过
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，v没有下个邻接节点，遍历所有节点并进行dfs
    public void dfs(){
        //遍历所有节点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //广度优先
    private void bfs(boolean[] isVisited, int i){
        int u; //表示队列头节点对应下标
        int w; //表示邻接节点w
        //队列，记录节点访问顺序
        LinkedList queue = new LinkedList<>();
        //输出节点
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);
        while(!queue.isEmpty()){
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个临界点的下标w
            w = getFirstNeighbor(u);
            while(w != -1){
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //已经访问过 以u为前驱点 找w后面的下一个临接点
                w = getNextNeighbor(u,w);
            }
        }
    }
    //遍历所有的节点进行广度优先
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    //显示图对应的矩阵
    public void showGraph() {
        for(int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }
    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    /**
     *
     * @param v1 表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
