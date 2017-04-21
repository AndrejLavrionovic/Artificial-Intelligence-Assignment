# Artificial Intelligence Assignment

- **Student Names:** Will Hogan & Andrej Lavrinovic
- **Student Number:** G00318460, G00196984
- **College Name:** GMIT
- **Course:** Software Development
- **Module:** Artifical Intelligence
- **Lecturer:** John Healy
- **Current College Year:** 4rd Year 
- **Project Title:** Controlling Game Characters with Neural Networks and Fuzzy Logic

---

# Assignment Description

### Summary
Our game has been created as part of the Artifical Intelligence module, which is a 4th year project in Software Development in GMIT.
The Application is a maze game in which you control a Spartan Warrior by moving various arrow keys around a maze. 
The warrior needs to navigate around the maze and either avoid or engage various coloured spiders that are moving around the maze. The warrior can also pick up various Weapons. 

### Purpose
The purpose of this application is to demonstrate various Artificial Intelligence components such as Fuzzy Logic and Neural Networks and how they work within the game. 

### Game Play Instructions
To play the game, you use the arrow keys to move the Spartan Warrior around the maze.

Here's the controls;
* **Key-up** - Move Up
* **Key-down** - Move down
* **Key-left** - Move left
* **Key-right** - Move right

The warrior can pick up Weapons. 
Here's a break down of te type of weapons available and what the damage they do;
* **FIST** - Default Weapon and Always available even when no other wepon in collection, Damage: 5
* **SWORD** - Damage: 15
* **BOMB** - Damage: 40
* **HYDROGENBOMB** - Damage: 75
* **QUESTION MARK** - Picks a random weapon consisting of Sword, Bomb or Hydrogen Bomb. Damage: Random number between 1-100
---

# Application Features

## Fuzzy Logic
Fuzzy Logic has been incorporated into this project by using the Fuzzy Logic API jar file. Each Spider (which are independently threaded), will move around the maze and if they come in contact with the Spartan warrior, ie they are both in the same cell simultaneously, then the Fuzzy Logic is triggered. The contents of the FCL file (Fuzzy Control Logic) will determine the outcome of the battle using fuzzy logic liguistic variables and ruleblocks. Once the outcome has been ascertained, the result will reduce the health of both the spider and the Spartan Warrior. 

As the Fuzzy Logic outputs a Crisp Output, we needed to figure out what to do with this value. 


## Neural Network

## Threaded Characters

## AI Search Algorithms (Document what we have tried)

---

# Deployment

