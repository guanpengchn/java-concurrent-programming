package ch6.s2;

public interface IAnimal {
    default void breath(){
        System.out.println("breath");
    }
}
