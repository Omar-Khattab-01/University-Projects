#include <iostream>
#include <vector>
using namespace std;

#define MAX_CARD 1000

using namespace std;
template <typename type1, typename type2>
struct Pair
{
    type1 first;
    type2 second;
};


template <typename  type1, typename type2>
class MappingRelation {
private:
    int size;
    Pair<type1,type2>* elements;
    int capacity;
public:
    MappingRelation();
    MappingRelation(const MappingRelation<type1,type2>&);
    ~MappingRelation();
    int cardinality();
    bool add_element(type1, type2);
    void remove_element(type1, type2);
    bool is_member(type1, type2);
    bool operator ==(MappingRelation<type1,type2>);
    MappingRelation<type1,type2> operator +(MappingRelation<type1,type2>);
    MappingRelation<type1,type2> inverse();
    MappingRelation<type1,type2> operator -(MappingRelation<type1,type2>);
    vector<type2> operator[](type1);
    MappingRelation<type1,type2>& operator=(MappingRelation<type1,type2>);
    MappingRelation<type1,type2> intersection(MappingRelation<type1,type2>);
    bool is_function();
    MappingRelation<type1,type2> combination(MappingRelation<type1,type2>);
    template <typename  type3,typename type4>
    friend ostream& operator<<(ostream&, MappingRelation<type3,type4>&);





};
