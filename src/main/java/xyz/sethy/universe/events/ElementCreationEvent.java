package xyz.sethy.universe.events;

import xyz.sethy.universe.Element;
import xyz.sethy.universe.utils.event.Event;

/**
 * Created by seth on 26/06/17.
 */
public class ElementCreationEvent extends Event
{
    private final Element element;

    public ElementCreationEvent(final Element element)
    {
        super("ElementCreationEvent");
        this.element = element;
    }

    @Override
    public void handle()
    {
        getUniverse().addElement(this.element);
    }
}
