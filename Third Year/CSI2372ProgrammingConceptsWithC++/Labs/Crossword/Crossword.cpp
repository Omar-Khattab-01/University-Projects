#include "Crossword.h"

ostream& operator<<(ostream& out, Crossword& c)
{
	int i, j;
	out << "Puzzle:" << endl;

	for (j = 0; j < c.words[0].size(); ++j)
		out << '\t' << j + 1;

	out << endl;


	for (i = 0; i < c.words.size(); ++i)
	{
		out << i + 1;
		for (j = 0; j < c.words[0].size(); ++j)
		{
			out << '\t';
			if (c.filled[i][j]) out << c.words[i][j]; else out << ' ';
		}

		out << endl;
	}

	out << "Questions:" << endl;

	for (i = 0; i < c.questions.size(); ++i)
		out << "(" << c.questions[i].row << ", " << c.questions[i].col << ") - " << c.questions[i].question << " (" << (c.questions[i].direction ? "Horizontal" : "Vertical") << ")" << endl;

	return out;
}

Crossword::Crossword()
{
	int i, j;
	for (i = 0; i < 10; ++i)
	{
		words.push_back("");
		filled.push_back(vector <bool>());
		for (j = 0; j < 10; ++j)
		{
			words[i] += ' ';
			filled[i].push_back(false);
		}
	}
}

Crossword::Crossword(int n, int m)
{
	int i, j;
	for (i = 0; i < n; ++i)
	{
		words.push_back("");
		filled.push_back(vector <bool>());
		for (j = 0; j < m; ++j)
		{
			words[i] += ' ';
			filled[i].push_back(false);
		}
	}
}

bool Crossword::add_question(string q, string a, int r, int c, bool d)
{
	if (d)
	{
		if ((c - 1) + a.size() <= words[r - 1].size())
		{
			int i = c - 1, j = 0;
			bool could = true;
			for (j = 0; j < a.size(); ++j)
			{
				if (words[r - 1][i] != ' ' && words[r - 1][i] != a[j])
				{
					could = false;
					break;
				}
				++i;
			}

			if (!could)
			{
				cout << "The answer does not match with the previous answers!" << endl;
				return false;
			}

			i = c - 1;
			for (j = 0; j < a.size(); ++j)
			{
				words[r - 1][i] = a[j];
				++i;
			}

			problem p;
			p.question = q;
			p.answer = a;
			p.row = r;
			p.col = c;
			p.direction = d;
			questions.push_back(p);
			return true;
		}
		else cout << "Not enough space to add the answer!" << endl;
	}
	else
	{
		if ((r - 1) + a.size() <= words.size())
		{
			int i = r - 1, j = 0;
			bool could = true;
			for (j = 0; j < a.size(); ++j)
			{
				if (words[i][c - 1] != ' ' && words[i][c - 1] != a[j])
				{
					could = false;
					break;
				}
				++i;
			}

			if (!could)
			{
				cout << "The answer does not match with the previous answers!" << endl;
				return false;
			}

			i = r - 1;
			for (j = 0; j < a.size(); ++j)
			{
				words[i][c - 1] = a[j];
				++i;
			}

			problem p;
			p.question = q;
			p.answer = a;
			p.row = r;
			p.col = c;
			p.direction = d;
			questions.push_back(p);
			return true;
		}
		else cout << "Not enough space to add the answer!" << endl;
	}

	return false;
}

bool Crossword::solve(int r, int c, string a)
{
	int i;
	for (i = 0; i < questions.size(); ++i)
	{
		if (questions[i].row == r && questions[i].col == c)
		{
			if (questions[i].answer == a)
			{
				int j;

				if (questions[i].direction)
				{
					i = c - 1;
					for (j = 0; j < a.size(); ++j)
					{
						filled[r - 1][i] = true;
						++i;
					}
				}
				else
				{
					i = r - 1;
					for (j = 0; j < a.size(); ++j)
					{
						filled[i][c - 1] = true;
						++i;
					}
				}
				return true;
			}
			else cout << "The answer is incorrect!" << endl;
			break;
		}
	}
	return false;
}
