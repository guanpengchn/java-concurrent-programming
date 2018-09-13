package ch6.s2;

import java.util.ArrayList;
import java.util.List;

public class InstanceMethodRef {

    static class User{
        private int num;
        private String name;

        public User(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args){
        List<User> users=new ArrayList<User>();
        for(int i=1;i<10;i++){
            users.add(new User(i,"billy"+Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }
}
