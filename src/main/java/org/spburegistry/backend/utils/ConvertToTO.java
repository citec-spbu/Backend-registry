package org.spburegistry.backend.utils;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.dto.CommitTO;
import org.spburegistry.backend.dto.CuratorTO;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.ProjectTO;
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
                .role(user.getRole().name())
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
                .degree(student.getDegree().name())
                .sex(student.getSex().name())
                .grade(student.getGrade())
                .educationalProgram(educationalProgramToTO(student.getEducationalProgram()))
                .build();
    }

    // TODO: change this function with all fields that in ProjectTO
    public static ProjectTO projectToTO(Project project) {

        Set<ClinicTO> clinics = project.getClinics().stream()
                .map(ConvertToTO::clinicToTO)
                .collect(Collectors.toSet());

        Set<CommitTO> commits = project.getCommits().stream()
                .map(ConvertToTO::commitToTO)
                .collect(Collectors.toSet());

        Set<String> links = project.getLinks().stream()
                .map(Link::getLink)
                .collect(Collectors.toSet());

        Set<StudentTO> students = project.getStudents().stream()
                .map(ConvertToTO::studentToTO)
                .collect(Collectors.toSet());

        Set<TagTO> tags = project.getTags().stream()
                .map(ConvertToTO::tagToTO)
                .collect(Collectors.toSet());

        return ProjectTO.builder()
                .projectId(project.getId())
                .clinics(clinics)
                .commits(commits)
                .description(project.getDescription())
                .requirements(project.getRequirements())
                .links(links)
                .maxStudents(project.getMaxStudents())
                .name(project.getName())
                .resultLink(project.getResultLink())
                .students(students)
                .tags(tags)
                .workFormat(project.getWorkFormat())
                .build();
    }
}
