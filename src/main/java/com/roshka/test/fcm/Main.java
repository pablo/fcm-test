package com.roshka.test.fcm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roshka.test.fcm.config.Config;
import com.roshka.test.fcm.firebase.bean.JFCM;
import com.roshka.test.fcm.firebase.bean.Message;
import com.roshka.test.fcm.firebase.bean.Notification;
import com.roshka.test.fcm.firebase.util.FCMHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class Main {

    private static org.apache.logging.log4j.Logger logger =
            org.apache.logging.log4j.LogManager.getLogger(Main.class);

    private static String configFileName = "config.json";


    public static void main(String[] args)
        throws Exception
    {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        logger.info("Running with " + args.length + " arguments");

        try(InputStream fileStream = new FileInputStream(configFileName)) {
            ObjectMapper mapper = new ObjectMapper();
            Config config = mapper.readValue(fileStream, Config.class);

            if (config.getTokens() == null || config.getTokens().size() <= 0) {
                logger.error("Need some tokens in configuration to send messages");
                return;
            }

            if (config.getProjectId() == null || config.getProjectId().length() <= 0) {
                logger.error("Need a non-empty/null project id in configuration to send messages");
                return;
            }

            FCMHelper.init(config.getKeyFile());

            int qty = config.getQty() != null ? config.getQty() : 1;
            boolean doEnumerate = config.isDoEnumerateMessages() != null ? config.isDoEnumerateMessages() : true;

            for (int i=1; i<=qty; i++) {
                String msg = config.getMessage() != null ? config.getMessage() + " " : "Test Message ";
                String title = config.getTitle() != null ? config.getTitle() + " " : "Test Title ";
                if (doEnumerate) {
                    msg += String.valueOf(i);
                    title += String.valueOf(i);
                }

                msg = msg.trim();
                title = title.trim();

                JFCM jFcm = new JFCM();
                Message message = new Message();
                jFcm.setMessage(message);
                if (config.getAdditionalData() != null) {
                    message.setData(config.getAdditionalData());
                } else {
                    message.setData(new HashMap<>());
                }


                Notification notification = new Notification();
                message.setNotification(notification);
                notification.setTitle(title);
                notification.setBody(msg);

                for (String token: config.getTokens()) {
                    logger.debug("Sending message " + i + " to token " + token);
                    message.setToken(token);
                    FCMHelper.sendMessage(config.getProjectId(), jFcm);
                }

            }

        }

    }

}
