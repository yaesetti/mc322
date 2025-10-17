# MC322
## U-Energy | Online RPG
A super-hero themed Online RPG in Java<br>
<br>
Based on the TTRPG made by João Marcio (Tell)

## Authors:
Victor Yaegashi Setti  - RA: 206362<br>
Iago Lucini da Silva   - RA: 281244<br>

## Run Script
Use the script to easily compile and run the RPG<br>
```console
foo@bar:~/tarefaX$ ./run.sh
```

## Disclaimer for Task 4
Even tough the Task Description actively discourage unnecessary keyboard interactions, most outputs made by the program currently require the user to press `Enter` to continue, in order to improve readability and overall aesthetics.

## Disclaimer for Task 5
Documentation is temporally implemented in Brazilian Portuguese. Will be changed later on.
Getters and Setters are intentionally not documented because are too simple.

## Disclaimer for Task 6
The technology used to save and load different Game Saves was the Java Serializable interface, not the JAXB library. Thus, the Game Saves are made in `.bin` files and not in `.xml` files, and they are all under `tarefaX/src/saves`.

The class `Battle` is only used to store the relevant data to the Game Save. The logic used to run the game is still in `GameManager` in order to maintain consistency.

Even though some methods required in the Task Description are not implemented, their functions exists and are working well, for example: `executeNextLevel()` required in Topic 3.2 does not exist, but when a Battle is loaded, the Post Combat Menu appears and the next level can be executed.

Drops are following the principle of aggregation by using a `LootFactory` class, not by using `Sword.class.getName()` like suggested in the Task Description.

## Current Classes
Hero Classes: Mutant, Specialist<br>
<br>
Monster Classes: TwistedMutant, ThugGang<br>
<br>
Weapon Classes: Sword, Gauntlets, Pistol, Fists<br>
<br>
Combat Actions Classes: Attack, Heal->Self Heal, UseSpecialSkill<br>
<br>
Scenarios: Downtown, Forest, Ice Cave<br>
<br>
Event Classes: Ambush, Nature Blessing, Freezing<br>
<br>
Exception Classes: CharacterKnocked, InsufficientCharacterLevel, InsufficientWillPoints<br>
<br>
Utilities Classes: Dice<br>
<br>
