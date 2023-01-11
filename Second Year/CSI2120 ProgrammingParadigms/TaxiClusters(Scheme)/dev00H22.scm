#lang racket
(require racket/trace)


(define (readlist filename)
 (call-with-input-file filename
  (lambda (in)
    (read in))))

(define (import)
  (let ((p65 (readlist "partition65.scm"))
        (p74 (readlist "partition74.scm")) 
        (p75 (readlist "partition75.scm"))
        (p76 (readlist "partition76.scm"))
        (p84 (readlist "partition84.scm"))
        (p85 (readlist "partition85.scm"))
        (p86 (readlist "partition86.scm")))
    (append p65 p74 p75 p76 p84 p85 p86)
    )
  )

(define (saveList filename L)
  (call-with-output-file filename
    (lambda (out)
      (write L out))
    #:exists 'truncate))


(define (reverseSubList lst)
  (map (lambda (e)
       (if (list? e) (reverse e) e))
     lst)
  )


(define (sortBy1st lst)
  (define (lowerFirst? x y) (<= (car x) (car y)))
  (sort lst lowerFirst?)
  )


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (changeClusterId nId oldId revOutList)
  (match (assv oldId revOutList)
    [(list oid y x pt)
     (display "change point ") (display pt) (display " from ") (display oid)
               (display " to ") (display nId) (display "\n")
     (let ((revOutListNew (append (remove (list oid y x pt) revOutList) (list (list nId y x pt)))))
       (changeClusterId nId oldId revOutListNew))]
    [_ revOutList]
    )
  )

(define (findClusters nPtId tOutList)
  (match (assv nPtId tOutList)
    [(list ptid _ _ cid) cid]
    [_ #f]
    )
  )
                                   
(define (computeintersection inPoint tOutList)
  (match inPoint
    [(list ptid _ _ cid)
     (match (findClusters ptid tOutList)
       [#f (display "not found. add to ") (display cid) (display "\n")
           (let ((tOutListN (append tOutList (list inPoint))))
                tOutListN)] 
       [oldCid (display "found, change ") (display oldCid)
               (display " to ") (display cid) (display "\n")
               (if (eq? cid oldCid) tOutList
                   (let ((tOutListN (append (reverseSubList
                               (changeClusterId cid oldCid (reverseSubList tOutList))))))
                     tOutListN))]
     )] 
    [_ tOutList] 
    )
  )
                                
(define (addPoint inPoint tOutList)
  (match inPoint
    ['() tOutList]
    [pair (display "add point ") (display (car inPoint)) (display "\n")
          (let ((nTempOutList (computeintersection inPoint tOutList)))
            nTempOutList)]
    [_ #f]
    )
  )
   
(define (mergeClusters inList tOutList)
  (match inList
    ['() tOutList]
    [pair (let ((nTempOutList (addPoint (drop (car inList) 1) tOutList)))
            (mergeClusters (cdr inList) nTempOutList))]
    [_ #f]
    )
  )
          

(define (mainCode inList)
  (let ((outList
         (mergeClusters (reverseSubList (sortBy1st (reverseSubList inList))) null)))
    (sortBy1st outList))
  )
     

(define (runcode)
        (saveList "Output.scm" (mainCode (import)))
        (display "File saved!\n")
)


(runcode)
        