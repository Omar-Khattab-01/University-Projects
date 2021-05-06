# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 3
# year 2020


###################
#Question 2.1
###################
import math

def sum_odd_divisors(n):
    '''
    (int)->int/none
    Preconditions: n != 0
    Returns the sum of all positive odd divisors of n
    '''

    #creating an empty variable 
    s=0

    #checking if n is positive
    if n>0:
       for i in range(1,n+1,2):
            if n/i == int(float(n/i)):
                s=s+i
    #checking if n is negative
    #modifying the negative number
    elif n<0:
        n=(str(n))[1:]
        n=int(n)
        for i in range(1,n+1,2):
            if n/i == int(float(n/i)):
                s=s+i
    else: s= None
    return s

###################
#Question 2.2
###################
                                        
def series_sum():
    '''
    ()->int/float
    Preconditions: 
    prompts the user for an non-negative integer n
    Return the sum of the following series (1000+ 1/1^2 + 1/2^2 +...+1/n^2)
    '''

    #creating an emty variable
    s=0

    #promoting for the non-negative number
    n=int(input("Please enter a non-negative integer:"))

    #if n is a negative number
    if n<0:
        return None
    #if n is equal to zero
    elif n==0:
        s
    else:
        stop=True
        #looping as long as stop is equal to True to compute the sum of the sequence
        while stop:
            for i in range(1,n+1):
                if i== n :
                    s=s+(1/(i**2))
                    stop= False
                else:
                    s=s+(1/(i**2))
    return 1000+s
                    
        
###################
#Question 2.3
###################

def pell(n):
    '''
    (int)->int
    If n is negative, pell returns None. Else,
    pell returns the nth Pell number
    '''

    #setting variables to help computing the sequence
    p1=0
    p2=1

    #setting counter to help stopping the loop
    l=2

    #if n is zero
    if n ==0:
        return 0
    #if n is 1 
    elif n==1:
        return 1
    #if n is negative
    elif n<0:
        return None
    else:
        #setting stop variable to True to start the loop
        stop=True
        #looping over the seqence untill l counter is equal to n+1 
        while stop:
            if l==n+1:
                stop=False
            else:
                p3=(2*p2)+p1
                p4=p3
                p1=p2
                p2=p3
                l=l+1
            
    #reseting inital values for next use(N/A in the testing of the function in the shell 
    p1=0
    p2=1
    l=2       
    return p4
    
###################
#Question 2.4
###################

def countMembers(s):
    '''
    (str)->int
    returns the number of characters in s, that are extraordinary.
    the lower case letter between e and j (inclusive), the upper
    case letters between F and X (inclusive), numerals between 2 and 6 (inclusive), and the exclamation point (!), comma (,),
    and backslash (\)
    '''

    #setting a counter variable
    l=0

    #looping around the characters of s and checking how many characters are extraordinary
    for char in s:
        if char in "efghij":
            l=l+1
        elif char in "FJHIJKLMNOPQRSTUVWX":
            l=l+1
        elif char in "\\!,":
            l=l+1
        elif char in"23456":
            l=l+1
        else:pass
    return l     
                
    
###################
#Question 2.5
###################

def casual_number(s):
    '''
    (str)->int
    return an integer representing a number in s.
    '''
    
    #Checking first character of s 
    for i in s: 
        #checking if first character of s in the negative character and checking if the rest of the characters are numbers
        #and returning the number if conditions satisfied without the commas(",")
        if i==s[0] and (i=="-") and (i not in s[1:]) and  ((((s[1:]).replace(",","")).isdigit())==True) :
            return int(s[:].replace(",",''))
        #checking if number is positive and if characters are numbers
        #and returning the number if conditions satisfied without the commas(",")
        elif ((((s[1:]).replace(",","")).isdigit())==True) :
            return int(s[:].replace(",",'')) 
        #checking if first character is blank and returning none         
        elif i==" ":
            return None

        
###################
#Question 2.6
###################       
def alienNumbers(s):
    '''
    (Str)->int
    returns the integer value represented by s
    '''

    #Counting and Translating the weird numbering system
    return (s.count("T")*1024)+(s.count("y")*598)+(s.count("!")*121)+(s.count("a")*42)+(s.count("N")*6)+(s.count("U")*1)

###################
#Question 2.7
###################

def alienNumbersAgain(s):
    '''

    '''
    T=0
    y=0
    exl=0
    a=0
    N=0
    U=0
    
    for char in s:
        if char in "T":
            T=T+1
        elif char in "y":
            y=y+1
        elif char in "!":
            exl=exl+1
        elif char in "a":
            a=a+1
        elif char in "N":
            N=N+1
        elif char in "U":
            U=U+1

    return (1024*T)+(598*y)+(121*exl)+(42*a)+(6*N)+(1*U)


###################
#Question 2.8
###################

def encrypt(s):
    '''
    (str)->str
    returns a string which is
    the encrypted version of s.
    '''

    #Creating an accumulator    
    x=""
    #flipping the string
    s=s[::-1]
    lenn=len(s)
    
        
    
    for b in range(1):
        #Seting variables that would replace len(s) in case of length of 1,2 or 3
        if len(s)==1 or len(s)==2:
            lenn=3
        elif len(s)==3:
            lenn=4
            
        
            
        
        for i in range(0,lenn-2):
            if i==0:
                #if s contains only one character add it to the accumlator
                if len(s)==1:
                    x=x+s[:]
                #otherwise
                #add the first and last character together and add them to the accumlator
                #set a new value for s by slicing first and last character 
                else:
                    x=x+s[i:i+len(s):len(s)-1]
                    s=s[i+1:len(s)-1]
            elif i%2==0:
                if len(s)==1:
                    x=x+s[:]
                else:
                    x=x+s[0:i+len(s):len(s)-1]
                    s=s[1:len(s)-1]
            elif i%2!=0:
                if len(s)==1:
                     x=x+s[:]
                else:
                    x=x+s[0:i+len(s):len(s)-1]
                    s=s[1:len(s)-1]
    return x
            

###################
#Question 2.9
###################

def weaveop(s):
    '''
    (str)->str
    returns a string with the letters o and p inserted between every pair of
    consecutive characters of s
    '''

    #creating an acumulator
    ns=""

    #checking if s has one or less characters
    #and returning s as it is 
    if len(s)==0 or len(s)==1:
        return s

    #otherwise
    else:
        #a loop around the length of s will operate until it reach the len(s)-1((second last character))
        for i in range(0, len(s)-1):
            #checking if the two consecutive characters are the same type 
            if s[i].isalpha() == s[i+1].isalpha():
                #configuring if the pair is going to be seperated by an op wave
                if s[i].isalpha()== True :
                    #if the pair consists of two lower case letters
                    #op is added between
                    if s[i]==s[i].lower() and s[i+1]==s[i+1].lower():
                        #if ns is empty add only op and s[i+1]
                        if ns!="":
                            ns=ns+"op"+s[i+1]
                        else:
                            ns=ns+s[i]+"op"+s[i+1]
                    #if the pair consists of a lower and an upper case letters
                    #oP is added between
                    elif s[i]==s[i].lower() and  s[i+1]!=s[i+1].lower():
                        if ns!="":
                            ns=ns+"oP"+s[i+1]
                        else:
                            ns=ns+s[i]+"oP"+s[i+1]
                    #if the pair consists of an upper and a lower case letters
                    #Op is added between
                    elif s[i]!=s[i].lower() and s[i+1]==s[i+1].lower():
                        if ns!="":
                            ns=ns+"Op"+s[i+1]
                        else:
                            ns=ns+s[i]+"Op"+s[i+1]
                    #if the pair consists of a two upper case letters
                    #OP is added between
                    elif  s[i]!=s[i].lower() and s[i+1]!=s[i+1].lower():
                        if ns!="":
                            ns=ns+"OP"+s[i+1]
                        else:
                            ns=ns+s[i]+"OP"+s[i+1]
                #otherwise
                #add pair without an op wave if
                #And if ns is not empty add only the second
                #part of that pair as the fisrt already exists in ns
                else:
                    if ns!="":
                        ns=ns+s[i+1]
                    else:
                        ns=ns+s[i]+s[i+1]
            else:
                if ns!="":
                    ns=ns+s[i+1]
                else:
                    ns=ns+s[i]+s[i+1]

    return ns
                    
        
###################
#Question 2.10
###################

def squarefree(s):
    '''
    (str)->bool
    returns True if s is
    squarefree  and False otherwise.
    A squarefree word is a word that does not contain any subword twice in a row.
    '''

    #setting values for accumators

    #setring n as the range for subwords
    n=math.ceil(len(s)/2)
    z=""
    q=True
    w=False  
    counter=2
    counter2=1
    p=0

    #looping for every possible solution and breaking if s is not squarefree
    while q==True:
        #checking if s is squarefree
        #and breaking out of the loop of condition is met
        if w==True:
            break
        else:
            #Main loop
            for i in range(1):
                #looping over three conditions
                for v in range(3):
                    #checking if s is squarefree
                    #and breaking out of the loop of condition is met
                    if w==True:
                        break
                    #first condition(for looping)
                    elif v==0:
                        #looping over the length of s
                        #and comparing each character to the next character
                        #and checking if there is any consecutive repition
                        for c in range(len(s)):
                            #Checking if s[c](character number c) is equal to the next variable
                            #emptyin z for next comparison
                            #assiging s[c](current character) to z
                            w= z==s[c]
                            z=""
                            z=z+s[c]
                            #checking if s is squarefree
                            #and breaking out of the loop of condition is met
                            if w==True:
                                                
                                break
                    #second condition(for looping)       
                    elif v==1:
                        #emptying z from the previous loop entries
                        z=""
                        #looping over every set of nth chaacters less than half the length of the entered string
                        #and comparing each set to the next set of the same length
                        #and checking if there is any consecutive repition
                        for a in range(2,len(s)+counter):
                            z=""
                            for c in range(0,len(s),counter):
                                w= z==s[c:c+counter]
                                z=""
                                z=z+s[c:c+counter]
                                #checking if s is squarefree
                                #and breaking out of the loop of condition is met
                                if w==True:
                                    break
                            #checking if s is squarefree
                            #and breaking out of the loop of condition is met 
                            if w==True:
                                break
                            else:
                                counter=counter+1
                        #checking if s is squarefree
                        #and breaking out of the loop of condition is met    
                        if w==True:
                            break
                    #Third condition(for looping)   
                    elif v==2:
                        #reseting value of counter for next use
                        counter=2
                        #emptying z from the previous loop entries
                        z=""
                        #the only difference between this loop and previous one is that this one
                        #checks all possible subwords statring from s[1] (second character)
                        #and counter and counter2 are accumlators that sets the length of possible subworda
                        #_______________________________________________________________________________________
                        #looping over every set of nth chaacters less than half the length of the entered string
                        #and comparing each set to the next set of the same length
                        #and checking if there is any consecutive repition
                        for b in range((len(s[0+1:len(s)]))):
                            for a in range(2,len(s)+counter):
                                z=""
                                for c in range(counter2,len(s),counter):
                                    w= z==s[c:c+counter]
                                    z=""
                                    z=z+s[c:c+counter]
                                    #checking if s is squarefree
                                    #and breaking out of the loop of condition is met
                                    if w==True:
                                        break
                                #checking if s is squarefree
                                #and breaking out of the loop of condition is met
                                if w==True:
                                    break
                                elif counter>n:
                                    counter2=counter2+1
                                        
                                else:
                                    #reseting value of counter 2 for next use
                                    counter2=2
                                    #increment of 1 to counter
                                    #responsible for length of the subword
                                    #increment in action untill a non-squarafree
                                    #characters found or untill it reach maximum
                                    #length for a possible repeated subword
                                    counter=counter+1
                            #checking if s is squarefree
                            #and breaking out of the loop of condition is met    
                            if w==True:
                                break
                            counter2=counter2+1
                        #checking if s is squarefree
                        #and breaking out of the loop of condition is met
                        if w==True:
                            break        
                        else:
                            q=False
    #Checking if s is squarefree
    #returns True if is squarefree
    if w==True:
        return False
    else:
        return True
    

            
        
