package com.baeldung.springpagination.service;

import com.baeldung.persistence.dao.Student;
import com.baeldung.persistence.dao.StudentDao;
import com.baeldung.springpagination.model.StudentDto;

import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	private final StudentDao studentDao;

	@Autowired
	private ModelMapper modelMapper;

	public AccountService(StudentDao balanceDao) {
		this.studentDao = balanceDao;
	}

	@Transactional
	public void createAccount(StudentDto balanceDto) {

		Student balance = this.modelMapper.map(balanceDto, Student.class);

		studentDao.save(balance);
	}


	public StudentDto findStudentById(String id) {
		Optional<Student> balance = studentDao.findById(id);
		return StudentMapper.INSTANCE.toDto(balance.get());
	}

	public ResponseInfo deleteStudent(String id) {
		Objects.requireNonNull(id);
		studentDao.deleteById(id);
		return new ResponseInfo(String.valueOf(Literals.SUCCESS_CD), Literals.SUCCESS_MESSAGE);
	}

}