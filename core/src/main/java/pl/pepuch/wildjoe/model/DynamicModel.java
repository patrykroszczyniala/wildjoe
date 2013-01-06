package pl.pepuch.wildjoe.model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.PlayN;

public abstract class DynamicModel {

    protected Body body;
    protected float height;
    protected float width;
    protected GameWorld world;
    protected Vec2 origin;
    protected float speed;
    protected float originSpeed;
    protected Vec2 positionBefore;
    protected Vec2 position;
    private boolean isTurnedLeft;
    private boolean isTurnedRight;

    public DynamicModel(GameWorld world) {
        this.world = world;
    }

    public GameWorld gameWorld() {
        return world;
    }

    public Body body() {
        return body;
    }

    public Vec2 origin() {
        float x = origin.x + world.getArenaPositionX();
        float y = origin.y;
        return new Vec2(x, y);
    }

    public Vec2 position() {
        return body.getPosition();
    }

    public Vec2 positionBefore() {
        return positionBefore;
    }

    public void destroy() {
        PlayN.invokeLater(new Runnable() {
            @Override
            public void run() {
                world.world().destroyBody(body);
                origin = null;
            }
        });
    }

    public Vec2 gameWorldPositionBefore() {
        float x = positionBefore().x + world.getArenaPositionX();
        float y = positionBefore().y;
        return new Vec2(x, y);
    }

    public Vec2 gameWorldPosition() {
        float x = body.getPosition().x + world.getArenaPositionX();
        float y = body.getPosition().y;
        return new Vec2(x, y);
    }

    public float angle() {
        return body.getAngle();
    }

    public void setAngle(float angle) {
        body.setTransform(body.getPosition(), angle);
    }

    public void setPosition(Vec2 position) {
        positionBefore = position().clone();
        body.setTransform(position, body.getAngle());
    }

    public float height() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float width() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
    
    public float originSpeed() {
        return originSpeed;
    }
    
    public void setOriginSpeed(float originSpeed) {
        if ((isTurnedLeft() && originSpeed > 0) || (isTurnedRight() && originSpeed < 0)) {
            originSpeed = -originSpeed;
        }
        this.originSpeed = originSpeed;
    }

    public float speed() {
        return speed;
    }

    public void setSpeed(float speed) {
        if ((isTurnedLeft() && speed > 0) || (isTurnedRight() && speed < 0)) {
            speed = -speed;
        }
        this.speed = speed;
    }

    public boolean isTurnedLeft() {
        return isTurnedLeft;
    }

    public void turnLeft() {
        speed = speed > 0 ? speed * -1 : speed;
        this.isTurnedRight = false;
        this.isTurnedLeft = true;
    }

    public boolean isTurnedRight() {
        return isTurnedRight;
    }

    public void turnRight() {
        speed = speed < 0 ? speed * -1 : speed;
        this.isTurnedLeft = false;
        this.isTurnedRight = true;
    }
    
    public void update(float delta) {
        if (position==null) {
            position = body.getPosition();
            positionBefore = new Vec2(0,0);
        }
        else {
            positionBefore = position.clone();
            position = body.getPosition();
        }
    }

    protected abstract Body createBody(GameWorld world);

}
