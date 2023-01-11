#include <iostream>
#include <set>
#include "Relation.h"
#include <string>
#include <vector>

using namespace std;

template <typename  type>
Relation<type>::Relation()
{
	size = 0;
	capacity = 0;
	elements = new Pair<type>[MAX_CARD];
	if (elements == NULL)
		cout << "Not enough memory for this set!" << endl;
	else
		capacity = MAX_CARD;
}


template <typename  type>
Relation<type>::Relation(const Relation<type>& r)
{
	int i;
	size = 0;
	capacity = 0;
	elements = new Pair<type>[r.capacity];
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
template <typename type>
bool Relation<type>::operator==(Relation<type> r)
{
    return elements == r.elements;


}


template <typename type>
Relation<type>& Relation<type>::operator=(Relation<type> r)
{
	int i;
	Pair<type>* temp;
	size = 0;
	capacity = 0;
	temp = new Pair<type>[r.capacity];
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

template <typename type>
Relation<type>::~Relation()
{
//	delete[] elements;
}

template <typename type>
int Relation<type>::cardinality()
{
	return size;
}

template <typename type>
bool Relation<type>::is_member(type f, type s)
{
    Pair<type> p;
    p.first = f;
    p.second =s;
	int i;
	for (i = 0; i < size; ++i)
		if (elements[i].first == p.first && elements[i].second == p.second) return true;

	return false;
}

template <typename type>
void Relation<type>::add_to_set(type x)
{
	root.insert(x);

}

/*int Set::get_item(int index)
{
	if (index < card)
		return elements[index];
	cout << "Index out of Range!" << endl;
	return 0; //just a default
}*/

template <typename type>
bool Relation<type>::add_element(type f, type s)
{
    Pair<type> p;
    p.first = f;
    p.second = s;
	int i;

    if ( (root.find(f) == root.end()) || (root.find(s)) == root.end())
        return false;
//	if (!root.is_member(p.first, p.second) || !root.is_member(p.second,p.first))
//		return false;

	if (size == capacity)
	{
		int new_cap = capacity + MAX_CARD / 2;
		Pair<type>* temp;
		temp = new Pair<type>[new_cap];
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

template <typename type>
void Relation<type>::remove_element(type f, type s)
{
    Pair<type> p;
    p.first = f;
    p.second = s;
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

template <typename type>
bool Relation<type>::subset(Relation<type> r)
{
	int i;
	for (i = 0; i < size; ++i)
		if (!r.is_member(elements[i])) return false;

	return true;
}

template <typename type>
bool Relation<type>::equal(Relation<type> r)
{
	if (subset(r) && r.subset(*this))
		return true;

	return false;
}

template <typename type>
bool Relation<type>::reflexive()
{

    typename set<type>::iterator it;
	for (it = root.begin(); it != root.end(); ++it)
	{
		Pair<type> p;
		p.first = p.second = *(it);
		if (!is_member(p.first,p.second))
			return false;
	}

	return true;
}

template <typename type>
bool Relation<type>::irreflexive()
{
    typename set<type>::iterator it;
    for (it = root.begin(); it != root.end(); ++it)
    {
        Pair<type> p;
        p.first = p.second = *(it);
        if (is_member(p.first,p.second))
            return false;
    }

	return true;
}

template <typename type>
bool Relation<type>::symmetric()
{
	int i;
	for (i = 0; i < size; ++i)
	{
		Pair<type> p;
		p.first = elements[i].second;
		p.second = elements[i].first;
		if (!is_member(p.first,p.second))
			return false;
	}

	return true;
}

template <typename type>
bool Relation<type>::asymmetric()
{
	int i;
	for (i = 0; i < size; ++i)
	{
		if (elements[i].first != elements[i].second)
		{
			Pair<type> p;
			p.first = elements[i].second;
			p.second = elements[i].first;
			if (is_member(p.first,p.second))
				return false;
		}
	}

	return true;
}

template <typename type>
bool Relation<type>::transitive()
{
	int i, j;
	for (i = 0; i < size; ++i)
	{
		for (j = 0; j < size; ++j)
		{
			if (elements[i].second == elements[j].first)
			{
				Pair<type> p;
				p.first = elements[i].first;
				p.second = elements[j].second;
				if (!is_member(p.first,p.second))
					return false;
			}
		}
	}

	return true;
}

template <typename type>
bool Relation<type>::is_function()
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

template <typename type>
Relation<type> Relation<type>::inverse()
{
	int i;
	Relation result;
    typename set<type>::iterator it;
    for (it = root.begin(); it != root.end(); ++it)
        result.add_to_set(*(it));

//	for (i = 0; i < root.cardinality(); ++i)


	for (i = 0; i < size; ++i)
	{
		Pair<type> p;
		p.first = elements[i].second;
		p.second = elements[i].first;
		result.add_element(p.first,p.second);
	}

	return result;
}

template <typename type>
Relation<type> Relation<type>::combination(Relation<type> r)
{
	int i, j;
    Relation result;
    typename set<type>::iterator it;
    for (it = root.begin(); it != root.end(); ++it)
        result.add_to_set(*(it));

	for (i = 0; i < size; ++i)
	{
		for (j = 0; j < r.size; ++j)
		{
			if (elements[i].second == r.elements[j].first)
			{
				Pair<type> p;
				p.first = elements[i].first;
				p.second = r.elements[j].second;
				result.add_element(p.first,p.second);
			}
		}
	}

	return result;
}

template <typename type>
vector <type> Relation<type>::operator[](type f){

    vector<type> res;

    for (int i = 0; i < size; ++i) {
        if (elements[i].first == f){
            res.push_back(elements[i].second);
        }

    }

    return res;



}

template <typename type>
ostream& operator<<(ostream& out, Relation<type>& list) {
    int i;
    out << "{ ";
    for (i = 0; i < list.size; ++i)
    {
        if (i != 0) out << ", ";
        out << "(" << list.elements[i].first << ", " << list.elements[i].second << ")";
    }
    out << " }";

    return out;

}


