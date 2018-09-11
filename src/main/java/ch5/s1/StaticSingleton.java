package ch5.s1;

public class StaticSingleton {
    private StaticSingleton(){
        System.out.println("StaticSingleton is created");
    }
    // 内部类没被调用前不会被加载
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
