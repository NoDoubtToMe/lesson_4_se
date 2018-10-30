import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    final static ExecutorService ex = Executors.newFixedThreadPool(3);;
    final static ThreadClass threadClass = new ThreadClass();

    public static void main(String[] args) {
        ex.execute(threadClass.t1);
        ex.execute(threadClass.t2);
        ex.execute(threadClass.t3);
    }
}
