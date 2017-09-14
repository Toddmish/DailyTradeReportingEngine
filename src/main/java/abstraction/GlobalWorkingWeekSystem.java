package abstraction;
import java.time.LocalDate;
import java.util.Currency;


/**
 * The GlobalWorkingWeekSystem interface type provides a method which for a given date
 * and Currency returns the same date if it is date of a working day in areas specified by Currency code,
 * otherwise returns date of the closest coming working day of that area.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 */
public interface GlobalWorkingWeekSystem {

    /**
     * The AdjustSettlementDateIfThatIsNotWorkingDay method for a given date
     * and Currency returns the same date if it is date of a working day in areas specified by Currency code,
     * otherwise returns date of the closest coming working day of that area.
     * */
    LocalDate AdjustSettlementDateIfThatIsNotWorkingDay(LocalDate date, Currency currency);

}

