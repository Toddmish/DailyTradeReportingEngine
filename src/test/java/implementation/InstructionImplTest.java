package implementation;

import abstraction.GlobalWorkingWeekSystem;
import abstraction.Instruction;
import abstraction.OperationType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;


/**
 * The InstructionImplTest class aim is to test if objects of type Instruction
 * are correctly created.
 *
 *
 * @author Todian Mishtaku
 *
 *
 * */
public class InstructionImplTest {

    private Instruction instructionImpl;
    private GlobalWorkingWeekSystem globalWorkingWeekSystem;

    private final LocalDate MONDAY= LocalDate.of(2017,8,28);

    private final LocalDate WEDNESDAY= LocalDate.of(2017,8,31);
    private final LocalDate THURSDAY= LocalDate.of(2017,8,31);


    @Before
    public void SetUp() {

        globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();

        instructionImpl = new InstructionImpl(new EntityImpl("foo"),
                OperationType.BUY,
                BigDecimal.valueOf(1.40),
                Currency.getInstance("GBP"),
                WEDNESDAY,
                THURSDAY,
                3,
                BigDecimal.valueOf(50.00),
                globalWorkingWeekSystem

        );
    }


    @Test
    public void getEntity() throws Exception {
        assertEquals("foo",instructionImpl.getEntity().getName());
    }

    @Test
    public void getOperation() throws Exception {
        assertEquals(OperationType.BUY,instructionImpl.getOperation());
    }

    @Test
    public void getAgreedFx() throws Exception {
        assertEquals(BigDecimal.valueOf(1.4),instructionImpl.getAgreedFx());
    }

    @Test
    public void getCurrency() throws Exception {
        assertEquals(Currency.getInstance("GBP"),instructionImpl.getCurrency());
    }

    @Test
    public void getInstructionDate() throws Exception {
        assertEquals(WEDNESDAY,instructionImpl.getInstructionDate());
    }

    @Test
    public void getSettlementDate() throws Exception {
        assertEquals(THURSDAY,instructionImpl.getSettlementDate());
    }

    @Test
    public void getUnits() throws Exception {
        assertEquals(3,instructionImpl.getUnits());
    }

    @Test
    public void getPricePerUnit() throws Exception {
        assertEquals(BigDecimal.valueOf(50.00),instructionImpl.getPricePerUnit());
    }

    @Test
    public void getTradeAmount() throws Exception {
        assertEquals(getCorrectFormat(210.0),instructionImpl.getTradeAmount());
    }


    private BigDecimal getCorrectFormat(double amount) {
        return BigDecimal.valueOf(amount).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

}