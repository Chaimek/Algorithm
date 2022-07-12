package com.atguigu.tree;

public class TowNodeMaxDistance {
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
        int maxDistance ;
        int height ;
        public  Info(int maxDistance , int height){
            this.maxDistance = maxDistance ;
            this.height = height ;
        }
    }

    public static Info getMaxDistance(Node X){
        if ( X == null){
            return new Info( 0 , 0 ) ;
        }
        Info leftInfo =  getMaxDistance(X.left);
        Info rightInfo =  getMaxDistance(X.right);
        int  height =Math.max(leftInfo.height , rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height+ rightInfo.height+1);
        return new Info(maxDistance , height) ;
    }


}
