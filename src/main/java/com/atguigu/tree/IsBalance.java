package com.atguigu.tree;

public class IsBalance {
    public static class Node{
        int value ;
        Node left ;
        Node right;
        public String toString(){
            return value + "->" ;
        }
        public Node(int value){
            this.value = value ;
        }
    }

    public static class Info{
        boolean isBalance ;
        int height ;
        public Info(boolean isBalance , int height){
            this.isBalance = isBalance ;
            this.height = height ;
        }
    }
    public static Info isBalance(Node head){
        if (head == null ){
            return  new Info(true , 0 ) ;
        }
        Info leftInfo = isBalance(head.left);
        Info rightInfo = isBalance(head.right) ;
        int height = Math.max(leftInfo.height , rightInfo.height) + 1 ;
        boolean isBalance = false ;
        if (leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height  - rightInfo.height ) < 2){
            isBalance   = true ;
        }
        return new Info(isBalance ,height);
    }


    public static boolean isBalance1(Node head) {
        if ( head == null ){
            return true ;
        }
        boolean leftBalance = isBalance1(head.left);
        boolean rightBalance = isBalance1(head.right);
        //其实这里并不需要重新获取，只需要下级向上级返回就行了
        int leftHeight =  getHeight(head.left);
        int rightHeight =  getHeight(head.right);
        boolean isBalance = false ;
        if (leftBalance && rightBalance && Math.abs(leftHeight - rightHeight) < 2){
            isBalance = true ;
        }
        return isBalance ;
    }

    public static int getHeight(Node head){
        if (head == null ){
            return 0 ;
        }
        return Math.max(head.left == null ? 0: getHeight(head.left) , head.right == null? 0 :getHeight(head.right))+1;
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 50;
        int maxValue = 10000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalance(head).isBalance != isBalance1(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
