package abstraction;

/**
 * Object of this type includes reference to an object of type Instruction
 *  after its settlement date is adjusted in implementation of  InstructionsReceiver
 *
 * Object of type StatisticsGenerator will operate only on InstructionWithSettlDateAdjusted type.
 * In this way is assured that statistics are generated on instructions with settlement date already
 * corrected.
 */
public interface InstructionWithSettlDateAdjusted {

    Instruction getInstruction();
}
