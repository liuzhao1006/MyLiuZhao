package com.sx.trans.supervision.widget.alertdialog;

import com.sx.trans.supervision.widget.alertdialog.effects.BaseEffects;
import com.sx.trans.supervision.widget.alertdialog.effects.FadeIn;
import com.sx.trans.supervision.widget.alertdialog.effects.FlipH;
import com.sx.trans.supervision.widget.alertdialog.effects.FlipV;
import com.sx.trans.supervision.widget.alertdialog.effects.NewsPaper;
import com.sx.trans.supervision.widget.alertdialog.effects.RotateLeft;
import com.sx.trans.supervision.widget.alertdialog.effects.Shake;
import com.sx.trans.supervision.widget.alertdialog.effects.SideFall;
import com.sx.trans.supervision.widget.alertdialog.effects.SlideLeft;
import com.sx.trans.supervision.widget.alertdialog.effects.SlideRight;
import com.sx.trans.supervision.widget.alertdialog.effects.SlideTop;
import com.sx.trans.supervision.widget.alertdialog.effects.Slit;

/**
 * Created by heyaobao on 2014/7/30.
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(com.sx.trans.supervision.widget.alertdialog.effects.SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(com.sx.trans.supervision.widget.alertdialog.effects.Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(com.sx.trans.supervision.widget.alertdialog.effects.RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}
