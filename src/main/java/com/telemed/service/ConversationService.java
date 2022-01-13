package com.telemed.service;

import com.telemed.dto.*;
import com.telemed.model.*;
import com.telemed.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MedicRepository medicRepository;
    private final PacientRepository pacientRepository;
    private final AuthorRepository authorRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository,
                        MedicRepository medicRepository, PacientRepository pacientRepository,
                        AuthorRepository authorRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository =  messageRepository;
        this.medicRepository = medicRepository;
        this.pacientRepository = pacientRepository;
        this.authorRepository = authorRepository;
    }

    public List<Conversation> getConversationsForPacientId(Long id) {
        Pacient pacient = pacientRepository.findPacientById((id));
        if (pacient == null) {
            throw new NoSuchElementException("No pacient with id %d found".formatted(id));
        }

        List<Conversation> conversations = conversationRepository.findConversationsByPacient(pacient);
        return conversations;
    }

    public List<Conversation> getConversationsForMedicId(Long id) {
        Medic medic = medicRepository.findMedicById((id));
        if (medic == null) {
            throw new NoSuchElementException("No medic with id %d found".formatted(id));
        }

        List<Conversation> conversations = conversationRepository.findConversationsByMedic(medic);
        return conversations;
    }

    public Conversation add(NewConversationRequest request) {
        Medic medic = medicRepository.findMedicById(request.getMedicId());
        Pacient pacient = pacientRepository.findPacientById(request.getPacientId());

        if(medic == null || pacient == null){
            throw new NoSuchElementException("No medic or pacient found");
        }

        Conversation conversation = new Conversation();
        conversation.setPacient(pacient);
        conversation.setMedic(medic);
        conversation.setCreatedAt(OffsetDateTime.now());
        Conversation saved = conversationRepository.save(conversation);
        return saved;
    }


    public Message addMessage(NewMessageRequest request, Long conversationId) {

        Conversation conversation = conversationRepository.findConversationById(conversationId);
        Author author = authorRepository.findAuthorById(request.getAuthorId());

        if(author == null || conversation == null){
            throw new NoSuchElementException("Invalid author or conversation");
        }

        Message newMessage = new Message();
        newMessage.setText(request.getText());
        newMessage.setAuthor(author);
        newMessage.setConversation(conversation);
        newMessage.setCreatedAt(OffsetDateTime.now());

        Message saved = messageRepository.save(newMessage);
        return saved;
    }

    public Conversation closeConversation(Long id) {
        Conversation conversation = conversationRepository.findConversationById(id);
        if(conversation == null){
            throw new NoSuchElementException("Invalid conversation");
        }

        if(conversation.getIsClosed() == true){
            throw new NoSuchElementException("Conversation is already closed");
        }

        conversation.setIsClosed(true);
        Conversation saved = conversationRepository.save(conversation);
        return saved;
    }

    public Conversation getOne(Long id) {
        return conversationRepository.findConversationById(id);
    }
}