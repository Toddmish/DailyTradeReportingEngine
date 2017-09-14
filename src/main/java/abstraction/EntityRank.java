package abstraction;

import java.math.BigDecimal;

/**
 * The EntityRank interface provides a definition for the type which will keep in memory
 * a tuple made of amount and its corresponding rank. EntityRank tuple will be needed while
 * generating and printing statistics. Task' spec asks only ranks, but data structure
 * provided in project leaves open the eventual option of printing amount beside rank.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 **/
public interface EntityRank {

    int getRank();

    void setRank(int rank);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);
}
