Bubble Sort Program in Python
This program sorts a list using bubble sort technique. The question is, write a program in Python to apply bubble sort technique to sort a list. The answer to this question is given below:

nums = []
print("Enter the Size of List: ")
tot = int(input())

print("Enter " + str(tot) + " Numbers: ")
for i in range(tot):
    nums.insert(i, int(input()))

for i in range(tot-1):
    for j in range(tot-i-1):
        if nums[j]>nums[j+1]:
            temp = nums[j]
            nums[j] = nums[j+1]
            nums[j+1] = temp

print("The Sorted List is:")
for i in range(tot):
    print(nums[i])
    Here is its sample run:

bubble sort python
Now supply the size of list say 6 and then enter any six numbers say 60, 10, 50, 20, 40, 30, press ENTER key to sort the list using bubble sort technique as shown in the sample run given below:
    
    This program sorts a list using bubble sort technique. The question is, write a program in Python to apply bubble sort technique to sort a list. The answer to this question is given below:

nums = []
print("Enter the Size of List: ")
tot = int(input())

print("Enter " + str(tot) + " Numbers: ")
for i in range(tot):
    nums.insert(i, int(input()))

for i in range(tot-1):
    for j in range(tot-i-1):
        if nums[j]>nums[j+1]:
            temp = nums[j]
            nums[j] = nums[j+1]
            nums[j+1] = temp

print("The Sorted List is:")
for i in range(tot):
    print(nums[i])
