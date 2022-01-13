package com.telemed.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false", name = "is_closed")
    private Boolean isClosed = false;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicId")
    private Medic medic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pacientId")
    private Pacient pacient;
}