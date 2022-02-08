# TheVault
Project 3 - Banking application

## Project Descriptions

Revature Banking App

---

## Technologies Used
---

## Features


---

### Conventions

### Working on the Project

- Camel Case for names 
  - `variableName`


- Clear variable names, and clear commenting (but do not overcomment)
  - ~~a = 1~~
  - `number = 1`


- Tests for every component on completion
- 
>Follow TDD as much as reasonably possible, it is easier to make tests as you progress than all at the end

- Create a feature branch whenever you are working on something with your team

```
//To get the repository on your local machine use git clone with the url
$ git clone git@github.com:DementedTiger/TheVault.git
// or if ssh is not setup 
$ git clone https://github.com/DementedTiger/TheVault.git

// This will create a folder that is initialized with git and the branches that exist on the remote git

// Starting from main branch, first check if your branch already exists that you wish to work on with

$ git branch -a

// This will tell you your local branches and the remote branches
// If the branch you are creating does not exist, make a new branch with the below command

$ git branch newFeature

// Switch to that branch with either git switch or git checkout

$ git switch newFeature  || or || $ git checkout newFeature


// Now when you are working on this new feature branch, make sure to tell your team
// If for example you are working on login with your team and you wish to implement input, create a new feature branch of the login branch

// CURRENT BRANCH LOGIN
$ git branch loginInput
$ git switch loginInput

// Now this creates a copy of what your team has done on the login branch, do your work and then add and commit it to your local branch

$ git add .
$ git commit -m "message related to what you did"

// This saves it locally, now go to your shared team feature branch of login and merge what you did
$ git switch login

// First before merging your local changes, ensure no one else has done anything new on the branch by pulling from the remote branch
$ git pull origin login

// Deal with any new remote commits and conflicts that could arise in your IDE of choice
// Once this is complete git add and commit the merge with the remote
// Now merge in your feature branch of login feature branch while on the branch you wish to merge it into

// CURRENT BRANCH LOGIN
$ git merge loginInput

// Deal with any new conflicts if they arise like with the remote pull origin from login
// On success, push to the remote feature branch for your team to have access to it

$ git push origin login

// Delete your local branch once you are done with it.

$ git branch -d loginInput

```

- **NEVER** work on main or dev_branch directly


- Commit at the minimum daily
  - **Ensure** you have useful messages with them

---

## Contributors

