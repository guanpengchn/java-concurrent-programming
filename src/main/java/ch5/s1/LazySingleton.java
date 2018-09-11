package ch5.s1;

public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton is created");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }
}
