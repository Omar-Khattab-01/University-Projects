#pragma once

#include <iostream>
#include <string>
#include <set>

#define MAX_CARD 1000

using namespace std;
template <typename type>
struct Pair
{
	type first;
	type second;
};

template <typename  type>
class Relation
{
private:
    int size;
	set<type> root;
	Pair<type>* elements;
	int capacity;
public:
	Relation();
	Relation(const Relation<type>&);
	~Relation();//Destructor
	int cardinality();
	void add_to_set(type);
	bool add_element(type, type);
	void remove_element(type, type);
	bool is_member(type, type);
	bool equal(Relation<type>);
	bool subset(Relation<type>);
	bool reflexive();
	bool irreflexive();
	bool symmetric();
	bool asymmetric();
	bool transitive();
	bool is_function();

	Relation<type> inverse();
    vector <type> operator[](type);
    Relation<type> combination(Relation<type>);
    Relation<type>& operator=(Relation<type>);
    bool operator==(Relation<type>);
    template <typename  type2>
    friend ostream& operator<<(ostream&, Relation<type2>&);
//	void print();
};