package implementation;

import abstraction.GlobalWorkingWeekSystem;
import abstraction.Instruction;
import abstraction.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.UUID;

/**
 * The InstructionImpl implements Instruction interface.
 * The Instruction provides all the information needed for accomplishing
 * a BUY or SELL of shares of the given entity.
 *
 *
 * @author Todian Mishtaku
 *
 */
public class InstructionImpl implements Instruction {

    /**
     *  A unique ID which will help to avoid duplications
     */
    String uniqueID;

    /**
     * A financial entity whose shares are to be bought or sold
     */

    EntityImpl entity;

    /**
     * A Buy/Sell flag: B – Buy
     */

    OperationType operation;

    /**
     *
     * The foreign exchange rate with respect to USD that was agreed
     */

    BigDecimal agreedFx;

    /**
     * currency given by entity
     */
    Currency currency;

    /**
     * Date on which the instruction was sent to JP Morgan by various clients
     */
    LocalDate instructionDate;

    /**
     * The date on which the client wished for the instruction to be settled with respect
     * to InstructionImpl Date
     **/
    LocalDate settlementDate;

    /**
     * Number of shares to be bought or sold
     * */
    int units;

    /**
     *  Price of unit given in currency specified by currency field
     */
    BigDecimal pricePerUnit;

    /**
     *  Total amount in USD requested to trade by this instruction
     */
    private BigDecimal tradeAmount;

    private LocalDate adjustSettlementDate(LocalDate date,Currency currency, GlobalWorkingWeekSystem globalWorkingWeekSystem ) {
        return globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency);
    }


    /** By adjusting the date in Constructor is assured that Statistics Generator will operate on correct dates
     * This decision takes under consideration the fact that adjusting the settlement is not expensive operation,
     * therefore can be called from constructor.
     **/
    public InstructionImpl(EntityImpl entity, OperationType operation,
                           BigDecimal agreedFx, Currency currency, LocalDate instructionDate, LocalDate settlementDate,
                           int units,
                           BigDecimal pricePerUnit, GlobalWorkingWeekSystem globalWorkingWeekSystem)
    {
        this.uniqueID = UUID.randomUUID().toString();
        this.entity=entity;
        this.operation=operation;
        this.agreedFx=agreedFx;
        this.currency=currency;
        this.instructionDate=instructionDate;
        this.units=units;
        this.pricePerUnit=pricePerUnit;
        /*These two operations are not expensive that is why decided to call the rom constructor*/
        this.tradeAmount = calculateTradeAmount();
        this.settlementDate= adjustSettlementDate(settlementDate,currency,globalWorkingWeekSystem);
    }

    /*Why BigDecimal.ROUND_HALF_EVEN ?
         From Wikipedia:
         Despite the custom of rounding the number 4.5 up to 5, in fact 4.5 is no nearer to 5 than
         it is to 4 (it is 0.5 away from both). When dealing with large sets of scientific or statistical data,
         where trends are important, traditional rounding on average biases the data upwards slightly. Over a
         large set of data, or when many subsequent rounding operations are performed as in digital signal
         processing, the round-to-even rule tends to reduce the total rounding error, with (on average) an
         equal portion of numbers rounding up as rounding down. This generally reduces upwards skewing of
         the result.
         */
    private BigDecimal calculateTradeAmount() {
        return BigDecimal.valueOf(getUnits()).multiply(getPricePerUnit()).multiply(
                getAgreedFx()).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * @return a financial entity whose shares are to be bought or sold.
     */
    public EntityImpl getEntity() {
        return entity;
    }

    /**
     * @return A Buy/Sell flag: B – Buy – outgoing  S – Sell – incoming.
     * */

    public OperationType getOperation() {
        return operation;
    }

    /**
     *
     * @return The foreign exchange rate with respect to USD that was agreed.
     */
    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    /**
     *
     * @return currency given by entity.
     */
    public Currency getCurrency() { return currency; }

    /**
     * @return date on which the instruction was sent (ex. to Bank yyyxxx) by various clients.
     */
    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    /**
     * @return date on which the client wished for the instruction to be settled.
     */
    public LocalDate getSettlementDate() {
        return settlementDate;
    }


    /**
     * @return number of shares to be bought or sold.
     */
    public int getUnits() {
        return units;
    }

    /**
     *  @return price of unit given in currency specified by currency field.
     */
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     *
     * @return total amount in USD requested to trade by this instruction.
     */
    public BigDecimal getTradeAmount() {
        return  tradeAmount;
    }


    @Override
    public String toString() {
        String msg="***************************************************************" + System.lineSeparator();
        msg+=   "Entity Name: " +getEntity().getName()+ System.lineSeparator()+
                "Operation: " +getOperation()+ System.lineSeparator()+
                "InstructionDate: " +getInstructionDate()+ System.lineSeparator()+
                "settlementDate: " +getSettlementDate()+ System.lineSeparator()+
                "AgreedFx: " +getAgreedFx()+ System.lineSeparator()+
                "Units: " +getUnits()+ System.lineSeparator()+
                "PricePerUnit: " +getPricePerUnit()+ System.lineSeparator();
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstructionImpl)) return false;

        InstructionImpl that = (InstructionImpl) o;

        return uniqueID.equals(that.uniqueID);
    }

    @Override
    public int hashCode() {
        return uniqueID.hashCode();
    }
}







