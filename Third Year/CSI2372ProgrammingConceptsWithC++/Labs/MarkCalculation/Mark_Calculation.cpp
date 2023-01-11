#include <iostream>
#include <vector>
#include <string>
#include <fstream>

using namespace std;

struct student
{
	string fname;
	string lname;
	int id;
	double labs;
	double assignment[5];
	double termtest;
	double midterm;
	double finalexam;
};

string letter_grade(double mark)
{
	if (mark >= 90) return "A+";
	if (mark >= 85) return "A ";
	if (mark >= 80) return "A-";
	if (mark >= 75) return "B+";
	if (mark >= 70) return "B ";
	if (mark >= 65) return "C+";
	if (mark >= 60) return "C ";
	if (mark >= 55) return "D+";
	if (mark >= 50) return "D ";
	if (mark >= 40) return "E ";
	return "F ";
}

int main()
{
	int i, j;
	ifstream fin("CSI2372.info", ios::binary | ios::in);

	if (!fin)
	{
		cout << "Could not open the input file!" << endl;
		return 0;
	}

	vector <student> course;
	student st;
	char ch;

	while (true)
	{
		fin.read((char*)&ch, 1);
		st.fname = "";
		while (ch != ';')
		{
			st.fname += ch;
			fin.read((char*)&ch, 1);
		}

		fin.read((char*)&ch, 1);
		st.lname = "";
		while (ch != ';')
		{
			st.lname += ch;
			fin.read((char*)&ch, 1);
		}

		fin.read((char*)&st.id, sizeof(int));
		fin.read((char*)&st.labs, sizeof(double));

		for(i = 0; i < 5; ++i)
			fin.read((char*)&st.assignment[i], sizeof(double));

		fin.read((char*)&st.termtest, sizeof(double));
		fin.read((char*)&st.midterm, sizeof(double));
		fin.read((char*)&st.finalexam, sizeof(double));

		if (fin.eof()) break;
		course.push_back(st);
	}

	fin.close();

	ofstream fout("CSI2372_final.info", ios::binary | ios::out);

	if (!fout)
	{
		cout << "Could not open the output file!" << endl;
		return 0;
	}

	for (i = 0; i < course.size(); ++i)
	{
		double total;
		int len = course[i].fname.size();
		fout.write((char*)&len, sizeof(int));
		fout.write(course[i].fname.c_str(), len);
		
		len = course[i].lname.size();
		fout.write((char*)&len, sizeof(int));
		fout.write(course[i].lname.c_str(), len);

		fout.write((char*)&(course[i].id), sizeof(int));
		fout.write((char*)&(course[i].labs), sizeof(double));
		total = course[i].labs;

		for (j = 0; j < 5; ++j)
		{
			double a = course[i].assignment[j] / 6.0;
			fin.read((char*)&a, sizeof(double));
			total += a;
		}
		
		fout.write((char*)&(course[i].termtest), sizeof(double));
		total += course[i].termtest;

		fout.write((char*)&(course[i].midterm), sizeof(double));
		total += course[i].midterm;

		fout.write((char*)&(course[i].finalexam), sizeof(double));
		total += course[i].finalexam;

		fout.write((char*)&total, sizeof(double));
		
		string lg = letter_grade(total);
		fout.write((char*)lg.c_str(), 2);
	}

	fout.close();
	return 0;
}