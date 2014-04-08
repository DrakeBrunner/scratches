package mizunonLab4;

public class RunnableImplementer implements Runnable {

    private NumberPrinter printer;

    public RunnableImplementer(NumberPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 200; i < 300; i += 10) {
            // System.out.println("\t\t" + i);
            printer.printTenNumbers(i);
        }

        printer.printString("Runnable completed!");
    }
}
