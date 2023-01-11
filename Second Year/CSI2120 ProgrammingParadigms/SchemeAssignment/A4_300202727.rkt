#lang racket


;;Question 1
(define-syntax-rule (while condition body)
  (let loop ()
    (when condition
      body
      (loop))))
(define (lowest-exponent x y)
  (define counter 1)
  (while (< (expt x counter) y) (set! counter (add1 counter))
   )
  (displayln counter)
  )


;;Question 2
(define (find-abundant-helper x)
  (define res #t)
  (define sum 0)
  (for ([i (in-range 1 (+ x 1))])
    (cond
      [(= (modulo x i) 0)
       (set! sum (+ sum i))]
      )
    )
  
  (cond
    [(> sum (* 2 x)) (set! res #t)]
    [#t (set! res #f)]
  )
  res
  
)


(define (find-abundant x)
  (define result '())
  (for ([i (in-range 1 (+ 1 x))])
    (cond
      [(find-abundant-helper i) (set! result (cons i result))]
      )
    )
  
  (displayln result)
  )

;;Question 3
(define (make-string-list x)
  (define result '("finished"))
  (for ([i (in-range 1 (+ 1 x))])
    (cond
      [(= i 1) (set! result (cons (~a i " second") result))]
      [#t (set! result (cons (~a i " seconds") result))]
      )
    
    )
  
  result
  )

;;Tests
(define (test)
  (displayln "(lowest-exponent 3 27)")
  (lowest-exponent 3 27)
  (displayln "(lowest-exponent 3 28)")
  (lowest-exponent 3 28)
  (displayln "(find-abundant 25)")
  (find-abundant 27)
  (displayln "(make-string-list 3)")
  (make-string-list 3)
  )

(test)




