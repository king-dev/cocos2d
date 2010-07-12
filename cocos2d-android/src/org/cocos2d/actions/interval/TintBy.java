package org.cocos2d.actions.interval;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.ccColor3B;

//
// TintBy
//

public class TintBy extends IntervalAction {
    protected int deltaR, deltaG, deltaB;
    protected int fromR, fromG, fromB;

    public static TintBy action(float t, int r, int g, int b) {
        return new TintBy(t, r, g, b);
    }

    protected TintBy(float t, int r, int g, int b) {
        super(t);
        deltaR = r;
        deltaG = g;
        deltaB = b;
    }

    @Override
    public IntervalAction copy() {
        return new TintBy(duration, deltaR, deltaG, deltaB);
    }

    @Override
    public void start(CCNode aTarget) {
        super.start(aTarget);

        ccColor3B c = ((CCNode.CocosNodeRGBA) target).getColor();
        fromR = c.g;
        fromG = c.g;
        fromB = c.b;
    }

    @Override
    public void update(float t) {
        CCNode.CocosNodeRGBA tn = (CCNode.CocosNodeRGBA) target;
        tn.setColor(new ccColor3B((int) (fromR + deltaR * t), (int) (fromG + deltaG * t), (int) (fromB + deltaB * t)));
    }

    @Override
    public IntervalAction reverse() {
        return new TintBy(duration, -deltaR, -deltaG, -deltaB);
    }
}
