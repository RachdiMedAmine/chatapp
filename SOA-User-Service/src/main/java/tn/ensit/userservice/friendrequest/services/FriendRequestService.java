package tn.ensit.userservice.friendrequest.services;

import org.springframework.stereotype.Service;
import tn.ensit.userservice.friendrequest.entities.FriendRequest;
import tn.ensit.userservice.friendrequest.repositories.FriendRequestRepository;
import tn.ensit.userservice.user.entities.User;
import tn.ensit.userservice.user.services.UserService;

import java.util.List;

@Service
public class FriendRequestService {

    private final FriendRequestRepository repository;

    private final UserService userService;

    public FriendRequestService(FriendRequestRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public FriendRequest sendFriendRequest(Long requesterId, Long receiverId) {
        User requester = userService.findById(requesterId);
        User receiver = userService.findById(receiverId);
        FriendRequest friendRequest = new FriendRequest(requester, receiver);
        return repository.save(friendRequest);
    }

    public List<FriendRequest> getPendingRequests(Long receiverId) {
        User receiver = userService.findById(receiverId);
        return repository.findByReceiverAndAccepted(receiver, false);
    }

    public FriendRequest acceptRequest(Long requestId) {
        FriendRequest request = repository.findById(requestId).orElseThrow();
        request.setAccepted(true);
        return repository.save(request);
    }
}
