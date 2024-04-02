package application.controllers;

import application.entity.ChatMessage;
import application.entity.ChatRoom;
import application.entity.Client;
import application.services.ChatRoomService;
import application.services.ClientService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/communication")
public class RestChatController {
    private final ChatRoomService chatRoomService;
    private final ClientService clientService;
    public RestChatController(ChatRoomService chatRoomService, ClientService clientService){
        this.chatRoomService = chatRoomService;
        this.clientService = clientService;
    }

    @GetMapping("/{senderId}/{recipientId}")
    public ResponseEntity<String> getRoomBySenderIdRecipientId(@PathVariable Integer senderId,
                                                  @PathVariable Integer recipientId) {
        List<ChatRoom> chatRoomList = chatRoomService.findAll();
        if (!chatRoomList.isEmpty()) {
            List<ChatRoom> foundRooms = chatRoomList.stream()
                    .filter(room -> room.getClients().size() == 2)
                    .filter(room -> room.getClients().stream().anyMatch(client -> Objects.equals(client.getClientId(), senderId)))
                    .filter(room -> room.getClients().stream().anyMatch(client -> Objects.equals(client.getClientId(), recipientId)))
                    .toList();
            if (!foundRooms.isEmpty()){
                ChatRoom chatRoomFound = foundRooms.get(0);
                return new ResponseEntity<>(makeJSONRoom(chatRoomFound).toString(), HttpStatus.OK);
            }
        }
        Set<Client> clients = new HashSet<>();
        clients.add(clientService.findClientByClientId(senderId));
        clients.add(clientService.findClientByClientId(recipientId));
        ChatRoom chatRoomFound = chatRoomService.create(clients);
        return new ResponseEntity<>(makeJSONRoom(chatRoomFound).toString(), HttpStatus.OK);
    }

    private JSONObject makeJSONRoom(ChatRoom chatRoomFound){
        JSONObject roomJson = new JSONObject();
        roomJson.put("roomId", chatRoomFound.getRoomId());
        JSONArray messagesArray = new JSONArray();
        for (ChatMessage message : chatRoomFound.getMessages()) {
            JSONObject messageJson = new JSONObject();
            messageJson.put("senderEmail", message.getSenderEmail());
            messageJson.put("content", message.getContent());
            messagesArray.put(messageJson);
        }
        roomJson.put("messages", messagesArray);
        return roomJson;
    }
}
