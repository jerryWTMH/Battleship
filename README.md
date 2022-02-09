# ece651-sp22-ch450-battleship

BattleShip is a command-line based Java game.
By Choosing one of the modes(Human V.S. Human, Human V.S. Computer, Computer V.S. Computer), users can play the BattleShip game.

When users get into the game, he/she can place different types of ships: Submarine, Destroyer, Battleship, Carrier.

And the Placement format would be like: 
```
**A0H** -> place ship at **A0** with the orientation of **Horizontal**
**B3U** -> place ship at **B3** with the orientation of **Up**
```

Additionally, Submarine and Destroyer only has the orientaion of **H**, **V**. (Horizontal, Vertical)
Battleship and Carrier only has the orientations of **U**, **D**, **R**, **L**. (Up, Down, Right, Left)

After placing ten ships, we can start the game.

There are three actions for the user to use: **F**, **M**, **S**. Those represent Fire enemy ship, Move my ship, and Sonar enemy ship.
Users can use infinite times for firing, three times for moving and sonar.

After firing whole the ships of enemy, the user will win!

