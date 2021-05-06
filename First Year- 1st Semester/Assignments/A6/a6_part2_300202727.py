# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 6
# year 2020

class Point:
    'class that represents a point in the plane'

    def __init__(self, xcoord=0, ycoord=0):
        ''' (Point,number, number) -> None
        initialize point coordinates to (xcoord, ycoord)'''
        self.x = xcoord
        self.y = ycoord

    def setx(self, xcoord):
        ''' (Point,number)->None
        Sets x coordinate of point to xcoord'''
        self.x = xcoord

    def sety(self, ycoord):
        ''' (Point,number)->None
        Sets y coordinate of point to ycoord'''
        self.y = ycoord

    def get(self):
        '''(Point)->tuple
        Returns a tuple with x and y coordinates of the point'''
        return (self.x, self.y)

    def move(self, dx, dy):
        '''(Point,number,number)->None
        changes the x and y coordinates by dx and dy'''
        self.x += dx
        self.y += dy

    def __eq__(self, other):
        '''(Point,Point)->bool
        Returns True if self and other have the same coordinates'''
        return self.x == other.x and self.y == other.y
    def __repr__(self):
        '''(Point)->str
        Returns canonical string representation Point(x, y)'''
        return 'Point('+str(self.x)+','+str(self.y)+')'
    def __str__(self):
        '''(Point)->str
        Returns nice string representation Point(x, y).
        In this case we chose the same representation as in __repr__'''
        return 'Point('+str(self.x)+','+str(self.y)+')'

class Rectangle(Point):
    'class that represents a rectangle in a 2d plane with bottom left and top right coordinates and color'
    def __init__(self,Point1,Point2,color):
        ''' (Rectangle,Point, Point str) -> None
        initialize rectangle with bottom left and top right points and color'''
        self.bottom_left=Point1
        self.top_right=Point2
        self.color=color
    def __repr__(self):
        '''(Rectangle)->str
        Returns canonical string representation Rectangle(Point(x,y),Point(x2,y2),color)'''
        return "Rectangle({0:}, {1:} , '{2:}')".format(self.bottom_left,self.top_right,self.color)
    def __str__(self):
        '''(Rectangle)->str
        Returns nice string representation of the information of the rectangle.'''
        return 'I am a '+self.color+' rectangle with bottom left corner at '+str(Point.get(self.bottom_left)) +' and top right corner at '+str(Point.get(self.top_right)) +'.'

    def __eq__(self, other):
        '''(Rectangle,Rectangle)->bool
        Returns True if self and other have the same coordinates'''
        first=Point.__eq__(self.bottom_left,other.bottom_left)
        second=Point.__eq__(self.top_right,other.top_right)
        third=(self.color==other.color)
        if first==second and second==third:
            return first
        else: return False
        
    def get_color(self):
        '''(Rectangle)->str
        Returns a str of the color of a rectangle'''
        return self.color

    def get_bottom_left(self):
        '''(Rectangle)->str
        Returns canonical string representation of bottom left point - Point(x, y)'''
        
        #(Point.__repr__(self.bottom_left))
        return self.bottom_left
    def get_top_right(self):
        '''(Rectangle)->str
        Returns canonical string representation of bottom right point - Point(x, y)'''
        
        #(Point.__repr__(self.top_right))
        return self.top_right
    def reset_color(self,color):
        '''
        (Rectangle,str)->none
        Sets color of rectangle to color
        '''
        self.color=color

    def get_perimeter(self):
        '''
        (Rectangle)->int/float
        Returns perimeter of rectangle
        '''
        return 2*(((self.top_right.x)-(self.bottom_left.x))+((self.top_right.y)-(self.bottom_left.y)))

    def get_area(self):
        '''
        (Rectangle)->int/float
        Returns area of rectangle
        '''
        return (((self.top_right.x)-(self.bottom_left.x))*((self.top_right.y)-(self.bottom_left.y)))

    def move(self,dx,dy):
        '''(Rectangle,int/float,int/float)->None
        changes the bottom left and top right coordinates by dx and dy'''
        Point.move(self.bottom_left,dx,dy)
        Point.move(self.top_right,dx,dy)

    def intersects(self,other):
        '''
        (Rectangle,Rectangle)->bool
        Returns True if the calling rectangle intersects the given rectangle and False
        otherwise
        '''
        
        obj=[self,other]
        self1 = obj[0]
        other1= obj[1]
        r=[]
        for i in range(2):
            if i!=0:
                self1  = obj[1]
                other1 = obj[0]
            r.append((((self1.bottom_left.x==other1.bottom_left.x) and (self1.bottom_left.y==other1.bottom_left.y))or ((self1.bottom_left.x==other1.top_right.x) and (self1.top_right.y==other1.bottom_left.y)) or ((self1.top_right.x==other1.top_right.x and self1.top_right.y==other1.top_right.y)))or(self1.bottom_left.x==other1.top_right.x and other1.bottom_left.y<=self1.bottom_left.y<=other1.top_right.y)or(self1.bottom_left.x > other1.bottom_left.x and self1.bottom_left.x<other1.top_right.x)or   (self1.top_right.x > other1.bottom_left.x and self1.top_right.x < other1.top_right.x) or ((other1.bottom_left.y<self1.bottom_left.y and self1.top_right.y<other1.top_right.y) and (other1.bottom_left.x==self1.top_right.x)))
        return r[0] or r[1]
    def contains(self,x2,y2):
        '''
        (Rectangle,int/float,int/float)->bool
        Returns True of a point with coodrinates (x2,y2) is inside of the calling rectangle (A point on the boundary of the
        rectangle is considered to be inside)
        '''
        return (self.bottom_left.x <= x2 <= self.top_right.x and self.bottom_left.y <= y2 < self.top_right.y)

    
    
    
    
class Canvas(Rectangle,Point):
    def __init__(self):
        ''' (Canvas) -> None
        initialize Canvas with an emty dictionary variable'''
        self.recdict= {}

    def __len__(self):
        '''
        (Canvas)->int
        returns length of reclist (storing rectangles)
        '''
        return len(self.recdict)

    def __repr__(self):
        '''
        (Canvas)->str
        Returns nice string representation of the information each rectangle in recdict(rectangles dictionary).
        '''
        c='Canvas(['
        for i in self.recdict:
            c+="Rectangle("+Point.__str__(self.recdict[i][3].bottom_left)+","+Point.__str__(self.recdict[i][3].top_right)+",'"+self.recdict[i][2]+"')"
            c+=", "
        c=c[:-2]
        c+="])"
        c.replace('"',"")
        return c
    

    def add_one_rectangle(self,rectangle):
        '''
        (Canvas,Rectangle)->none
        Adds a new rectangle to recdict(rectangles dictionary).
        '''
        self.recdict[len(self)+1]=[list(rectangle.bottom_left.get()),list(rectangle.top_right.get()),rectangle.color, rectangle]

    

    def count_same_color(self,color):
        '''
        (Canvas,str)->int
        Returns how many rectangles in recdict that have the same color as variable color
        '''
        counter=0
        for c in self.recdict:
            if  self.recdict[c][2]==color:
                counter+=1
        return counter

    def total_perimeter(self):
        '''
        (Canvas)->int
        Returns the sum of the perimeters of all the rectangles in the calling canvas.
        '''
        total=0
        for i in self.recdict:
            total+=Rectangle.get_perimeter(self.recdict[i][3])
        
        return total

    def min_enclosing_rectangle(self):
        '''
        (Canvas)->str
        Precondition: Canvas non empty
        Returns minimum enclosing rectangle that contains all the
        rectangles in the calling canvas.
        '''
        import random
        minx=self.recdict[1][0][0]
        maxx=self.recdict[1][0][1]
        miny=self.recdict[1][1][0]
        maxy=self.recdict[1][1][1]
        color=["red","blue","yellow","black","white","orange","green"]
        k=0
        for i in self.recdict:
            for j in range(2):
                if j!=0:
                    k=1   
                if not(minx <(self.recdict[i][k][0])<maxx):
                    
                    if minx>(self.recdict[i][k][0]) :
                        minx=(self.recdict[i][k][0])
                        
                    elif maxx<(self.recdict[i][k][0]):
                        maxx=(self.recdict[i][k][0])
                        
                if not(miny <(self.recdict[i][k][1])<maxy):
                    if miny>(self.recdict[i][k][1]):
                        miny=(self.recdict[i][k][1])
                    
                    elif maxy<(self.recdict[i][k][1]):
                        maxy=(self.recdict[i][k][1])
                        
            k=0
        newrec=Rectangle(Point(minx,miny),Point(maxx,maxy),random.choice(color))
        #"Rectangle(Point({0:},{1:}),Point({2:},{3:}),'{4:}')".format(minx,miny,maxx,maxy,random.choice(color))
        return newrec
    def common_point(self):
        '''
        (Canvas)->bool
        Returns True if there exists a point that intersects all rectangles in the calling
        canvas and False otherwise.
        '''

        x=""
        for i in range(1,len(self.recdict)):
            for j in range(i+1,(len(self.recdict)+1)):
                if Rectangle.intersects(self.recdict[i][3],self.recdict[j][3])==False:
                    x+=" "
        if x!="":
            return False
        else:
            return True
        



    

