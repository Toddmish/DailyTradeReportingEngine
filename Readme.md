# Project overview

This project is aimed to print out to console ,for a set of instructions sent by clients, statistics like:

     - daily incoming amount in USD
     - daily outgoing amount in USD
     - entity ranking based on incoming amount
     - entity ranking based on outgoing amount

The class which implements the generation of statistics is found at DailyTradeReportingEngine/src/main/java/implementation/**StatisticsGeneratorImpl.java**

The entry point of the application is the Main class found at /DailyTradeReportingEngine/src/main/java/**Main.java**.

This software was developed using SOLID principles and following TDD process.
In addition, in archived files I needed to use Template method pattern.

## Note on Rankings tasks:

Task spec asks only for rankings not for amounts, but If one wants to see amount next to ranks,
please uncomment commented parts in composeReportOnEntityRanking() method found at
DailyTradeReportingEngine/src/main/java/implementation/ReportPrinterImpl.java

## Note on archive folder:

The Archive folder contains an alternative implementation of InstructionReceiver
which when receiving a new instruction adjusts its settlement date and then creates
a new object of InstructionWithSettlDateAdjusted type which contains a reference to
already adjusted instructions. And it is exactly the object of InstructionWithSettlDateAdjusted type
which is added in the collection of received instructions.


Object of type StatisticsGenerator will operate only on InstructionWithSettlDateAdjusted type.
In this way is assured that statistics are generated on instructions with settlement date already
corrected.

Template method pattern is used to assure that method which adjusts settlement date is
called before method which adds instruction of type InstructionWithSettlDateAdjusted is called.