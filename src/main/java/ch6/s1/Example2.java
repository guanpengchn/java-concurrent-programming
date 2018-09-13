package ch6.s1;

import java.util.Arrays;

public class Example2 {
    public static void main(String[] args){
        int[] arr={1,3,4,5,6,7,8,9,10};
        Arrays.stream(arr).map((x)->x=x+1).forEach(System.out::println);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::println);
    }
}
