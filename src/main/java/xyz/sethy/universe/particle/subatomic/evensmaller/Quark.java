package xyz.sethy.universe.particle.subatomic.evensmaller;

/**
 * Created by seth on 26/06/17.
 */
public class Quark {
    private final QuarkType type;
    private final double mass;

    public Quark(QuarkType type) {
        this.type = type;
        this.mass = 2;
    }

    public QuarkType getType() {
        return type;
    }

    public double getMass() {
        return mass;
    }
}
