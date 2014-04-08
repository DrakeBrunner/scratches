package mizunonLab4;

public class ThreadMaker {

    public static void main(String[] args) {

        NumberPrinter printer = new NumberPrinter();

        new SimpleGUI(printer);

        ThreadExtender t1 = new ThreadExtender("foo", printer);
        t1.start();
        // t1.run();

        RunnableImplementer ri = new RunnableImplementer(printer);
        Thread t = new Thread(ri);
        t.start();

        for (int i = 0; i < 100; i += 10) {
            // System.out.println(i);
            printer.printTenNumbers(i);

            try {
                Thread.sleep((long) (Math.random() * 10));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Thread.yield();
        }
    }

}
