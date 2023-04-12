package org.spburegistry.backend.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.StudentTO;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testCreateUser() {

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
    void testFindById() {

    }
}
