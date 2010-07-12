package org.cocos2d.menus;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import javax.microedition.khronos.opengles.GL10;

/**
 * MenuItemSprite accepts CocosNode<CocosNodeRGBA> objects as items.
 * The images has 3 different states:
 * - unselected image
 * - selected image
 * - disabled image
 *
 * @since v0.8.0
 */
public class MenuItemSprite extends MenuItem implements CCNode.CocosNodeRGBA {

    protected CCNode normalImage_;
    protected CCNode selectedImage_;
    protected CCNode disabledImage_;

    public static MenuItemSprite item(CCNode normalSprite, CCNode selectedSprite, CCNode disabledSprite) {
        return new MenuItemSprite(normalSprite, selectedSprite, disabledSprite, null, null);
    }

    public static MenuItemSprite item(CCNode normalSprite, CCNode selectedSprite, CCNode disabledSprite, CCNode target, String selector) {
        return new MenuItemSprite(normalSprite, selectedSprite, disabledSprite, target, selector);
    }

    protected MenuItemSprite(CCNode normalSprite, CCNode selectedSprite, CCNode disabledSprite, CCNode target, String selector) {
        super(target, selector);
        normalImage_ = normalSprite;
        selectedImage_ = selectedSprite;
        disabledImage_ = disabledSprite;
        CGSize size = normalImage_.getContentSize();
        setContentSize(size);
    }

    @Override
    public void draw(GL10 gl) {
        if (isEnabled_) {
            if (isSelected_)
                selectedImage_.draw(gl);
            else
                normalImage_.draw(gl);

        } else {
            if (disabledImage_ != null)
                disabledImage_.draw(gl);

                // disabled image was not provided
            else
                normalImage_.draw(gl);
        }
    }

    // CocosNodeRGBA protocol

    public void setOpacity(int opacity) {
        ((CocosNodeRGBA) normalImage_).setOpacity(opacity);
        ((CocosNodeRGBA) selectedImage_).setOpacity(opacity);
        ((CocosNodeRGBA) disabledImage_).setOpacity(opacity);
    }

    public void setColor(ccColor3B color) {
        ((CocosNodeRGBA) normalImage_).setColor(color);
        ((CocosNodeRGBA) selectedImage_).setColor(color);
        ((CocosNodeRGBA) disabledImage_).setColor(color);
    }

    public ccColor3B getColor() {
        return ((CocosNodeRGBA) normalImage_).getColor();
    }

    public int getOpacity() {
        return ((CocosNodeRGBA) normalImage_).getOpacity();
    }
}
