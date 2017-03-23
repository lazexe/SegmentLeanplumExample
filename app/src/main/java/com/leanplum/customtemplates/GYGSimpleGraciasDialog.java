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

public class GYGSimpleGraciasDialog {

    public static final String NAME = "GYG simple gracias";

    public static void register() {
        final String defaultTitle = "Gracias!";
        final String closeDelayKey = "Close after";

        ActionArgs actionArgs = new ActionArgs();
        actionArgs = actionArgs.with(MessageTemplates.Args.TITLE, defaultTitle);
        actionArgs = actionArgs.with(closeDelayKey, 2);

        Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
                actionArgs, new ActionCallback() {
                    @Override
                    public boolean onResponse(final ActionContext actionContext) {
                        LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
                                String title = actionContext.stringNamed(MessageTemplates.Args.TITLE);
                                int closeDelay = actionContext.numberNamed(closeDelayKey).intValue();

                                AppCompatActivity activity = (AppCompatActivity) LeanplumActivityHelper.getCurrentActivity();
//                                SimpleGraciasDialog.newInstance(title, closeDelay).show(activity);
                            }
                        });
                        return true;
                    }
                });
    }
}
