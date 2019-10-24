package xyz.sethy.universe.events;

import xyz.sethy.universe.Element;
import xyz.sethy.universe.Nucleus;
import xyz.sethy.universe.Universe;
import xyz.sethy.universe.location.Movement;
import xyz.sethy.universe.subatomic.Electron;
import xyz.sethy.universe.utils.event.ASyncEvent;
import xyz.sethy.universe.utils.event.Event;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by seth on 26/06/17.
 */
public class ElementCollideEvent extends Event implements ASyncEvent {
    private final Set<Element> colliders;
    private Element result;

    public ElementCollideEvent(final Set<Element> colliders) {
        super("ElementCollideEvent");
        this.colliders = colliders;
    }

    @Override
    public void handle() {
        final Set<Electron> electrons = new LinkedHashSet<>();
        Nucleus nucleus = getUniverse().getNucleiFactory().createEmptyNucleus();
        for (Element element : this.colliders) {
            nucleus = getUniverse().getNucleiFactory().mergeNuclei(nucleus, element.getNucleus());
            electrons.addAll(element.getElectrons());
        }

        Element element = new Element(nucleus, electrons);
        Movement currentMovement = element.getLocation().getMovement();
        this.result = element;
        Movement movement = new Movement(getNextSpeed(currentMovement.getSpeedX()), getNextSpeed(currentMovement.getSpeedY()), getNextSpeed(currentMovement.getSpeedZ()));
        this.result.getLocation().setMovement(movement);
        Universe.getInstance().callEvent(new ElementCreationEvent(element));

        for (Element element1 : getColliders())
            Universe.getInstance().callEvent(new ElementDestoryEvent(element1));

        System.out.println("ElementCollideEvent: Created an element with " + getResult().getNucleus().getProtons().size() + " protons.");
    }

    public Set<Element> getColliders() {
        return colliders;
    }

    public Element getResult() {
        return result;
    }

    private long getNextSpeed(Long currentSpeed) {
        long range = 533L;
        Random r = getUniverse().getRandom();
        long number = (long) (r.nextDouble() * range);
        long speed = currentSpeed;

        int next = getUniverse().getRandom().nextInt(1);
        if (next == 0)
            speed = speed - number;
        else
            speed = speed + number;
        return speed;
    }

    @Override
    public boolean isAsyc() {
        return true;
    }
}
