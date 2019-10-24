package xyz.sethy.universe.subatomic;

import xyz.sethy.universe.subatomic.evensmaller.Quark;

import java.util.Set;

/**
 * Created by seth on 25/06/17.
 */
public class Proton {
    private final Set<Quark> quarks;

    public Proton(Set<Quark> quarks) {
        this.quarks = quarks;
    }

    public Set<Quark> getQuarks() {
        return quarks;
    }

    public double getMass() {
        double mass = 0;
        for (Quark quark : this.quarks)
            mass = mass + quark.getMass();
        return mass;
    }
}
