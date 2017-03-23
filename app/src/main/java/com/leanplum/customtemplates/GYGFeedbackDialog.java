package com.leanplum.customtemplates;

import android.support.v7.app.AppCompatActivity;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by Maksym Lazarenko on 01.11.16.
 * Skype: lazexe
 */

public class GYGFeedbackDialog {

    public static final String NAME = "GYG feedback";

    public static void register() {
        final String defaultTitle = "Que?";
        final String defaultMessage = "What went wrong? Leave your feedback below";

        final String onSendFeedbackActionKey = "on Send Feedback Action";
        final String onDismissFeedbackAction = "on Dismiss Action";

        ActionArgs actionArgs = new ActionArgs();
        actionArgs = actionArgs.with(MessageTemplates.Args.TITLE, defaultTitle);
        actionArgs = actionArgs.with(MessageTemplates.Args.MESSAGE, defaultMessage);

        actionArgs = actionArgs.withAction(onSendFeedbackActionKey, null);
        actionArgs = actionArgs.withAction(onDismissFeedbackAction, null);

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
//                                FeedbackDialog feedbackDialog = FeedbackDialog.newInstance(title, message,
//                                        new FeedbackHandler() {
//                                            @Override
//                                            public void onSendFeedback() {
//                                                actionContext.runActionNamed(onSendFeedbackActionKey);
//                                            }
//
//                                            @Override
//                                            public void onDismiss() {
//                                                actionContext.runActionNamed(onDismissFeedbackAction);
//                                            }
//                                        });
//                                feedbackDialog.show(activity);
                            }
                        });
                        return true;
                    }
                });
    }

    public interface FeedbackHandler {
        void onSendFeedback();
        void onDismiss();
    }
}
