# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 4
# year 2020

def two_length_run(l):
    '''
    (list)->bool
    Returns if list has a run sequence by returning True
    '''

    #looping over length of the given list
    #returning True once
    #any two consecutive items are found to be equal
    for i in range(len(l)-1):
        #changing type of items to an integer 
        if float(l[i])==float(l[i+1]):
            return True
    
    return False

#Main

#asking user for numbers seperated by spaces
#creating a list of those numbers by using
#strip and split methods
given_list=input("Please input a list of numbers separated by space: ").strip().split()

#printing returned value after calling two_length_run()
print(two_length_run(given_list))
