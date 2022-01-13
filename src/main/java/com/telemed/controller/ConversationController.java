package com.telemed.controller;
import com.telemed.dto.*;
import com.telemed.model.Conversation;
import com.telemed.model.Message;
import com.telemed.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@Api(value = "/conversations", tags = "Conversations")
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private final ConversationService service;

    public ConversationController(ConversationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a conversation",
            notes = "Get conversation based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The conversation with the entered id does not exist")
    })
    public ResponseEntity<Conversation> get(@PathVariable Long id) {
        if (service.getOne(id) == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(service.getOne(id));
    }

    @GetMapping("/pacienti/{pacientId}")
    @ApiOperation(value = "Get conversations by patient id",
            notes = "Get conversations based on the patient id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The patient with the entered id does not exist")
    })
    public ResponseEntity<List<Conversation>> getConversationsForPacient(@PathVariable Long pacientId) {
        return ResponseEntity
                .ok()
                .body(service.getConversationsForPacientId(pacientId));
    }

    @GetMapping("/medics/{medicId}")
    @ApiOperation(value = "Get conversations by medic",
            notes = "Get conversations based on the medic id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The medic with the entered id does not exist")
    })
    public ResponseEntity<List<Conversation>> getConversationsForMedic(@PathVariable Long medicId) {
        return ResponseEntity
                .ok()
                .body(service.getConversationsForMedicId(medicId));
    }

    @PostMapping("/new")
    @ApiOperation(value = "Create new conversation",
            notes = "Create new conversation between patient and medic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The conversation was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Conversation> createConversation(@RequestBody NewConversationRequest conversationDto) {
        return ResponseEntity
                .ok()
                .body(service.add(conversationDto));
    }

    @PutMapping("/{conversationId}/close_conversation")
    @ApiOperation(value = "Close conversation",
            notes = "Close conversation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The conversation was successfully closed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Conversation> closeConversation(@PathVariable Long conversationId) {
        return ResponseEntity
                .ok()
                .body(service.closeConversation(conversationId));
    }

    @PostMapping("/{conversationId}/new_message")
    @ApiOperation(value = "Add new message to conversation",
            notes = "Add new message to conversation by given author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The message was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Message> addMessage(@PathVariable Long conversationId,@RequestBody NewMessageRequest messageRequest) {
        return ResponseEntity
                .ok()
                .body(service.addMessage(messageRequest, conversationId));
    }
}