# 10PinBowling
Project to calculate score of 10 pin bowling from a text file.

This project was develop using Java(JDK 1.8.0_271), Maven(3.6.3) and Springboot(2.4.3)



To run the project use the command bellow in the root directory
```
$ mvn spring-boot:run
```

This project has 5 txt files with some scenarios

fouls.tx -> The player miss all shoots
perfect.tx -> The player strikes all shoots
valid.tx -> Has a valid match with 2 players
almost.tx -> Has a invalid match missing some shoots
empty.tx -> Has a invalid file with no records