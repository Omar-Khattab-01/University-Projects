# Family name: Omar Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 1
# year 2020

import turtle 
import math

########################
# Question 1
########################

def f_to_k(t):
    '''
    (number)-> number
    Returns temperatue t in Kelvin when given in Fahrenheit
    '''
    t=(t+459.67)*5/9
    return t 

#######################
# Question 2
#######################

def poem_generator():
    '''
    ()->none
    Prints a poem generated with a name
    and ciry of birth after when both being promotly asked from the user.
    '''
    #creating input to that prompts for the user's name
    n=input("Enter your name: ")
    #creating input to that prompts for the user's city of birth
    c=input("Enter your city of birth: ")

    #printing the poem
    print('"In One of those days \n', n,
          "was sleeping, \n Woke up in a place \n And started freaking. \n And then a stranger started speaking\n",n,
          ' said :"This can\'t be real?!, \n that\'s not how poeple be speaking in',c, "\n And yes,",n, 'was right!\n It was still in the middile of the night."')

#######################
# Question 3
#######################

def impl2loz(w):
    '''
    (number)->(int,number)
    Preconditions: w>=0
    Returns a pair of number(l,o) such that (w = l + o/16) and (l) is an integer and
    (o) is a non-negative number smaller than 16 when given a non integer number w.
    '''
    #Use floor function so we can attain an integer for variable l
    l=math.floor(w)
    #solve for o
    o=(w-l)*16

    return l,o
    


#######################
# Question 4
#######################

def pale(n):
    '''
    (number)->bool
    Preconditions: n has to be a 4 digits number
    Returns True if (n) is a pale number.
    '''
    #test A 1-check if 1st two numbers are two consecutives threes
    p1= n//100 !=33
    #test B 2-check if 2nd two numbers are two consecutives threes
    p2= n//10%100 !=33                                           #if true - not pale  should return false
    #test C 3-check if 1st two numbers are two consecutives threes
    p3= n%100 !=33

    #test B 1-check if n is divisible by 4
    p41= n%10 != 8
    #test B 2 check if n is divisible by 4
    p42= n%10 !=4

    #check if the number is pale
    p4f= p41 and p42
    f= p1 and p2 and p3 and p4f
    
    return f

#######################
# Question 5
#######################



def bibformat(author,title,city,publisher,year):
    '''
    (str,str,str,str,int)->'str (str). str. str: str.'
    returns a string of the form: 'author (year). title. city: publisher.'    
    '''
    #turning the integer year into a string to ruturn a string as required
    y= str(year)
    #formating the final string as requested
    final= author+" ("+y+"). "+title+". "+city+": "+publisher+"."
    return final



#######################
# Question 6
#######################


def bibformat_display():
    '''
    none->'str (str). str. str: str.'
    Prints informations about a book entered by a user
    '''

    #inputs created to gather informations required about the book
    title=input("Enter Book Title: ")
    author=input("Enter Book\'s Auuthor: ")
    year=input ("Year of Publication: ")
    publisher=input("Publisher of the Book: ")
    city=input("what is the headquarter city of the publisher? ")

    #calling function bibformat to organize the format of the string 
    b =  bibformat(author,title,city,publisher,year)
    
    print(b)
    
#######################
# Question 7
#######################

def compound(x,y,z):
    '''
    (int,int,int)->bool
    Preconditions: x,y,z are integer numbers
    Returns True if (x) is the only even number and if at least one pair
    of the three parameters add up to more than a hundred.
    '''
    #checking if x is even
    x1 = x%2 ==0
    y1 = y%2 ==1
    z1 = z%2 ==1

    c1= x1 and y1 and z1
    #checking if one pair add up to a number greater than a 100 
    xy2 = x+y >100
    yz2 = y+z >100
    zx2 = z+x >100

    c2= xy2 or yz2 or zx2
    
    #checking final result
    r= c1 and c2

    return r 


#######################
# Question 8
#######################

def  funct(p):
    '''
    (number)->none
    pre conditions: p >= 11
    Prints a message about the the solution of r in
    the equation (5^r^2)-p+10 for a given p.
    '''

    #equation for solving variable r 
    r= math.sqrt(math.log(p-10,5))
    
    print("The solution is ",r)


#######################
# Question 9
#######################

def gol(n):
    '''
    (number)->number
    Preconditions: n>=1
    returns the minimum number of times that n needs to be divided by 2
    to get a number =<1
    '''
    
    #after solving for t "number of times we have to divide n" from the equation we get
    #by using ceil method we can approach a integer which will be presented as t 
    t=math.ceil(math.log(n,2))
    return t

#######################
# Question 10
#######################

def draw_rocket():
    '''
    none->none
    Draws a rocket in turtle's python screen
    '''
    
    import turtle
    #creating screen
    s=turtle.Screen()

    #creating turtle and moving them it to its starting positions
    r=turtle.Turtle()
    r.hideturtle()
    r2=turtle.Turtle()
    r2.hideturtle()

    #setting color and speed
    r.fillcolor("navy")
    r.speed(200)

    #repostining
    r.penup()
    r.goto(0,-250)

    r.begin_fill()
    r.pendown()
    r.forward(55)
    r.left(40)

    #right side 
    for i in range(182):
        r.speed(2000)
        r.forward(3)
        r.left(.6)

    #repostioning
    r.penup()
    r.goto(0,-250)
    r.pendown()
    r.setheading(180)
    r.forward(55)
    r.right(40)

    #left side
    for i in range(182):
        r.speed(2000)
        r.forward(3)
        r.right(.6)
        
    #repositioing 
    r.setheading(0)
    r.forward(40)
    r.setheading(135)
    r.forward(40)
    r.setheading(90)
    r.forward(40)
    r.setheading(0)

    #1st circle
    r.circle(10)

    #repositioning
    r.setheading(270)
    r.forward(40)
    r.setheading(225)
    r.forward(40)
    r.setheading(270)
    r.end_fill()

    #color
    r.fillcolor("Black")
    r.begin_fill()

    #2nd circle
    r.circle(30)
    r.end_fill()

    #repositioning & color
    r.penup()
    r.goto(-50,100)
    r.pendown()
    r.fillcolor("Blue")
    r.begin_fill()

    #3rd circle
    r.circle(50)
    r.end_fill()

    #repositioning & color
    r.penup()
    r.goto(-30,100)
    r.pendown()
    r.fillcolor("red")
    r.begin_fill()

    #4rth circle
    r.circle(30)
    r.end_fill()

    #respoitioning & color
    r.penup()
    r.goto(-20,100)
    r.pendown()
    r.fillcolor("white")
    r.begin_fill()

    #5th circle
    r.circle(20)
    r.end_fill()

    #repositioning & color
    r.penup()
    r.goto(-40,0)
    r.pendown()
    r.fillcolor("Blue")
    r.begin_fill()

    #6th circle
    r.circle(40)
    r.end_fill()

    #respoitioning circle
    r.penup()
    r.goto(-20,0)
    r.pendown()
    r.fillcolor("red")
    r.begin_fill()

    #7th circle
    r.circle(20)
    r.end_fill()

    #repositiong & color
    r.penup()
    r.goto(-10,0)
    r.pendown()
    r.fillcolor("white")
    r.begin_fill()

    #8th circle
    r.circle(10)
    r.end_fill()

    #repositioning & color
    r.penup()
    r.goto(-30,-100)
    r.pendown()
    r.fillcolor("Blue")
    r.begin_fill()

    #9th circle
    r.circle(30)
    r.end_fill()

    #respoitioning circle
    r.penup()
    r.goto(-10,-100)
    r.pendown()
    r.fillcolor("red")
    r.begin_fill()

    #10th circle
    r.circle(10)
    r.end_fill()

    #repositiong & color
    r.penup()
    r.goto(-5,-100)
    r.pendown()
    r.fillcolor("white")
    r.begin_fill()

    #11th circle
    r.circle(5)
    r.end_fill()

    #repositioning & color
    r.penup()
    r.goto(55,-250)
    r.pendown()
    r.fillcolor("black")
    r.begin_fill()


    #bottom exhaust
    r.setheading(270)
    r.forward(20)
    r.setheading(180)
    r.goto(-55,-270)
    r.goto(-55,-250)
    r.goto(55,-250)
    r.end_fill()
    r.goto(56,-270)
    r.fillcolor("orange")
    r.begin_fill()

    for i in range(3):
        r.setheading(225)
        r.forward(20)
        r.setheading(135)
        r.forward(20)
    r.setheading(225)
    r.forward(20)
    r.setheading(135)
    r.goto(-55,-270)
    r.end_fill()

    #right exhaust

    r.setheading(0)
    r.penup()
    r.goto(157,0)
    r.pendown()
    r.fillcolor("black")
    r.begin_fill()
    r.forward(55)
    r.right(90)
    r.forward(20)
    r.right(90)
    r.forward(55)
    r.end_fill()

    r.fillcolor("maroon")
    r.penup()
    r.setheading(0)
    r.forward(55)
    r.setheading(90)
    r.forward(20)
    r.setheading(180)
    r.begin_fill()
    r.forward(40)
    r.setheading(0)
    r.forward(120)
    r.setheading(90)
    r.pendown()
    r.circle(60,180)
    r.setheading(0)
    r.forward(120)
    r.end_fill()

    r.fillcolor("darkorange")
    r.begin_fill()
    for i in range(3):
        r.setheading(225)
        r.forward(20)
        r.setheading(135)
        r.forward(20)
    r.end_fill()
    #left exhaust

    r.setheading(180)
    r.penup()
    r.goto(-157,0)
    r.pendown()
    r.fillcolor("black")
    r.begin_fill()
    r.forward(55)
    r.left(90)
    r.forward(20)
    r.left(90)
    r.forward(55)
    r.end_fill()

    r.fillcolor("maroon")
    r.penup()
    r.setheading(180)
    r.forward(55)
    r.setheading(90)
    r.forward(20)
    r.setheading(0)
    r.begin_fill()
    r.forward(40)
    r.setheading(90)
    r.pendown()
    r.circle(60,180)
    r.setheading(0)
    r.forward(120)
    r.end_fill()

    r.fillcolor("darkorange")
    r.penup()
    r.setheading(180)
    r.forward(40)
    r.pendown()
    r.begin_fill()
    for i in range(2):
        r.setheading(225)
        r.forward(20)
        r.setheading(135)
        r.forward(20)

    r.setheading(225)
    r.forward(20)
    r.setheading(135)
    r.goto(-292,0)

    r.end_fill()


        

    r.end_fill()

#######################
# Question 11
#######################

def cad_cashier(price,payment):
    '''
    (float,int)->float
    Preconditions: price is a float with two decimal places,
                   payment >= price
    Returns exchange as a two decimals real number.
     '''
    #set an equation for the exchange 
    x=payment-price

    #divide it by 0.05 to get a whole number and round it 
    y=x/0.05

    #multiply y by the exact exchange around it to two decimal places to avoid many special cases
    z=round(y)*0.05
    
    exchange=round(z,2)
    
    
    return exchange
    
#######################
# Question 12
#######################

def min_CAD_coins(price,payment):
    '''
    (float,int)->(int,int,int,int,int)
    Preconditions: price is a float with two decimal places,
                   payment >= price
    returns five numbers (t,l,q,d,n) that represent the smallest number of coins (toonies, loonies, quarters,
    dimes, and nickels) that add up to the amount owed to the customer
    '''

    #call function cad_cashier to calculate exchange
    exchange = cad_cashier(price,payment)
    #computing total in cents and rounding to avoid mathmatical errors
    cents=round(exchange*100,2)

    #no. of toonies
    t= int(cents/200)
    total1=cents-(t*200)

    #no. of loonies
    l= int(total1/100)
    total2=total1-(l*100)

    #no. of quarters
    q=int(total2/25)
    total3=total2-(q*25)

    #no. od dimes
    d=int(total3/10)
    total4=total3-(d*10)

    #no. of nickles
    n=int(total4/5)

    return (t,l,q,d,n)
    

