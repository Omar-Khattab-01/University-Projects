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


#exhaust
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
