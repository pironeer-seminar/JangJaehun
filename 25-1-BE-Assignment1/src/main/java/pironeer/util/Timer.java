package pironeer.util;

public class Timer {

    public void sleep(long millis) {
        try {
            Thread.sleep(millis); // millis(ms) 동안 현재 스레드를 멈춤
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
