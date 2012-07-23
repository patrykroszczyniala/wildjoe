package pl.pepuch.wildjoe.core.body;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.JointDef;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Image;
import playn.core.ImageLayer;

public class NormalBlock extends GameBody {
	
	public NormalBlock(Vec2 position) {
		image = assets().getImage("images/normalBlock.png");
		layer = graphics().createImageLayer(image);
		
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.STATIC;
	    PolygonShape shape = new PolygonShape();
	    Vec2[] polygon = new Vec2[4];
	    polygon[0] = new Vec2(0, 0);
	    polygon[1] = new Vec2((image.width()*GameWorld.physUnitPerScreenUnit()), 0);
		polygon[2] = new Vec2((image.width()*GameWorld.physUnitPerScreenUnit()), (image.height()*GameWorld.physUnitPerScreenUnit()));
		polygon[3] = new Vec2(0, (image.height()*GameWorld.physUnitPerScreenUnit()));
		shape.set(polygon, polygon.length);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.95f;
		fixtureDef.restitution = 0.2f;
	
		setPosition(position);
		setLayer(layer);
		layer.setTranslation(position.x/GameWorld.physUnitPerScreenUnit(), position.y/GameWorld.physUnitPerScreenUnit());
	}
	
}
