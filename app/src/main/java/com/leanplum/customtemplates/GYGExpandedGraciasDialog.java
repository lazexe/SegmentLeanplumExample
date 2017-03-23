package com.leanplum.customtemplates;

import android.support.v7.app.AppCompatActivity;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by Maksym Lazarenko on 02.11.16.
 * Skype: lazexe
 */

public class GYGExpandedGraciasDialog {

    public static final String NAME = "GYG expanded gracias";

    public static void register() {
        final String defaultTitle = "Gracias!";
        final String defaultMessage = "Would you like to rate us on the app store?";
        final String onDismissActionKey = "on Dismiss Action";

        ActionArgs actionArgs = new ActionArgs();
        actionArgs = actionArgs.with(MessageTemplates.Args.TITLE, defaultTitle);
        actionArgs = actionArgs.with(MessageTemplates.Args.MESSAGE, defaultMessage);
        actionArgs = actionArgs.withAction(onDismissActionKey, null);

        Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
                actionArgs, new ActionCallback() {
                    @Override
                    public boolean onResponse(final ActionContext actionContext) {
                        LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
                                String title = actionContext.stringNamed(MessageTemplates.Args.TITLE);
                                String message = actionContext.stringNamed(MessageTemplates.Args.MESSAGE);

                                AppCompatActivity activity = (AppCompatActivity) LeanplumActivityHelper.getCurrentActivity();
//                                ExpandedGraciasDialog.newInstance(title, message, new GraciasHandler() {
//                                    @Override
//                                    public void onDismiss() {
//                                        actionContext.runActionNamed(onDismissActionKey);
//                                    }
//                                }).show(activity);
                            }
                        });
                        return true;
                    }
                });
    }

    public interface GraciasHandler {
        void onDismiss();
    }
}
