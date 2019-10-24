package xyz.sethy.universe.events;

import xyz.sethy.universe.Element;
import xyz.sethy.universe.utils.event.Event;

/**
 * Created by seth on 26/06/17.
 */
public class ElementDestoryEvent extends Event {
    private final Element element;

    public ElementDestoryEvent(final Element element) {
        super("ElementDestroyEvent");
        this.element = element;
    }

    @Override
    public void handle() {
        getUniverse().removeElement(element);
    }
}
