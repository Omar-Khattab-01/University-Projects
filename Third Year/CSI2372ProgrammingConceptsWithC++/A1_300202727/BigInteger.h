#pragma once
#include <iostream>

using namespace std;
#define MAX_BASE  36
#define NUM_SIZE 100
class BigInteger
{

private:
    char* num;
    int base, size;
	//The number should be considered signed.
	//We have both positive and negative integers
public:
	BigInteger(); //default contsructor
	BigInteger(int, int);//int num, int base
	BigInteger(const BigInteger&);
	~BigInteger();
	int num_digits();
	bool add_digit(int);
	void remove_digit();
	bool insert_digit(int, int);//int digit, int position
	//You should know the signature of operators
    char operator [](int);
    bool operator ==(BigInteger);
    bool operator !=(BigInteger);
    bool operator >(BigInteger);
    bool operator <(BigInteger);
    bool operator >=(BigInteger);
    bool operator <=(BigInteger);
    BigInteger operator +(BigInteger);
    BigInteger operator -(BigInteger);
    BigInteger operator *(BigInteger);
    BigInteger operator ++();
    BigInteger operator ++(int);
    BigInteger operator --();
    BigInteger operator --(int);
    BigInteger operator /(BigInteger);
    BigInteger operator %(BigInteger);
    friend  ostream& operator <<(ostream&, BigInteger);
    friend  istream& operator >>(istream&, BigInteger&);
    char reVal(int);
    void strev(char*);
    char* fromDeci(char*, int , int );
    int toDeci(char*, int);
    int val(char);
    void setNum(int,int);


};