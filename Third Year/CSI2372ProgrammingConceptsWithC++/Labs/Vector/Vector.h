#pragma once

#include <iostream>

using namespace std;

#define MAX_DIM 100

class Vector
{
private:
	int n;
	int capacity;
	double* elements;
public:
	Vector();
	Vector(double*, int);
	Vector(const Vector&);
	~Vector();
	int dimension();
	bool add_dimension(double);
	void remove_dimension(int);
	bool insert_dimension(int, double);
	double magnitude();
	double& operator[](int);
	bool operator==(Vector) const;
	bool operator!=(Vector) const;
	Vector& operator=(Vector);
	Vector operator+(Vector);
	Vector operator-(Vector);
	Vector operator*(Vector);
	Vector operator*(double);
	Vector operator+=(Vector);
	Vector operator-=(Vector);
	friend Vector operator*(double, Vector);
	friend ostream& operator<<(ostream&, Vector);
};