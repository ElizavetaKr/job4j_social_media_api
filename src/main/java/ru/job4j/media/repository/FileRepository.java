package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.media.model.File;

public interface FileRepository  extends ListCrudRepository<File, Integer> {
}
