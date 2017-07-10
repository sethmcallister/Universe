package xyz.sethy.universe.utils.event;

import xyz.sethy.universe.Universe;

/**
 * Created by seth on 26/06/17.
 */
public abstract class Event
{
    private final String eventName;

    public Event(String eventName)
    {
        this.eventName = eventName;
    }

    public abstract void handle();

    public String getEventName()
    {
        return eventName;
    }

    public Universe getUniverse()
    {
        return Universe.getInstance();
    }
}
