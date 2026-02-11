public class PrimeCalculater implements Runnable{
   private  final int start;
   private final int end;

    public PrimeCalculater(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private  boolean isPrime(int number){
        if (number<=1) return false;
        if (number==2)return  true;
        if(number%2==0) return false;
        for (int i=3;i<-Math.sqrt(number);i+=2){
            if(number%i==0)return false;
        }
        return true;
    }
    private static synchronized void printPrime(int number){
        System.out.println(Thread.currentThread().getName()+"-"+number);
    }
    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                printPrime(i);
            }
        }
    }

    public static void main(String[] args) {

        // Divide work among threads
        Thread t1 = new Thread(new PrimeCalculater(1, 50), "Thread-1");
        Thread t2 = new Thread(new PrimeCalculater(51, 100), "Thread-2");
        Thread t3 = new Thread(new PrimeCalculater(101, 150), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
