#include <iostream>
#include <set>
#include <map>
#include "Mapping.h"

using namespace std;

template <typename type1, typename type2>
Mapping<type1, type2>::Mapping()
{
	dict = map <type1, set <type2> >();
}

template <typename type1, typename type2>
Mapping<type1, type2>::Mapping(const Mapping<type1, type2>& m)
{
	dict = m.dict;
}

template <typename type1, typename type2>
Mapping<type1, type2>::~Mapping()
{
	dict.clear();
}

template <typename type1, typename type2>
bool Mapping<type1, type2>::add_item(type1 x, type2 y)
{
	if (dict.find(x) != dict.end())
		return dict[x].insert(y).second;

	dict[x] = set <type2>();
	return dict[x].insert(y).second;
}

template <typename type1, typename type2>
void Mapping<type1, type2>::remove_item(type1 x, type2 y)
{
	if (dict.find(x) != dict.end())
		dict[x].erase(y);
}

template <typename type1, typename type2>
void Mapping<type1, type2>::clear_item(type1 x)
{
	if (dict.find(x) != dict.end())
	{
		dict[x].clear();
		dict.erase(x);
	}
}

template <typename type1, typename type2>
set <type2> Mapping<type1, type2>::find_item(type1 x)
{
	if (dict.find(x) != dict.end())
		return dict[x];
	return dummy;
}

template <typename type1, typename type2>
set <type2>& Mapping<type1, type2>::operator[](type1 x)
{
	if (dict.find(x) != dict.end())
		return dict[x];
	return dummy;
}

template <typename typex, typename typey> 
ostream& operator <<(ostream& out, Mapping<typex, typey>& m)
{
	typename map <typex, set <typey> >::iterator mtst;
	typename set <typey>::iterator st;
	out << "[ ";
	bool sw = false;

	for (mtst = m.dict.begin(); mtst != m.dict.end(); ++mtst)
	{
		if (sw) out << ", ";
		else sw = true;
		out << mtst->first << ": { ";
		bool sw2 = false;

		for (st = (mtst->second).begin(); st != (mtst->second).end(); ++st)
		{
			if (sw2) out << ", ";
			else sw2 = true;
			out << *st;
		}
		out << " }";
	}

	out << " ]";
	return out;
}