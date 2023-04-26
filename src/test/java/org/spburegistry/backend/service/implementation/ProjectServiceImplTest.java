package org.spburegistry.backend.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.repository.ClinicRepo;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

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

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindById() {

    }

    @Test
    void testAddProject_Fail() {

    }

    @Test
    void testFindAll_Fail() {

    }

    @Test
    void testFindById_Fail() {

    }
}
