import turtle

#creating screen
s=turtle.Screen()

#creating turtles and moving them to their starting positions
r=turtle.Turtle()
r.hideturtle()
r2=turtle.Turtle()
r2.hideturtle()
r3=turtle.Turtle()
r3.hideturtle()
r4=turtle.Turtle()
r4.hideturtle()
r.penup()
r.goto(150,-50)
r.pendown()
r.setheading(90)
r2.penup()
r2.goto(-150,-50)
r2.pendown()
r2.left(180)
r2.setheading(90)
r3.penup()
r3.goto(150,-50)
r3.pendown()
r3.setheading(45)
r4.penup()
r4.goto(-150,-50)
r4.pendown()
r4.setheading(135)
r5=turtle.Turtle()
r5.hideturtle()
r5.penup()
r5.goto(150,-50)
r5.pendown()
r6=turtle.Turtle()
r6.hideturtle()
r6.penup()
r6.goto(-150,50)
r6.pendown()

#turtles and final shape colors  
r.color("green")
r2.color("black")
r3.color("green")
r4.color("black")
r5.color("green")
r6.color("black")

r.fillcolor("green")
r2.fillcolor("black")
r3.fillcolor("green")
r4.fillcolor("black")
r5.fillcolor("green")
r5.fillcolor("black")
r6.fillcolor("green")

r.begin_fill()
r2.begin_fill()
r3.begin_fill()
r4.begin_fill()
r5.begin_fill()
r6.begin_fill()

for x in range(25):
    r.forward(7)
    r.left(1)
r.left(8)    
r.forward(150)
r.left(58)
r.forward(65)


for x2 in range(25):
    r2.forward(7)
    r2.right(1)
r2.right(8)    
r2.forward(150)

for x3 in range(5):
    r2.forward(10)
    r2.right(6)
r2.right(65)

for x4 in range(5):
    r2.forward(10)
    r2.right(6)
    
   

r3.forward(150)
r3.left(270)

for x5 in range(30):
    r3.forward(6)
    r3.right(3)
for x6 in range(31):
    r3.forward(10)
    r3.right(0.6)



r4.forward(150)
r4.left(90)


for x7 in range(30):
    r4.forward(6)
    r4.left(3)
for x8 in range(31):
    r4.forward(10.6)
    r4.left(0.77)

for x9 in range(120):
    r5.left(20)
    r5.forward(20)

for x10 in range(120):
    r6.right(20)
    r6.forward(20)

r.end_fill()
r2.end_fill()
r3.end_fill()
r4.end_fill()
r5.end_fill()
r6.end_fill()
