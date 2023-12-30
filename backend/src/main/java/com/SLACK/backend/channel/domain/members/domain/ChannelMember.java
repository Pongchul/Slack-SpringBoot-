package com.SLACK.backend.channel.domain.members.domain;

import com.SLACK.backend.channel.domain.Channel;
import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_channel_member")
@Getter
@Entity
@NoArgsConstructor
public class ChannelMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member host;


}
