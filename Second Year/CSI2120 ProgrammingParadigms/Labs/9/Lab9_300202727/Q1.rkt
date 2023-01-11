#lang racket

;;using_functions Excercise 1
(define (apply-list fc L)
  (if (null? L)
      '()
      (cons (fc (car L))(apply-list fc (cdr L)))))

(display "The squares for the numbers 1 to 4\n")
(apply + (apply-list sqr'(1 2 3 4)))
  
(define (solve A B C)
  (printf "Equation ~ax²+(~ax)+(~a)\nRoot 1: " A B C)
  (print (/ (+ (- B)(sqrt (- (sqr B) (* 4 A C))) (* 2 A))))
  (display "\nRoot 2: ")
  (let ((a A)(b B)(c C))
  (let ((r (/ (- (- b)(sqrt (- (sqr b) (* 4 a c))) (* 2 a)))))
    (print r)))
  
  )
(display "\n")
(solve 2 5 -3)
(display "\nResult of the sin(pi/4) cos(pi/3) + cos(pi/4) sin(pi/3): ")


(+ (* (sin (/ pi 4)) (cos (/ pi  3))) (* (cos (/ pi 4)) (sin (/ pi 3))))

;;Excersice 2

(define myFavourite 42)
(display "\nEvaluation of the summation of local x and y: ")
(let ((x 1)(y 2)) (+ x y))
(display "\nResult of the sin(pi/4) cos(pi/3) + cos(pi/4) sin(pi/3)\nusing local let variables: ")
(let ((x (/ pi 4))(y (/ pi 3))) (+ (* (sin x) (cos y)) (* (cos x) (sin y))))

;;Excersice 3
(display "\nUsing lambda fn to cal 1+2: ")
( (lambda (x y) (+ x y)) 1 2)
;;global
(define foo (lambda (x y) (+ x y)))
(display "\nUsing the global foo: ")
(foo 1 2)
;3
(display "\n\nUsing lambda fn to\ncal sin(pi/4) cos(pi/3) + cos(pi/4) sin(pi/3): ")
((lambda (x y)(+ (* (sin x) (cos y)) (* (cos x) (sin y)))) (/ pi 4)(/ pi 3))
;;global
(define calSinCos (lambda (x y)(+ (* (sin x) (cos y)) (* (cos x) (sin y)))))
(display "\nUsing the global calSinCos: ")
(calSinCos (/ pi 4)(/ pi 3))
;0.9659258262890684

;;Excersice 4

(define foo2 ( lambda (a b c) (let ((d (sqrt (- (sqr b) (* 4 a c)))))
                          (list(/ (+ (- b) d) (* 2 a))
                               (/ (- (- b) d) (* 2 a))))))
(display "Using the global foo2: ")
(foo2 2 5 -3)