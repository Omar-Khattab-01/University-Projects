#pragma once

#include <iostream>
#include <cstdlib>

using namespace std;

#define MAX_NUM 100

class RandomGenerator
{
private:
	int num_digits;
	int capacity;
	int num_generated;
	unsigned long long* random_nums;
public:
	RandomGenerator();
	RandomGenerator(int, unsigned long long, unsigned long long);
	RandomGenerator(const RandomGenerator&);
	~RandomGenerator();
	unsigned long long operator[](int);
	bool operator==(RandomGenerator);
	bool operator!=(RandomGenerator);
	RandomGenerator& operator=(RandomGenerator);
	RandomGenerator operator+(RandomGenerator);
	RandomGenerator operator-(RandomGenerator);
	RandomGenerator operator*(RandomGenerator);
	RandomGenerator operator+=(RandomGenerator);
	RandomGenerator operator-=(RandomGenerator);
	RandomGenerator operator*=(RandomGenerator);
	RandomGenerator  operator++();
	RandomGenerator  operator--();
	RandomGenerator  operator++(int);
	RandomGenerator  operator--(int);
	friend ostream& operator<<(ostream&, RandomGenerator);
};