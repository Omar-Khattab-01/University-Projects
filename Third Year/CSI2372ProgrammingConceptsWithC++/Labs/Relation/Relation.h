#pragma once

#include <iostream>
#include <string>
#include "Set.h"

using namespace std;

struct Pair
{
	int first;
	int second;
};

class Relation
{
private:
	int size;
	Set root;
	Pair* elements;
	int capacity;
public:
	Relation(string);
	Relation(const Relation&);
	~Relation();//Destructor
	int cardinality();
	bool add_to_set(int);
	bool add_element(Pair);
	void remove_element(Pair);
	bool is_member(Pair);
	bool equal(Relation);
	bool subset(Relation);
	bool reflexive();
	bool irreflexive();
	bool symmetric();
	bool asymmetric();
	bool transitive();
	bool is_function();
	Relation inverse();
	Relation combination(Relation);
	Relation& operator=(Relation);
	void print();
};