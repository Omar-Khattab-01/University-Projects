#include <iostream>
#include <cstdlib>
#include "RandomGenerator.h" 

using namespace std;

unsigned long long power(unsigned long long x, int y)
{
	if (y == 0) return 1;

	unsigned long long t = power(x, y / 2);
	if (y % 2 == 0)
		return t * t;
	return t * t * x;
}

ostream& operator<<(ostream& out, RandomGenerator R)
{
	int i;

	for (i = 0; i < R.num_generated; ++i)
	{
		if (i != 0) out << ", ";
		out << R.random_nums[i];
	}
	return out;
}

RandomGenerator::RandomGenerator()
{
	num_digits = 4;
	capacity = 0;
	num_generated = 0;
	random_nums = new unsigned long long[MAX_NUM];
	if (random_nums == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		capacity = MAX_NUM;
		num_generated = 2;
		unsigned long long B = power(10, num_digits);
		unsigned long long A = B / 10;
		--B;
		random_nums[0] = (rand() % (B - A)) + A;
		random_nums[1] = (rand() % (B - A)) + A;
	}
}

RandomGenerator::RandomGenerator(int hnum, unsigned long long x0, unsigned long long x1)
{
	num_digits = hnum * 2;
	capacity = 0;
	num_generated = 0;
	random_nums = new unsigned long long[MAX_NUM];
	if (random_nums == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		capacity = MAX_NUM;
		num_generated = 2;
		unsigned long long B = power(10, num_digits);
		unsigned long long A = B / 10;
		--B;

		if (x0 >= A && x0 <= B)
			random_nums[0] = x0;
		else
			random_nums[0] = (rand() % (B - A)) + A;
		
		if (x1 >= A && x1 <= B)
			random_nums[1] = x1;
		else
			random_nums[1] = (rand() % (B - A)) + A;
	}
}

RandomGenerator::RandomGenerator(const RandomGenerator& R)
{
	num_digits = R.num_digits;
	capacity = 0;
	num_generated = 0;
	random_nums = new unsigned long long[R.capacity];
	if (random_nums == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		capacity = R.capacity;
		num_generated = R.num_generated;
		int i;
		for (i = 0; i < num_generated; ++i)
			random_nums[i] = R.random_nums[i];
	}
}

RandomGenerator::~RandomGenerator()
{
	delete[] random_nums;
}

bool RandomGenerator::operator ==(RandomGenerator R)
{
	int i;
	if (num_generated == R.num_generated)
	{
		for (i = 0; i < num_generated; ++i)
		{
			if (random_nums[i] != R.random_nums[i])
				return false;
		}
		return true;
	}

	return false;
}

bool RandomGenerator::operator !=(RandomGenerator R)
{
	return !((*this) == R);
}

unsigned long long RandomGenerator::operator[](int index)
{
	if (index >= 0 && index < num_generated)
		return random_nums[index];
	
	cout << "Index out of Range!" << endl;
	unsigned long long dummy = 89012231;
	return dummy;
}

RandomGenerator& RandomGenerator::operator =(RandomGenerator R)
{
	num_digits = R.num_digits;
	capacity = 0;
	num_generated = 0;
	unsigned long long *temp;
	temp = new unsigned long long[R.capacity];
	if (temp == NULL)
		cout << "Not Enough Memory!" << endl;
	else
	{
		capacity = R.capacity;
		num_generated = R.num_generated;
		int i;
		for (i = 0; i < num_generated; ++i)
			temp[i] = R.random_nums[i];

		delete[] random_nums;
		random_nums = temp;
	}

	return *this;
}

RandomGenerator RandomGenerator::operator ++()
{
	if (num_generated == capacity)
	{
		int new_cap = capacity + MAX_NUM / 2;
		unsigned long long *temp = new unsigned long long[new_cap];
		if (temp == NULL)
			cout << "Not Enough Memory!" << endl;
		else
		{
			capacity = new_cap;
			int i;
			for (i = 0; i < num_generated; ++i)
				temp[i] = random_nums[i];
			
			unsigned long long mult;
			unsigned long long p1 = power(10, num_digits / 2);
			unsigned long long p2 = power(10, num_digits);
			mult = temp[num_generated - 1] * temp[num_generated - 2];
			mult = (mult % p1) % p2;
			temp[num_generated++] = mult;
			
			delete[] random_nums;
			random_nums = temp;
		}
	}
	else
	{
		unsigned long long mult;
		unsigned long long p1 = power(10, num_digits / 2);
		unsigned long long p2 = power(10, num_digits);
		mult = random_nums[num_generated - 1] * random_nums[num_generated - 2];
		mult = (mult / p1) % p2;
		random_nums[num_generated++] = mult;
	}
	
	return *this;
}

RandomGenerator RandomGenerator::operator ++(int x)
{
	RandomGenerator result = *this;
	if (num_generated == capacity)
	{
		int new_cap = capacity + MAX_NUM / 2;
		unsigned long long* temp = new unsigned long long[new_cap];
		if (temp == NULL)
			cout << "Not Enough Memory!" << endl;
		else
		{
			capacity = new_cap;
			int i;
			for (i = 0; i < num_generated; ++i)
				temp[i] = random_nums[i];

			unsigned long long mult;
			unsigned long long p1 = power(10, num_digits / 2);
			unsigned long long p2 = power(10, num_digits);
			mult = temp[num_generated - 1] * temp[num_generated - 2];
			mult = (mult % p1) % p2;
			temp[num_generated++] = mult;

			delete[] random_nums;
			random_nums = temp;
		}
	}
	else
	{
		unsigned long long mult;
		unsigned long long p1 = power(10, num_digits / 2);
		unsigned long long p2 = power(10, num_digits);
		mult = random_nums[num_generated - 1] * random_nums[num_generated - 2];
		mult = (mult % p1) % p2;
		random_nums[num_generated++] = mult;
	}

	return result;
}

RandomGenerator RandomGenerator::operator --()
{
	if (num_generated > 2)
		--num_generated;
	return *this;
}

RandomGenerator RandomGenerator::operator --(int x)
{
	RandomGenerator result = *this;
	if (num_generated > 2)
		--num_generated;
	return result;
}

RandomGenerator RandomGenerator::operator +(RandomGenerator R)
{
	int i;
	RandomGenerator result(*this);
	unsigned long long p = power(10, num_digits);
	result.random_nums[0] = (random_nums[0] + R.random_nums[0]) % p;
	result.random_nums[1] = (random_nums[1] + R.random_nums[1]) % p;

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		result.random_nums[i] = (random_nums[i] + R.random_nums[i]) % p;
	
	return result;
}

RandomGenerator RandomGenerator::operator +=(RandomGenerator R)
{
	int i;
	unsigned long long p = power(10, num_digits);
	random_nums[0] = (random_nums[0] + R.random_nums[0]) % p;
	random_nums[1] = (random_nums[1] + R.random_nums[1]) % p;

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		random_nums[i] = (random_nums[i] + R.random_nums[i]) % p;

	return (*this);
}

RandomGenerator RandomGenerator::operator -(RandomGenerator R)
{
	int i;
	RandomGenerator result(*this);
	unsigned long long p = power(10, num_digits);
	result.random_nums[0] = max(random_nums[0], R.random_nums[0]) - min(random_nums[0], R.random_nums[0]);
	result.random_nums[1] = max(random_nums[1], R.random_nums[1]) - min(random_nums[1], R.random_nums[1]);

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		result.random_nums[i] = max(random_nums[i], R.random_nums[i]) - min(random_nums[i], R.random_nums[i]);

	return result;
}

RandomGenerator RandomGenerator::operator -=(RandomGenerator R)
{
	int i;
	unsigned long long p = power(10, num_digits);
	random_nums[0] = max(random_nums[0], R.random_nums[0]) - min(random_nums[0], R.random_nums[0]);
	random_nums[1] = max(random_nums[1], R.random_nums[1]) - min(random_nums[1], R.random_nums[1]);

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		random_nums[i] = max(random_nums[i], R.random_nums[i]) - min(random_nums[i], R.random_nums[i]);

	return (*this);
}

RandomGenerator RandomGenerator::operator *(RandomGenerator R)
{
	int i;
	RandomGenerator result(*this);
	unsigned long long p1 = power(10, num_digits / 2);
	unsigned long long p2 = power(10, num_digits);
	result.random_nums[0] = ((random_nums[0] * R.random_nums[0]) / p1) % p2;
	result.random_nums[1] = ((random_nums[1] * R.random_nums[1]) / p1) % p2;

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		result.random_nums[i] = ((random_nums[i] * R.random_nums[i]) / p1) % p2;

	return result;
}

RandomGenerator RandomGenerator::operator *=(RandomGenerator R)
{
	int i;
	unsigned long long p1 = power(10, num_digits / 2);
	unsigned long long p2 = power(10, num_digits);
	random_nums[0] = ((random_nums[0] * R.random_nums[0]) / p1) % p2;
	random_nums[1] = ((random_nums[1] * R.random_nums[1]) / p1) % p2;

	if (num_generated < R.num_generated)
	{
		for (i = num_generated; i < R.num_generated; ++i)
			++(*this);
	}
	else
	{
		for (i = R.num_generated; i < num_generated; ++i)
			++R;
	}

	for (i = 2; i < num_generated; ++i)
		random_nums[i] = ((random_nums[i] * R.random_nums[i]) / p1) % p2;

	return (*this);
}

