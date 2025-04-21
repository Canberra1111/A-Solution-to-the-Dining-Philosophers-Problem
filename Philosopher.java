public class Philosopher implements Runnable {
    public int id;
    public static int N=6;
    public static int THINKING=0;
    public static int HUNGRY=1;
    public static int EATING=2;
    private static int[] state=new int[N];
    private static Semaphore[] c;
    Semaphore mutex=new Semaphore(1);
    public Philosopher(int id){
        this.id=id;
        c=new Semaphore[N];
        for(int i=0;i<N;i++){
            c[i]=new Semaphore(0);
        }
    }
    public void think() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Philosopher "+id+" is thinking.");
    }
    public void eat() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Philosopher "+id+" is eating.");
        Thread.sleep(5000);
        System.out.println("Philosopher "+id+" finishes eating");
    }
    public void get_forks(int i) throws InterruptedException {
        mutex.P();
        state[i]=HUNGRY;
        test(i);
        mutex.V();
        c[i].P();
    }
    public void put_forks(int i) throws InterruptedException {
        mutex.P();
        state[i]=THINKING;
        offer_fork(i==0?N-1:i-1);
        offer_fork(i==N-1?0:i+1);
        mutex.V();
    }
    public void test(int i)throws InterruptedException {
        if((state[i==0 ? N-1:i-1]!=EATING)&&(state[i==N-1 ? 0:i+1]!=EATING)){
            state[i]=EATING;
            c[i].V();
        }
    }
    public void offer_fork(int i) throws InterruptedException {
        if(state[i]==HUNGRY&&state[i==0?N-1:i-1]!=EATING&&state[i==N-1?0:i+1]!=EATING){
            state[i]=EATING;
            System.out.println("Philosopher "+id+" offers "+i+" forks.");
            c[i].V();
        }
    }
    public void philosopher(int i) throws InterruptedException {
        while(true){
            think();
            get_forks(i);
            eat();
            put_forks(i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Philosopher p1=new Philosopher(0);
        Philosopher p2=new Philosopher(1);
        Philosopher p3=new Philosopher(2);
        Philosopher p4=new Philosopher(3);
        Philosopher p5=new Philosopher(4);
        Philosopher p6=new Philosopher(5);
        Thread t1=new Thread(p1);
        Thread t2=new Thread(p2);
        Thread t3=new Thread(p3);
        Thread t4=new Thread(p4);
        Thread t5=new Thread(p5);
        Thread t6=new Thread(p6);
        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();
        Thread.sleep(100);
        t4.start();
        Thread.sleep(100);
        t5.start();
        Thread.sleep(100);
        t6.start();
    }
    @Override
    public void run() {
        try {
            philosopher(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Semaphore{
    private int count;
    public Semaphore(int init_val){
        count=init_val;
    }
    public synchronized void P() throws InterruptedException {
        count--;
        while(count<0)wait();
    }
    public synchronized void V() throws InterruptedException {
        count++;
        notifyAll();
    }
}