package com.SLACK.backend.channel.dto.response;

import com.SLACK.backend.channel.domain.Channel;
import com.SLACK.backend.workspace.domain.Workspace;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResponse {

    @NotNull
    private Long channelId;

    @NotNull
    private String name;

    private boolean isPrivate;

    private Workspace workspaceId;


    public static ChannelResponse toResponse(Channel channel) {
        return new ChannelResponse(channel.getId(), channel.getName(), channel.isPrivate(), channel.getWorkspaceId());
    }



    public static List<ChannelResponse> channelResponses(List<Channel> channels) {
        return channels.stream()
                .map(ChannelResponse::toResponse)
                .collect(Collectors.toList());
    }
}
