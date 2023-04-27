package org.spburegistry.backend.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.Clinic;
import org.spburegistry.backend.entity.EducationalProgram;
import org.spburegistry.backend.entity.Faculty;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.Student;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.enums.Degree;
import org.spburegistry.backend.enums.Role;
import org.spburegistry.backend.enums.Sex;
import org.spburegistry.backend.enums.WorkFormat;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.repository.ClinicRepo;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.repository.TagRepo;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepo projectRepo;

    @MockBean
    private ClientRepo clientRepo;

    @MockBean
    private ClinicRepo clinicRepo;

    @MockBean
    private TagRepo tagRepo;

    @Test
    void testAddProject() {
        Client client = Client.builder()
                .link("lya.com")
                .organizationName("LYA")
                .phone("+923857987")
                .build();
        User user = User.builder()
                .name("Lya")
                .email("lya@gmail.com")
                .role(Role.ADMIN)
                .student(null)
                .client(client)
                .build();
        client.setUser(user);
        ProjectRequestTO projectRequestTO = ProjectRequestTO.builder()
                .name("ololo")
                .description("lalal")
                .requirements("elele")
                .clientId(1l)
                .clinicsIds(new HashSet<Long>(List.of(1l)))
                .tags(new HashSet<TagTO>(List.of(
                        TagTO.builder().name("Python").build())))
                .workFormat(WorkFormat.DISTANT)
                .build();

        Project project = Project.builder()
                .description(projectRequestTO.getDescription())
                .end(projectRequestTO.getEnd())
                .maxStudents(projectRequestTO.getMaxStudents())
                .name(projectRequestTO.getName())
                .requirements(projectRequestTO.getRequirements())
                .scientificSupervisor(projectRequestTO.getScientificSupervisor())
                .start(projectRequestTO.getStart())
                .workFormat(projectRequestTO.getWorkFormat())
                .client(client)
                .clinics(new HashSet<Clinic>(List.of(
                        Clinic.builder()
                                .name("it")
                                .link("it.cmo")
                                .faculty(Faculty.builder()
                                        .link("acmp.com")
                                        .name("ПМ-ПУ")
                                        .build())
                                .build())))
                .tags(new HashSet<Tag>(List.of(Tag.builder().name("Python").build())))
                .build();

        doReturn(Tag.builder().name("Python").build())
                .when(tagRepo)
                .findByNameIgnoreCase("Python");
        doReturn(
                Clinic.builder()
                        .name("it")
                        .link("it.cmo")
                        .faculty(Faculty.builder()
                                .link("acmp.com")
                                .name("ПМ-ПУ")
                                .build())
                        .build())
                .when(clinicRepo)
                .getReferenceById(1l);

        doReturn(Optional.of(client)).when(clientRepo).findById(1l);
        doReturn(project).when(projectRepo).save(project);

        ProjectTO savedProject = projectService.addProject(projectRequestTO);

        assertNotNull(savedProject);
        assertEquals(projectRequestTO.getName(), savedProject.getName());
        assertEquals(client.getUser().getName(), savedProject.getClient().getName());

        verify(projectRepo, times(1)).save(project);
        verify(tagRepo, times(1))
                .findByNameIgnoreCase("Python");
        verify(clinicRepo, times(1)).getReferenceById(1l);
        verify(clientRepo, times(1)).findById(1l);

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
        Faculty faculty1 = Faculty.builder()
                .link("acmp.com")
                .name("ПМ-ПУ")
                .build();
        Student student = Student.builder()
                .sex(Sex.MALE)
                .degree(Degree.BACHELOR)
                .grade(3)
                .educationalProgram(EducationalProgram.builder()
                        .code("21.42.52")
                        .name("ПМИ")
                        .faculty(faculty1)
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
        Clinic clinic = Clinic.builder()
                .name("dfjgjk")
                .link("skdfjls")
                .faculty(faculty1)
                .build();
        List<Tag> tags = List.of(Tag.builder().name("Python").build(),
                Tag.builder().name("C++").build());
        List<Project> projects = List.of(
                Project.builder()
                        .name("kdslk")
                        .description("dfjgdfljg")
                        .requirements("jdfkgljld")
                        .workFormat(WorkFormat.DISTANT)
                        .start(new Date())
                        .end(new Date())
                        .maxStudents(4)
                        .scientificSupervisor("slfdhgkjfg")
                        .resultLink("skdfgdfg.com")
                        .client(client)
                        .students(new HashSet<Student>(List.of(student)))
                        .clinics(new HashSet<Clinic>(List.of(clinic)))
                        .tags(new HashSet<Tag>(tags))
                        .build(),
                Project.builder()
                        .name("anim")
                        .description("desk")
                        .requirements("waffle")
                        .workFormat(WorkFormat.FULL_TIME)
                        .start(new Date())
                        .end(new Date())
                        .maxStudents(5)
                        .scientificSupervisor("vasya")
                        .resultLink("anim.com")
                        .client(client)
                        .students(new HashSet<Student>(List.of(student)))
                        .clinics(new HashSet<Clinic>(List.of(clinic)))
                        .tags(new HashSet<Tag>(tags))
                        .build());

        doReturn(projects).when(projectRepo).findAll();

        Iterable<ProjectTO> projectsFromService = projectService.findAll();

        assertNotNull(projectsFromService);
        assertTrue(projectsFromService.iterator().hasNext());
        assertEquals(2, Lists.newArrayList(projectsFromService).size());
        assertEquals(projects.get(0).getName(), projectsFromService.iterator().next().getName());

        verify(projectRepo, times(1)).findAll();
    }

    @Test
    void testFindById() {
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
        Faculty faculty1 = Faculty.builder()
                .link("acmp.com")
                .name("ПМ-ПУ")
                .build();
        Student student = Student.builder()
                .sex(Sex.MALE)
                .degree(Degree.BACHELOR)
                .grade(3)
                .educationalProgram(EducationalProgram.builder()
                        .code("21.42.52")
                        .name("ПМИ")
                        .faculty(faculty1)
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
        Clinic clinic = Clinic.builder()
                .name("dfjgjk")
                .link("skdfjls")
                .faculty(faculty1)
                .build();
        List<Tag> tags = List.of(Tag.builder().name("Python").build(),
                Tag.builder().name("C++").build());
        Project project = Project.builder()
                .name("anim")
                .description("desk")
                .requirements("waffle")
                .workFormat(WorkFormat.FULL_TIME)
                .start(new Date())
                .end(new Date())
                .maxStudents(5)
                .scientificSupervisor("vasya")
                .resultLink("anim.com")
                .client(client)
                .students(new HashSet<Student>(List.of(student)))
                .clinics(new HashSet<Clinic>(List.of(clinic)))
                .tags(new HashSet<Tag>(tags))
                .build();

        doReturn(Optional.of(project)).when(projectRepo).findById(1l);

        ProjectTO projectFromService = projectService.findById(1l);

        assertNotNull(projectFromService);
        assertEquals(project.getName(), projectFromService.getName());
        assertEquals(project.getClient().getUser().getName(), projectFromService.getClient().getName());

        verify(projectRepo, times(1)).findById(1l);
    }

    @Test
    void testFindById_Fail() {
        doReturn(Optional.empty()).when(projectRepo).findById(1l);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> projectService.findById(1l));
        assertEquals(thrown.getMessage(), "Project with id " + 1l + " not found");

        verify(projectRepo, times(1)).findById(1l);
    }

}
