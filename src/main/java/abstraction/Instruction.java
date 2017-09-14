package abstraction;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * The Instruction interface serves as a contract between client sending instruction and
 * server consuming them. The Instruction provides all the information needed for accomplishing
 * a BUY or SELL of shares of the given entity.
 *
 *
 * @author Todian Mishtaku
 *
 */
public interface Instruction {

    /**
     * A financial entity whose shares are to be bought or sold
     */

    Entity getEntity() ;

    /**
     * A Buy/Sell flag: B – Buy – outgoing  S – Sell – incoming
     * */
    OperationType getOperation();

    /**
     * @return date on which the instruction was sent (ex. to Bank yyyxxx) by various clients
     */
    LocalDate getInstructionDate() ;

    /**
     * @return date on which the client wished for the instruction to be settled
     */
    LocalDate getSettlementDate() ;

    /**
     *
     * @return currency given by entity
     */

    Currency getCurrency();

    /**
     * The foreign exchange rate with respect to USD that was agreed
     * @return
     */
    BigDecimal getAgreedFx();


    /**
     * @return number of shares to be bought or sold
     */
    int getUnits() ;

    /**
     *
     * @return total amount in USD requested to trade by this instruction
     */
    BigDecimal getTradeAmount();

    /**
     *  @return price of unit given in currency specified by currency field
     */
    BigDecimal getPricePerUnit();

}





