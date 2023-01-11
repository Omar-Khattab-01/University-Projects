#include "MappingRelation.h"
#include <iostream>
#include <set>
#include <vector>

using namespace  std;

template <typename  type1, typename  type2>
MappingRelation<type1, type2>::MappingRelation() {

    size = 0;
    capacity = 0;
    elements = new Pair<type1,type2>[MAX_CARD];
    if (elements == NULL)
        cout << "Not enough memory for this set!" << endl;
    else
        capacity = MAX_CARD;
}

template <typename  type1, typename  type2>
MappingRelation<type1, type2>::MappingRelation(const MappingRelation<type1, type2>& r) {

    int i;
    size = 0;
    capacity = 0;
    elements = new Pair<type1, type2>[r.capacity];
    if (elements == NULL)
        cout << "Not enough memory!" << endl;
    else {
        capacity = r.capacity;
        size = r.size;

        for (i = 0; i < r.size; ++i)
            elements[i] = r.elements[i];
    }

}

template <typename  type1, typename  type2>
MappingRelation<type1,type2>::~MappingRelation(){

    delete[] elements;

}

template <typename  type1, typename  type2>
bool MappingRelation<type1, type2>::operator==(MappingRelation<type1, type2> r) {

    if(size != r.size)
        return false;

    for (int i = 0; i < size; ++i) {
        if (elements[i].first != elements[i].first || elements[i].second != elements[i].second)
            return false;
    }
    return true;

}

template <typename type1, typename type2>
int MappingRelation<type1, type2>::cardinality() {

    return size;
}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::add_element(type1 f, type2 s) {
    Pair<type1, type2> p;
    p.first = f;
    p.second = s;
    int i;


    if (size == capacity)
    {
        int new_cap = capacity + MAX_CARD / 2;
        Pair<type1, type2>* temp;
        temp = new Pair<type1, type2>[new_cap];
        if (temp == NULL)
        {
            cout << "Not Enough Memory" << endl;
            return false;
        }

        for (i = 0; i < size; ++i)
            temp[i] = elements[i];

        delete[] elements;
        elements = temp;
        capacity = new_cap;
    }

    for (i = 0; i < size; ++i)
        if (elements[i].first == p.first && elements[i].second == p.second) return true;

    elements[size++] = p;
    return true;


}

template <typename type1, typename type2>
void MappingRelation<type1, type2>::remove_element(type1 f, type2 s) {

    Pair<type1,type2> p;
    p.first = f;
    p.second = s;
    int i, pos = -1;

    for (i = 0; i < size; ++i)
    {
        if (elements[i].first == p.first && elements[i].second == p.second)
        {
            pos = i;
            break;
        }
    }

    if (pos != -1)
    {
        for (i = pos; i < size; ++i)
            elements[i] = elements[i + 1];
        --size;
    }


}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::is_member(type1 f, type2 s) {
    Pair<type1,type2> p;
    p.first = f;
    p.second =s;
    int i;
    for (i = 0; i < size; ++i)
        if (elements[i].first == p.first && elements[i].second == p.second) return true;

    return false;

}


template <typename type1, typename type2>
bool MappingRelation<type1, type2>::is_function() {

    int i, j;
    for (i = 0; i < size; ++i)
    {
        for (j = 0; j < size; ++j)
        {
            if (elements[i].first == elements[j].first && elements[i].second != elements[j].second)
                return false;
        }
    }
    return true;
}

template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::inverse()
{
    int i;
    MappingRelation<type1, type2> result;
    for (i = 0; i < size; ++i)
    {
    Pair<type1,type2> p;
    p.first = elements[i].second;
    p.second = elements[i].first;
    result.add_element(p.first,p.second);
    }

    return result;
}

template <typename type1,typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::operator+(MappingRelation<type1, type2> r) {
    MappingRelation<type1,type2> result;
    MappingRelation<type1,type2> diff;
    diff = r - *(this);


    for (int i = 0; i < size; ++i)
        result.add_element(elements[i].first,elements[i].second);

    for (int i = 0; i < diff.size; ++i)
        result.add_element(diff.elements[i].first,diff.elements[i].second);

    return result;
}

template <typename type1,typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::operator-(MappingRelation<type1, type2> r) {
    MappingRelation<type1,type2> result;

    for (int i = 0; i < size; ++i) {
        if(!r.is_member(elements[i].first,elements[i].second))
            result.add_element(elements[i].first,elements[i].second);
    }

    return result;

}


template <typename type1,typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::intersection(MappingRelation<type1, type2> r) {
    MappingRelation<type1,type2> result;

    for (int i = 0; i < size; ++i) {
        if(r.is_member(elements[i].first,elements[i].second))
            result.add_element(elements[i].first,elements[i].second);
    }

    return result;

}

template <typename type1,typename type2>
vector<type2> MappingRelation<type1, type2>::operator[](type1 f) {
    vector<type2> result;

    for (int i = size-1; i > -1; --i) {
        if (elements[i].first == f)
            result.push_back(elements[i].second);
    }

    return result;
}

template <typename  type1, typename type2>
MappingRelation<type1, type2> &MappingRelation<type1, type2>::operator=(MappingRelation<type1, type2> r) {

    int i;
    Pair<type1,type2>* temp;
    size = 0;
    capacity = 0;
    temp = new Pair<type1,type2>[r.capacity];
    if (temp == NULL)
        cout << "Not enough memory!" << endl;
    else
    {
        capacity = r.capacity;
        size = r.size;
        for (i = 0; i < r.size; ++i)
            temp[i] = r.elements[i];

        delete[] elements;
        elements = temp;
    }

    return (*this);
}
template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::combination(MappingRelation<type1, type2> r) {
    int i, j;
    MappingRelation<type1,type2> result;


    for (i = 0; i < size; ++i)
    {
        for (j = 0; j < r.size; ++j)
        {
            if (elements[i].second == r.elements[j].first)
            {
                Pair<type1,type2> p;
                p.first = elements[i].first;
                p.second = r.elements[j].second;
                result.add_element(p.first,p.second);
            }
        }
    }

    return result;
}
template <typename type1,typename type2>
ostream& operator<<(ostream& out, MappingRelation<type1,type2>& list) {
    int i;
    out << "{ ";
    for (i = 0; i < list.size; ++i)
    {
        if (i != 0) out << ", ";
        out << "(" << list.elements[i].first << ", " << list.elements[i].second << ")";
    }
    out << " }";

    return out;

}




