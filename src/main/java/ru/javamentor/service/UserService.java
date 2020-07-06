package ru.javamentor.service;

import org.springframework.security.core.GrantedAuthority;
import ru.javamentor.model.User;
import ru.javamentor.dto.UserDTO;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс для работы с пользователями в системе
 *
 * @version 1.0
 * @autor Java Mentor
 */
public interface UserService {
    /**
     * метод для добавления пользователя
     *
     * @param user - пользователь которого необходимо добавить
     * @return boolean удалось добавить или нет
     */
    public boolean addUser(User user);

    /**
     * метод для добавления пользователя из социальной сети
     *
     * @param user - пользователь которого необходимо добавить
     * @return boolean удалось добавить или нет
     */
    public boolean addUserThroughSocialNetworks(User user);

    /**
     * метод для получения всего списка пользователей
     *
     * @return List пользоватей
     */
    public List<User> getAllUsers();

    /**
     * метод для получения пользователя по уникальному id
     *
     * @param id - уникальный id пользователя
     * @return User - пользователь
     */
    public User getUserById(Long id);

    /**
     * метод для обновления пользовательских данных
     *
     * @param user - пользователь с обновленными данными
     * @return boolean - удалось обновить данные или нет
     */
    public boolean updateUser(User user);

    /**
     * метод для удаления пользователя
     *
     * @param id - уникальный id пользователя в системе
     * @return void
     */
    public void removeUser(Long id);

    /**
     * метод для активации пользователя в системе
     *
     * @param code - активационный код пользователя
     * @return boolean - удалось активировать пользователя или нет
     */
    boolean activateUser(String code);

    /**
     * метод для получения пользователя по его электронной почте
     *
     * @param email -  электронная почта пользователя
     * @return User пользователь
     */
    public User getUserByEmail(String email);

    /**
     * метод для получения пользователя по его username
     *
     * @param username
     * @return User пользователь
     */
    User getUserByUsername(String username);

    /**
     * метод для получения всех пользователей у которых имя пользователя начинается с @param pattern
     *
     * @param pattern - email пользователя
     * @return List<User> - объект представляющий список пользователей
     */
    List<UserDTO> getUsersLikeUsername(String pattern);

    /**
     * метод для отправки письма подтверждения на почту
     *
     * @param user - пользователь которому нужно отправить письмо
     * @return void
     */
    void sendCode(User user);

    /**
     * метод для отправки получения пользователя по его активационному коду
     *
     * @param code - строковое представление активационного кода
     * @return User - пользователь
     */
    User findByActivationCode(String code);

    /**
     * метод для входа пользователя в систему by Spring Security
     *
     * @param username    - логие
     * @param password    - пароль
     * @param authorities - роли by Spring Security
     * @return void
     */
    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);
//    void login(String username, String password, Set<Role> roles);

}
