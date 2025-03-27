package ru.job4j.media.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.File;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @AfterEach
    public void setUp() {
        fileRepository.deleteAll();
    }

    @Test
    void whenSaveFileThenFindById() {
        File file = new File();
        file.setName("fileName");
        file.setPath("filePath");
        fileRepository.save(file);
        Optional<File> foundFile = fileRepository.findById(file.getId());
        assertThat(foundFile).isPresent();
        assertThat(foundFile.get().getName()).isEqualTo("fileName");
    }

    @Test
    void whenFindAllThenReturnAllFile() {
        File file1 = new File();
        file1.setName("fileName1");
        file1.setPath("filePath1");
        File file2 = new File();
        file2.setName("fileName2");
        file2.setPath("filePath2");
        fileRepository.save(file1);
        fileRepository.save(file2);
        List<File> files = fileRepository.findAll();
        assertThat(files).hasSize(2);
        assertThat(files).extracting(File::getName).contains("fileName1", "fileName2");
        assertThat(files).extracting(File::getPath).contains("filePath1", "filePath2");
    }
}
