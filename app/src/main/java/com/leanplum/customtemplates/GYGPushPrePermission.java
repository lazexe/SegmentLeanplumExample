package com.leanplum.customtemplates;

import android.content.Context;
import android.graphics.Color;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.ActionCallback;

/**
 * Created by Maksym Lazarenko on 30.01.17.
 * Skype: lazexe
 * This is just stub class to have possibility synchronize leanplum dialogs with Android and iOS well.
 * It's important for synchronization so do not delete it.
 * Also it had to be registered in MessageTemplates.register(Context, DataManager);
 */

public class GYGPushPrePermission {

    public static final String NAME = "Push Ask to Ask";

    private static final String DEFAULT_MESSAGE_TEXT = "Tap OK to receive important notifications from our app.";
    private static final String DEFAULT_LATER_BUTTON_TEXT = "Maybe Later";

    public static void register(Context context) {
        ActionArgs actionArgs = new ActionArgs();
        actionArgs = actionArgs.with(MessageTemplates.Args.TITLE_TEXT, MessageTemplates.getApplicationName(context));
        actionArgs = actionArgs.withColor(MessageTemplates.Args.TITLE_COLOR, Color.BLACK);
        actionArgs = actionArgs.with(MessageTemplates.Args.MESSAGE_TEXT, DEFAULT_MESSAGE_TEXT);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.MESSAGE_COLOR, Color.BLACK);
        actionArgs = actionArgs.withFile(MessageTemplates.Args.BACKGROUND_IMAGE, null);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.BACKGROUND_COLOR, Color.LTGRAY);
        actionArgs = actionArgs.with(MessageTemplates.Args.ACCEPT_BUTTON_TEXT, MessageTemplates.Values.OK_TEXT);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.ACCEPT_BUTTON_BACKGROUND_COLOR, Color.LTGRAY);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.ACCEPT_BUTTON_TEXT_COLOR, Color.BLUE);
        actionArgs = actionArgs.withAction(MessageTemplates.Args.CANCEL_ACTION, null);
        actionArgs = actionArgs.with(MessageTemplates.Args.CANCEL_BUTTON_TEXT, DEFAULT_LATER_BUTTON_TEXT);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.CANCEL_BUTTON_BACKGROUND_COLOR, Color.LTGRAY);
        actionArgs = actionArgs.withColor(MessageTemplates.Args.CANCEL_BUTTON_TEXT_COLOR, Color.GRAY);
        actionArgs = actionArgs.with(MessageTemplates.Args.LAYOUT_WIDTH, MessageTemplates.Values.CENTER_POPUP_WIDTH);
        actionArgs = actionArgs.with(MessageTemplates.Args.LAYOUT_HEIGHT, MessageTemplates.Values.CENTER_POPUP_HEIGHT);

        Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, actionArgs, new ActionCallback() {
            @Override
            public boolean onResponse(ActionContext actionContext) {
//                Timber.d("Leanplum [%s] dialog called", NAME);
                return true;
            }
        });
    }
}
