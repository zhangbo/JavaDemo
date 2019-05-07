import com.sun.jmx.snmp.tasks.Task;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class PrintTask extends NotificationThread implements TaskListener{

    private String tmp;
    private final Callback cb;
    public PrintTask(String str, Callback callback) {
        addListener(this);
        this.tmp = str;
        this.cb = callback;
    }
    @Override
    public void doSomeWork() {
        System.out.println(this.tmp + " is running!");
    }

    @Override
    public void threadExecuteComplete(Runnable runnable) {
        System.out.println(this.tmp + " finished!");
        cb.notifyMainThread(-1);
    }
}
