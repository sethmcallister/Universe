package xyz.sethy.universe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.sethy.universe.events.ElementMoveEvent;
import xyz.sethy.universe.factories.NucleiFactory;
import xyz.sethy.universe.location.Location;
import xyz.sethy.universe.particle.Element;
import xyz.sethy.universe.particle.Nucleus;
import xyz.sethy.universe.particle.subatomic.Electron;
import xyz.sethy.universe.utils.UniverseThread;
import xyz.sethy.universe.utils.event.ASyncEvent;
import xyz.sethy.universe.utils.event.Event;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by seth on 26/06/17.
 */
public class Universe {
    private static final Logger LOGGER = LoggerFactory.getLogger(Universe.class);

    private static Universe instance;
    private final Set<Element> elements;
    private final NucleiFactory nucleiFactory;
    private final UniverseThread mainUniverseThread;
    private final Random random;
    private final Set<Event> nextTick;
    private boolean laskTickCompleted;

    public Universe() {
        instance = this;
        this.elements = new LinkedHashSet<>();
        this.nucleiFactory = NucleiFactory.getNewInstance();
        this.random = new Random();
        this.nextTick = new HashSet<>();
        this.laskTickCompleted = true;

        int i = 100;
        while (i > 0) {
            i--;
            Nucleus nucleus = getNucleiFactory().createNewNucleus();
            final Set<Electron> electrons = new LinkedHashSet<>();
            electrons.add(new Electron());
            Element element = new Element(nucleus, electrons);
            this.elements.add(element);

        }
        this.mainUniverseThread = new UniverseThread() {
            @Override
            public void run() {
                tick();
            }
        }.register(1000L);
    }

    public static Universe getInstance() {
        return instance;
    }

    private void tick() {
        if (!this.laskTickCompleted) {
            LOGGER.warn("Universe: Last tick not completed");
            return;
        }
        final Set<Event> events = new LinkedHashSet<>();
        events.addAll(this.nextTick);
        for (Event event : events) {
            event.handle();
            this.nextTick.remove(event);
        }
        events.clear();

        this.laskTickCompleted = false;
        for (Element element : this.elements) {
            final Location from = element.getLocation();
            final Location to = new Location(from.getX() + from.getMovement().getSpeedX(), from.getY() + from.getMovement().getSpeedY(), from.getZ() + from.getMovement().getSpeedZ());
            final ElementMoveEvent event = new ElementMoveEvent(to, from, element);
            event.handle();
        }
//        final Set<Location> toDraw = new LinkedHashSet<>();
//        int radius = 50;
//
//        Location loc = new Location(0, 0, 0);
//
//        for(long x = loc.getX() - radius; x < loc.getX() + radius; x++)
//        {
//            for(long y = loc.getY() - radius; y < loc.getX() + radius; y++)
//            {
//                for(long z = loc.getZ() - radius; z < loc.getX() + radius; z++)
//                    toDraw.add(new Location(x, y, z));
//            }
//        }
//        int i = 100;
//        for(Location location : toDraw)
//        {
//            i--;
//            StringBuilder toPrint = new StringBuilder();
//            if(findAtLocation(location).isEmpty())
//                toPrint.append(" ");
//            else
//                toPrint.append("*");
//            if(i == 0)
//            {
//                i = 10;
//                toPrint.append("\n");
//            }
//
//            System.out.print(toPrint.toString());
//        }

//        System.out.println("------- TICK COMPLETE -----------");
        this.laskTickCompleted = true;
    }

    public Set<Element> findAtLocation(final Location location) {
        final Set<Element> same = new LinkedHashSet<>();
        for (Element element : this.elements) {
            if (element.getLocation().getX() == location.getX() && element.getLocation().getY() == location.getY() && element.getLocation().getZ() == element.getLocation().getZ()) {
                same.add(element);
            }
        }
        return same;
    }

    public void callEvent(final Event event) {
        if (event instanceof ASyncEvent)
            event.handle();
        else
            this.nextTick.add(event);
    }

    public void removeElement(Element element) {
        this.elements.remove(element);
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }

    public void throwExpection(final String expection) {
        System.out.println(expection);
    }

    public Set<Element> getElements() {
        return elements;
    }

    public NucleiFactory getNucleiFactory() {
        return nucleiFactory;
    }

    public Random getRandom() {
        return random;
    }
}
