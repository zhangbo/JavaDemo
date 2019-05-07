import java.io.PrintStream;
import java.lang.reflect.Field;

public class TestDemo implements Callback {

    private int globalThreads = 4;

    public TestDemo() {

    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Integer a = 10, b = 20;
//        System.out.println("a = " + a + ", b = " + b);
//        swap(a, b);
//        swap_reflection(a, b);
//        System.out.println("a = " + a + ", b = " + b);

        TestDemo td = new TestDemo();
        int copy = td.globalThreads;

        for (int i = 1; i <= copy; i++) {
            PrintTask pt = new PrintTask("线程" + i, td);
            new Thread(pt).start();
        }
    }

    public static void swap(Integer x, Integer y) {
        PrintStream ps = new PrintStream(System.out) {
            @Override
            public void println(String s) {
               super.println("a = 20, b = 10");
            }
        };
        System.setOut(ps);
    }

    public static void swap_reflection(Integer x, Integer y) throws NoSuchFieldException, IllegalAccessException {
        int temp = x.intValue();
        Class<Integer> clazz = Integer.class;
        Field f = clazz.getDeclaredField("value");
        f.setAccessible(true);
        f.set(x, y);
        f.setInt(y, new Integer(temp));
    }

    @Override
    public void notifyMainThread(int num) {
        globalThreads += num;
        if (globalThreads == 0) {
            System.out.println("All Task finished");
        }

    }

}





