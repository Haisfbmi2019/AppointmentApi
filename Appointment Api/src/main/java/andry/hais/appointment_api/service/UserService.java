package andry.hais.appointment_api.service;

import andry.hais.appointment_api.models.User;

public interface UserService {

    User findOne(String email);

    User save(User user);

    User update(User user);

}
