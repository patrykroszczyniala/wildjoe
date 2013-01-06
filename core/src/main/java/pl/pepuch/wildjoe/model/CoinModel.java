package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.PlayN;

public class CoinModel extends DynamicModel {

    CircleShape shape;

    public CoinModel(GameWorld world, Vec2 position) {
        super(world);
        setSpeed(0.1f);
        setWidth(1f);
        setHeight(1f);
        body = createBody(world);
        setPosition(position);
        origin = body.getPosition().clone();
    }

    @Override
    protected Body createBody(GameWorld world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.KINEMATIC;

        shape = new CircleShape();
        shape.m_radius = 0.2f;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.density = 0.0f;

        body = world.world().createBody(bodyDef);
        body.createFixture(fixtureDef);

        return body;
    }

    public void setRadius(final float radius) {
        if (shape != null) {
            PlayN.invokeLater(new Runnable() {
                @Override
                public void run() {
                    shape.m_radius = radius;
                }
            });
        }
    }

    public float radius() {
        return shape.m_radius;
    }
}
