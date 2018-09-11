package ch4.s3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakHashMap，此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值，
 * 见实例：此例子中声明了两个Map对象，一个是HashMap，一个是WeakHashMap，同时向两个map中放入a、b两个对象，
 * 当HashMap  remove掉a 并且将a、b都指向null时，WeakHashMap中的a将自动被回收掉。出现这个状况的原因是，
 * 对于a对象而言，当HashMap  remove掉并且将a指向null后，除了WeakHashMap中还保存a外已经没有指向a的指针了，
 * 所以WeakHashMap会自动舍弃掉a，而对于b对象虽然指向了null，但HashMap中还有指向b的指针，所以WeakHashMap将会保留
 */
public class WeakVsHashMap {

    public static void main(String[] args) throws Exception {
        String a = new String("a");
        String b = new String("b");
        Map weakmap = new WeakHashMap();
        Map map = new HashMap();
        map.put(a, "aaa");
        map.put(b, "bbb");


        weakmap.put(a, "aaa");
        weakmap.put(b, "bbb");

        map.remove(a);

        a = null;
        b = null;

        System.gc();
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry en = (Map.Entry) i.next();
            System.out.println("map:" + en.getKey() + ":" + en.getValue());
        }

        Iterator j = weakmap.entrySet().iterator();
        while (j.hasNext()) {
            Map.Entry en = (Map.Entry) j.next();
            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());
        }
    }
}
