package xyz.sethy.universe.events;

import xyz.sethy.universe.Element;
import xyz.sethy.universe.location.Location;
import xyz.sethy.universe.utils.event.ASyncEvent;
import xyz.sethy.universe.utils.event.Event;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by seth on 26/06/17.
 */
public class ElementMoveEvent extends Event implements ASyncEvent
{
    private final Location to;
    private final Location from;
    private final Element element;

    public ElementMoveEvent(final Location to, final Location from, final Element element)
    {
        super("ElementMoveEvent");
        this.to = to;
        this.from = from;
        this.element = element;
    }

    public Location getTo()
    {
        return to;
    }

    public Location getFrom()
    {
        return from;
    }

    public Element getElement()
    {
        return element;
    }

    @Override
    public void handle()
    {
        this.element.setLocation(this.to);

        final Set<Element> colliders = new LinkedHashSet<>();
        for(Element element : getUniverse().getElements())
            if(element.getLocation().equals(this.to))
                colliders.add(element);

        final ElementCollideEvent event = new ElementCollideEvent(colliders);
        getUniverse().callEvent(event);

        System.out.println("ElementMoveEvent: Element moved x:(" + (to.getX()) + "), y:(" + (to.getY()) + "), z:(" + (to.getZ()) + ")");
    }

    @Override
    public boolean isAsyc()
    {
        return true;
    }
}
