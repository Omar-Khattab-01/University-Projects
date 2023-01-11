# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 3
# year 2020

#1.1 Core Function(is_up_monotone)
def is_up_monotone(X, d):
    '''
    (str,str)->bool
    Preconditions: X is a string of a positive number, d is a string of  an integer
    Returns True if X with d digits is monotone or not

    >>> is_up_monotone("1234","1")
    1, 2, 3, 4
    True
    >>> is_up_monotone("10111215","2")
    10, 11, 12, 15
    True
    >>> is_up_monotone("5674","1")
    5, 6, 7, 4
    False
    >>> is_up_monotone("123","1")
    123
    True
    '''
    #creating empty three variable to use in the looping 
    ns=""
    sets=0
    r1=1
    d= int(d)
    # Your code for is_up_monotone function goes here (instead of keyword pass)
    # Your code should include  dosctrings and the body of the function
    #checking if the length of the number X has a remainder of zero
    if len(X)%d==0:
        #checking if number X is divisble by d
        #if X/d = 1  then print number X without changing format and return True 
        if len(X)/d ==1:
            print(X[:])
            return True
        #otherwise
        else:
            #looping around the length of X with d steps, with d being the digits of the number
            for i in range(0,len(X),d):
                #When i=0 
                if i==0:
                    sets= X[i:i+d]
                    ns= ns + sets+", "
            
                #otherwise if statment checks if  r1(bool) is False
                #created to compare if the two
                #consecutives numbers meet the monotone idea
                else:
                    if r1 == False:
                        r1= False
                        sets= X[i:i+d]
                        ns= ns + sets+", "
                        #assigning r1 with a new bool of the sets(containing previous d digits
                        #number) with the next sets of number
                        #creating a new sets containg a d digits number and adding it to
                        #ns which will be displayed as a set of numbers 
                    else:
                        r1= int(sets)<int(X[i:i+d])
                        sets= X[i:i+d]
                        ns= ns + sets+", "

            
            #Getting rid of the comma of the last set of numbers
            print (ns[:len(ns)-2])
            return r1
        
            
                
    else:
        return False
    #reseting variables for next use 
    ns=""
    sets=0
    r1=1
        


# you can add more function definitions here if you like       

def greeting(name):
    ''' (str)->None
    Draws/Prints name plaque'''
    print()
    print(5*"*"+len(name)*"*"+5*"*")
    print("*"+4*" "+len(name)*" "+4*" "+"*")
    print("*  "+2*"_"+name+2*"_"+"  *")
    print("*"+4*" "+len(name)*" "+4*" "+"*")
    print(5*"*"+len(name)*"*"+5*"*")
    print()
            
# main
# Your code for the welcome message goes here, instead of name="Vida"

#welcome message
#input promoting a question for the name
#manipulating string to generalize
greeting("Welcome to up-monotone inquiry")
name = input("What is your name? ")
name = name.strip()
greeting(name + ", welcome to up-monotone inquiry.")


#While flag is True the following will loop untill flag is False
flag=True
while flag:
    #input promoting the first question
    question=input(name+", would you like to test if a number admits an up-monotone split of given size? ")
    #manipulating string to generalize
    question=(question.strip()).lower()
    #loop will stop only if question is assigned by a string "no"
    if question=='no':
        flag=False
    #YOUR CODE GOES HERE. The next line should be elif or else.

    #Checking if question is neither "yes" or "no" which in this case will
    #ask the user input on of them
    elif question not in 'noyes':
        flag=True 
        print("Please enter yes or no. Try again.")

    #If condition in case question is "yes"
    elif question == 'yes':
        print("Good Choice!")
        #asking for the number to be a positive integer
        #assiging pi with the float of that number
        pi=input("Enter a positive integer:")
        pi=(pi.strip()).lower()
        #checking if integer
        if pi[:1] in "abcdefghijklmnopqrstuvwxyz":
            print("The input can only contain digits. Try again.")
            

        else:
            X=(pi.strip()).lower()
            pi2=int(float(pi))
            #assiging pi2 to the same variable in the origin function
            #and manipulating string to generalize
            

            #Checking if pi(e.i positive integer) is an integre and positive number ( False Case)
            if  pi2 <=0 and pi2 != float(pi):
                
                print("The input can only contain digits and has to be a positive integer. Try again.")
                


            #checking if positive
            elif pi2 <=0 :
                
                print("The input has to be a positive integer.Try again.")
                
            elif pi2 != float(pi):
                print("The input has to only contain digits. Try again.")
            #Checking if pi(e.i positive integer) is an integre and positive number ( True Case)
            elif pi2 >0 and pi2 ==float(pi):
                d=int(input("Input the split. The split has to divide the length of "+pi+" i.e. "+str(len(pi))+"\n"))
                #checking if the split number is a poitive int
                if d<0:
                    print("The split can only contain digits. Try again.")
                #checking if given number X is divisble by given number d
                elif len(pi)/d != int(len(pi)/d):
                    
                    print(str(d)+" does not divide "+str(len(pi))+". Try again")
                
                #Checking if given number X is an integer and divisble by d 
                elif len(pi)/d == int(len(pi)/d):
                    #callling the origin function
                    #and assiging its return value to r1
                    r1=is_up_monotone(X,d)
                    #to print sequence
                    r1
                    #If statment for the final result
                    if r1==True:
                        print("The sequence is up-monotone")
                    else:
                        print("The sequence is not up-monotone")
                    
                

       
#finally your code goes here too.
#Good Bye message

greeting("Good bye "+ name+"!")
