package xyz.sethy.universe;
import xyz.sethy.universe.subatomic.Neutron;
import xyz.sethy.universe.subatomic.Proton;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by seth on 25/06/17.
 */
public class Nucleus
{
    private final Set<Neutron> neutrons;
    private final Set<Proton> protons;

    public Nucleus(final Set<Neutron> neutrons, final Set<Proton> protons)
    {
        this.neutrons = neutrons;
        this.protons = protons;
    }

    public Set<Neutron> getNeutrons()
    {
        Set<Neutron> secureSet = new LinkedHashSet<>();
        secureSet.addAll(this.neutrons);
        return secureSet;
    }

    public Set<Proton> getProtons()
    {
        Set<Proton> secureSet = new LinkedHashSet<>();
        secureSet.addAll(this.protons);
        return secureSet;
    }

    public double getMass()
    {
        double mass = 0;
        for(Neutron neutron : neutrons)
            mass = mass + neutron.getMass();

        for(Proton proton : protons)
            mass = mass + proton.getMass();
        return mass;
    }
}
