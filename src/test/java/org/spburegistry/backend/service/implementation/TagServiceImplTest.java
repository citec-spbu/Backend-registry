package org.spburegistry.backend.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spburegistry.backend.ExceptionHandler.exception.EntityAlreadyExistsException;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.repository.TagRepo;
import org.spburegistry.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @MockBean
    private TagRepo tagRepo;

    @Test
    void testAddTag() {
        TagTO tag = TagTO.builder().name("hehe").build();

        TagTO savedTag = tagService.addTag(tag);

        assertNotNull(savedTag);
        assertEquals(tag.getName(), savedTag.getName());
        assertNotNull(savedTag.getTagId());

        verify(tagRepo, times(1)).findByNameIgnoreCase(tag.getName());
        verify(tagRepo, times(1)).save(Tag.builder()
                                        .name(tag.getName())
                                        .build());
    }

    @Test
    void testAddTag_Fail() {
        TagTO tag = TagTO.builder().name("Python").build();

        doReturn(Tag.builder().name("Python").build()).when(tagRepo).findByNameIgnoreCase("Python");

        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class,
                () -> tagService.addTag(tag));
        assertEquals("Tag with name " + tag.getName() + " already exists", thrown.getMessage());

        verify(tagRepo, times(1)).findByNameIgnoreCase(tag.getName());
    }

    @Test
    void testFindAll() {
        List<Tag> tags = List.of(Tag.builder().name("Python").build(),
                Tag.builder().name("C++").build());

        doReturn(tags).when(tagRepo).findAll();

        Iterable<TagTO> tagsFromService = tagService.findTagsByParameters(Optional.empty(), Optional.empty(), Optional.empty());

        assertNotNull(tagsFromService);
        assertEquals(tags.get(0).getName(), tagsFromService.iterator().next().getName());

        verify(tagRepo, times(1)).findAll();
    }

    @Test
    void testFindTagById() {
        Optional<Tag> tag = Optional.of(Tag.builder().name("Python").build());

        doReturn(tag).when(tagRepo).findById(1L);

        TagTO tagFromService = tagService.findTagById(1L);

        assertNotNull(tagFromService);
        assertEquals(tag.get().getName(), tagFromService.getName());

        verify(tagRepo, times(1)).findById(1L);
    }

    @Test
    void testFindTagById_Fail() {
        doReturn(Optional.empty()).when(tagRepo).findById(1L);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> tagService.findTagById(1L));
        assertEquals(thrown.getMessage(), "Tag with id " + 1L + " not found");

        verify(tagRepo, times(1)).findById(1L);
    }

    @Test
    void testFindTagBySubstring() {
        List<Tag> tag = List.of(Tag.builder().name("Python").build(),
                Tag.builder().name("PYTHON").build());

        doReturn(tag).when(tagRepo).findByNameContainsIgnoreCase("Py");

        Iterable<TagTO> tagFromService = tagService.findTagsByParameters(Optional.empty(), Optional.of("Py"), Optional.empty());

        assertNotNull(tagFromService);
        assertTrue(tagFromService.iterator().hasNext());
        assertEquals(2, Lists.newArrayList(tagFromService).size());

        verify(tagRepo, times(1)).findByNameContainsIgnoreCase("Py");
    }

    @Test
    void testFindTagBySubstring_Fail() {
        doReturn(new ArrayList<Tag>()).when(tagRepo).findByNameContainsIgnoreCase("Py");

        Iterable<TagTO> tagFromService = tagService.findTagsByParameters(Optional.empty(), Optional.of("Py"), Optional.empty());

        assertNotNull(tagFromService);
        assertFalse(tagFromService.iterator().hasNext());

        verify(tagRepo, times(1)).findByNameContainsIgnoreCase("Py");
    }

    @Test
    void testFindTagSortByWeight() {
        List<Tag> tags = new ArrayList<>(List.of(
                Tag.builder()
                        .name("kanal")
                        .projects(Set.of(Project.builder()
                                .name("Lalala")
                                .build()))
                        .build(),
                Tag.builder()
                        .name("Python")
                        .projects(Set.of(
                            Project.builder()
                                .name("Lalala")
                                .build(),
                            Project.builder()
                                .name("Nanana")
                                .build()))
                        .build()));

        doReturn(tags).when(tagRepo).findAll();

        Iterable<TagTO> tagsFromService = tagService.findTagsByParameters(Optional.of(true), Optional.empty(), Optional.empty());

        assertNotNull(tagsFromService);
        assertTrue(tagsFromService.iterator().hasNext());
        assertEquals(2, Lists.newArrayList(tagsFromService).size());
        assertEquals("Python", tagsFromService.iterator().next().getName());

        verify(tagRepo, times(1)).findAll();
    }

    @Test
    void testFindTagSortByWeight_Fail() {
        doReturn(new ArrayList<Tag>()).when(tagRepo).findAll();

        Iterable<TagTO> tagFromService = tagService.findTagsByParameters(Optional.of(true), Optional.empty(), Optional.empty());

        assertNotNull(tagFromService);
        assertFalse(tagFromService.iterator().hasNext());

        verify(tagRepo, times(1)).findAll();
    }
}
