package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.respoitory.EmployeeEntityRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistence",havingValue = "relational")
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;

	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo identity) {
		var entity = employeeEntityRepository.findById(identity.getValue());
		if (entity.isEmpty())
			return Optional.empty();
		return Optional.of(modelMapper.map(entity.get(), Employee.class));
	}

	@Override
	public boolean exists(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	@Transactional
	public Employee persist(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		var persistedEntity = employeeEntityRepository.save(entity);
		return modelMapper.map(persistedEntity, Employee.class);
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		employeeEntityRepository.deleteById(employee.getIdentity().getValue());
	}

}
