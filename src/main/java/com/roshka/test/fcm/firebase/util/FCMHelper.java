package com.roshka.test.fcm.firebase.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.roshka.test.fcm.firebase.bean.JFCM;
import com.roshka.test.fcm.firebase.bean.SendMessageResponse;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

public class FCMHelper {

    private static org.apache.logging.log4j.Logger logger =
            org.apache.logging.log4j.LogManager.getLogger(FCMHelper.class);

    private static boolean _initialized = false;
    private static GoogleCredential googleCredential;

    private static final String BASE_URL = "https://fcm.googleapis.com";

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = { MESSAGING_SCOPE };

    public static void init(String keyFile) {
        try {
            googleCredential = GoogleCredential
                    .fromStream(new FileInputStream(keyFile))
                    .createScoped(Arrays.asList(SCOPES));
            _initialized = true;
            logger.info("Got Google Credentials initialized. Access Token is: " + googleCredential.getAccessToken());
        } catch (IOException e) {
            logger.error("Can't initialize FCMServiceImpl", e);
        }
    }

    private static String getAccessToken(GoogleCredential googleCredential) throws IOException {
        boolean refreshToken = googleCredential.refreshToken();
        logger.debug("token refreshed: " + refreshToken);
        logger.debug("access token: " + googleCredential.getAccessToken());
        return googleCredential.getAccessToken();
    }

    private static HttpURLConnection getConnection(GoogleCredential googleCredential, String projectId) throws IOException {
        // [START use_access_token]

        String urlString = BASE_URL + "/v1/projects/" + projectId + "/messages:send";

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        String at = getAccessToken(googleCredential);
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + at);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        return httpURLConnection;
    }

    public static SendMessageResponse sendMessage(String projectId, JFCM jFcm)
    {
        logger.debug("Trying to send message to token: " + jFcm.getMessage().getToken());

        if (!_initialized) {
            logger.error("Failed to send message. Google Credentials are not present. Initialize them again");
            return null;
        }

        try {

            // just in case

            HttpURLConnection connection = getConnection(googleCredential, projectId);
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            ObjectMapper objectMapper = new ObjectMapper();

            String val = objectMapper.writeValueAsString(jFcm);
            logger.debug("Sending JSON: " + val);
            if (val != null)
                outputStream.write(val.getBytes(Charset.forName("UTF-8")));
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return objectMapper.readValue(connection.getInputStream(), SendMessageResponse.class);
            } else {
                String errorResponse = IOUtils.toString(new InputStreamReader(connection.getErrorStream()));
                logger.error("Can't send message to firebase:\n" + errorResponse);
            }
        } catch (IOException e) {
            logger.error("IOException while trying to delivery message to Firebase", e);
        }
        return null;
    }

}
