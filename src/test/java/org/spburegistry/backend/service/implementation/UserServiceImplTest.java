package org.spburegistry.backend.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.EducationalProgram;
import org.spburegistry.backend.entity.Faculty;
import org.spburegistry.backend.entity.Student;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.enums.Degree;
import org.spburegistry.backend.enums.Role;
import org.spburegistry.backend.enums.Sex;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testCreateUser() {
        User user = User.builder()
                .name("hehe")
                .email("null")
                .role(Role.USER)
                .build();

        doReturn(user).when(userRepo).save(user);

        User saveduser = userService.createUser(user.getName(), user.getEmail());

        assertNotNull(saveduser);
        assertEquals(user.getName(), saveduser.getName());
        assertNotNull(saveduser.getId());

        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testFindAll() {
        Client client = Client.builder()
                .link("lya.com")
                .organizationName("LYA")
                .phone("+923857987")
                .build();
        User user1 = User.builder()
                .name("Lya")
                .email("lya@gmail.com")
                .role(Role.ADMIN)
                .student(null)
                .client(client)
                .build();
        client.setUser(user1);
        Student student = Student.builder()
                .sex(Sex.MALE)
                .degree(Degree.BACHELOR)
                .grade(3)
                .educationalProgram(EducationalProgram.builder()
                        .code("21.42.52")
                        .name("ПМИ")
                        .faculty(Faculty.builder()
                                .link("acmp.com")
                                .name("ПМ-ПУ")
                                .build())
                        .build())
                .build();
        User user2 = User.builder()
                .client(null)
                .role(Role.ADMIN)
                .name("Vasya")
                .email("vasya@gmail.com")
                .student(student)
                .build();
        student.setUser(user2);
        List<User> users = List.of(user1, user2);

        doReturn(users).when(userRepo).findAll();

        Iterable<UserTO> usersFromService = userService.findAll();

        assertNotNull(usersFromService);
        assertTrue(usersFromService.iterator().hasNext());
        assertEquals(2, Lists.newArrayList(usersFromService).size());

        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testFindById_Student() {
        Student student = Student.builder()
                .sex(Sex.MALE)
                .degree(Degree.BACHELOR)
                .grade(3)
                .educationalProgram(EducationalProgram.builder()
                        .code("21.42.52")
                        .name("ПМИ")
                        .faculty(Faculty.builder()
                                .link("acmp.com")
                                .name("ПМ-ПУ")
                                .build())
                        .build())
                .build();
        Optional<User> user = Optional.of(User.builder()
                .name("Lya")
                .email("lya@gmail.com")
                .role(Role.ADMIN)
                .student(student)
                .client(null)
                .build());
        student.setUser(user.get());

        doReturn(user).when(userRepo).findById(1l);

        UserTO userFromService = userService.findById(1l);

        assertNotNull(userFromService);
        assertEquals(user.get().getName(), userFromService.getStudent().getName());

        verify(userRepo, times(1)).findById(1l);
    }

    @Test
    void testFindById_Client() {
        Client client = Client.builder()
                .link("lya.com")
                .organizationName("LYA")
                .phone("+923857987")
                .build();
        Optional<User> user = Optional.of(User.builder()
                .name("Lya")
                .email("lya@gmail.com")
                .role(Role.ADMIN)
                .student(null)
                .client(client)
                .build());
        client.setUser(user.get());

        doReturn(user).when(userRepo).findById(1l);

        UserTO userFromService = userService.findById(1l);

        assertNotNull(userFromService);
        assertEquals(user.get().getName(), userFromService.getClient().getName());

        verify(userRepo, times(1)).findById(1l);
    }

    @Test
    void testFindById_Fail() {
        doReturn(Optional.empty()).when(userRepo).findById(1l);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> userService.findById(1l));
        assertEquals(thrown.getMessage(), "User with id " + 1l + " not found");

        verify(userRepo, times(1)).findById(1l);
    }
}
