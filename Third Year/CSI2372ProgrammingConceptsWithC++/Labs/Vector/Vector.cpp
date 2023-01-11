#include <iostream>
#include "Vector.h"

Vector operator*(double x, Vector v)
{
	int i;
	Vector result(v);
	for (i = 0; i < v.n; ++i)
		result.elements[i] = x * v.elements[i];
	return result;
}

ostream& operator<<(ostream& out, Vector v)
{
	int i;
	out << "(";
	for (i = 0; i < v.n; ++i)
	{
		if (i != 0) out << ", ";
		out << v.elements[i];
	}
	out << ")";
	return out;
}

Vector::Vector()
{
	n = capacity = 0;
	elements = new double[MAX_DIM];
	if (elements == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		n = 2;
		capacity = MAX_DIM;
		elements[0] = elements[1] = 1.0;
	}
}

Vector::Vector(double *arr, int num)
{
	int i;
	n = capacity = 0;
	elements = new double[num + MAX_DIM / 2];
	if (elements == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		n = num;
		capacity = num + MAX_DIM / 2;
		for (i = 0; i < num; ++i)
			elements[i] = arr[i];
	}
}

Vector::Vector(const Vector& v)
{
	int i;
	n = capacity = 0;
	elements = new double[v.n + MAX_DIM / 2];
	if (elements == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		n = v.n;
		capacity = v.n + MAX_DIM / 2;
		for (i = 0; i < v.n; ++i)
			elements[i] = v.elements[i];
	}
}

Vector::~Vector()
{
	delete[] elements;
}

int Vector::dimension()
{
	return n;
}

bool Vector::add_dimension(double x)
{
	if (n < capacity)
	{
		elements[n++] = x;
		return true;
	}

	int i;
	double* temp;
	temp = new double[n + MAX_DIM / 2];
	if (temp == NULL)
	{
		cout << "Not Enough Memory!" << endl;
		return false;
	}
	
	for (i = 0; i < n; ++i)
		temp[i] = elements[i];

	capacity = n + MAX_DIM / 2;
	temp[n++] = x;
	delete[] elements;
	elements = temp;
	return true;
}

bool Vector::insert_dimension(int ind, double x)
{
	int i;
	if (n < capacity)
	{
		for(i = n; i >= ind; --i)
			elements[i + 1] = elements[i];
		elements[ind] = x;
		++n;
		return true;
	}

	double* temp;
	temp = new double[n + MAX_DIM / 2];
	if (temp == NULL)
	{
		cout << "Not Enough Memory!" << endl;
		return false;
	}

	for (i = 0; i < ind; ++i)
		temp[i] = elements[i];

	for (i = ind + 1; i < n; ++i)
		temp[i] = elements[i - 1];

	capacity = n + MAX_DIM / 2;
	temp[ind] = x;
	++n;
	delete[] elements;
	elements = temp;
	return true;
}

void Vector::remove_dimension(int ind)
{
	int i;
	if (ind >= 0 && ind < n)
	{
		for (i = ind; i < n; ++i)
			elements[i] = elements[i + 1];
		--n;
	}
}

double Vector::magnitude()
{
	double sum = 0;
	int i;
	for (i = 0; i < n; ++i)
		sum += elements[i] * elements[i];
	return sqrt(sum);
}

double& Vector::operator[](int ind)
{
	return elements[ind];
}

bool Vector::operator==(Vector v) const
{
	int i;
	if (n == v.n)
	{
		for (i = 0; i < n; ++i)
			if (elements[i] != v.elements[i]) return false;
		return true;
	}
	return false;
}

bool Vector::operator!=(Vector v) const
{
	return !((*this) == v);
}

Vector& Vector::operator=(Vector v)
{
	int i;
	n = capacity = 0;
	elements = new double[v.n + MAX_DIM / 2];
	if (elements == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		n = v.n;
		capacity = v.n + MAX_DIM / 2;
		for (i = 0; i < v.n; ++i)
			elements[i] = v.elements[i];
	}

	return (*this);
}

Vector Vector::operator+(Vector v)
{
	int i;
	if (v.n > n)
	{
		Vector result(v);
		for (i = 0; i < n; ++i)
			result.elements[i] = elements[i] + v.elements[i];
		for (i = n; i < v.n; ++i)
			result.elements[i] = v.elements[i];
		return result;
	}

	Vector result(*this);
	for (i = 0; i < v.n; ++i)
		result.elements[i] = elements[i] + v.elements[i];
	for (i = v.n; i < n; ++i)
		result.elements[i] = elements[i];
	return result;
}

Vector Vector::operator*(double x)
{
	int i;
	Vector result(*this);
	for (i = 0; i < n; ++i)
		result.elements[i] = x * elements[i];
	return result;
}

Vector Vector::operator-(Vector v)
{
	return (*this) + (v * (-1));
}

Vector Vector::operator*(Vector v)
{
	int i;
	if (v.n > n)
	{
		Vector result(v);
		for (i = 0; i < n; ++i)
			result.elements[i] = elements[i] * v.elements[i];
		for (i = n; i < v.n; ++i)
			result.elements[i] = 0.0;
		return result;
	}

	Vector result(*this);
	for (i = 0; i < v.n; ++i)
		result.elements[i] = elements[i] * v.elements[i];
	for (i = v.n; i < n; ++i)
		result.elements[i] = 0.0;
	return result;
}

Vector Vector::operator+=(Vector v)
{
	(*this) = (*this) + v;
 	return (*this);
}

Vector Vector::operator-=(Vector v)
{
	(*this) = (*this) - v;
	return (*this);
}