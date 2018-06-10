package net.mossol.util;

import net.mossol.model.LineReplyRequest;
import net.mossol.model.LocationInfo;
import net.mossol.model.Message.LocationMessage;
import net.mossol.model.Message.TextMessage;

public final class MessageBuildUtil {

    private static final String NO_LOCATION = "멍멍! 등록된 위치 정보가 없어요 ㅜ";
    private static final String EXIST_LOCATION = "멍멍! 등록된 위치 정보가 있어요!";

    private MessageBuildUtil() {

    }

    private static TextMessage buildTextMessage(String content) {
        TextMessage replyMessage = new TextMessage(content);
        replyMessage.setType("text");
        return replyMessage;
    }

    private static LocationMessage buildLocationMessage(String title, String address,
                                                        double latitude, double longitude) {
        LocationMessage locationMessage = new LocationMessage(title, address, latitude, longitude);
        locationMessage.setType("location");
        return locationMessage;
    }

    public static LineReplyRequest sendTextMessage(String token, String content) {
        LineReplyRequest replyRequest = new LineReplyRequest(token);
        replyRequest.setMessage(buildTextMessage(content));
        return replyRequest;
    }

    public static LineReplyRequest sendLocationMessage(String token, String title,
                                                       String address, double latitude, double longitude) {
        LineReplyRequest replyRequest = new LineReplyRequest(token);
        replyRequest.setMessage(buildLocationMessage(title, address, latitude, longitude));
        return replyRequest;
    }

    public static LineReplyRequest sendFoodMessage(String token, String content) {
        LineReplyRequest replyRequest = new LineReplyRequest(token);
        replyRequest.setMessage(buildTextMessage(content));
        replyRequest.setMessage(buildTextMessage(NO_LOCATION));
        return replyRequest;
    }

    public static LineReplyRequest sendFoodMessage(String token, String content, LocationInfo locationInfo) {
        LineReplyRequest replyRequest = new LineReplyRequest(token);
        replyRequest.setMessage(buildTextMessage(content));
        replyRequest.setMessage(buildTextMessage(EXIST_LOCATION));
        replyRequest.setMessage(buildLocationMessage(locationInfo.getTitle(), locationInfo.getTitle(),
                                                     locationInfo.getLatitude(), locationInfo.getLongitude()));
        return replyRequest;
    }
}