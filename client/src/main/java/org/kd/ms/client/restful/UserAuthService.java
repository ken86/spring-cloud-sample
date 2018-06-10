package org.kd.ms.client.restful;

import java.util.Optional;

public interface UserAuthService {

    Optional<String> login(String username, String password);

    Optional<User> findByToken(String token);

    void logout(User user);
}
