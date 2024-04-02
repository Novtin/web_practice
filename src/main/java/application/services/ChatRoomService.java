package application.services;

import application.entity.ChatMessage;
import application.entity.ChatRoom;
import application.entity.Client;
import application.repositories.ChatMessageRepository;
import application.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public void save(ChatRoom room){
        chatRoomRepository.save(room);
    }

    public List<ChatRoom> findAll(){
        return chatRoomRepository.findAll();
    }

    public ChatRoom create(Set<Client> clients) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoomRepository.save(chatRoom);
        Set<Client> clientSet = chatRoom.getClients();
        clientSet.addAll(clients);
        chatRoomRepository.save(chatRoom);
        return chatRoomRepository.findFirstByOrderByRoomIdDesc();
    }
}
