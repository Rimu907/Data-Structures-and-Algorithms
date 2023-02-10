package com.nd.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/27 16:48
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
//        byte[] bytes = str.getBytes();
//        System.out.println(Arrays.toString(bytes));
//
//        List<Node> nodes = getNodes(bytes);
//        System.out.println(nodes);
//
//        Node huffmanTree = createHuffmanTree(nodes);
//        huffmanTree.preOrder();
//
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
//        System.out.println(huffmanCodes);
//
//        byte[] zip = zip(bytes, huffmanCodes);
//        System.out.println(Arrays.toString(zip));
        byte[] zip = huffmanZip(str);
        byte[] decode = decode(huffmanCodes, zip);
        System.out.println(Arrays.toString(zip));
        System.out.println(Arrays.toString(decode));
    }

    //解压
    //1.重新转成二进制的字符串
    //2.将二进制字符串按找赫夫曼编码还原数据
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length ; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        HashMap<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i, i + count);
                b=map.get(key);
                if (b==null){ //没匹配到
                    count++;
                } else {
                    flag = false; //匹配到了
                }
            }
            list.add(b);
            i+=count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    //将一个byte转成二进制字符串
    public static String byteToBitString(boolean flag, byte b){
        //使用变量保存b
        int temp = b;
        if (flag){
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp); //返回的是temp对应二进制的补码
        if (flag){
            return s.substring(s.length()-8);
        }
        return s;
    }

    //压缩方法总包
    private static byte[] huffmanZip(String str){
        byte[] bytes = str.getBytes();
        //数据转节点
        List<Node> nodes = getNodes(bytes);
        //生成赫夫曼编码树
        Node huffmanTree = createHuffmanTree(nodes);
        //根据编码获取对应byte数组
        Map<Byte, String> codes = getCodes(huffmanTree);
        //压缩
        byte[] zip = zip(bytes, codes);
        return zip;
    }

    //编写一个方法，将元数据根据赫夫曼编码进行处理
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将编码的133字符串转成byte[]
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8){
            String strByte;
            if (i+8>stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i+8);
            }
            //将strByte转成一个byte，放入赫夫曼数组
            huffmanCodeBytes[index++] = (byte)Integer.parseInt(strByte,2);
        }
        return huffmanCodeBytes;
    }

    //赫夫曼编码 32->01 97->100 100->11000等等
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * @description: 将中传入的node节点的所有叶子节点的赫夫曼编码存档
     * @param node  节点
     * @param code 路径 左0 右1
     * @param stringBuilder 拼接路径
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/27 17:25
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){
            if (node.data == null){ //非叶子节点
                //递归处理
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            } else {
                //表示找到路某个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    private static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        //存储每个byte出现的次数
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.put(b,counts.getOrDefault(b,0)+1);
        }
        //把每一个键值对转换成一个Node对象，加入nodes集合
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //权值，data为空
            Node root = new Node(null, leftNode.weight + rightNode.weight);
            //左右挂树上
            root.left = leftNode;
            root.right = rightNode;
            //删除左右
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //加进去新的
            nodes.add(root);
        }
        return nodes.get(0);
    }
}

//创建node, 有数据和权值
class Node implements Comparable<Node> {
    Byte data; //ascii 字符本身
    int weight; //权值，表示数据/字符存的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
