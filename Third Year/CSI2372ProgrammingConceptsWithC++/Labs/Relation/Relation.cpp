#include <iostream>
#include "Set.h"
#include "Relation.h"
#include <string>

using namespace std;

Relation::Relation(string s)
{
	size = 0;
	capacity = 0;
	elements = new Pair[MAX_CARD];
	if (elements == NULL)
		cout << "Not enough memory for this set!" << endl;
	else
		capacity = MAX_CARD;
}

Relation::Relation(const Relation& r)
{
	int i;
	size = 0;
	capacity = 0;
	elements = new Pair[r.capacity];
	if (elements == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		capacity = r.capacity;
		size = r.size;
		root = r.root;

		for (i = 0; i < r.size; ++i)
			elements[i] = r.elements[i];
	}
}

Relation& Relation::operator=(Relation r)
{
	int i;
	Pair* temp;
	size = 0;
	capacity = 0;
	temp = new Pair[r.capacity];
	if (temp == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		capacity = r.capacity;
		size = r.size;
		root = r.root;

		for (i = 0; i < r.size; ++i)
			temp[i] = r.elements[i];

		delete[] elements;
		elements = temp;
	}

	return (*this);
}


Relation::~Relation()
{
	delete[] elements;
}

int Relation::cardinality()
{
	return size;
}

bool Relation::is_member(Pair p)
{
	int i;
	for (i = 0; i < size; ++i)
		if (elements[i].first == p.first && elements[i].second == p.second) return true;

	return false;
}

bool Relation::add_to_set(int x)
{
	if (root.add_element(x))
		return true;
	return false;
}

/*int Set::get_item(int index)
{
	if (index < card)
		return elements[index];
	cout << "Index out of Range!" << endl;
	return 0; //just a default
}*/

bool Relation::add_element(Pair p)
{
	int i;

	if (!root.is_member(p.first) || !root.is_member(p.second))
		return false;

	if (size == capacity)
	{
		int new_cap = capacity + MAX_CARD / 2;
		Pair* temp;
		temp = new Pair[new_cap];
		if (temp == NULL)
		{
			cout << "Not Enough Memory" << endl;
			return false;
		}

		for (i = 0; i < size; ++i)
			temp[i] = elements[i];

		delete[] elements;
		elements = temp;
		capacity = new_cap;
	}

	for (i = 0; i < size; ++i)
		if (elements[i].first == p.first && elements[i].second == p.second) return true;

	elements[size++] = p;
	return true;
}

void Relation::remove_element(Pair p)
{
	int i, pos = -1;

	for (i = 0; i < size; ++i)
	{
		if (elements[i].first == p.first && elements[i].second == p.second)
		{
			pos = i;
			break;
		}
	}

	if (pos != -1)
	{
		for (i = pos; i < size; ++i)
			elements[i] = elements[i + 1];
		--size;
	}
}

bool Relation::subset(Relation r)
{
	int i;
	for (i = 0; i < size; ++i)
		if (!r.is_member(elements[i])) return false;

	return true;
}

bool Relation::equal(Relation r)
{
	if (subset(r) && r.subset(*this))
		return true;

	return false;
}

bool Relation::reflexive()
{
	int i;
	for (i = 0; i < root.cardinality(); ++i)
	{
		Pair p;
		p.first = p.second = root.get_item(i);
		if (!is_member(p))
			return false;
	}

	return true;
}

bool Relation::irreflexive()
{
	int i;
	for (i = 0; i < root.cardinality(); ++i)
	{
		Pair p;
		p.first = p.second = root.get_item(i);
		if (is_member(p))
			return false;
	}

	return true;
}

bool Relation::symmetric()
{
	int i;
	for (i = 0; i < size; ++i)
	{
		Pair p;
		p.first = elements[i].second;
		p.second = elements[i].first;
		if (!is_member(p))
			return false;
	}

	return true;
}

bool Relation::asymmetric()
{
	int i;
	for (i = 0; i < size; ++i)
	{
		if (elements[i].first != elements[i].second)
		{
			Pair p;
			p.first = elements[i].second;
			p.second = elements[i].first;
			if (is_member(p))
				return false;
		}
	}

	return true;
}

bool Relation::transitive()
{
	int i, j;
	for (i = 0; i < size; ++i)
	{
		for (j = 0; j < size; ++j)
		{
			if (elements[i].second == elements[j].first)
			{
				Pair p;
				p.first = elements[i].first;
				p.second = elements[j].second;
				if (!is_member(p))
					return false;
			}
		}
	}

	return true;
}

bool Relation::is_function()
{
	int i, j;
	for (i = 0; i < size; ++i)
	{
		for (j = 0; j < size; ++j)
		{
			if (elements[i].first == elements[j].first && elements[i].second != elements[j].second)
				return false;
		}
	}

	return true;
}

Relation Relation::inverse()
{
	int i;
	Relation result("res");
	for (i = 0; i < root.cardinality(); ++i)
		result.add_to_set(root.get_item(i));

	for (i = 0; i < size; ++i)
	{
		Pair p;
		p.first = elements[i].second;
		p.second = elements[i].first;
		result.add_element(p);
	}

	return result;
}

Relation Relation::combination(Relation r)
{
	int i, j;
	Relation result("res");
	for (i = 0; i < root.cardinality(); ++i)
		result.add_to_set(root.get_item(i));

	for (i = 0; i < size; ++i)
	{
		for (j = 0; j < r.size; ++j)
		{
			if (elements[i].second == r.elements[j].first)
			{
				Pair p;
				p.first = elements[i].first;
				p.second = r.elements[j].second;
				result.add_element(p);
			}
		}
	}

	return result;
}

void Relation::print()
{
	int i;
	cout << "{ ";
	for (i = 0; i < size; ++i)
	{
		if (i != 0) cout << ", ";
		cout << "(" << elements[i].first << ", " << elements[i].second << ")";
	}
	cout << " }";
}