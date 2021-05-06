# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 2
# year 2020


###################
#   question 2.1
###################

def  min_enclosing_rectangle(radius, x, y):
    '''
    (float/int, float/int, float/int)->(float/int,float/int)
    Preconditions: radius >0
    When given a radius of a circle and its center point coordinates(x,y) it retutrns the
    coordinates of the bottom-left conrner of the smallest axis-aligned rectangle containg that circle
    '''

    #checking if the parameters x and y are positive number
    #and computing for the left-bottom coordinate of the rectangle
    if radius>0:
        x2_y2=(x-radius,y-radius)
        return x2_y2
    elif radius<0:
        return

    
###################
#   question 2.2
###################

def vote_percentage(results):
    '''
    (str)->(float)
    Preconditions:string must contain yes, no, or abstained
    Returns percentage of "yes" in the given string (results) among all "yes" and "no"
    '''

    #using lower() so that we can all values of substring "yes" equal
    r=results.lower()
    #using count() to count how many substring "yes" and "no" in result
    y=r.count("yes")
    n=r.count("no")
    #computing the percecntage of "yes" substring in result
    z= (y)/(y+n)
    return z

###################
#   question 2.3
###################

def vote():
    '''
    none->none
    Asks for the votes to be wrtiiten one by one and returns
    if the proposal passes or not depending on the majority of
    yes and no in the input
    '''

    #input for the votes
    r=input("Enter the yes, no, abstained votes one by one and then press enter:\n ")
    #lowering the substrings; "yes" and "no" and counting them
    y=r.lower().count("yes")
    n=r.lower().count("no")

    #calling the function vote_percentage(r) and assiging its return value to a variable 
    p= vote_percentage(r)
    #checking and computing for the final result
    if 1> p >= 2/3:
            print("proposal passes with super majority")
    elif 2/3 > p >= 1/2:
        print("proposal passes with simple majority")
    elif p < 1/2:
        print("proposal failed")
    elif p == 1:
        print("proposal passes unanimously")
