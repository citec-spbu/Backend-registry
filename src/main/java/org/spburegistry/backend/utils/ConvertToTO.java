package org.spburegistry.backend.utils;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.dto.CommitTO;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.Clinic;
import org.spburegistry.backend.entity.Commit;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.Student;
import org.spburegistry.backend.entity.User;

public class ConvertToTO {

    public static UserTO userToTO(User user) {
        return UserTO.builder()
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole().name())
            .student((user.getStudent() == null) ? null: ConvertToTO.studentToTO(user.getStudent()))
            .client((user.getClient() == null) ? null: ConvertToTO.clientToTO(user.getClient()))
            .build();
    }

    public static ClientTO clientToTO(Client client) {
        return ClientTO.builder()
                .name(client.getUser().getName())
                .email(client.getUser().getEmail())
                .link(client.getLink())
                .orgName(client.getOrganizationName())
                .phone(client.getPhone())
                .build();
    }

    public static ClinicTO clinicToTO(Clinic clinic) {
        return ClinicTO.builder()
                .link(clinic.getLink())
                .name(clinic.getName())
                .faculty(FacultyTO.builder()
                        .link(clinic.getFaculty().getLink())
                        .name(clinic.getFaculty().getName())
                        .build())
                .build();
    }

    public static CommitTO commitToTO(Commit commit) {
        return CommitTO.builder()
                .commitDate(commit.getCommitDate())
                .numDifferences(commit.getNumDifferences())
                .build();
    }

    public static StudentTO studentToTO(Student student) {
        return StudentTO.builder()
                .name(student.getUser().getName())
                .email(student.getUser().getEmail())
                .degree(student.getDegree().name())
                .sex(student.getSex().name())
                .grade(student.getGrade())
                .educationalProgram(EducationalProgramTO.builder()
                        .code(student.getEducationalProgram().getCode())
                        .name(student.getEducationalProgram().getName())
                        .faculty(FacultyTO.builder()
                                .name(student.getEducationalProgram()
                                        .getFaculty().getName())
                                .link(student.getEducationalProgram()
                                        .getFaculty().getLink())
                                .build())
                        .build())
                .build();
    }

    public static ProjectTO projectToTO(Project project) {

        Set<ClinicTO> clinics = project.getClinics().stream()
                .map(clinic -> clinicToTO(clinic))
                .collect(Collectors.toSet());

        Set<CommitTO> commits = project.getCommits().stream()
                .map(commit -> commitToTO(commit))
                .collect(Collectors.toSet());

        Set<String> links = project.getLinks().stream()
                .map(link -> link.getLink())
                .collect(Collectors.toSet());

        Set<String> requirementsForPerformers = project.getRequirementsForPerformers().stream()
                .map(re -> re.getRequirement())
                .collect(Collectors.toSet());

        Set<StudentTO> students = project.getStudents().stream()
                .map(student -> studentToTO(student))
                .collect(Collectors.toSet());

        Set<String> tags = project.getTags().stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toSet());

        return ProjectTO.builder()
                .client(clientToTO(project.getClient()))
                .clinics(clinics)
                .commits(commits)
                .description(project.getDescription())
                .requirements(project.getRequirements())
                .end(project.getEnd())
                .links(links)
                .maxStudents(project.getMaxStudents())
                .name(project.getName())
                .requirementsForPerformers(requirementsForPerformers)
                .resultLink(project.getResultLink())
                .scientificSupervisor(project.getScientificSupervisor())
                .start(project.getStart())
                .students(students)
                .tags(tags)
                .build();
    }
}
