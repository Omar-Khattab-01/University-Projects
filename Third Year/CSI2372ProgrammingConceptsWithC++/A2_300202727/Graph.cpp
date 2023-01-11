#include "Graph.h"
#include <iostream>


Graph::Graph() {
    n = capacity = 0;
    vertices = new LinkedList[SIZE];
    if (vertices == NULL)
        cout << "Not Enough Memory!"<< endl;
    else
    {
        capacity = SIZE;
    }
}

Graph::Graph(int num) {
    n = capacity = 0;
    vertices = new LinkedList[SIZE];
    if (vertices == NULL)
        cout << "Not Enough Memory!"<< endl;
    else
    {
        n = num;
        capacity = SIZE;
        for (int i = 0; i < n; ++i) {
            vertices[i] = LinkedList();
            vertices[i].add_to_front(i+1);
        }
    }
}

Graph::Graph(Graph & g){
    n = capacity = 0;
    vertices = new LinkedList[SIZE];
    if (vertices == NULL)
        cout << "Not Enough Memory!"<< endl;
    else
    {
        n = g.n;
        capacity = g.capacity;
        for (int i = 0; i < n; ++i) {
            vertices[i] = g.vertices[i];
        }

    }
}

Graph::~Graph() {

//    delete[] vertices;
}

void Graph::add_edge(int x, int y) {

    if ((x < 0 || y < 0) || (x > n+1 || y > n+1))
        return;

    if (!path_exist(x,y) || x == y){
        vertices[x-1].add_to_back(y);
    }
//

}
void Graph::remove_edge(int x, int y) {

    if ((x < 0 || y < 0) || (x > n+1 || y > n+1))
        return;

    if (path_exist(x,y)){
        vertices[x-1].remove_item(y);
    }
//

}

void Graph::operator++() {

    if (n < capacity){
        vertices[n] = LinkedList();
        vertices[n].add_to_back(n+1);
        n+=1;
        return;
    }

    LinkedList* temp;
    temp = new LinkedList[n+SIZE];
    if (temp == NULL){

        cout <<"Not enough memory!"<<endl;
        return;
    }

    for (int i = 0; i < n; ++i) {
        temp[i] = vertices[i];
    }
    capacity = n+SIZE;
    temp[n] = LinkedList();
    temp[n].add_to_back(n+1);
    n+=1;
    delete[] vertices;
    vertices = temp;
}

void Graph::operator++(int a) {

    ++*(this);

}

void Graph::operator--() {

    if (n > 0){
        vertices->remove_item(n-1);
        n-=1;

    }


}

void Graph::operator--(int a) {

    --*(this);

}

bool Graph::edge_exist(int x, int y) {
    if ((x < 0 || y < 0) || (x > n+1 || y > n+1))
        return false;

    return vertices[x-1].search(y) || vertices[y-1].search(x);

}

bool Graph::path_exist(int x, int y) {
    if ((x < 0 || y < 0) || (x > n+1 || y > n+1))
        return false;

    return vertices[x-1].search(y);

}
int Graph::get_degree(int v)
{
    if ( v > 0 && v < n+1){
        return vertices[v-1].count_nodes();

    }
    return -1;
}


ostream& operator<<(ostream& out, Graph g){

    out <<"V ={";
    for (int i = 0; i < g.n; ++i) {

        if (i != 0) out << ", ";
        out <<i+1;

    }
    out << "}"<<endl<< "E = "<<endl<<"{"<<endl;

    for (int i = 0; i < g.n; ++i) {
        out <<"   "<< g.vertices[i]<<endl;

    }
    out <<"}";


    return out;

}

