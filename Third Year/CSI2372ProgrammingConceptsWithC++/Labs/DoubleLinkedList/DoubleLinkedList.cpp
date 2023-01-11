#include <iostream>
#include "DoubleLinkedList.h"

using namespace std;

ostream& operator<<(ostream& out, DoubleLinkedList& list)
{
	int n = list.count_nodes(), i;
	
	for (i = 0; i < n; ++i)
	{
		if (i != 0) out << ", ";
		out << list[i];
	}

	return out;
}

DoubleLinkedList::DoubleLinkedList()
{
	head = tail = NULL;
	dummy = 0;
}

DoubleLinkedList::~DoubleLinkedList()
{
	if (head == NULL)
		return;

	Node* current;
	current = head;

	while (current != NULL)
	{
		Node* temp = current;
		current = current->next;
		delete temp;
	}

	head = tail = NULL;
}

bool DoubleLinkedList::add_to_front(int x)
{
	//Allocating memory for one node using a pointer to a node
	Node* new_node = new Node;

	if (new_node == NULL)
		return false;

	new_node->data = x;
	new_node->next = head;
	new_node->previous = NULL;

	if (head == NULL)
		head = tail = new_node;
	else
	{
		head->previous = new_node;
		head = new_node;
	}

	return true;
}

bool DoubleLinkedList::add_to_back(int x)
{
	//Allocating memory for one node using a pointer to a node
	Node* new_node = new Node;

	if (new_node == NULL)
		return false;
	
	new_node->data = x;
	new_node->next = NULL;
	new_node->previous = NULL;

	if (head == NULL)
		head = tail = new_node;
	else
	{
		tail->next = new_node;
		new_node->previous = tail;
		tail = new_node;
	}

	return true;
}

bool DoubleLinkedList::search(int x)
{
	Node* current;

	for (current = head; current != NULL; current = current->next)
		if (current->data == x) return true;

	return false;
}

bool DoubleLinkedList::insert_item(int pos, int x)
{
	int i;
	Node* current;
	Node* new_node;
	if (pos == 0)
		return add_to_front(x);
	
	int n = count_nodes();
	if (pos == n)
		return add_to_back(x);

	if (pos > n)
	{
		cout << "Position is out of range!" << endl;
		return false;
	}
	//Allocating memory for one node to be added
	//to the linked list
	new_node = new Node;

	if (new_node == NULL)
		return false;

	new_node->data = x;
	new_node->next = NULL;
	new_node->previous = NULL;

	current = head;
	for (i = 0; i < pos; ++i)
		current = current->next;

	new_node->next = current;
	new_node->previous = current->previous;
	new_node->previous->next = new_node;
	current->previous = new_node;

	return true;
}

int DoubleLinkedList::count_nodes()
{
	int c = 0;
	Node* current;

	for (current = head; current != NULL; current = current->next)
		++c;

	return c;
}

void DoubleLinkedList::remove_from_front()
{
	if (head == NULL)
		return;

	Node* temp = head;

	//If we do not check head is NULL
	//NULL pointer exception
	if (head == tail)
		head = tail = NULL;
	else	
		head = head->next;
	
	delete temp;
}

void DoubleLinkedList::remove_item(int x)
{
	if (head == NULL)
		return;

	if (head->data == x)
	{
		remove_from_front();
		return;
	}

	if (tail->data == x)
	{
		remove_from_back();
		return;
	}

	Node* current;
	for (current = head; current != NULL; current = current->next)
	{
		if (current->data == x)
		{
			Node* temp = current;
			current->previous->next = current->next;
			current->next->previous = current->previous;
			delete temp;
			break;
		}
	}
}

void DoubleLinkedList::remove_from_back()
{
	if (head == NULL)
		return;

	if (head == tail)
	{
		remove_from_front();
		return;
	}

	Node* temp = tail;
	tail->previous->next = NULL;
	tail = tail->previous;
	delete temp;
}

int& DoubleLinkedList::operator[](int index)
{
	int i, n = count_nodes();

	if (index < 0 || index >= n)
	{
		cout << "Index out of range!" << endl;
		return dummy;
	}

	Node* current = head;
	for (i = 0; i < index; ++i)
		current = current->next;

	return current->data;
}