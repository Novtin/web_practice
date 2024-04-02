package application.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @ManyToMany
    @JoinTable(
            name = "chat_room_clients",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    @Fetch(FetchMode.JOIN)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChatMessage> messages = new ArrayList<>();

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}