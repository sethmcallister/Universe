package xyz.sethy.universe.location;

/**
 * Created by seth on 26/06/17.
 */
public class Movement
{
    private long speedX;
    private long speedY;
    private long speedZ;

    public Movement(final long speedX, final long speedY, final long speedZ)
    {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }

    public long getSpeedX()
    {
        return speedX;
    }

    public void setSpeedX(long speedX)
    {
        this.speedX = speedX;
    }

    public long getSpeedY()
    {
        return speedY;
    }

    public void setSpeedY(long speedY)
    {
        this.speedY = speedY;
    }

    public long getSpeedZ()
    {
        return speedZ;
    }

    public void setSpeedZ(long speedZ)
    {
        this.speedZ = speedZ;
    }
}
