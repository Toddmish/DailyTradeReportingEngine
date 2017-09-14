package implementation;


/**
 * The AreaByCurrency enum represents a categorization of world areas(countries and other political entities)
 * based on Currency code.
 * Working days are not the same everywhere in the World, but currency codes give all the information needed for
 * the classification which allows to know working and non-working days for areas specified by
 * Currency code.
 *
 *
 * @author Todian Mishtaku
 *
 *
 * */
public enum AreaByCurrency {

    AED("AED"),
    SAR("SAR"),
    /*REMAINING is for the rest of World which does not use the above currency codes */
    REMAINING("REMAINING");

    private String name;

    AreaByCurrency(String name) {
        this.name = name;
    }

    public static AreaByCurrency getFromName(String name) {
        if (name.equalsIgnoreCase(AreaByCurrency.AED.name()))
            return AreaByCurrency.AED;
        else if (name.equalsIgnoreCase(AreaByCurrency.SAR.name()))
            return AreaByCurrency.SAR;

        return AreaByCurrency.REMAINING;
    }

    public String getName() {
        return name;
    }
}
