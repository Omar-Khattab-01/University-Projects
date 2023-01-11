#pragma once

#include <iostream>
#include <map>
#include <set>

using namespace std;

template <typename type1, typename type2>
class Mapping
{
private:
	map <type1, set <type2> > dict;
	set <type2> dummy;
public:
	Mapping();
	Mapping(const Mapping<type1, type2>&);
	~Mapping();
	bool add_item(type1, type2);
	void remove_item(type1, type2);
	void clear_item(type1);
	set <type2> find_item(type1);
	set <type2>& operator[](type1);
	template <typename typex, typename typey> friend ostream& operator <<(ostream&, Mapping<typex, typey>&);
};