public class MyThread extends Thread{
    private int threadNumber;

    public MyThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i=0; i<1000; i++) {
            System.out.println(threadNumber);
        }
    }
}
