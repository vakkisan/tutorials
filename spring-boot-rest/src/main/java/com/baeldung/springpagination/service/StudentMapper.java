package com.baeldung.springpagination.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.baeldung.persistence.dao.Student;
import com.baeldung.springpagination.model.StudentDto;

@StudentMapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class StudentMapper {
	public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	@Mapping(target = "studentName", source = "studentName")
	@Mapping(target = "addressDto", source = "addressDto")
	@Mapping(target = "phoneNumber", source = "phoneNumber")
	public abstract Student fromDto(StudentDto dto);

	@Mapping(target = "studentName", source = "studentName")
	@Mapping(target = "addressDto", source = "addressDto")
	@Mapping(target = "phoneNumber", source = "phoneNumber")
	public abstract StudentDto toDto(Student dto);

}
