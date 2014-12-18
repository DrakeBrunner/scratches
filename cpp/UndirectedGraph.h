#include <iostream>
#include <utility>
#include <set>

/*
 * An implementation of the data structure undirected graph.
 * Supports:
 *  - Adding nodes
 *  - Adding edges
 *  - Getting complement graph
 *  - Printing edges in the graph
 *  - Getting adjacent nodes
 *  - Getting adjacent edges
 */

template<typename Node>
using Edge = std::pair<Node, Node>;

template<typename Node>
class UndirectedGraph {
public:
    UndirectedGraph();
    ~UndirectedGraph();

    /* Adds edge between nodes n1 and n2. Nodes n1 and n2 must be present. */
    void add_edge(Node n1, Node n2);

    /* Creates an edge between nodes n1 and n2. Nodes n1 and n2 do not necessarily have to exist. */
    void create_edge(Node n1, Node n2);

    /* Returns true if there is an edge between nodes n1 and n2. */
    // TODO: Overload using has(n) and has(n1, n2)?
    bool has_edge(Node n1, Node n2);

    bool has_node(Node n);

    /* Adds a new node to the graph. */
    void create_node(Node n);
    // Same thing. TODO: use either but not both
    void add_node(Node n);

    int size();

    /* Returns a set of adjacent nodes for a given node. */
    std::set<Node>* get_adj_nodes(Node n);

    /* Returns a set of adjacent edges for a given node. */
    std::set<Edge<Node>*>* get_adj_edges(Node n);

    /* Returns a complement graph. */
    UndirectedGraph<Node>* get_complement();

    /* Prints all the edges */
    void print();

private:
    std::set<Node> nodes;
    std::set<Edge<Node>*> edges;
};

/* Implementations */
template<typename Node>
UndirectedGraph<Node>::UndirectedGraph() {
}

template<typename Node>
UndirectedGraph<Node>::~UndirectedGraph() {
    // Deallocate all the edges
    for (Edge<Node>* e : edges) {
        delete e;
    }
}

template<typename Node>
void UndirectedGraph<Node>::add_edge(Node n1, Node n2) {
    if (!has_node(n1) || !has_node(n2)) {
        throw "No such edge";
    }

    Edge<Node>* e = new Edge<Node>(n1, n2);
    edges.insert(e);
}

template<typename Node>
void UndirectedGraph<Node>::create_edge(Node n1, Node n2) {
    if (!has_node(n1)) {
        add_node(n1);
    }
    if (!has_node(n2)) {
        add_node(n2);
    }

    add_edge(n1, n2);
}

template<typename Node>
bool UndirectedGraph<Node>::has_edge(Node n1, Node n2) {
    for (Edge<Node>* e : edges) {
        if (e->first == n1 && e->second == n2) {
            return true;
        }
        if (e->first == n2 && e->second == n1) {
            return true;
        }
    }

    return false;
}

template<typename Node>
bool UndirectedGraph<Node>::has_node(Node n) {
    return nodes.count(n) != 0;
}

template<typename Node>
void UndirectedGraph<Node>::create_node(Node n) {
    add_node(n);
}

template<typename Node>
void UndirectedGraph<Node>::add_node(Node n) {
    nodes.insert(n);
}

template<typename Node>
int UndirectedGraph<Node>::size() {
    return nodes.size();
}

template<typename Node>
std::set<Node>* UndirectedGraph<Node>::get_adj_nodes(Node n) {
    std::set<Node>* adj_nodes = new std::set<Node>();

    for (Edge<Node>* e : edges) {
        if (e->first == n) {
            adj_nodes->insert((*e).second);
        }
        if (e->second == n) {
            adj_nodes->insert((*e).first);
        }
    }

    return adj_nodes;
}

template<typename Node>
std::set<Edge<Node>*>* UndirectedGraph<Node>::get_adj_edges(Node n) {
    std::set<Edge<Node>*>* adj_edges = new std::set<Edge<Node>*>();

    for (Edge<Node>* e : edges) {
        if (e->first == n || e->second == n) {
            adj_edges->insert(e);
        }
    }

    return adj_edges;
}

template<typename Node>
UndirectedGraph<Node>* UndirectedGraph<Node>::get_complement() {
    UndirectedGraph<Node>* complement = new UndirectedGraph<Node>();

    for (Node n1 : nodes) {
        for (Node n2 : nodes) {
            if (n1 == n2) {
                continue;
            }

            if (!has_edge(n1, n2) && !complement->has_edge(n1, n2)) {
                complement->create_edge(n1, n2);
            }
        }
    }

    return complement;
}

template<typename Node>
void UndirectedGraph<Node>::print() {
    for (Edge<Node>* e : edges) {
        std::cout << e->first << " -> " << e->second << std::endl;
    }
}
