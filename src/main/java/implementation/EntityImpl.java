package implementation;

import abstraction.Entity;


/**
 * The EntityImpl class completely implements Entity interface.
 * By entity concept we should understand a financial entity whose shares are to be bought or sold.
 *
 * @author Todian Mishtaku
 *
 *
 **/

public class EntityImpl implements Entity {

    private String name;
    private String description;


    public EntityImpl(String name)
    {
        this.name =name;

    }

    public EntityImpl(String name, String description)
    {
        this.name =name;

    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String setDescription() {
        return null;
    }


}
