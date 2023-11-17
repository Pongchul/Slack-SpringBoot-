package com.SLACK.backend.channel.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ChannelRepository extends Repository<Channel, Long> {

    Channel save(Channel channel);

    Optional<Channel> findChannelById(Long id);
}
