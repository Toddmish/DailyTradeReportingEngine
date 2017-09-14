package abstraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 *  The StatisticsGenerator interface defines the set of methods to be implemented for generating statistics on:
 *
 *  1) daily incoming amount in USD
 *  2) daily outgoing amount in USD
 *  3) entity ranking based on incoming amount
 *  4) entity ranking based on outgoing amount .
 *
 *
 *
 *  @author Todian Mishtaku
 *
 *
 */
public interface StatisticsGenerator {

    /**
     * Generates stats on total incoming amount in USD per each date
     * @param instructions set of instructions for which report is generated
     * @return a map from date to  sum of all incoming amounts in USD settled on that date from all
     * entities
     */
    Map<LocalDate, BigDecimal> findAmountInUSDsettledIncomingEveryday(Set<Instruction> instructions);

    /**
     * Generates stats on total outgoing amount in USD per each date
     * @param instructions set of instructions for which report is generated
     * @return a map from date to  sum of all outgoing amounts in USD settled on that date from all
     * entities
     */
    Map<LocalDate, BigDecimal> findAmountInUSDsettledOutgoingEveryday(Set<Instruction> instructions);

    /**
     * Generates ranking of entities based on their incoming amount in USD
     * @param instructions set of instructions for which ranking is generated
     * @return a map from entity name to a tuple made of rank and sum of all incoming amount in USD settled
     * by that entity which in fact determines the rank
     */
    Map<String, EntityRank> findRankingOfEntitiesBasedOnIncomingAmount(Set<Instruction> instructions);

    /**
     * Generates ranking of entities based on their outgoing amount in USD
     * @param instructions set of instructions for which ranking is generated
     * @return a map from entity name to a tuple made of rank and sum of all outgoing amount in USD settled
     * by that entity which in fact determines the rank
     */
    Map<String, EntityRank> findRankingOfEntitiesBasedOnOutgoingAmount(Set<Instruction> instructions);

}
