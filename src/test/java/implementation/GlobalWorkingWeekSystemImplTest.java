package implementation;

import abstraction.GlobalWorkingWeekSystem;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The GlobalWorkingWeekSystemImplTest class aim is to test if settlement date is adjusted correctly.
 * It contains different test cases for testing the only method of GlobalWorkingWeekSystemImpl class:
 * AdjustSettlementDateIfThatIsNotWorkingDay() against GlobalWorkingWeekSystem interface.
 * The GlobalWorkingWeekSystemImplTest class contains a bunch of test cases where each case is a combination
 * of non-working days or working days with AED/SAR location or locations from rest of the world.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 * */

public class GlobalWorkingWeekSystemImplTest {

    private GlobalWorkingWeekSystem globalWorkingWeekSystem;

    @Before
    public void setUp() throws Exception {
        globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();
    }

    @Test
    public void AdjustSettlementDateCaseForWorkingDaysForNonAEDSAR_Areas() throws Exception {

        // test for a Monday
        Currency currency = Currency.getInstance("EUR");
        LocalDate date = LocalDate.of(2017,8,28);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Tuesday
        currency = Currency.getInstance("USD");
        date = LocalDate.of(2017,8,29);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Wendesday
        currency = Currency.getInstance("USD");
        date = LocalDate.of(2017,8,30);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Thursday
        currency = Currency.getInstance("USD");
        date = LocalDate.of(2017,8,31);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Friday
        currency = Currency.getInstance("SEK");
        date = LocalDate.of(2017,9,1);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));
    }

    @Test
    public void AdjustSettlementDateCaseForSaturdayForNonAEDSAR_Areas() throws Exception {

        Currency currency = Currency.getInstance("EUR");
        LocalDate date = LocalDate.of(2017,9,2);// Saturday
        LocalDate closestWorkingDayToGivenDate = globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency);

        assertNotEquals( date, closestWorkingDayToGivenDate);

        assertNotEquals( date.plus(1, ChronoUnit.DAYS), closestWorkingDayToGivenDate);

        assertEquals( date.plus(2, ChronoUnit.DAYS), closestWorkingDayToGivenDate);

        assertNotEquals( date.plus(3, ChronoUnit.DAYS), closestWorkingDayToGivenDate);
    }


    @Test
    public void AdjustSettlementDateCaseForSundayForNonAEDSAR_Areas() throws Exception {

        Currency currency = Currency.getInstance("EUR");
        LocalDate date = LocalDate.of(2017,9,3);// Sunday
        LocalDate closestWorkingDayToGivenDate = globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency);

        assertNotEquals( date, closestWorkingDayToGivenDate);

        assertEquals( date.plus(1, ChronoUnit.DAYS), closestWorkingDayToGivenDate);

        assertNotEquals( date.plus(2, ChronoUnit.DAYS), closestWorkingDayToGivenDate);
    }


    @Test
    public void AdjustSettlementDateCaseForWorkingDaysForAEDSAR_Areas() throws Exception {

        // test for a Sunday
        Currency  currency = Currency.getInstance("AED");
        LocalDate date = LocalDate.of(2017,8,27);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Monday
        currency = Currency.getInstance("AED");
        date = LocalDate.of(2017,8,28);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Tuesday
        currency = Currency.getInstance("SAR");
        date = LocalDate.of(2017,8,29);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Wendesday
        currency = Currency.getInstance("AED");
        date = LocalDate.of(2017,8,30);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));

        // test for a Thursday
        currency = Currency.getInstance("AED");
        date = LocalDate.of(2017,8,31);
        assertEquals( date, globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency));


    }


    @Test
    public void getNextWorkingDayNonTestForFridayForAEDSAR_Areas() throws Exception {

        Currency currency = Currency.getInstance("AED");
        LocalDate date = LocalDate.of(2017,9,1);// Friday
        LocalDate closestWorkingDayToGivenDate = globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency);

        assertNotEquals( date, closestWorkingDayToGivenDate);

        assertNotEquals( date.plus(1, ChronoUnit.DAYS), closestWorkingDayToGivenDate);

        assertEquals( date.plus(2, ChronoUnit.DAYS), closestWorkingDayToGivenDate);
    }

    @Test
    public void getNextWorkingDayNonTestForSaturdayForAEDSAR_Areas() throws Exception {

        Currency currency = Currency.getInstance("AED");
        LocalDate date = LocalDate.of(2017,9,2);// Saturday
        LocalDate closestWorkingDayToGivenDate = globalWorkingWeekSystem.AdjustSettlementDateIfThatIsNotWorkingDay(date,currency);

        assertNotEquals( date, closestWorkingDayToGivenDate);

        assertEquals( date.plus(1, ChronoUnit.DAYS), closestWorkingDayToGivenDate);

        assertNotEquals( date.plus(2, ChronoUnit.DAYS), closestWorkingDayToGivenDate);
    }

}