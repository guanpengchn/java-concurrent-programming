package ch6.s1;

import java.util.Arrays;

public class Example1 {
    public static void imperative(){
        int[] iArr={1,3,4,5,6,9,8,7,4,2};
        for(int i=0;i<iArr.length;i++){
            System.out.println(iArr[i]);
        }
    }

    public static void declarative(){
        int[] iArr={1,3,4,5,6,9,8,7,4,2};
        Arrays.stream(iArr).forEach(System.out::println);
    }

    public static void main(String[] args){
        imperative();
        declarative();
    }
}
