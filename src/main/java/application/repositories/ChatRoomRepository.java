package application.repositories;

import application.entity.ChatRoom;
import application.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findFirstByOrderByRoomIdDesc();
}
