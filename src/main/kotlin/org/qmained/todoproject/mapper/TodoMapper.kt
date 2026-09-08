package org.qmained.todoproject.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.MappingTarget
import org.qmained.todoproject.models.dto.TodoDto
import org.qmained.todoproject.models.entity.TodoModel
import java.time.Instant

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = [Instant::class])
interface TodoMapper {
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "updatedAt", expression = "java(Instant.now())")
    fun todoDtoToTodoModel(todoDto: TodoDto): TodoModel

    @Mapping(target = "updatedAt", expression = "java(Instant.now())")
    fun updateTodoModelFromDto(todoDto: TodoDto, @MappingTarget todoModel: TodoModel)
}
