#pragma once

#define MAX_CARD 1000
class Set
{
private:
	int card;
	int elements[MAX_CARD];
public:
	Set();
	Set(const Set&);
	int cardinality();
	bool add_element(int);
	void remove_element(int);
	bool is_member(int);
	bool equal(Set);
	bool subset(Set);
	Set Union(Set);
	Set intersection(Set);
	Set difference(Set);
	Set mutual_difference(Set);

};