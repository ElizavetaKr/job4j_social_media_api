package ru.job4j.media.repository.file;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.File;

@Repository
public interface FileRepository  extends ListCrudRepository<File, Integer> {
}
