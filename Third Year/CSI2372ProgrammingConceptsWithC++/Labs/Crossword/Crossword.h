#pragma once
#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct problem
{
	int row, col;
	string question;
	string answer;
	bool direction;
};

class Crossword
{
private:
	vector <string> words;
	vector <vector <bool> > filled;
	vector <problem> questions;
public:
	Crossword();
	Crossword(int, int);
	bool add_question(string, string, int, int, bool);
	bool solve(int, int, string);
	friend ostream& operator <<(ostream&, Crossword&);
};