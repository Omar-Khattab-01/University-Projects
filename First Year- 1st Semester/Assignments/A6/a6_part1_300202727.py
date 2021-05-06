# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 6
# year 2020

import string

####################Helper Functions###########################
def is_valid_file_name():
    '''
    None->str or None
    Returns file name and
    checks if file exists and catches FileNotFoundError with printing
    a message asking the right file name 
    '''
    file_name = None
    try:
        file_name=input("Enter the name of the file: ").strip()
        f=open(file_name)
        f.close()
    except FileNotFoundError:
        print("There is no file with that name. Try again.")
        file_name=None
    return file_name

def remove_punctuation(words):
    '''
    str->str
    Removes all punctuations for the given string and returns it.
    of word(s) without punctuation
    '''
    words=words.translate(str.maketrans("","",string.punctuation))
    return words

def is_mt_2(words):
    '''
    str->str
    removes words of length less than 2 from the given string
    '''
    
    if len(words)!=1:
        words=words.split()
        words2=[]
        for w in words:
            words2.append(w)
        for i in words:
            if len(i)<2:
                words2.remove(i)
        words=" ".join(words2)
    return words
###############################################################

def open_file():
    '''None->file object
    prompt the user for a file-name, and try to open that file. If the file exists, it will return the file
    object; otherwise it will re-prompt until it can successfully open the file.
    See the assignment text for what this function should do'''
    # YOUR CODE GOES HERE
    file_name=None
    while file_name==None:
        file_name=is_valid_file_name()
    return file_name

    
def read_file(fp):
    '''(file object)->dict
    Reads the contents of that file line by line, process each word and store which
    line they occurred  and store them in a dictionary.  Gets rid of words of length less than 2.
    The dictionary is returned.
    See the assignment text for what this function should do'''
    # YOUR CODE GOES HERE
    file=open(fp).read().lower()
    file=remove_punctuation(file)
    file=file.splitlines()
    
    allwords={}
    counter=1
    for lines in file:
        newlist=lines.split()
        for word in newlist:
            if word in allwords:
                allwords[word].add(counter)
            elif word.isalpha()==True and len(word)>=2:
                allwords[word]={counter}

        counter+=1
    
    return allwords
            
        
def find_coexistance(D, query):
    '''(dict,str)->list
    Returns a list of number of lines that have all words given if
    a word(s) does not exist in the dictionary it returns a list
    of those words that does not exist in the dictionary instead.
    See the assignment text for what this function should do'''
    # YOUR CODE GOES HERE
    words=remove_punctuation(query)
    words=words.split()
    commonlines=[]
    notin=[]
    for i in words:
        try:
            commonlines.append(D[i])

        except KeyError:
            notin.append(i)
            
    
    
    if len(notin)!=0:
        return notin
    
    lines=[]
    m=0
    
    listed=list(commonlines[0])
    if len(commonlines)==1:
        return list(commonlines[0])
    else:
        
        for i in range(len(commonlines[0])):
            b=""
            for j in range(1,len(commonlines)):
                listed2=list(commonlines[j])
                if listed[i] in listed2:
                    pass
                else:
                    b=b+" "
            if b=="":
                lines.append(listed[i])

    return lines
                
    

         
    

##############################
# main
##############################
file=open_file()
d=read_file(file)

# YOUR CODE GOES HERE
query=""
while query!="q":
    try:
            
        query=input("Enter one or more words separated by spaces, or 'q' to quit: ").strip().lower()
        
        if query=="q":
            pass
        else:
            query=is_mt_2(query)
            coexis=sorted(find_coexistance(d, query))
            if type(coexis[0])!=int:
                print("word(s):",end="( ")
                for i in coexis:
                    print("'"+i+"'",end=" ")
                print(") not in the file.")
            else:
                print("The one or more words you entered coexisted in the following lines of the file:")
                for i in coexis:
                    print(i,end=" ")
                print("")
    except IndexError:
        if query.isalpha()==True:
            print("Word '"+query+"' not in the file.")
        else:
            print("Word '' not in the file.")
    

