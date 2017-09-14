package implementation;

import abstraction.GlobalWorkingWeekSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Currency;
import java.util.EnumMap;
import java.util.List;


/**
 * The GlobalWorkingWeekSystemImpl class fully implements GlobalWorkingWeekSystem interface.
 * It contains mapping between currency codes and list of non-working days for countries which uses that currency.
 * This class provides implementation of AdjustSettlementDateIfThatIsNotWorkingDay method which for a given date
 * returns the same date if it is date of a working day for the specified Country by Currency code,
 * otherwise returns date of the closest coming working day.
 * */
public class GlobalWorkingWeekSystemImpl implements GlobalWorkingWeekSystem {


    /**
     * The nonWorkingDaysInWorld field will store mapping of currency codes with list of non-working days
     * for countries which uses that currency
     * */
    private EnumMap<AreaByCurrency,List<DayOfWeek>> nonWorkingDaysInWorld;

    /**
     * Eventually, in future a new separation between working days and non-working days may occur in different countries
     * In such case it is easy to change lists or/and add new ones
     * */
    private List<DayOfWeek> arabicNonWorkingDays;
    private List<DayOfWeek> nonArabicNonWorkingDays;

    public GlobalWorkingWeekSystemImpl()
    {
        init();
    }

    private void init() {

        nonWorkingDaysInWorld = new EnumMap<AreaByCurrency, List<DayOfWeek>>(AreaByCurrency.class);
        arabicNonWorkingDays = Arrays.asList(DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
        nonArabicNonWorkingDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);

        nonWorkingDaysInWorld.put(AreaByCurrency.REMAINING, nonArabicNonWorkingDays);
        nonWorkingDaysInWorld.put(AreaByCurrency.AED, arabicNonWorkingDays);
        nonWorkingDaysInWorld.put(AreaByCurrency.SAR, arabicNonWorkingDays);

    }


    /*
    *  Returns the same date if it is the date of a working day for the specified areas(countries,regions) by Currency code,
    *  otherwise returns date of first working day of the week for that area.
    * */

    @Override
    public LocalDate AdjustSettlementDateIfThatIsNotWorkingDay(LocalDate date, Currency currency)
    {

        AreaByCurrency currencyArea=AreaByCurrency.getFromName(currency.getCurrencyCode());

        List<DayOfWeek> nonWorkingDays= nonWorkingDaysInWorld.get(currencyArea);

        while (isNonWorkingDay(date.getDayOfWeek(), nonWorkingDays))
        {
            date= date.plus(1, ChronoUnit.DAYS);
        }

        return date;
    }

    private boolean isNonWorkingDay(DayOfWeek day, List<DayOfWeek> nonWorkingDays) {
        return nonWorkingDays.stream().filter(s->s.compareTo(day)==0).findFirst().isPresent();
    }
}
