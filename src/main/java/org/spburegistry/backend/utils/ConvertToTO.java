package org.spburegistry.backend.utils;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.dto.CommitTO;
import org.spburegistry.backend.dto.CuratorTO;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.LinkTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.RoleTO;
import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.dto.SupervisorTO;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.Clinic;
import org.spburegistry.backend.entity.Commit;
import org.spburegistry.backend.entity.Curator;
import org.spburegistry.backend.entity.EducationalProgram;
import org.spburegistry.backend.entity.Faculty;
import org.spburegistry.backend.entity.Link;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.ProjectRole;
import org.spburegistry.backend.entity.Student;
import org.spburegistry.backend.entity.Supervisor;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.entity.User;

public class ConvertToTO {

    private ConvertToTO() {
    }

    public static TagTO tagToTO(Tag tag) {
        return TagTO.builder()
                .tagId(tag.getId())
                .name(tag.getName())
                .build();
    }

    public static FacultyTO facultyToTO(Faculty faculty) {
        return FacultyTO.builder()
                .facultyId(faculty.getId())
                .link(faculty.getLink())
                .name(faculty.getName())
                .build();
    }

    public static EducationalProgramTO educationalProgramToTO(EducationalProgram educationalProgram) {
        return EducationalProgramTO.builder()
                .educationalProgramId(educationalProgram.getId())
                .code(educationalProgram.getCode())
                .name(educationalProgram.getName())
                .faculty(facultyToTO(educationalProgram.getFaculty()))
                .build();
    }

    public static CuratorTO curatorToTO(Curator curator) {
        return CuratorTO.builder()
                .curatorId(curator.getId())
                .name(curator.getUser().getName())
                .email(curator.getUser().getEmail())
                .link(curator.getLink())
                .phone(curator.getPhone())
                .build();
    }

    public static SupervisorTO supervisorToTO(Supervisor supervisor) {
        return SupervisorTO.builder()
                .supervisorId(supervisor.getId())
                .name(supervisor.getUser().getName())
                .email(supervisor.getUser().getEmail())
                .link(supervisor.getLink())
                .phone(supervisor.getPhone())
                .build();
    }

    public static UserTO userToTO(User user) {
        return UserTO.builder()
                .userId(user.getId())
                .role(user.getRole())
                .student((user.getStudent() == null) ? null
                        : ConvertToTO.studentToTO(user.getStudent()))
                .client((user.getClient() == null) ? null : ConvertToTO.clientToTO(user.getClient()))
                .curator((user.getCurator() == null) ? null : ConvertToTO.curatorToTO(user.getCurator()))
                .supervisor((user.getSupervisor() == null) ? null : ConvertToTO.supervisorToTO(user.getSupervisor()))
                .build();
    }

    public static ClientTO clientToTO(Client client) {
        return ClientTO.builder()
                .clientId(client.getId())
                .name(client.getUser().getName())
                .email(client.getUser().getEmail())
                .link(client.getLink())
                .orgName(client.getOrganizationName())
                .phone(client.getPhone())
                .build();
    }

    public static ClinicTO clinicToTO(Clinic clinic) {
        return ClinicTO.builder()
                .clinicId(clinic.getId())
                .link(clinic.getLink())
                .name(clinic.getName())
                .faculty(facultyToTO(clinic.getFaculty()))
                .build();
    }

    public static CommitTO commitToTO(Commit commit) {
        return CommitTO.builder()
                .commitId(commit.getId())
                .commitDate(commit.getCommitDate())
                .numDifferences(commit.getNumDifferences())
                .build();
    }

    public static StudentTO studentToTO(Student student) {
        return StudentTO.builder()
                .studentId(student.getId())
                .name(student.getUser().getName())
                .email(student.getUser().getEmail())
                .degree(student.getDegree())
                .sex(student.getSex())
                .grade(student.getGrade())
                .educationalProgram(educationalProgramToTO(student.getEducationalProgram()))
                .build();
    }

    public static RoleTO roleToTO(ProjectRole projectRole) {
        return RoleTO.builder()
                .role(projectRole.getRole())
                .student(studentToTO(projectRole.getStudent()))
                .roleId(projectRole.getId())
                .projectId(projectRole.getProject().getId())
                .build();
    }

    public static LinkTO linkToTO(Link link) {
        return LinkTO.builder()
                .linkId(link.getId())
                .name(link.getName())
                .link(link.getLink())
                .projectId(link.getProject().getId())
                .build();
    }

    private static RoleTO getRoleFromStudent(Student student, Long projectId) {
        return student.getRoles().stream()
                        .filter(role -> role.getProject().getId() == projectId)
                        .map(ConvertToTO::roleToTO)
                        .findFirst().get();
    }

    public static ProjectTO projectToTO(Project project) {

        Set<ClinicTO> clinics = project.getClinics().stream()
                .map(ConvertToTO::clinicToTO)
                .collect(Collectors.toSet());

        Set<ClientTO> clients = project.getClients().stream()
                .map(ConvertToTO::clientToTO)
                .collect(Collectors.toSet());

        Set<CuratorTO> curators = project.getCurators().stream()
                .map(ConvertToTO::curatorToTO)
                .collect(Collectors.toSet());

        Set<SupervisorTO> supervisors = project.getSupervisors().stream()
                .map(ConvertToTO::supervisorToTO)
                .collect(Collectors.toSet());

        Set<LinkTO> links = project.getLinks().stream()
                .map(ConvertToTO::linkToTO)
                .collect(Collectors.toSet());

        Set<TagTO> tags = project.getTags().stream()
                .map(ConvertToTO::tagToTO)
                .collect(Collectors.toSet());

        Set<RoleTO> projectRoles = project.getStudents().stream()
                .map(student -> getRoleFromStudent(student, project.getId()))
                .collect(Collectors.toSet());

        Set<Long> linkedProjectsIds = project.getLinkedProjects().stream()
                .map(Project::getId)
                .collect(Collectors.toSet());

        return ProjectTO.builder()
                .projectId(project.getId())
                .name(project.getName())
                .tags(tags)
                .clinics(clinics)
                .clients(clients)
                .curators(curators)
                .supervisors(supervisors)
                .description(project.getDescription())
                .links(links)
                .projectRoles(projectRoles)
                .requirements(project.getRequirements())
                .requirementsForPerformers(project.getRequirementsForPerformers())
                .startTime(project.getStartTime())
                .startFiling(project.getStartFiling())
                .endFiling(project.getEndFiling())
                .startImplementation(project.getStartImplementation())
                .endImplementation(project.getEndImplementation())
                .startDefense(project.getStartDefense())
                .endDefense(project.getEndDefense())
                .status(project.getStatus())
                .linkedProjectsIds(linkedProjectsIds)
                .workFormat(project.getWorkFormat())
                .maxStudents(project.getMaxStudents())
                .build();
    }
}
