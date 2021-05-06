# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 4
# year 2020

def is_valid_file_name():
    '''()->str or None'''
    file_name = None
    try:
        file_name=input("Enter the name of the file: ").strip()
        f=open(file_name)
        f.close()
    except FileNotFoundError:
        print("There is no file with that name. Try again.")
        file_name=None
    return file_name 

def get_file_name():
    file_name=None
    while file_name==None:
        file_name=is_valid_file_name()
    return file_name

def clean_word(word):
    '''(str)->str
    Returns a new string which is lowercase version of the given word
    with special characters and digits removed

    The returned word should not have any of the following characters:
    ! . ? : , ' " - _ \ ( ) [ ] { } % 0 1 2 3 4 5 6 7 8 9 tab character and new-line character

    >>> clean_word("co-operate.")
    'cooperate'
    >>> clean_word("Anti-viral drug remdesivir has little to no effect on Covid patients' chances of survival, a study from the World Health Organization (WHO) has found.")
    'antiviral drug remdesivir has little to no effect on covid patients chances of survival a study from the world health organization who has found'
    >>> clean_word("1982")
    ''
    >>> clean_word("born_y1982_m08\n")
    'bornym'

    '''
    #YOUR CODE GOES HERE

    #lower case of the string
    clean_word=(word.lower())

    #creating an empty string
    clean_word2=""

    #checking if there is any special characters 
    for i in clean_word:
        #gettig rid of special characters
        if i in"!.?:,\'\"-_\\()[]{}%0123456789\n\t":
            clean_word2=clean_word2+""
        else:
            #adding clean words to the created variable 
            clean_word2=clean_word2+i
            
    return clean_word2


def test_letters(w1, w2):
    '''(str,str)->bool
    Given two strings w1 and w2 representing two words,
    the function returns True if w1 and w2 have exactlly the same letters,
    and False otherwise

    >>> test_letters("listen", "enlist")
    True
    >>> test_letters("eekn", "knee")
    True
    >>> test_letters("teen", "need")
    False
    '''

    #calling clean_word() to get rid of any special characters in both words
    
    w1=clean_word(w1)
    w2=clean_word(w2)

    #returning False if w1 and w2 do not have the same length 
    if len(w1)!=len(w2):
        return False

    flag = True
    while flag:
        #looping over characters of w1
        for i in w1:
            for j in w2:
                #checking if characters in w1 in w2 and vice versa
                if i in w2 and j in w1 :
                    pass
                #returning False if i not in w2
                else:
                    return False

        flag= False

    return True
    
    #YOUR CODE GOES HERE
    pass
    
def create_clean_sorted_nodupicates_list(s):
    '''(str)->list of str
    Given a string s representing a text, the function returns the list of words with the following properties:
    - each word in the list is cleaned-up (no special characters nor numbers)
    - there are no duplicated words in the list, and
    - the list is sorted lexicographicaly (you can use python's .sort() list method or sorted() function.)

    This function must call clean_word function.

    You may find it helpful to first call s.split() to get a list version of s split on white space.
    
    >>> create_clean_sorted_nodupicates_list('able "acre bale beyond" binary boat brainy care cat cater crate lawn\nlist race react cat sheet silt slit trace boat cat crate.\n')
    ['able', 'acre', 'bale', 'beyond', 'binary', 'boat', 'brainy', 'care', 'cat', 'cater', 'crate', 'lawn', 'list', 'race', 'react', 'sheet', 'silt', 'slit', 'trace']

    >>> create_clean_sorted_nodupicates_list('Across Europe, infection rates are rising, with Russia reporting a record 14,321 daily cases on Wednesday and a further 239 deaths.')
    ['', 'a', 'across', 'and', 'are', 'cases', 'daily', 'deaths', 'europe', 'further', 'infection', 'on', 'rates', 'record', 'reporting', 'rising', 'russia', 'wednesday', 'with']
    '''
    
    #YOUR CODE GOES HERE

    #using split method to creat a list of s 
    s=s.split()

    #creating an empty list
    clean_list=[]

    #looping over items of s 
    for i in s:
        #making sure all items are in lower case
        #and that it does not contain any special
        #characters by calling the function
        #clean_word() and appending the return value
        #to clean_list
        clean_list.append(clean_word(i))
    #looping over elements of clean_list
    for i in clean_list:
        #checking if there is any duplicates
        #and removing if any found
        if clean_list.count(i)!=1:
            index=clean_list.index(i)
            clean_list.remove(clean_list[index])
    #using sort method to sort the list lexicographicaly
    clean_list.sort()
    
    return clean_list
                

def word_anagrams(word, wordbook):
    '''(str, list of str) -> list of str
    - a string (representing a word)
    - wordbook is a list of words (with no words duplicated)

    This function should call test_letters function.

    The function returs a (lexicographicaly sorted) list of anagrams of the given word in wordbook
    >>> word_anagrams("listen", wordbook)
    ['enlist', 'silent', 'tinsel']
    >>> word_anagrams("race", wordbook)
    ['acre', 'care']
    >>> word_anagrams("care", wordbook)
    ['acre', 'race']
    >>> word_anagrams("year", wordbook)
    []
    >>> word_anagrams("ear", wordbook)
    ['are', 'era']
    '''
       
    #YOUR CODE GOES HERE

    #creating an empty string
    anagrams=""

    #looping over elements of wordbook
    for i in wordbook:
        #calling test_letters() and checking which words in wordbook
        #have the same letters and length as in word
        #if true and if clean versions of word is not a duplicat in a clean version of word in wordbook (i.e. wordbook[i])
        #will be added to the string anagrams
        if test_letters(word, i)==True and clean_word(word)!=clean_word(i):
            anagrams=anagrams+" "+ i
        

            
    #calling create_clean_sorted_nodupicates_list() to create the list of string 
    anagrams=create_clean_sorted_nodupicates_list(anagrams)

    return anagrams
        
        

def count_anagrams(l, wordbook):
    '''(list of str, list of str) -> list of int

    - l is a list of words (with no words duplicated)
    - wordbook is another list of words (with no words duplicated)

    The function returns a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.
    
    Whenever a word in l is the same as a word in wordbook, that is not counted.

    >>> count_anagrams(["listen","care", "item", "year", "race", "ear"], wordbook)
    [3, 2, 3, 0, 2, 2]

    The above means that "listen" has 3 anagrams in wordbook, that "care" has 2 anagrams in wordbook ...
    Note that wordbook has "care", "race" and "acre" which are all anagrams of each other.
    When we count anagrams of "care" we count "race" and "acre" but not "care" itself.
    '''
    
    #YOUR CODE GOES HERE

    #creating an empty list contain integers
    #representing number of anagrams 
    counter=[]

    #looping over elements of l
    for i in l:
        #calling word_anagram() and checking if return value is not a null(empty) list
        #and adding word to  counter
        #if returns an empty list (0) will be added to counter
        if word_anagrams(i, wordbook)!=[]:
            counter.append(len(word_anagrams(i, wordbook)))
        else:
            counter.append(0)
    return counter
            



def k_anagram(l, anagcount, k):
    '''(list of str, list of int, int) -> list of str

    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordcbook of the i-th word in l.

    The function returns a  (lexicographicaly sorted) list of all the words
    in l that have exactlly k anagrams (in wordbook as recorded in anagcount)

    k_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2], 2)
    ['care', 'ear', 'race']
    '''

    
    #YOUR CODE GOES HERE

    #creating empty list
    k_anagrams=[]

    #creating an accumulator
    index=0

    #looping over integers of anagcount
    for i in anagcount:

        #checking if integer is equal to k
        #and adding word of index i in list(l) to the created list 
        if i==k:
            k_anagrams.append(l[index])
        #accumulating 
        index=index+1
        
    #lexicographicaly sorting 
    k_anagrams.sort()
    
    return k_anagrams
    
    

def max_anagram(l, anagcount):
    '''(list of str, list of int) -> list of str
    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.

    The function returns a (lexicographicaly sorted) list of all the words
    in l with maximum number of anagrams (in wordbook as recorded in anagcount)
    
    >>> max_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2])
    ['item', 'listen']
    '''
    
    #YOUR CODE GOES HERE

    #calling k_anagram() and replacing the k with the maximum of anagcount
    #to get all words in l with maximum number of anagrams   
    return k_anagram(l,anagcount, max(anagcount))
            
        

def zero_anagram(l, anagcount):
    '''(list of str, list of int) -> list of str
    - l is a list of words (with no words duplicated)
    - anagcount is a list of integers where i-th integer in the list
    represents the number of anagrams in wordbook of the i-th word in l.

    The function returns a (lexicographicaly sorted) list of all the words
    in l with no anagrams
    (in wordbook as recorded in anagcount)
    
    >>> zero_anagram(["listen","care", "item", "year", "race", "ear"], [3, 2, 3, 0, 2, 2])
    ['year']
    '''

    #YOUR CODE GOES HERE

    #calling k_anagram() and replacing k by zero to get
    #to get all words in l with zero anagrams
    return k_anagram(l, anagcount, 0)

    
            



                
    
##############################
# main
##############################
wordbook=open("english_wordbook.txt").read().lower().split()
list(set(wordbook)).sort()

print("Would you like to:")
print("1. Analize anagrams in a text -- given in a file")
print("2. Get small help for Scrabble game")
print("Enter any character other than 1 or 2 to exit: ")
#asking for users choice
choice=input()

#Checking if choice is either 1 or 2

#choice 1 is for analyzing anagrams in a text
#that later will be asked for in a form of
#txt file
if choice=='1':
    file_name=get_file_name()
    rawtx = open(file_name).read()
    l=create_clean_sorted_nodupicates_list(rawtx)
    anagcount = count_anagrams(l,wordbook)
    #printing words that has most anagrams
    print("\nOf all the words in your file, the following words have the most anagrams:")

    # YOUR CODE GOES HERE
    # when asking for k from the user you may assume that they will enter non-negative integer

    #calling max_anagram() to check which have the most anagrams
    #and print the returned list
    max_anagrams=max_anagram(l, anagcount)
    print (max_anagrams)

    #printing all the anagrams for the words that have most anagrams
    #by looping over length of list consisting words that has the most
    #anagrams
    print("\nHere are their anagrams:")
    for i in range(len(max_anagrams)):
        #using end in printing so that all string and list be printed in one line
        print("Anagrams of" ,end=" ")
        print(max_anagrams[i], end=" ")
        print("are: ", end="")
        print(word_anagrams(max_anagrams[i], wordbook))

    #calling zer_anagram() to check which words have no anagrams
    #and printing list of those words
    print("\nHere are the words from your file that have no anagrams:")
    print(zero_anagram(l, anagcount),end="\n")

    #asking user to under an integer that indicates the number of anagrams a word in their
    #given txt file have
    print("\nSay you are interested if there is a word in your file that has excatly k anagrams")
    k=int(input("Enter an integer for k: "))

    #assiging the returned list to a variable
    k_anagrams=k_anagram(l, anagcount, k)

    #checking if list returned is empty
    #which idicates that no words have k-number of anagrams
    if k_anagrams==[]:
        print("There are no words in your file with excatly "+str(k)+" anagrams")
    #returning all words with k-anagrams in a list
    #and printing it 
    else:
        
        print("Here is a word(words) in your file with excatly "+str(k)+" anagrams: ")
        print(k_anagrams)
    
    
#choice 2 is for a scrabble game   
elif choice=='2':

    #YOUR CODE GOES HERE
    
    #setting two variables to True
    #to use them to stop
    #two while-loops that will be exchuted
    flag=True
    flag2=flag
    #first while-loop
    #while flag is equal True
    while flag:
        #asking for the scrabbled letters from the user 
        word=input("Enter the letters that you have, one after another with no space: ")
        #checking if given string does not have any spaces
        #and pass if true to ask again for a proper input
        if word!=''.join(word.split()):
            print("Error: you entered space(s).")
            pass
        #if given input does not have any spaces
        else:
            #creating a list of a the given scrabbled letters
            #and assigning it a variable
            word2=list(word)

            #second while-loop
            #while flag2 is equal True
            while flag2:
                #printing a message asking for either 1 or 2 as an input 
                print("Would you like help forming a word with:")
                print("1. all these letters")
                print("2. all but one of these letters? ")
                choice=input()

                #checking if choice is 1 or 2
                #other than that a message will be printed
                #asking for an input of 1 or 2 only
                if choice !=str(1) and choice!=str(2):
                    print("\nYou must choose 1 or 2. Please try again.")

                #if choice is 1 
                elif choice==str(1):
                    #checking if letters have any anagrams
                    #by calling word_anagram()

                    #if return value is equal to []
                    #a message will be displayed saying that there is
                    #word comprised of those letters
                    if  word_anagrams(word, wordbook)==[]:
                        print("\nThere is no word comprised of excatly these letters.")
                        #changing values of flag and flag2 to break out of both while-loops
                        flag=False
                        flag2=flag
                    #else means retrun value is a list of word(s)
                    else:
                        
                        print("\nHere are words comprised of excatly these letters:")
                        #checking if letters as a word is an actual word in English
                        #to add it the list of words as sometimes people can get lucky
                        #by simply getting scrabbled letters organized
                        if word in wordbook:
                            word_anagram=word_anagrams(word, wordbook)
                            word_anagram.append(word)
                            print(word_anagram)
                        #and if they are not lucky only the returned list will be printed
                        else:
                            print(word_anagrams(word,wordbook))
                        #checking if letters as a word is an actual word in English
                        flag=False
                        flag2=flag
                #if choice is 2 
                elif choice==str(2):
                    
                    print("\nThe letters you gave us are: "+word+"\nLet\'s see what we can get if we ommit one of these letters.\n")
                    #looping over the length of given letters
                    for i in range(len(word2)):
                        #omitting letters by removing i from the lisr word2
                        #and joining the rest of the letters by adding them
                        #to an empty string using join method
                        word2=list(word)
                        word2.pop(i)
                        word2=''.join(word2)
                        
                        print("Without the letter in position "+str(i+1)+" we have letters "+word2)
                        #checking if the rest of the letters have an anagram in English
                        #by calling word_anagrams()
                        #and printing returned list as is
                        if word_anagrams(word2,wordbook)!=[]:
                            print("Here are the words that are comprised of letters: "+word2)
                            print(word_anagrams(word2,wordbook))
                            print("\n")
                        #else means there are no anagrams
                        else:
                            print("There is no word comprised of letters: "+word2+"\n")
                    #checking if letters as a word is an actual word in English
                    flag=False
                    flag2=flag

            
                                         
else:
    print("Good bye")


