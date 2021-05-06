# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 4
# year 2020

def number_divisible(l,n):
    '''
    (list, int)->list
    preconditions: n>0
    returns the number of elements in the list that are divisible by n.
    '''
    #setting an accumlator
    number_of_divisible=0
    #looping over length of the given list
    #and checking each item if disivble by n
    #increment of 1 if True
    for i in range(len(l)):
        #changing type of items to an integer 
        if int(l[i])%n==0:
            number_of_divisible=number_of_divisible+1

    return number_of_divisible
            
#main


#asking user for numbers seperated by spaces
#creating a list of those numbers by using
#strip and split methods
given_list=input("Please input a list of numbers separated by space: ").strip().split()
#asking for an integer
#and converting type to an int
divisor=int(input("Please input an integer: "))

#printing out how many number are divisble by given divisor by calling number_divisible() and printing
#returned value as a string
print("The number of elements divisible by "+str(divisor)+" is "+str(number_divisible(given_list,divisor)))
