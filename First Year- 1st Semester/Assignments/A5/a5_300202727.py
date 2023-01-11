# Family name: Khattab 
# Student number:  300202727
# Course: IT1 1120 
# Assignment Number 5
# year 2020

import random

####################Helper Functions###########################
def sorted_nodupicates_list(s,usernumbers):
    '''
    (list,int)->list
    returns users id's sorted
    '''
    i=0
    allusers=[]

    while len(allusers)<usernumbers:
        if (int(s[i][0]) not in allusers):
            
            allusers.append(int(s[i][0]))
           
        if  (int(s[i][1]) not in allusers):
           
            allusers.append(int(s[i][1]))
            
        i+=1
        
    q=sorted(allusers)
    
    return( q)

def binary_search(L,v):
     '''
     (list,object)->int

     Returns an index of v in L
     or returns -1, if v not in L

     Precondition: L is sorted
     and the elements of L are pairwise comparable
     '''
     
     low = 0
     high = len(L)- 1

     while low <= high:
          mid = (low + high) // 2
          if v < L[mid][0]:
               high = mid - 1
          elif v == L[mid][0]:
               return mid
          else:
               low = mid + 1

     return - 1

def binary_search_v2(L,v):
     '''
     (list,object)->int

     Returns an index of v in L
     or returns -1, if v not in L

     Precondition: L is sorted
     and the elements of L are pairwise comparable
     '''
     low = 0
     high = len(L)- 1

     while low <= high:
          mid = (low + high) // 2
          if v < L[mid]:
               high = mid - 1
          elif v == L[mid]:
               return mid
          else:
               low = mid + 1

     return - 1

def Binary_search_v3(L, element,n ):
    '''
     (list,object,int)->int

     Returns an index of v in L
     or returns -1, if v not in L

     Precondition: L is sorted
     and the elements of L are pairwise comparable
     '''

    # The index of first occurrence of x
    i = first_occurrence(L, 0, n-1, element, n)

    # If x not found 
    if i == -1: 
        return i 

    #The index of last occurrence 
    j = last_occurrence(L, i, n-1, element, n);

    # return number of occurrence
    return j-i+1

def first_occurrence(L, low, high, element, n):
    '''
     (list,int,int,object,int)->int

     Returns an index of first occurance of an element in L
     or returns -1, if v not in L

     Precondition: L is sorted
     and the elements of L are pairwise comparable
     '''
    if high >= low: 

        
        mid = (low + high)//2

    if (mid == 0 or element > L[mid-1]) and L[mid] == element: 
        return mid 
    elif element > L[mid]:
        #look to the right
        return first_occurrence(L, (mid + 1), high, element, n) 
    else:
        #look to the left
        return first_occurrence(L, low, (mid -1), element, n) 
    return -1

def last_occurrence(L, low, high, element, n):
    '''
     (list,int,int,object,int)->int

     Returns an index of last occurance of an element in L
     or returns -1, if v not in L

     Precondition: L is sorted
     and the elements of L are pairwise comparable
     '''
    if high >= low: 

        
        mid = (low + high)//2;

    if(mid == n-1 or element < L[mid+1]) and L[mid] == element : 
        return mid 
    elif element < L[mid]:
        #look to the the left
        return last_occurrence(L, low, (mid -1), element, n)
    else:
         
        #look to the right
        return last_occurrence(L, (mid + 1), high, element, n)
    return -1



###############################################################
        
def create_network(file_name):
    '''(str)->list of tuples where each tuple has 2 elements the first is int and the second is list of int

    Precondition: file_name has data on social netowrk. In particular:
    The first line in the file contains the number of users in the social network
    Each line that follows has two numbers. The first is a user ID (int) in the social network,
    the second is the ID of his/her friend.
    The friendship is only listed once with the user ID always being smaller than friend ID.
    For example, if 7 and 50 are friends there is a line in the file with 7 50 entry, but there is line 50 7.
    There is no user without a friend
    Users sorted by ID, friends of each user are sorted by ID
    Returns the 2D list representing the frendship nework as described above
    where the network is sorted by the ID and each list of int (in a tuple) is sorted (i.e. each list of friens is sorted).
    '''
    friends = open(file_name).read().splitlines()
    network=[]

    # YOUR CODE GOES HERE

    #getting number of users
    #and removig it from the list of users
    nusers=int(friends.pop(0))
    
    size=len(friends)
    users=[]
    
    for i in range(size):
        friends[i]=friends[i].split(" ")
    
    
    
    usersid=sorted_nodupicates_list(friends,nusers)
    
    for i in usersid:
        network.append((int(i),[]))
    
            
   
    i=0#friend index
    v=0#network index
    z=0#flag for while
    while z!=len(friends):
        v=usersid.index(int(friends[i][0]))
        network[v][1].append(int(friends[i][1]))
        v=usersid.index(int(friends[i][1]))
        network[v][1].append(int(friends[i][0]))
        i+=1
        z+=1
    
    return network


def getCommonFriends(user1, user2, network):
    '''(int, int, 2D list) ->list
    Precondition: user1 and user2 IDs in the network. 2D list sorted by the IDs, 
    and friends of user 1 and user 2 sorted 
    Given a 2D-list for friendship network, returns the sorted list of common friends of user1 and user2
    '''
    common=[]
    # YOUR CODE GOES HERE
    
    index1=binary_search(network,user1)
    index2=binary_search(network,user2)

    
    for i in (network[index1][1]):
        x=binary_search_v2(network[index2][1],i)
        if x!=-1:
            common.append(int(i))
    return common

def getCommonFriends_v2(user, user1, user2, network):
    '''(int, int, 2D list) ->list
    Precondition: user1 and user2 IDs in the network. 2D list sorted by the IDs, 
    and friends of user 1 and user 2 sorted 
    Given a 2D-list for friendship network, returns the sorted list of common friends of user1 and user2
    '''
    common=[]
    # YOUR CODE GOES HERE

    index=binary_search(network,user)
    index1=binary_search(network,user1)
    index2=binary_search(network,user2)

    
    for i in (network[index1][1]):
        x=binary_search_v2(network[index2][1],i)
        if (x!=-1) and (i!=user) and (i not in network[index][1]):
            common.append(int(i))
    return common
    
def recommend(user, network):
    '''(int, 2Dlist)->int or None
    Given a 2D-list for friendship network, returns None if there is no other person
    who has at least one neighbour in common with the given user and who the user does
    not know already.
    
    Otherwise it returns the ID of the recommended friend. A recommended friend is a person
    you are not already friends with and with whom you have the most friends in common in the whole network.
    If there is more than one person with whom you have the maximum number of friends in common
    return the one with the smallest ID. '''

    # YOUR CODE GOES HERE
    user1index=binary_search(network,user)
    allcommon=[]
    n=len(network[user1index][1])-1
    n1=0
    n2=n
    start=0
    if n!=0:
        
        while n2>=1:
            for i in range(n):
                
                allcommon.append(getCommonFriends(network[user1index][1][start], network[user1index][1][n1+1], network))
                n1+=1
            start+=1
            n1=start
            n2-=1
            n=n2

        
        
        common=[]
        
        
        for i in allcommon:
            for x in i:
                if (binary_search_v2(network[user1index][1],x)==-1) and (x!= user):
                    common.append(x)
                elif len(allcommon)==1:
                    common.append(x)
   

    else:
        user2index=binary_search(network,network[user1index][1][0])
        common=network[user2index][1]
        if (len(common)==1):
            pass
        else:
            common.remove(user)
    
    
    common.sort()


    incommon=-1
    if common==[]:
        pass
    else:
        maximum=0
        incommon=0
        j=0
        for i in common:
            newmax=Binary_search_v3(common,i ,len(common))
            if newmax>maximum:
                maximum=newmax
                incommon=common[j]

            elif newmax==maximum:
                if incommon>common[j]:
                    incommon=common[j]
            
            j+=1

    
    
          
     
    
    if incommon==-1 or incommon==user:
        return
    else:
        
        return incommon
        


def k_or_more_friends(network, k):
    '''(2Dlist,int)->int
    Given a 2D-list for friendship network and non-negative integer k,
    returns the number of users who have at least k friends in the network
    Precondition: k is non-negative'''
    # YOUR CODE GOES HERE

    kfriends=0
    
    for i in range(len(network)):
        if len(network[i][1])>=k:
            kfriends+=1
    return kfriends
 

def maximum_num_friends(network):
    '''(2Dlist)->int
    Given a 2D-list for friendship network,
    returns the maximum number of friends any user in the network has.
    '''
    # YOUR CODE GOES HERE
    maximum=len(network[0][1])
    for i in range(len(network)):
        if len(network[i][1])>=maximum:
            maximum=len(network[i][1])
    return maximum
    

def people_with_most_friends(network):
    '''(2Dlist)->1D list
    Given a 2D-list for friendship network, returns a list of people (IDs) who have the most friends in network.'''
    max_friends=[]
    # YOUR CODE GOES HERE
    maximum=maximum_num_friends(network)

    for i in range(len(network)):
        if len(network[i][1])==maximum:
            max_friends.append(network[i][0])
    return    max_friends


def average_num_friends(network):
    '''(2Dlist)->number
    Returns an average number of friends overs all users in the network'''

    # YOUR CODE GOES HERE
    sumofusers=0

    for i in range(len(network)):
        sumofusers=sumofusers+len(network[i][1])

    averagesum=(sumofusers)/len(network)

    return averagesum
    

def knows_everyone(network):
    '''(2Dlist)->bool
    Given a 2D-list for friendship network,
    returns True if there is a user in the network who knows everyone
    and False otherwise'''
    
    # YOUR CODE GOES HERE

    for i in range(len(network)):
        if len(network[i][1])==(len(network)-1):
            return True
        return False


####### CHATTING WITH USER CODE:

def is_valid_file_name():
    '''None->str or None'''
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
    '''()->str
    Keeps on asking for a file name that exists in the current folder,
    until it succeeds in getting a valid file name.
    Once it succeeds, it returns a string containing that file name'''
    file_name=None
    while file_name==None:
        file_name=is_valid_file_name()
    return file_name


def get_uid(network):
    '''(2Dlist)->int
    Keeps on asking for a user ID that exists in the network
    until it succeeds. Then it returns it'''
    
    # YOUR CODE GOES HERE
    Flag=-1
    j=-1
    while Flag==-1:
        
        try:
            userid=int((input("Enter an integer for a user ID:")))
                
            for i in range(len(network)):
                if (network[i][0]!=userid) or (userid<0):
                    pass
                else:
                    Flag=0
                    j=0
            if j==-1:
                print("That user ID does not exist. Try again.")
        except ValueError :
            print("That was not an integer. Please try again.")

        
    return userid
            
##############################
# main
##############################

# NOTHING FOLLOWING THIS LINE CAN BE REMOVED or MODIFIED

file_name=get_file_name()
    
net=create_network(file_name)

print("\nFirst general statistics about the social network:\n")

print("This social network has", len(net), "people/users.")
print("In this social network the maximum number of friends that any one person has is "+str(maximum_num_friends(net))+".")
print("The average number of friends is "+str(average_num_friends(net))+".")
mf=people_with_most_friends(net)
print("There are", len(mf), "people with "+str(maximum_num_friends(net))+" friends and here are their IDs:", end=" ")
for item in mf:
    print(item, end=" ")

print("\n\nI now pick a number at random.", end=" ")
k=random.randint(0,len(net)//4)
print("\nThat number is: "+str(k)+". Let's see how many people has that many friends.")
print("There is", k_or_more_friends(net,k), "people with", k, "or more friends")

if knows_everyone(net):
    print("\nThere at least one person that knows everyone.")
else:
    print("\nThere is nobody that knows everyone.")

print("\nWe are now ready to recommend a friend for a user you specify.")
uid=get_uid(net)
rec=recommend(uid, net)
if rec==None:
    print("We have nobody to recommend for user with ID", uid, "since he/she is dominating in their connected component")
else:
    print("For user with ID", uid,"we recommend the user with ID",rec)
    print("That is because users", uid, "and",rec, "have", len(getCommonFriends(uid,rec,net)), "common friends and")
    print("user", uid, "does not have more common friends with anyone else.")
        

print("\nFinally, you showed interest in knowing common friends of some pairs of users.")
print("About 1st user ...")
uid1=get_uid(net)
print("About 2st user ...")
uid2=get_uid(net)
print("Here is the list of common friends of", uid1, "and", uid2)
common=getCommonFriends(uid1,uid2,net)
for item in common:
    print(item, end=" ")

    
