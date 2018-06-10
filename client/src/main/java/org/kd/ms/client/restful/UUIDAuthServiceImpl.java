package org.kd.ms.client.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UUIDAuthServiceImpl implements UserAuthService {

    @Autowired
    UserCrudService userCrudService;

    @Override
    public Optional<String> login(String username, String password) {
        final String uuid = UUID.randomUUID().toString();
        final User user = new User(uuid, username, password);

        userCrudService.save(user);
        return Optional.of(uuid);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userCrudService.find(token);
    }

    @Override
    public void logout(User user) {

    }
}
