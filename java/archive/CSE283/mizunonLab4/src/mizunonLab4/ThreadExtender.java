package mizunonLab4;

public class ThreadExtender extends Thread {

    private NumberPrinter printer;

    public ThreadExtender(String threadName) {
        super(threadName);
    }

    public ThreadExtender(String threadName, NumberPrinter printer) {
        this(threadName);
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 100; i < 200; i += 10) {
            // System.out.println("\t" + i);
            printer.printTenNumbers(i);

            try {
                Thread.sleep((long) (Math.random() * 10));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Thread.yield();
        }

        printer.printString(this.getName() + " completed!");
    }
}
