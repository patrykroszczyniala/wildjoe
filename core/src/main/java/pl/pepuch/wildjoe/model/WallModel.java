package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class WallModel extends DynamicModel {

    public WallModel(GameWorld world, Vec2 position) {
        super(world);
        setWidth(0.1f);
        setHeight(world.getWorldHeight() + 1);
        setSpeed(0.1f);
        body = createBody(world);
        setPosition(position);
        origin = body.getPosition().clone();
    }

    @Override
    protected Body createBody(GameWorld world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.KINEMATIC;

        PolygonShape shape = new PolygonShape();
        Vec2[] polygon = new Vec2[4];
        polygon[0] = new Vec2(0, 0);
        polygon[1] = new Vec2(width, 0);
        polygon[2] = new Vec2(width, height);
        polygon[3] = new Vec2(0, height);
        shape.set(polygon, polygon.length);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.density = 30.0f;

        body = world.world().createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setSleepingAllowed(false);
        body.setAwake(true);
        body.setActive(true);

        return body;
    }
}
