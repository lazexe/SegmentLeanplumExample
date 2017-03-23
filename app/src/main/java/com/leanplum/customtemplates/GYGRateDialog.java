package com.leanplum.customtemplates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Maksym Lazarenko on 17.10.16.
 * Skype: lazexe
 */

public class GYGRateDialog {

    public static final String NAME = "GYG rate";
    public static final String DEFAUL_TITLE = "Your last meal";
    public static final String DEFAULT_MESSAGE = "Rate your experience";

    public static void register(/*final DataManager dataManager*/) {
        final String on1StarActionKey = "on 1 Star Selected";
        final String on2StarActionKey = "on 2 Stars Selected";
        final String on3StarActionKey = "on 3 Stars Selected";
        final String on4StarActionKey = "on 4 Stars Selected";
        final String on5StarActionKey = "on 5 Stars Selected";

        final Map<String, String> hatmans = new LinkedHashMap<>();
        hatmans.put("1 star title", "terrible");
        hatmans.put("2 stars title", "bad");
        hatmans.put("3 stars title", "average");
        hatmans.put("4 stars title", "good");
        hatmans.put("5 stars title", "excellent");

        ActionArgs actionArgs = new ActionArgs();
        actionArgs = actionArgs.with(MessageTemplates.Args.TITLE, DEFAUL_TITLE);
        actionArgs = actionArgs.with(MessageTemplates.Args.MESSAGE, DEFAULT_MESSAGE);

        for (Object item : hatmans.entrySet()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) item;
            actionArgs = actionArgs.with(pair.getKey(), pair.getValue());
        }

        final Map<String, String> hatmansBackgrounds = new LinkedHashMap<>();
        hatmansBackgrounds.put("1 star selected bg", "1 star unselected bg");
        hatmansBackgrounds.put("2 stars selected bg", "2 stars unselected bg");
        hatmansBackgrounds.put("3 stars selected bg", "3 stars unselected bg");
        hatmansBackgrounds.put("4 stars selected bg", "4 stars unselected bg");
        hatmansBackgrounds.put("5 stars selected bg", "5 stars unselected bg");

        for (Object item : hatmansBackgrounds.entrySet()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) item;
            actionArgs = actionArgs.withFile(pair.getKey(), null);
            actionArgs = actionArgs.withFile(pair.getValue(), null);
        }

        actionArgs.withAction(on1StarActionKey, null);
        actionArgs.withAction(on2StarActionKey, null);
        actionArgs.withAction(on3StarActionKey, null);
        actionArgs.withAction(on4StarActionKey, null);
        actionArgs.withAction(on5StarActionKey, null);

        Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
                actionArgs, new ActionCallback() {
                    @Override
                    public boolean onResponse(final ActionContext actionContext) {
                        LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
//                                PaymentStatus paymentStatus = dataManager.getPaymentStatusForAnalytics();
//                                if (paymentStatus == null || paymentStatus.order == null) {
//                                    LeanplumTracker.trackRateTriggered();
//                                    return;
//                                }

                                String title = actionContext.stringNamed(MessageTemplates.Args.TITLE);
                                String message = actionContext.stringNamed(MessageTemplates.Args.MESSAGE);

                                ArrayList<String> hatmansTitles = new ArrayList<>();
                                for (String key : hatmans.keySet()) {
                                    hatmansTitles.add(actionContext.stringNamed(key));
                                }

                                ArrayList<Hatman> hatmanIcons = new ArrayList<>();
                                for (Object item : hatmansBackgrounds.entrySet()) {
                                    Map.Entry<String, String> pair = (Map.Entry<String, String>) item;
                                    InputStream inputStream = actionContext.streamNamed(pair.getKey());
                                    Bitmap selected = BitmapFactory.decodeStream(inputStream);
                                    inputStream = actionContext.streamNamed(pair.getValue());
                                    Bitmap unselected = BitmapFactory.decodeStream(inputStream);
                                    hatmanIcons.add(new Hatman(selected, unselected));
                                }

                                AppCompatActivity activity = (AppCompatActivity) LeanplumActivityHelper.getCurrentActivity();
//                                RateDialog rateDialog = RateDialog.newInstance(title, message, hatmansTitles,
//                                        hatmanIcons, new RateHandler() {
//                                            @Override
//                                            public void onStarSelected(int selectedStar) {
//                                                if (selectedStar == 1) actionContext.runActionNamed(on1StarActionKey);
//                                                if (selectedStar == 2) actionContext.runActionNamed(on2StarActionKey);
//                                                if (selectedStar == 3) actionContext.runActionNamed(on3StarActionKey);
//                                                if (selectedStar == 4) actionContext.runActionNamed(on4StarActionKey);
//                                                if (selectedStar == 5) actionContext.runActionNamed(on5StarActionKey);
//                                            }
//                                        });
//                                rateDialog.show(activity);
                            }
                        });

                        return true;
                    }
                });
    }

    public interface RateHandler {
        void onStarSelected(int selectedStar);
    }
}