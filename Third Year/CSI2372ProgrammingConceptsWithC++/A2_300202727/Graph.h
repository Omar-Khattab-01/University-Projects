#pragma once
#include <iostream>
#include "LinkedList.h"
#define SIZE 100

class Graph {
private:
    int n, capacity;
    LinkedList* vertices;
public:
    Graph();
    Graph(int);
    Graph(Graph&);
    ~Graph();
    void add_edge(int,int);
    void remove_edge(int,int);
    bool edge_exist(int,int);
    int get_degree(int);
    void operator++();
    void operator++(int);
    void operator--();
    void operator--(int);
    bool path_exist(int,int);
    friend ostream& operator<< (ostream&, Graph);

};

