#include <iostream>
#include <string>

class Person {
    float height, weight;
    std::string name;
public:
    Person(std::string name);
    Person(std::string name, float height, float weight);
    ~Person();

    float get_height();
    float get_weight();
    std::string get_name();

    void set_height(float height);
    void set_weight(float weight);

    std::string to_string();

    // Yes, I know these don't make sense :P
    Person operator+(Person& other);
    Person operator-(Person& other);
};

Person::Person(std::string name) {
    this->name = name;
    this->height = 0;
    this->weight = 0;
}

Person::Person(std::string name, float height, float weight) {
    this->name = name;
    this->height = height;
    this->weight = weight;
}

Person::~Person() {
}

float Person::get_height() {
    return height;
}

float Person::get_weight() {
    return weight;
}

std::string Person::get_name() {
    return name;
}

void Person::set_height(float height) {
    this->height = height;
}

void Person::set_weight(float weight) {
    this->weight = weight;
}

std::string Person::to_string() {
    std::string s;
    s.append("Name  : " + name + "\n");
    s.append("Height: " + std::to_string(height) + "\n");
    s.append("Weight: " + std::to_string(weight) + "\n");

    return s;
}

Person Person::operator+(Person& other) {
    float total_height = this->height + other.get_height();
    float total_weight = this->weight + other.get_weight();
    return Person("SUM", total_height, total_weight);
}

Person Person::operator-(Person& other) {
    float total_height = this->height - other.get_height();
    float total_weight = this->weight - other.get_weight();
    return Person("DIFF", total_height, total_weight);
}

int main(int argc, char const* argv[]) {
    Person p1("Foo", 173.64, 63.5);
    Person p2("Bar", 165.82, 58.2);

    std::cout << p1.to_string() << std::endl;
    std::cout << p2.to_string() << std::endl;

    std::cout << (p1 + p2).to_string() << std::endl;
    std::cout << (p1 - p2).to_string() << std::endl;

    return 0;
}
