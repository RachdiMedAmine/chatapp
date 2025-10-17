package tn.ensit.userservice.friendrequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ensit.userservice.friendrequest.entities.FriendRequest;
import tn.ensit.userservice.user.entities.User;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiverAndAccepted(User receiver, boolean accepted);
}
