package application.services;

import application.entity.ChatMessage;
import application.repositories.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void save(ChatMessage message){
        chatMessageRepository.save(message);
    }
}
