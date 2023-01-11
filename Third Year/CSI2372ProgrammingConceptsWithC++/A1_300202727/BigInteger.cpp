#include <iostream>
#include "BigInteger.h"
using namespace std;

BigInteger::BigInteger() {

    num = new char[NUM_SIZE];
    if (num == NULL)
        cout << "Not enough memory" <<endl;
    else{
        base = 10;
        size = NUM_SIZE;
        num[-1]= '+';
        num[0] = '0';
    }
}

BigInteger::BigInteger(int n, int b) {

    num = new char[NUM_SIZE];
    if (num == NULL)
        cout << "Not enough memory" <<endl;
    else{
        size = NUM_SIZE;
        setNum(n,b);

    }

}

void BigInteger::setNum(int n, int b){
    base = b;
    if (n < 0 ){
        num[-1]= '-';
    }else{
        num[-1]= '+';
    }
    base = b;
    fromDeci(num, base, n);
}

BigInteger::BigInteger(const BigInteger & b) {
    base = b.base;
    size = b.size;

    num = new char [NUM_SIZE];

    if (num == NULL)
        cout << "Not enough memory" <<endl;
    else{

        for (int i = -1; i < size; ++i) {
            num[i] = b.num[i];
        }

    }

}

BigInteger::~BigInteger() {

    num = NULL;
}
char BigInteger::operator[](int i) {

    if (i >= 0 && i < size)
        return num[i];

    cout << "Index out of range" << endl;
    return' ';

}

int BigInteger::num_digits() {
    int counter = 0;

    for (int i = 0; i < size ; ++i) {
        if (num[i] != NULL)
            counter++;
    }

    return counter;
}
char const  to_char(int n){
    string t = to_string(n);
    char const * n_char = t.c_str();
    return *n_char;
}
bool BigInteger::add_digit(int n ) {
    if (num_digits() < size){


        num[num_digits()]= to_char(n);
        return true;
    }

    char* temp;

    temp = new char[size +NUM_SIZE];

    if (temp == NULL)
    {
        cout << "Not Enough Memory!" << endl;
        return false;
    }

    int i;

    for (i = 0; i < size; ++i)
        temp[i] = num[i];

    temp[num_digits()]= char(n);

    size+= NUM_SIZE;
    delete[] num;
    num = temp;
    return true;
}

void BigInteger::remove_digit() {
    if (num_digits() >0)
        num[num_digits()-1]= NULL;

}

bool BigInteger::insert_digit(int d, int index) {
    if (index < 0 || index > num_digits()){
        cout <<"Index out of Range!"<<endl;
        return false;

    }

    int i;
    if (num_digits() < size)
    {
        for(i = num_digits(); i >= index; --i)
            num[i + 1] = num[i];
        num[index] = to_char(d);
        return true;
    }

    char* temp;
    temp = new char[size+NUM_SIZE];
    if (temp == NULL)
    {
        cout << "Not Enough Memory!" << endl;
        return false;
    }

    for (i = 0; i < index; ++i)
        temp[i] = num[i];

    for (i = index + 1; i < num_digits(); ++i)
        temp[i] = num[i - 1];

    size+= NUM_SIZE;
    temp[index] = to_char(d);
    delete[] num;
    num = temp;
    return true;
}


ostream& operator <<(ostream& out, BigInteger b){
    out<<"(";
    for (int i = -1; i < b.size ; ++i) {
        if (i == 0 && b.num[i] == NULL)
            out<<0;
        if (b.num[i] != NULL)
            out<<b.num[i];

    }
    out<<") base: "<<b.base;

    return out;
}


istream& operator >>(istream& in, BigInteger& b)
{
    int n,base;
    cout << "Enter the number in the decimal base: ";
    in >> n;
    cout << "Enter the desired base: ";
    in >> base;
    b.setNum(n,base);
    return in;
}



// To return char for a value. For example '2'
// is returned for 2. 'A' is returned for 10. 'B'
// for 11
char BigInteger::reVal(int num)
{
    if (num >= 0 && num <= 9)
        return (char)(num + '0');
    else
        return (char)(num - 10 + 'A');
}

// Utility function to reverse a string
void BigInteger::strev(char *str)
{
    int len = strlen(str);
    int i;
    for (i = 0; i < len/2; i++)
    {
        char temp = str[i];
        str[i] = str[len-i-1];
        str[len-i-1] = temp;
    }
}

// Function to convert a given decimal number
// to a base 'base' and
char* BigInteger::fromDeci(char*  res, int base, int inputNum)
{
    int index = 0; // Initialize index of result
    inputNum = abs(inputNum);
    // Convert input number is given base by repeatedly
    // dividing it by base and taking remainder
    while (inputNum > 0)
    {
        res[index++] = reVal(inputNum % base);
        inputNum /= base;
    }
    res[index] = '\0';

    // Reverse the result
    strev(res);

    return res;
}

// Function to convert a given number in base to the equivalent number in decimal base
//returning values of a character
int BigInteger::val(char c) {
    if (c >= '0' && c <= '9')
        return (int)c - '0';
    else
        return (int)c - 'A' + 10;
}
//converting number to decimal equivalent
int BigInteger::toDeci(char* str, int base) {
    int len = strlen(str);
    int power = 1;
    int n = 0;
    int i;
    for (i = len - 1; i >= 0; i--) {
        if (val(str[i]) >= base) {
            printf("Invalid Number");
            return -1;
        }
        n += val(str[i]) * power;
        power = power * base;
    }
    return n;
}

bool BigInteger::operator==(BigInteger b) {


    return (toDeci(num, base) == toDeci(b.num, b.base) ) && (num[-1] == b.num[-1]);

}

bool BigInteger::operator!=(BigInteger b) {

     return  !((toDeci(num, base) == toDeci(b.num, b.base)) && (num[-1] == b.num[-1]));
}

bool BigInteger::operator >(BigInteger b){
    int x,y,z;
    x  = toDeci(num, base);
    y  = toDeci(b.num, b.base);
    if (num[-1] == '+' && b.num[-1] == '+'){
        z=0;
    }else if (num[-1] == '-' && b.num[-1] == '-'){
        x = -x;
        y = -y;
    }else if (num[-1] == '-'){
        x = -x;
    }else{
        y = -x;
    }


    return x > y;
}

bool BigInteger::operator <(BigInteger b){

    return !((*this) > b);
}

bool BigInteger::operator >=(BigInteger b){

    return ((*this) > b) || ( (*this) == b) ;
}

bool BigInteger::operator <=(BigInteger b){

    return ((*this) < b) || ( (*this) == b) ;

}

BigInteger BigInteger::operator +(BigInteger b){

    int x,y, z;
    x = toDeci(num, base);
    y = toDeci(b.num, b.base);
    if (num[-1] == '+' && b.num[-1] == '+'){
        z = x + y;
    }else if (num[-1] == '-' && b.num[-1] == '-'){
        z = -x - y;
    }else if (num[-1] == '-'){
        z = -x + y;
    }else{
        z = x - y;
    }

    BigInteger res(z, base);
    return  res;
}

BigInteger BigInteger::operator -(BigInteger b){

    int x,y, z;
    x = toDeci(num, base);
    y = toDeci(b.num, b.base);
    if (num[-1] == '+' && b.num[-1] == '+'){
        z = x - y;
    }else if (num[-1] == '-' && b.num[-1] == '-'){
        z = -x + y;
    }else if (num[-1] == '-'){
        z = -x - y;
    }else{
        z = x + y;
    }
    BigInteger res(z, base);
    return  res;
}

BigInteger BigInteger::operator *(BigInteger b){

    int x,y, z;
    x = toDeci(num, base);
    y = toDeci(b.num, b.base);
    if (num[-1] == '+' && b.num[-1] == '+'){
        z = x * y;
    }else if (num[-1] == '-' && b.num[-1] == '-'){
        z = x + y;
    }else if (num[-1] == '-'){
        z = -x * y;
    }else{
        z = x * -y;
    }
    BigInteger res(z, base);
    return  res;
}

BigInteger BigInteger::operator++() {
    BigInteger y(1,10);
    (*this) =(*this) + y;
    return  (*this);

}

BigInteger BigInteger::operator--() {
    BigInteger y(-1,10);
    (*this) =(*this) + y;
    return  (*this);

}

BigInteger BigInteger::operator++(int a) {
    BigInteger y(1,10);
    (*this) =(*this) + y;
    return  (*this);
}

BigInteger BigInteger::operator--(int a) {
    BigInteger y(-1,10);
    (*this) =(*this) + y;
    return  (*this);
}

BigInteger BigInteger::operator/(BigInteger b) {
    int x,y;
    x = toDeci(num,base); y = toDeci(b.num,b.base);
    if (num[-1] = '-'){
        x = -x;
    }
    if (b.num[-1] = '-'){
        y = -y;
    }
    BigInteger res(x/y,base);
    return res;
}

BigInteger BigInteger::operator%(BigInteger b) {
    int x,y;
    x = toDeci(num,base); y = toDeci(b.num,b.base);
    if (num[-1] == '-'){
        x = -x;
    }
    if (b.num[-1] == '-'){
        y = -y;
    }
    BigInteger res(x % y,base);
    return res;
}