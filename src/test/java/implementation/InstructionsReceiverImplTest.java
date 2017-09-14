package implementation;


import abstraction.GlobalWorkingWeekSystem;
import abstraction.Instruction;
import abstraction.InstructionsReceiver;
import abstraction.OperationType;
import instructionsSamples.implementation.*;
import instructionsSamples.abstraction.InstructionsSample;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;


/**
 * The InstructionsReceiverImplTest class aim is to test if instructions are received and stored correctly in
 * the set which keeps all received instructions.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 * */

public class InstructionsReceiverImplTest {

    InstructionsReceiver instructionsReceiverImpl;
    InstructionsSample instructionsSample1;
    InstructionsSample instructionsSample2;
    private GlobalWorkingWeekSystem globalWorkingWeekSystem;

    Instruction instrFoo,instrZoo,instrLoo;

    private final LocalDate MONDAY= LocalDate.of(2017,8,28);

    private final LocalDate WEDNESDAY= LocalDate.of(2017,8,31);
    private final LocalDate THURSDAY= LocalDate.of(2017,8,31);



    @Before
    public void SetUp() {

        instructionsReceiverImpl =new InstructionsReceiverImpl();
        instructionsSample1= new InstructionsSample1() ;
        instructionsSample2= new InstructionsSample2() ;
        globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();

        instrFoo=new InstructionImpl(
                new EntityImpl("EntityFoo"),
                OperationType.BUY,
                BigDecimal.valueOf(1.5),
                Currency.getInstance("GBP"),
                MONDAY,
                MONDAY,  /*will remain on Monday*/
                3,
                BigDecimal.valueOf(50),
                globalWorkingWeekSystem);

        instrZoo=new InstructionImpl(
                new EntityImpl("EntityFoo"),
                OperationType.BUY,
                BigDecimal.valueOf(1.5),
                Currency.getInstance("GBP"),
                MONDAY,
                MONDAY,  /*will remain on Monday*/
                3,
                BigDecimal.valueOf(50),
                globalWorkingWeekSystem);

        instrLoo=new InstructionImpl(
                new EntityImpl("EntityFoo"),
                OperationType.BUY,
                BigDecimal.valueOf(1.5),
                Currency.getInstance("GBP"),
                MONDAY,
                MONDAY,  /*will remain on Monday*/
                3,
                BigDecimal.valueOf(50),
                globalWorkingWeekSystem);
    }


    @Test
    public void processInstruction() throws Exception {

        assertEquals(0,instructionsReceiverImpl.getAllAccumulatedInstructions().size());

        instructionsReceiverImpl.ProcessInstruction(instrFoo);

        assertEquals(1,instructionsReceiverImpl.getAllAccumulatedInstructions().size());
        assertEquals("EntityFoo",instrFoo.getEntity().getName());

        /*This instruction must not be added as Set type does not allow duplications */
        instructionsReceiverImpl.ProcessInstruction(instrFoo);

        assertEquals(1,instructionsReceiverImpl.getAllAccumulatedInstructions().size());
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrFoo));
    }



    @Test
    public void processInstructionsChunk() throws Exception {
        instructionsReceiverImpl =new InstructionsReceiverImpl();
        instructionsSample1= new InstructionsSample1() ;
        instructionsSample2= new InstructionsSample2() ;

        assertEquals(0,instructionsReceiverImpl.getAllAccumulatedInstructions().size());

        instructionsReceiverImpl.ProcessInstructionsChunk(new HashSet<>(Arrays.asList(instrZoo,instrFoo)));

        assertEquals(2,instructionsReceiverImpl.getAllAccumulatedInstructions().size());
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrFoo));
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrZoo));

        /*This instruction must not be added as Set type does not allow duplications */
        instructionsReceiverImpl.ProcessInstruction(instrFoo);

        assertEquals(2,instructionsReceiverImpl.getAllAccumulatedInstructions().size());
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrFoo));
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrZoo));

        instructionsReceiverImpl.ProcessInstruction(instrLoo);

        assertEquals(3,instructionsReceiverImpl.getAllAccumulatedInstructions().size());
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrFoo));
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrZoo));
        assertEquals(true,instructionsReceiverImpl.getAllAccumulatedInstructions().contains(instrLoo));
    }


}