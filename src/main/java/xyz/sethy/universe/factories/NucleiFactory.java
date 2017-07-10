package xyz.sethy.universe.factories;

import xyz.sethy.universe.Element;
import xyz.sethy.universe.Nucleus;
import xyz.sethy.universe.subatomic.Neutron;
import xyz.sethy.universe.subatomic.Proton;
import xyz.sethy.universe.subatomic.evensmaller.Quark;
import xyz.sethy.universe.subatomic.evensmaller.QuarkType;
import xyz.sethy.universe.utils.EventUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by seth on 25/06/17.
 */
public class NucleiFactory
{
    public Nucleus createEmptyNucleus()
    {
        return new Nucleus(new LinkedHashSet<Neutron>(), new LinkedHashSet<Proton>());
    }

    public Nucleus createACass()
    {
        final Set<Proton> protons1 = new LinkedHashSet<>();
        final Set<Neutron> neutrons1 = new LinkedHashSet<>();

        int i = 0;
        while(i < 29)
        {
            i++;
            final Set<Quark> quarks = new LinkedHashSet<>();
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.DOWN));
            protons1.add(new Proton(quarks));
        }
        i = 0;
        while(i < 29)
        {
            i++;
            final Set<Quark> quarks = new LinkedHashSet<>();
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.DOWN));
            quarks.add(new Quark(QuarkType.DOWN));
            neutrons1.add(new Neutron(quarks));
        }
        Nucleus nucleus1 = new Nucleus(neutrons1, protons1);

        final Set<Proton> protons2 = new LinkedHashSet<>();
        final Set<Neutron> neutrons2 = new LinkedHashSet<>();

        i = 0;
        while(i < 52)
        {
            i++;
            final Set<Quark> quarks = new LinkedHashSet<>();
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.DOWN));
            protons1.add(new Proton(quarks));
        }
        i = 0;
        while(i < 52)
        {
            i++;
            final Set<Quark> quarks = new LinkedHashSet<>();
            quarks.add(new Quark(QuarkType.UP));
            quarks.add(new Quark(QuarkType.DOWN));
            quarks.add(new Quark(QuarkType.DOWN));
            neutrons1.add(new Neutron(quarks));
        }
        Nucleus nucleus2 = new Nucleus(neutrons2, protons2);
        return mergeNuclei(nucleus1, nucleus2);
    }

    public Nucleus createNewNucleus()
    {
        if(!EventUtil.isBigBang())
            throw new RuntimeException("It doesn't just work like that lol");

        final Set<Proton> protons = new LinkedHashSet<>();
        final Set<Neutron> neutrons = new LinkedHashSet<>();

        //Just a hydrogen nucleus.
        final Set<Quark> pquarks = new LinkedHashSet<>();
        pquarks.add(new Quark(QuarkType.UP));
        pquarks.add(new Quark(QuarkType.UP));
        pquarks.add(new Quark(QuarkType.DOWN));
        protons.add(new Proton(pquarks));

        final Set<Quark> nquarks = new LinkedHashSet<>();
        nquarks.add(new Quark(QuarkType.UP));
        nquarks.add(new Quark(QuarkType.DOWN));
        nquarks.add(new Quark(QuarkType.DOWN));
        neutrons.add(new Neutron(nquarks));
        return new Nucleus(neutrons, protons);
    }

    public Nucleus createNewNucleus(final Element from1, final Element from2)
    {
        final Set<Proton> newProtons = new LinkedHashSet<>();
        final Set<Neutron> newNeutrons = new LinkedHashSet<>();

        newProtons.addAll(from1.getNucleus().getProtons());
        newProtons.addAll(from2.getNucleus().getProtons());

        newNeutrons.addAll(from1.getNucleus().getNeutrons());
        newNeutrons.addAll(from2.getNucleus().getNeutrons());

        return new Nucleus(newNeutrons, newProtons);
    }

    public Nucleus mergeNuclei(final Nucleus from1, final Nucleus from2)
    {
        final Set<Proton> newProtons = new LinkedHashSet<>();
        final Set<Neutron> newNeutrons = new LinkedHashSet<>();

        newProtons.addAll(from1.getProtons());
        newProtons.addAll(from2.getProtons());

        newNeutrons.addAll(from1.getNeutrons());
        newNeutrons.addAll(from2.getNeutrons());

        return new Nucleus(newNeutrons, newProtons);
    }

    public static NucleiFactory getNewInstance()
    {
        return new NucleiFactory();
    }
}
