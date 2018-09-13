package ch6.s2;

import java.util.ArrayList;
import java.util.List;

public class ConstrMethodRef {

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @FunctionalInterface
    interface UserFactory<U extends User> {
        U create(int id, String name);
    }

    static UserFactory<User> uf = User::new;

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        for(int i=0;i<10;i++){
            users.add(uf.create(i,"billy"+Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }
}
