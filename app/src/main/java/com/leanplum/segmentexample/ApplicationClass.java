package com.leanplum.segmentexample;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;
import com.leanplum.customtemplates.MessageTemplates;
import com.leanplum.segment.LeanplumIntegration;
import com.segment.analytics.Analytics;

/**
 * Created by fede on 1/18/17.
 */

public class ApplicationClass extends LeanplumApplication {

    private static final String SEGMENT_WRITE_KEY = "Oe0g4hDlH4VagUDjp2B41FZUinKtvgTx";

    @Override
    public void onCreate(){
        super.onCreate();

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);
        LeanplumActivityHelper.deferMessagesForActivities(MainActivity.class);

        // Important message for Leanplum support
        // When I add line below method LeanplumActivityHelper.deferMessagesForActivities(MainActivity.class); do not work
        MessageTemplates.register(this);

        Analytics analytics = new Analytics.Builder(getApplicationContext(), SEGMENT_WRITE_KEY)
                .use(LeanplumIntegration.FACTORY).build();

        analytics.onIntegrationReady("Leanplum",
                new Analytics.Callback() {
                    @Override
                    public void onReady(Object instance) {
                        Leanplum.track("test");
                    }
                });

    }
}
