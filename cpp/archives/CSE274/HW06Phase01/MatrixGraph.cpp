#include "MatrixGraph.h"

MatrixGraph::MatrixGraph(unsigned int num_nodes) {
    // Iterator for vector
    std::vector<std::vector<EdgeWeight>>::iterator it_v;
    // Iterator for edges
    std::vector<EdgeWeight>::iterator it_e;
    for (it_v = M.begin(); it_v != M.end(); it_v++) {
        for (it_e = it_v->begin(); it_e != it_v->end(); it_e++) {
            *it_e = -1;
        }
    }
}

EdgeWeight MatrixGraph::weight(NodeID u, NodeID v) const {
    return M[u][v];
}

std::list<NWPair> MatrixGraph::getAdj(NodeID u) const {
    std::list<NWPair> ret;
    std::vector<EdgeWeight>::iterator it;
    for (it = M.at(u).begin; it != M.at(u).end(); it++) {
        // Only add if it's not itself or distance is infinity
        if (*it != 0 && *it != -1)
            ret.push_back(NWPair(u, *it));
    }

    return ret;
}

unsigned int MatrixGraph::degree(NodeID u) const {
    return getAdj(u).size();
}

unsigned int MatrixGraph::size() const {
    return M.size();
}

unsigned int MatrixGraph::numEdges() const {
    int sum = 0;
    std::vector<EdgeWeight>::iterator it;
    for (NodeID id = (NodeID)0; id < (NodeID)size(); id++)
        sum += degree(id);
    sum /= 2;

    return sum;
}

void MatrixGraph::bfs(NodeID s) const {
}
