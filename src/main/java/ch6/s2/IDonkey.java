package ch6.s2;

public interface IDonkey {
    void eat();
    default void run(){
        System.out.println("Donkey run");
    }
}
