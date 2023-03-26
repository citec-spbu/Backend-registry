package org.spburegistry.backend.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.dto.CommitTO;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

        private final ProjectRepo projectRepo;

        @Autowired
        public ProjectServiceImpl(ProjectRepo projectRepo) {
                this.projectRepo = projectRepo;
        }

        @Override
        public Iterable<ProjectTO> findAll() {
                return projectRepo.findAll().stream().map(project -> convertToTO(project))
                                .collect(Collectors.toSet());
        }

        @Override
        public ProjectTO findById(long id) {
                Project project = projectRepo.findById(id).orElseThrow(
                                () -> new NoSuchEntityException("Project with id " + id + " not found"));
                return convertToTO(project);

        }

        private static ProjectTO convertToTO(Project project) {
                ClientTO client = ClientTO.builder()
                                .email(project.getClient().getEmail())
                                .link(project.getClient().getLink())
                                .name(project.getClient().getName())
                                .orgName(project.getClient().getOrganizationName())
                                .phone(project.getClient().getPhone())
                                .build();
                Set<ClinicTO> clinics = project.getClinics().stream()
                                .map(clinic -> ClinicTO.builder()
                                                .link(clinic.getLink())
                                                .name(clinic.getName())
                                                .faculty(FacultyTO.builder()
                                                                .link(clinic.getFaculty().getLink())
                                                                .name(clinic.getFaculty().getName())
                                                                .build())
                                                .build())
                                .collect(Collectors.toSet());
                Set<CommitTO> commits = project.getCommits().stream()
                                .map(commit -> CommitTO.builder()
                                                .commitDate(commit.getCommitDate())
                                                .numDifferences(commit.getNumDifferences())
                                                .build())
                                .collect(Collectors.toSet());
                Set<String> links = project.getLinks().stream()
                                .map(link -> link.getLink())
                                .collect(Collectors.toSet());
                Set<String> requirementsForPerformers = project.getRequirementsForPerformers().stream()
                                .map(re -> re.getRequirement())
                                .collect(Collectors.toSet());
                Set<StudentTO> students = project.getStudents().stream()
                                .map(student -> StudentTO.builder()
                                                .degree(student.getDegree().name())
                                                .name(student.getName())
                                                .sex(student.getSex().name())
                                                .email(student.getEmail())
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
                                                .build())
                                .collect(Collectors.toSet());

                Set<String> tags = project.getTags().stream()
                                .map(tag -> tag.getName())
                                .collect(Collectors.toSet());
                return ProjectTO.builder()
                                .client(client)
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
