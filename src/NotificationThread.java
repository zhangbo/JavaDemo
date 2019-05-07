import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;

public abstract class NotificationThread implements Runnable {


    private java.util.List<TaskListener> listeners = Collections.synchronizedList(new ArrayList<>());

    public abstract void doSomeWork();

    public final void addListener(TaskListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TaskListener listener) {
        listeners.remove(listener);
    }

    private final void notifyListeners() {
        synchronized (listeners) {
            for (TaskListener listener : listeners) {
                listener.threadExecuteComplete(this);
            }
        }
    }

    public void run() {
        try {
            doSomeWork();
        } finally {
            notifyListeners();
        }
    }


}
