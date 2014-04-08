// Why do I need `public` to used Base::method1() ?
class Derived : public Base {
    public:
        Derived();
        //void method1();
    private:
        int bar;
};
