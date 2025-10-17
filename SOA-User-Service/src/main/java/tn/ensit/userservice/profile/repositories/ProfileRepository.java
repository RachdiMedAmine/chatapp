package tn.ensit.userservice.profile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ensit.userservice.profile.entities.Profile;


// We just have to extend the JPARepository then Spring Data will automatically create the CRUD operations
// on its own (see the usage of the repository in the service)

// This annotation is used to declare a Repository
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> { }