package com.SLACK.backend.channel.dto.response;

import com.SLACK.backend.channel.domain.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChannelIdResponse {

    private Long channelId;

    public static ChannelIdResponse toResponse(Channel channel) {
        return new ChannelIdResponse(channel.getId());
    }
}
