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

At any point during the game, you can press the following keys, to provide extra information;
* **Z** - Zooms out of map and displays all game characters as small dots.
* **E** - Displays some in game information relating to the Spartan Warrior in a *JOptionPane*
   * *Current Warrior Health*
   * *Current Weapon Count*
   * *Current Weapon (The one that will be used next)*
   

The warrior can pick up Weapons. 
Here's a break down of the type of weapons available and the damage they do;
* **FIST** - Default Weapon and Always available even when no other wepon in collection, Damage: 5
* **SWORD** - Damage: 15
* **BOMB** - Damage: 40
* **HYDROGENBOMB** - Damage: 75
* **QUESTION MARK** - Picks a random weapon consisting of Sword, Bomb or Hydrogen Bomb. Damage: Random number between 1-100

Each Spider has 500 health and the warrior has 1000 health. The game is over when the warrior's health is 0.

---

# Application Features

## Fuzzy Logic
Fuzzy Logic has been incorporated into this project by using the Fuzzy Logic API jar file. Each Spider (which are independently threaded), will move around the maze and if they come in contact with the Spartan warrior, ie they ocupy cells beside one another, then the Fuzzy Logic is triggered. The contents of the FCL file (Fuzzy Control Logic) will determine the outcome of the battle using fuzzy logic linguistic variables and ruleblocks. Once the outcome has been ascertained, the result will reduce the health of both the spider and the Spartan Warrior. The warrior has 1000 health and each spider has 500, with each altercation reducing their health by 250.

As the Fuzzy Logic outputs a Crisp Output, we needed to figure out what to do with this value. If the value was high, ie the result was favourable like for example 75, then the remaining amount of 25 would be the amount calculated as the damage taken by the warrior. 


## Neural Network
We have incorporated a 3 layer back propagation Neural Network into our project. The 3 layers have been caluclated using the following code excerpt, taken from the NeuralNetwork.java file;
```java
public void feedForward(){ 
		//Feed the inputs forward through the network as a weighted sum
		double sum = 0.0d;
		
		//Compute Input->Hidden Layer
		for (int hid = 0; hid < hidden.length; hid++){
			sum = 0.0d;
			for (int in = 0; in < inputs.length; in++) sum += inputs[in] * ihW[in][hid];
			sum+= ihW[inputs.length][hid];
			hidden[hid] = activator.activate(sum); //Apply activation function
		}
	
		//Compute Hidden->Output Layer
		for (int out = 0; out < outputs.length; out++){
			sum = 0.0d;
			for (int hid = 0; hid < hidden.length; hid++)	sum += hidden[hid] * hoW[hid][out];		
			sum+= hoW[hidden.length][out];
			outputs[out] = activator.activate(sum); //Apply activation function
		}
	}
```

The network will be trained using the following;

**data**
[Health, Anger, Strength, Defence]

**expected**
[Walk Away, Attack, Build Hedge, Run Away]

Once the network is trained each spider, will be able to make any of the above Expected desisions, when they come in contact with the Spartan Warrior. The Attack option will trigger the Fuzzy Logic.

## Threaded Characters
This project has used an Executor service to deal with threads, that allows each spider to navigate independently around the maze.  

## AI Search Algorithms (Document what we have tried)

---

# Deployment
To run this application, download, unzip and run the jar file using the following command;

```java -cp "./mazegame.jar;lib/*" ie.gmit.sw.game.GameRunner```
