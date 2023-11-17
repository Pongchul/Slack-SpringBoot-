package com.SLACK.backend.channel.controller;

import com.SLACK.backend.channel.dto.request.ChannelRequest;
import com.SLACK.backend.channel.dto.response.ChannelIdResponse;
import com.SLACK.backend.channel.dto.response.ChannelResponse;
import com.SLACK.backend.channel.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/channels")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelIdResponse> create(Long workspaceId, ChannelRequest request) {
        ChannelIdResponse response = channelService.create(workspaceId, request);
        URI uri = URI.create("/api/{workspaceId}/channels/" + response.getChannelId());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ChannelResponse>> findChannels(Long channelId) {
        List<ChannelResponse> responses = channelService.findChannels(channelId);

        return ResponseEntity.ok().body(responses);
    }
}
