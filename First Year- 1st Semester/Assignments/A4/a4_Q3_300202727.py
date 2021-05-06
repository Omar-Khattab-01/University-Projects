# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 4
# year 2020

def longest_run(l):
    '''
    (list)->int
    returns the length of the longest run.
    '''
    #created a list with all numbers represented as integers
    clean=[]
    for k in l:
        clean.append(int(float(k)))
        
        
    #creating an empty string that would contain the respition of the
    #numbers as items
    max_run=[]

    #first for loop
    #looping over length of the given list and stopping at the second last number
    for i in range(len(l)-1):
        #appending a integer version of first number to the first index in max_run
        if i ==0:
            max_run.append(str(int(float(l[i]))))
        
        elif len(''.join(max_run))!=len(clean):
             max_run.append(str(int(float(l[len(''.join(max_run))]))))
        #setting a variable equal to zero 
        check=0
        #second for loop
        #looping over length of the given list and stopping at the second last number 
        for j in range(len(l)-1):
            #setting j so that it starts from right after
            #where the last two consecutive to check if there any more consecutive letters
            if i!=0 and len(''.join(max_run))!=len(clean):
                j=len(''.join(max_run))-1
            #checking the two consecutives numbers are equal and that
            #check is still equal to zero
            #if true letter will be added to a set of the same letters in max_run list
            if (float(l[j])==float(l[j+1])) and check==0:
                #checking if the length of all letters in max_run list
                #has reached the length of all letters in clean list
                #if true, check will be equal to a new value to exist exit the second
                #foor-loop
                if len(''.join(max_run))==len(clean):
                    check=1
                    pass
                else:
                    
                    max_run[i]=max_run[i]+str(int(float(l[j+1])))
            
            #if otherwise, check will be equal to a new value to exist exit the second
            #foor-loop
            else:
                check=1
                pass
           
    #using max() to get which item in clear has the most set of repetitive letters
    #and returning how many times they were repeated
    maxi=max(max_run, key=len)
    return(len(maxi))
    

        
#Main

#asking user for numbers seperated by spaces
#creating a list of those numbers by using
#strip and split methods
given_list=input("Please input a list of numbers separated by space: ").strip().split()

#printing returned value after calling two_length_run()
print(longest_run(given_list))
