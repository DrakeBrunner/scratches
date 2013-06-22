#include <iostream>
#include <ctime>
using namespace std;

class stopwatch {
    clock_t startTime;
    bool running;

    public:
    stopwatch();
    ~stopwatch();
    void start();
    double show();
    double stop();
};

stopwatch::stopwatch() {
    start();
}

stopwatch::~stopwatch() {
    if (running)
        stop();
}

void stopwatch::start() {
    running = true;
    startTime = clock();
}

double stopwatch::show() {
    if (!running)
        return 0;
    clock_t current = clock();
    return (current - startTime * 1.0) / CLOCKS_PER_SEC;
}

double stopwatch::stop() {
    clock_t stopTime = clock();
    running = false;

    double duration = (stopTime - startTime * 1.0) / CLOCKS_PER_SEC;

    return duration;
}

int main() {
    stopwatch sw;
    sw.start();

    int i;
    for (i = 0; i < 1000000000; i++)
        if (i == 100000000)
            cout << sw.show() << endl;
    cout << sw.stop();
    return 0;
}
