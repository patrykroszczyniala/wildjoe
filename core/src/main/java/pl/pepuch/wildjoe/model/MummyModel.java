package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import pl.pepuch.wildjoe.core.world.GameWorld;

public class MummyModel extends DynamicModel {

    public MummyModel(GameWorld world, Vec2 position) {
        super(world);
        setSpeed(2.0f);
        setOriginSpeed(2.0f);
        setWidth(1f);
        setHeight(1.8f);
        body = createBody(world);
        setPosition(position);
        origin = body.getPosition().clone();
    }

    @Override
    protected Body createBody(GameWorld world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2, new Vec2(0, 0), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.density = 30.0f;

        body = world.world().createBody(bodyDef);
        body.createFixture(fixtureDef);
        // body will be active all the time
        body.setSleepingAllowed(false);
        body.setActive(true);
        // wake up body
        body.applyForce(body.getLocalCenter(), body.getLocalCenter());
        // player will not change his angle !
        body.setFixedRotation(true);

        return body;
    }
}
