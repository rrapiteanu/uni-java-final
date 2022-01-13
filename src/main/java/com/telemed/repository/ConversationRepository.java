package com.telemed.repository;

import com.telemed.model.Author;
import com.telemed.model.Conversation;
import com.telemed.model.Medic;
import com.telemed.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConversationRepository extends  JpaRepository<Conversation, Integer> {
    Conversation findConversationById(Long id);
    List<Conversation> findConversationsByMedic(Medic medic);
    List<Conversation> findConversationsByMedicAndIsClosed(Medic medic, Boolean isClosed);
    List<Conversation> findConversationsByPacient(Pacient pacient);
    List<Conversation> findConversationsByPacientAndIsClosed(Pacient pacient, Boolean isClosed);
    List<Conversation> findConversationsByMedicAndPacient(Medic medic, Pacient pacient);
    List<Conversation> findAll();
}


