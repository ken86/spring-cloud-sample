package org.kd.ms.client.restful;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImMemoryUserService implements UserCrudService{

    Map<String, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        return userMap.put(user.getId(), user);
    }

    @Override
    public Optional<User> find(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userMap.values().stream().filter(u -> Objects.equals(username, u.getUsername())).findFirst();
    }
}
