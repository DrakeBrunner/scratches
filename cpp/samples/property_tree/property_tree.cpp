#include <iostream>
#include <string>
#include <set>
#include <exception>
#include <boost/foreach.hpp>
#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/xml_parser.hpp>

struct settings {
    std::string fn;
    int lv;
    std::set<std::string> modules;
    // Prototypes
    void load(const std::string filename);
    void save(const std::string filename);
};

void settings::load(const std::string filename) {
    using boost::property_tree::ptree;
    ptree pt;

    read_xml(filename, pt);
    fn = pt.get<std::string>("debug.filename");
    lv = pt.get("debug.level", -1);

    BOOST_FOREACH(ptree::value_type &v, pt.get_child("debug.modules"))
        modules.insert(v.second.data());
}

void settings::save(const std::string filename) {
    using boost::property_tree::ptree;
    ptree pt;

    pt.put("debug.filename2", "hogehoge");
    pt.put("debug.level", 19999);

    std::set<int> tmp;
    tmp.insert(5);
    tmp.insert(25);
    tmp.insert(3);
    tmp.insert(-23);
    tmp.insert(0);

    BOOST_FOREACH(int num, tmp)
        pt.add("debug.numbers.num", num);

    write_xml(filename, pt);
}

int main(int argc, char const* argv[]) {
    try {
        settings s;
        s.load("settings_test.xml");
        std::cout << "level: " << s.lv << std::endl;
        std::cout << "modules:" << std::endl;
        std::set<std::string>::iterator it;

        std::set<std::string> m_set = s.modules;
        for(it = m_set.begin(); it != m_set.end(); ++it)
            std::cout << *it << std::endl;

        s.save("test.xml.out");
        std::cout << "written!" << std::endl;
    }
    catch (std::exception e) {
        std::cout << "error: " << e.what();
    }
    return 0;
}
