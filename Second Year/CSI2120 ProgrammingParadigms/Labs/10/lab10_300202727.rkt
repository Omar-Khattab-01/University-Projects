#lang racket
;;Exercise 1
(cons 3 (cons 4 '()))
(cons 1 (cons 2 (cons 3 null)))
(cons 'a (cons (cons 'b (cons 'c '())) '()))
(cons 1 '())
(cons 2 (cons (cons 3 (cons (cons 4 '()) '())) '()))

;;Excercise 2

(define L '(1 2 3 4 5))
(cadr L) ;;car+cdr -> second
(caddr L)
(cadddr L)
(cadddr (cdr L))

(define LL '(1 (2 3 4) (5)))

(car (cadr LL))
(car (caddr LL))

;;Excercise 3

(define (getRange x y)
  (cond
    ((< y x) 'range_error)
    ((= x y) (cons x '()))
    (#t (cons x (range (+ x 1) y)))
    )
  )

;;Excercise 4 -- calculates the sum of square digits

(define (sosd x)
  (if (number? x)
      (if (>= x 10)
          (+ ((lambda (n) (* n n)) (modulo x 10)) (sosd (quotient x 10 )))
          (* x x))
      '()))

;;Excercise 5

(define (drop L K)
  (if (not (list? L)) 'List_error
      (drop-helper L K 1 ))
      )

(define (drop-helper L K S)
  (cond
    ((null? L) '())
    ((= S K) (drop-helper (cdr L) K 1))
    (#t (cons (car L) (drop-helper (cdr L) K (+ S 1))))
    )
  )