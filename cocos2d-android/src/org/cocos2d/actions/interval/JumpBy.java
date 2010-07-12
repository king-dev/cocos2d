package org.cocos2d.actions.interval;

import android.graphics.PointF;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGPoint;

//
// JumpBy
//

public class JumpBy extends IntervalAction {
    protected PointF startPosition;
    protected PointF delta;
    protected float height;
    protected int jumps;

    public static JumpBy action(float time, float x, float y, float height, int jumps) {
        return new JumpBy(time, x, y, height, jumps);
    }

    protected JumpBy(float time, float x, float y, float h, int j) {
        super(time);
        startPosition = new PointF();
        delta = new PointF(x, y);
        height = h;
        jumps = j;
    }

    @Override
    public IntervalAction copy() {
        return new JumpBy(duration, delta.x, delta.y, height, jumps);
    }

    @Override
    public void start(CCNode aTarget) {
        super.start(aTarget);
        CGPoint pnt = target.getPosition();
        startPosition.x = pnt.x;
        startPosition.y = pnt.y;
    }

    @Override
    public void update(float t) {
        float y = height * (float) Math.abs(Math.sin(t * (float) Math.PI * jumps));
        y += delta.y * t;
        float x = delta.x * t;
        target.setPosition(CGPoint.make(startPosition.x + x, startPosition.y + y));
    }

    @Override
    public IntervalAction reverse() {
        return new JumpBy(duration, -delta.x, -delta.y, height, jumps);
    }
}
