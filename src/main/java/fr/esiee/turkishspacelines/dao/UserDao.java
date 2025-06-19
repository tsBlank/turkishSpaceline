package fr.esiee.turkishspacelines.dao;

import fr.esiee.turkishspacelines.model.User;
import java.util.Optional;
import java.util.List;

public interface UserDao {

    Optional<User> findByEmailAndPassword(String email, String password);

}

