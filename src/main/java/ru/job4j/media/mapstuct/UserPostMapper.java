package ru.job4j.media.mapstuct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.media.dto.UserPostDto;
import ru.job4j.media.model.User;

@Mapper(componentModel = "spring")
public interface UserPostMapper {
    @Mapping(target = "userId", source = "id")
    UserPostDto getUserPostDtoFromEntity(User user);
}
