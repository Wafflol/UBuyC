### GIT WORKFLOW GUIDE
"[]" means optional if you know what you're doing

##### CREATING A NEW BRANCH
For every ticket, create a new branch off of main
```
git branch -b ticket-name [main]
```
Add it to remote
```
git add .
git commit
git push -u origin ticket-name
```

##### WORKING ON THE BRANCH
Commit whenever you have working code
```
git add .
git commit -m "message"
```

##### PUSHING AND REBASING
Once you are done and have tested that the code works
you can rebase from origin/main and push it to remote,
and make a pull request
```
git rebase origin/main
```
fix any merge conflicts
```
git rebase --continue
```
repeat previous step until finished, then
```
git push -f [ticket-name]
```
(you have to force push unless there were no merge conflicts 
due to the nature of rebasing)
