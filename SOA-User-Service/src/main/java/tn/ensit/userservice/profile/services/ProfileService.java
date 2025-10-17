package tn.ensit.userservice.profile.services;

import org.springframework.stereotype.Service;
import tn.ensit.userservice.profile.entities.Profile;
import tn.ensit.userservice.profile.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

// This annotation is required to declare a Service
@Service
public class ProfileService {

    // We are injecting the repository using the constructor (constructor based injection)
    // We don't need to initialize (or create) the repo ourselves, the Spring Framework will do it for us
    // Optionally we could have added an annotation @Autowired to specifically ask Spring to inject this field
    // But we didn't need it because Spring Framework is smart
    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

    public Optional<Profile> getOneProfile(Long id) {
        return repository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return repository.save(profile);
    }
}
