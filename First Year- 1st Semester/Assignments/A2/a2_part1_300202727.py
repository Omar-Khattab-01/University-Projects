# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 2
# year 2020
import math
import random

def elementary_school_quiz(flag, n):
    # Your code for elementary_school_quiz function goes here (instead of keyword pass)
    # Your code should include  dosctrings and the body of the function
    #
    # Preconditions: flag is 0 or 1, n is 1 or 2
    '''
    (int,int)->int
    Preconditions: flag is 0 or 1, n is 1 or 2
    Returns how many problems were solved right, returns (0) of all solved wrong
    '''
    
    #################subtraction practice#####################################
    #Practice with one question
    #1st condition and random number generator
    if flag== 0 and n == 1:
        n1=random.randint(0,9)
        n2=random.randint(0,9)
        m1=input("what is the result of "+ str(n1)+"-"+str(n2)+"? ")
        r1=n1-n2
        #comaring and computing if the result is correct
        if int(m1)==r1:
            print(1)
        else:
            print(0)
    #2nd condition and random number generator 
    #Practice with two questions
    elif flag==0 and n==2:
        n1=random.randint(0,9)
        n2=random.randint(0,9)
        n3=random.randint(0,9)
        n4=random.randint(0,9)
        print("Question 1:")
        m1=input("what is the result of "+ str(n1)+"-"+str(n2)+"? ")
        print("Question 2:")
        m2=input("what is the result of "+ str(n3)+"-"+str(n4)+"? ")
        r1=n1-n2
        r2=n3-n4
        c1=int(m1)==r1
        c2=int(m2)==r2
        #comparing and computing if the results are correct
        if c1== True and c2==True:
            return(2)
        elif c1==True and c2==False:
            return(1)
        elif c1==False and c2==True:
            return(1)
        else:
            return(0)
    #################exponentiation practice#####################################  
    #Practice with one question
    #3rd condition and random number generator
    elif flag==1 and n==1:
        n1=random.randint(0,9)
        n2=random.randint(0,9)
        print("Question 1:")
        m1=input("what is the result of "+ str(n1)+"^"+str(n2)+"? ")
        r1=n1**n2
        #comaring and computing if the result is correct
        if int(m1)==r1:
            return(1)
        else:
            return(0)
    #Practice with two questions
    #4rth condition and random number generator         
    elif flag==1 and n==2:
        n1=random.randint(0,9)
        n2=random.randint(0,9)
        n3=random.randint(0,9)
        n4=random.randint(0,9)
        print("Question 1:")
        m1=input("what is the result of "+ str(n1)+"^"+str(n2)+"? ")
        print("Question 2:")
        m2=input("what is the result of "+ str(n3)+"^"+str(n4)+"? ")
        r1=n1**n2
        r2=n3**n4
        c1=int(m1)==r1
        c2=int(m2)==r2
        #comaring and computing if the results are correct
        if c1== True and c2==True:
            return(2)
        elif c1==True and c2==False:
            return(1)
        elif c1==False and c2==True:
            return(1)
        else:
            return(0)
    elif not(0<=flag<=1):
        print("Invalid chose. Only 0 or 1 is accepted.")
        
        

def high_school_quiz(a,b,c):
    # Your code for high_school_quiz function goes here (instead of keyword pass)
    # Your code should include  dosctrings and the body of the function
    '''
    (int/float,int/float,int/float)->none
    Prints the equation given its coefiicents and constant, and solve and prints for its solution
    '''
    #firs condition   
    if a==0 and b==0 and c==0:
        print ("The quadratic equation  "+str(b)+"·x + "+str(c)+" = 0\nis satisfied for all numbers x")
    #second condition
    elif a==0 and b==0:
        print("The quadratic equation "+str(b)+"·x + "+str(c)+" = 0\nis satisfied for no number x")
    #third condtion for solving linear equation
    elif a==0:
        print("The linear equation "+ str(b)+"·x +" + str(c)+ " = 0"+"\n"+
              "has the following root/solution: "+str((-c)/b))
    #4rth condition for solving a quadratic with complex roots
    elif ((b**2)-(4*a*c))<0:
        sqrt1=str((math.sqrt(abs((b**2)-(4*(a*c)))))/(2*a))
        print("The quadratic equation "+ str(a)+"·x^2"+ str(b)+"·x + "+ str(c)+" = 0"+"\nhas the following two complex roots:\n"+str((-b)/(2*a))+ " +  i "+str(sqrt1)+"\n and\n"+str((-b)/(2*a))+ " -  i "+str(sqrt1))
    #using else for solving normal quadratics with two roots
    else:
        root1=((-b)+math.sqrt((b**2)-(4*a*c)))/(2*a)
        root2=((-b)-math.sqrt((b**2)-(4*a*c)))/(2*a)
        if root1==root2:
            print("The quadratic equation "+ str(a)+"·x^2"+ str(b)+"·x + "+ str(c)+" = 0"+"\nhas only one solution, a real root:\n"+str(root1))
        else:
            print("The quadratic equation "+ str(a)+"·x^2"+ str(b)+"·x + "+ str(c)+" = 0"+"\nhas the following real roots:\n"+ str(root1)+" and "+str(root2))
        
# main

# your code for the welcome tmessage goes here

print("***************************************")
print("*                                     *")
print("*__welcome to my math quiz-generator__*")
print("*                                     *")
print("***************************************")

name=input("What is your name? ")

status=input("Hi "+name+". Are you in? Enter \n1 for elementary school\n2 for high school or\n3 or other character(s) for none of the above?\n")

if status=='1':
    # your code goes here
    print("******************************************************************************")
    print("*                                                                            *")
    print("* ____"+name+" welcome to my quiz-generator for elementary school students.____  *")
    print("*                                                                            *")
    print("******************************************************************************")
    flag=int(input(name+" what would you like to practice? Enter\n0 for subtraction\n1 for exponentiation\n"))
    if not(0<=flag<=1):
        print("Invalid chose. Only 0 or 1 is accepted.")
    else:
        
        n=int(input("How many practice questions would you like to do? Enter 0, 1, or 2: "))
        if n==0:
            print("zero questions. OK. Good bye")
            
        elif n>2 or n<0:
            print("Only 0,1, or 2 are valid choices for the number of questions.")
        elif  n==1 or  n == 2:
            grade=elementary_school_quiz(flag, n)
            if grade == 2:
                print("Congratulations "+name+"! You’ll probably get an A tomorrow.")
            elif grade==1:
                print("You did ok "+name+", but I know you can do better.")
            else:
                print("I think you need some more practice "+name+".")

elif status=='2':

    # your code for welcome message
    print("*************************************************************************")
    print("*                                                                       *")
    print("*    __quadratic equation, a·x^2 + b·x + c= 0, solver for "+name+"__    *")
    print("*                                                                       *")
    print("*************************************************************************")
    flag=True
    while flag:
        question=input(name+", would you like a quadratic equation solved? ")

        # your code to handle varous form of "yes" goes here
        question=question.lower().strip()

        if question!="yes":
            flag=False
        else:
            print("Good choice!")
            # your code goes here (i.e ask for coefficients a,b and c and call)
            a=float(input("Enter a number the coefficient a: "))
            b=float(input("Enter a number the coefficient b: "))
            c=float(input("Enter a number the coefficient b: "))
            # then make a function call and pass to the fucntion
            # the three coefficients the pupil entered
            high_school_quiz(a,b,c)
else:
    # your code goes here
    print(name+" you are not a target audience for this software.")

print("Good bye "+name+"!")

