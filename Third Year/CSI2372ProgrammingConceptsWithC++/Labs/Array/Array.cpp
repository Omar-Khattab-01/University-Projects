#include <iostream>
#include "Array.h"

using namespace std;

template <class type>
ostream& operator<<(ostream& out, Array <type>& arr)
{
	int i, n = arr.upperbound - arr.lowerbound + 1;
	for (i = 0; i < n; ++i)
	{
		if (i != 0) out << ", ";
		out << arr.elements[i];
	}
	return out;
}

template <class type>
Array<type>::Array()
{
	capacity = 0;
	lowerbound = 0;
	upperbound = -1;
	dummy = 0;
	elements = NULL;
}

template <class type>
Array<type>::Array(int n)
{
	capacity = 0;
	lowerbound = 0;
	upperbound = -1;
	dummy = 0;
	try
	{
		elements = new type[n + MAX_ELEM];
	}
	catch (bad_alloc b)
	{
		cout << "Not enough memory!" << endl;
		exit(1);
	}

	//elements = new (nothrow) type[n + MAX_ELEM];
	//if (elements == NULL)
	//{
	//	cout << "Not enough memory!" << endl;
	//	exit(1);
	//}

	try
	{
		elements = new (nothrow) type[n + MAX_ELEM];
		if (elements == NULL) throw 1;
	}
	catch (int e)
	{
		if (e == 1)
		{
			cout << "Not enough memory!" << endl;
			exit(1);
		}
	}
	
	if (elements == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		lowerbound = 0;
		upperbound = n - 1;
		capacity = n + MAX_ELEM;
	}
}

template <typename type>
Array<type>::Array(int lb, int ub)
{
	int n = ub - lb + 1;
	capacity = 0;
	lowerbound = 0;
	upperbound = -1;
	dummy = 0;
	elements = new type[n + MAX_ELEM];
	if (elements == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		lowerbound = lb;
		upperbound = ub;
		capacity = n + MAX_ELEM;
	}
}

template <typename type>
Array<type>::Array(const Array<type>& ar)
{
	int i;
	capacity = 0;
	lowerbound = 0;
	upperbound = -1;
	elements = NULL;
	dummy = 0;
	int* temp = new type[ar.capacity];
	if (temp == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		int n = ar.upperbound - ar.lowerbound + 1;
		for (i = 0; i < n; ++i)
			temp[i] = ar.elements[i];
		lowerbound = ar.lowerbound;
		upperbound = ar.upperbound;
		capacity = ar.capacity;
		delete[] elements;
		elements = temp;
	}
}

template <typename type>
Array<type>::~Array()
{
	delete[] elements;
}

template <typename type>
bool Array<type>::add_item(type x)
{
	int i, n = upperbound - lowerbound + 1;
	if (n == capacity)
	{
		int* temp = new type[capacity + MAX_ELEM / 2];
		if (temp == NULL)
		{
			cout << "Not enough memory!" << endl;
			return false;
		}

		for (i = 0; i < n; ++i)
			temp[i] = elements[i];

		delete[] elements;
		elements = temp;
	}

	elements[n] = x;
	++upperbound;
	return true;
}

template <typename type>
bool Array<type>::insert_item(int pos, type x)
{
	if (pos < lowerbound || pos > upperbound)
	{
		cout << "Index out of range!" << endl;
		return false;
	}

	int i, n = upperbound - lowerbound + 1;
	if (n == capacity)
	{
		int* temp = new type[capacity + MAX_ELEM / 2];
		if (temp == NULL)
		{
			cout << "Not enough memory!" << endl;
			return false;
		}

		for (i = 0; i < n; ++i)
			temp[i] = elements[i];

		delete[] elements;
		elements = temp;
	}

	for (i = upperbound - lowerbound + 1; i >= pos - lowerbound; --i)
		elements[i] = elements[i - 1];

	elements[pos - lowerbound] = x;
	++upperbound;
	return true;
}

template <typename type>
void Array<type>::remove_item()
{
	if (lowerbound <= upperbound)
		--upperbound;
}

template <typename type>
void Array<type>::remove_item(type x)
{
	int i;
	if (lowerbound <= upperbound)
	{
		int n = upperbound - lowerbound + 1;
		int pos = -1;

		for (i = 0; i < n; ++i)
		{
			if (elements[i] == x)
			{
				pos = i;
				break;
			}
		}

		if (pos != -1)
		{
			for (i = pos; i < n; ++i)
				elements[i] = elements[i + 1];
			--upperbound;
		}
	}
}

template <typename type>
void Array<type>::erase_item(int pos)
{
	if (pos < lowerbound || pos > upperbound)
	{
		cout << "Index out of range!" << endl;
		return;
	}

	int i;
	for (i = pos - lowerbound; i < upperbound - lowerbound; ++i)
		elements[i] = elements[i + 1];

	--upperbound;
}

template <typename type>
bool Array<type>::find_item(type x, int& pos)
{
	int i;
	for (i = lowerbound; i <= upperbound; ++i)
	{
		if (elements[i - lowerbound] == x)
		{
			pos = i;
			return true;
		}
	}

	pos = lowerbound - 1;
	return false;
}

template <typename type>
Array<type>& Array<type>::operator=(Array<type>& ar)
{
	int i;
	int* temp = new type[ar.capacity];
	if (temp == NULL)
		cout << "Not enough memory!" << endl;
	else
	{
		int n = ar.upperbound - ar.lowerbound + 1;
		for (i = 0; i < n; ++i)
			temp[i] = ar.elements[i];
		lowerbound = ar.lowerbound;
		upperbound = ar.upperbound;
		capacity = ar.capacity;
		delete[] elements;
		elements = temp;
	}

	return *this;
}

template <typename type>
type& Array<type>::operator[](int index)
{
	try
	{
		if (index < lowerbound || index > upperbound)
		{
			//cout << "Index out of range!" << endl;
			throw 1;
			//return dummy;
		}
	}
	catch (int e)
	{
		cout << "Catched " << e << endl;
		cout << "Index out of range!" << endl;
		if (e == 1)
			cout << "Error Type 1" << endl;
		if (e == 2)
			cout << "Error Type 2" << endl;
		//return dummy;
		exit(1);
	}
	/*catch (string s)
	{

	}*/
	//We can have multiple catches for different data types

	return elements[index - lowerbound];
}