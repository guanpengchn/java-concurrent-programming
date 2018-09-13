package ch6.s2;

public interface IHorse {
    void eat();
    default void run(){
        System.out.println("hourse run");
    }
}
