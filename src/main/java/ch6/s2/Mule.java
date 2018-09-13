package ch6.s2;

public class Mule implements IHorse,IDonkey,IAnimal {

    @Override
    public void eat() {
        System.out.println("Mule eat");
    }

    @Override
    public void run() {
        IHorse.super.run();
    }

    public static void main(String[] args){
        Mule m =new Mule();
        m.run();
        m.breath();
    }
}
