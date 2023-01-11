#include <iostream>
#include "Set.h"

using namespace std;

Set::Set()
{
	card = 0;
}

Set::Set(const Set& s)
{
	int i;
	card = s.card;

	for (i = 0; i < s.card; ++i)
		elements[i] = s.elements[i];
}

int Set::cardinality()
{
	return card;
}

bool Set::is_member(int x)
{
	int i;
	for (i = 0; i < card; ++i)
		if (elements[i] == x) return true;

	return false;
}

bool Set::add_element(int x)
{
	int i;

	if (card == MAX_CARD)
		return false;

	for (i = 0; i < card; ++i)
		if (elements[i] == x) return true;

	elements[card++] = x;
	return true;
}

void Set::remove_element(int x)
{
	int i, p = -1;

	for (i = 0; i < card; ++i)
	{
		if (elements[i] == x)
		{
			p = i;
			break;
		}
	}

	if (p != -1)
	{
		for (i = p; i < card; ++i)
			elements[i] = elements[i + 1];
		--card;
	}
}

bool Set::subset(Set s)
{
	int i;
	for (i = 0; i < card; ++i)
		if (!s.is_member(elements[i])) return false;

	return true;
}

bool Set::equal(Set s)
{
	if (subset(s) && s.subset(*this))
		return true;

	return false;
}

Set Set::Union(Set s)
{
	int i;
	Set result;
	for (i = 0; i < card; ++i)
		result.add_element(elements[i]);

	for (i = 0; i < s.card; ++i)
		result.add_element(s.elements[i]);

	return result;
}

Set Set::intersection(Set s)
{
	int i;
	Set result;
	for (i = 0; i < card; ++i)
		if (s.is_member(elements[i])) result.add_element(elements[i]);

	return result;
}

Set Set::difference(Set s)
{
	int i;
	Set result;
	for (i = 0; i < card; ++i)
		if (!s.is_member(elements[i])) result.add_element(elements[i]);

	return result;
}

Set Set::mutual_difference(Set s)
{
	int i;
	Set s1 = difference(s);
	Set s2 = s.difference(*this);
	return s1.Union(s2);
}
