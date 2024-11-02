list = None
sum = 0
with open("Requirements.md", mode = 'r') as reqs:
    list = reqs.readlines()

for x in list: 
    # print(x[0:6])
    if x[0:6].strip() == "-[]" or x[0:6].strip() == "-[x]":
        print("asdf")
        sum += 1

print(sum)
