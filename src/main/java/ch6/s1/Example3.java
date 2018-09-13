package ch6.s1;

import java.util.Arrays;

public class Example3 {
    public static void main(String[] args){
        int[] arr={1,3,4,5,6,7,8,9,10};
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2!=0){
                arr[i]++;
            }
            System.out.println(arr[i]);
        }

        Arrays.stream(arr).map(x->(x%2==0?x:x+1)).forEach(System.out::println);
    }
}
