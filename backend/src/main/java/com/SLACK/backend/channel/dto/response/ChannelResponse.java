package com.SLACK.backend.channel.dto.response;

import com.SLACK.backend.channel.domain.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ChannelResponse {

    private Long channelId;
    private String name;
    private boolean isPrivate;
    private Long workspaceId;

    public ChannelResponse(Long channelId, String name, boolean isPrivate) {
        this.channelId = channelId;
        this.name = name;
        this.isPrivate = isPrivate;
    }

    public static ChannelResponse toResponse(Channel channel) {
        return new ChannelResponse(channel.getId(), channel.getName(), channel.isPrivate());
    }



    public static List<ChannelResponse> channelResponses(List<Channel> channels) {
        return channels.stream()
                .map(ChannelResponse::toResponse)
                .collect(Collectors.toList());
    }
}
