package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;

import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HibernateUserRepositoryTests {

  @TestConfiguration
  public static class UserRepositoryTestContextConfiguration {
    @Bean
    public UserRepository userRepository(EntityManager entityManager) {
      return new HibernateUserRepository(entityManager);
    }
  }

  @Autowired
  private UserRepository repository;

  @Test
  public void save_nullUsernameUser_shouldFail() {
    User invalidUser = User.create(null, "sunny@taskagile.com", "MyPassword!");
    Assertions.assertThrows(PersistenceException.class, () -> repository.save(invalidUser));
  }

  @Test
  public void save_validUser_shouldSuccess() {
    String username = "sunny";
    String emailAddress = "sunny@taskagile.com";
    User newUser = User.create(username, emailAddress, "MyPassword!");
    repository.save(newUser);
    Assertions.assertNotNull(newUser.getId(), "New user's id should be generated");
    Assertions.assertNotNull(newUser.getCreatedDate(), "New user's created date should be generated");
    Assertions.assertEquals(username, newUser.getUsername());
    Assertions.assertEquals(emailAddress, newUser.getEmailAddress());
    Assertions.assertEquals("", newUser.getFirstName());
    Assertions.assertEquals("", newUser.getLastName());
  }

  @Test
  public void save_emailAddressAlreadyExist_shouldFail() {
    String username = "sunny";
    String emailAddress = "sunny@taskagile.com";
    User alreadyExist = User.create(username, emailAddress, "MyPassword!");
    repository.save(alreadyExist);

    try {
      User newUser = User.create("new", emailAddress, "MyPassword!");
      repository.save(newUser);
    } catch (Exception e) {
      Assertions.assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
    }
  }

  @Test
  public void findByEmailAddress_exist_shouldReturnResult() {
    String emailAddress = "sunny@taskagile.com";
    String username = "sunny";
    User newUser = User.create(username, emailAddress, "MyPassword!");
    repository.save(newUser);
    User found = repository.findByEmailAddress(emailAddress);
    Assertions.assertEquals(username, found.getUsername(), "Username should match");
  }

  @Test
  public void findByUsername_notExist_shouldReturnEmptyResult() {
    String username = "sunny";
    User user = repository.findByUsername(username);
    Assertions.assertNull(user, "No user should be found");
  }
}
