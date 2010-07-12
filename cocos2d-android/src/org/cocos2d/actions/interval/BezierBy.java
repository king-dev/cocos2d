package org.cocos2d.actions.interval;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.CGPoint;

//
// BezierBy
//

public class BezierBy extends IntervalAction {

    private CCBezierConfig config;
    private CGPoint startPosition;

    public static BezierBy action(float t, CCBezierConfig c) {
        return new BezierBy(t, c);
    }

    protected BezierBy(float t, CCBezierConfig c) {
        super(t);
        config = c;
        startPosition = CGPoint.make(0, 0);
    }

    @Override
    public IntervalAction copy() {
        return new BezierBy(duration, config);
    }

    @Override
    public void start(CCNode aTarget) {
        super.start(aTarget);
        startPosition = target.getPosition();
    }

    @Override
    public void update(float t) {
        float xa = config.startPosition.x;
        float xb = config.controlPoint_1.x;
        float xc = config.controlPoint_2.x;
        float xd = config.endPosition.x;

        float ya = config.startPosition.y;
        float yb = config.controlPoint_1.y;
        float yc = config.controlPoint_2.y;
        float yd = config.endPosition.y;

        float x = bezierat(xa, xb, xc, xd, t);
        float y = bezierat(ya, yb, yc, yd, t);
        target.setPosition(CGPoint.make(startPosition.x + x, startPosition.y + y));
    }

    // Bezier cubic formulae :
    //	((1 - t) + t)3 = 1 expands to (1 - t)3 + 3t(1-t)2 + 3t2(1 - t) + t3 = 1
    private static float bezierat(float a, float b, float c, float d, float t) {
        return (float) (Math.pow(1 - t, 3) * a +
                3 * t * (Math.pow(1 - t, 2)) * b +
                3 * Math.pow(t, 2) * (1 - t) * c +
                Math.pow(t, 3) * d);
    }

    @Override
    public IntervalAction reverse() {
        // TODO: reverse it's not working as expected
        CCBezierConfig r = new CCBezierConfig();
        r.startPosition = CGPoint.ccpNeg(config.startPosition);
        r.endPosition = CGPoint.ccpNeg(config.endPosition);
        r.controlPoint_1 = CGPoint.ccpNeg(config.controlPoint_1);
        r.controlPoint_2 = CGPoint.ccpNeg(config.controlPoint_2);

        return new BezierBy(duration, r);
    }
}
