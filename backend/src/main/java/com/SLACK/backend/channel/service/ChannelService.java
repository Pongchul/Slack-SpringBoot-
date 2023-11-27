package com.SLACK.backend.channel.service;

import com.SLACK.backend.channel.domain.Channel;
import com.SLACK.backend.channel.domain.ChannelRepository;
import com.SLACK.backend.channel.dto.request.ChannelRequest;
import com.SLACK.backend.channel.dto.response.ChannelIdResponse;
import com.SLACK.backend.channel.dto.response.ChannelResponse;
import com.SLACK.backend.channel.exception.ChannelErrorCode;
import com.SLACK.backend.channel.exception.ChannelException;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.workspace.domain.Workspace;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceIdResponse;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import com.SLACK.backend.workspace.exception.WorkspaceErrorCode;
import com.SLACK.backend.workspace.exception.WorkspaceException;
import com.SLACK.backend.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelService {

    private ChannelRepository channelRepository;
    private WorkspaceService workspaceService;


    @Transactional
    public ChannelIdResponse create(Long workspaceId, ChannelRequest request) {
        Workspace workspacesById = workspaceService.findWorkspacesById(workspaceId);
        Channel channel = new Channel(request.getChannelId(), request.getName(), request.isPrivate(), workspacesById);
        Channel savedChannel = channelRepository.save(channel);

        return ChannelIdResponse.toResponse(savedChannel);
    }

    public Channel findChannelById(Long id) {
        return channelRepository.findChannelById(id)
                .orElseThrow(() -> new ChannelException(ChannelErrorCode.CHANNEL_IS_NOT_EXIST));
    }

    public List<ChannelResponse> findChannels(Long channelId) {
        Channel channel = this.findChannelById(channelId);
        List<Channel> channels = channel.getChannels();

        return ChannelResponse.channelResponses(channels);
    }

}
