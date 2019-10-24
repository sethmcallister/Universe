package xyz.sethy.universe.location;

import java.util.Objects;

/**
 * Created by seth on 26/06/17.
 */
public class Location {
    private final long x;
    private final long y;
    private final long z;
    private Movement movement;

    public Location(final long x, final long y, final long z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.movement = new Movement(1L, 2L, 4L);
    }

    public Location(final long x, final long y, final long z, final Movement speed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.movement = speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location))
            return false;

        Location location = (Location) o;

        return getX() == location.getX() && getY() == location.getY() && getZ() == location.getZ();
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public Movement getMovement() {
        return this.movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
