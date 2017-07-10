package xyz.sethy.universe;

import xyz.sethy.universe.location.Location;
import xyz.sethy.universe.subatomic.Electron;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by seth on 25/06/17.
 */
public class Element
{
    private Location location;
    private final Nucleus nucleus;
    private final Set<Electron> electrons;

    public Element(final Nucleus nucleus, final Set<Electron> electrons)
    {
        this.location = new Location(0, 0, 0);
        this.nucleus = nucleus;
        this.electrons = electrons;
    }

    public Element(final Location location, final Nucleus nucleus, final Set<Electron> electrons)
    {
        this.location = location;
        this.nucleus = nucleus;
        this.electrons = electrons;
    }

    public Nucleus getNucleus()
    {
        return nucleus;
    }

    public Set<Electron> getElectrons()
    {
        Set<Electron> secureSet = new LinkedHashSet<>();
        secureSet.addAll(this.electrons);
        return secureSet;
    }

    public double getMass()
    {
        double mass = this.nucleus.getMass();
        for(Electron electron : electrons)
             mass = mass + electron.getMass();
        return mass;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(final Location location)
    {
        this.location = location;
    }
}
