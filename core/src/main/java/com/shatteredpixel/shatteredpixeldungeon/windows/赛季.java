

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;

import java.util.ArrayList;

public class 赛季 extends Window {

    private static final int WIDTH = 120;
    private static final int TTL_HEIGHT = 16;
    private static final int BTN_HEIGHT = 16;
    private static final int GAP = 1;

    private boolean editable;
    private ArrayList<CheckBox> boxes;

    public 赛季(int checked,boolean editable) {

        super();

        this.editable = editable;

        RenderedTextBlock title = PixelScene.renderTextBlock(Messages.get(this, "title"), 12);
        title.hardlight(TITLE_COLOR);
        title.setPos(
                (WIDTH - title.width()) / 2,
                (TTL_HEIGHT - title.height()) / 2
        );
        PixelScene.align(title);
        add(title);

        boxes = new ArrayList<>();

        float pos = TTL_HEIGHT;
        for (int i=0;i<赛季设置.NAME_IDS.length;i++) {

            final String
                    赛季= 赛季设置.NAME_IDS[i];

            CheckBox cb = new CheckBox(Messages.titleCase(Messages.get(赛季设置.class,赛季)));
            cb.checked((checked&赛季设置.MASKS[i])!=0);
            cb.active =editable;

            if (i > 0) {
                pos += GAP;
            }
            cb.setRect(0, pos, WIDTH - 16, BTN_HEIGHT);

            add(cb);
            boxes.add(cb);

            IconButton info = new IconButton(Icons.get(Icons.INFO)) {
                @Override
                protected void onClick() {
                    super.onClick();
                    ShatteredPixelDungeon.scene().add(
                            new WndMessage(Messages.get(赛季设置.class,赛季+"_desc"))
                    );
                }
            };
            info.setRect(cb.right(), pos, 16, BTN_HEIGHT);
            add(info);

            pos = cb.bottom();
        }

        resize(WIDTH, (int) pos);
    }

    @Override
    public void onBackPressed() {

        if (editable) {
            int value = 0;
            for (int i = 0; i < boxes.size(); i++) {
                if (boxes.get(i).checked()) {
                    value |= 赛季设置.MASKS[i];
                }
            }
            SPDSettings.赛季(value);
        }

        super.onBackPressed();
    }
}