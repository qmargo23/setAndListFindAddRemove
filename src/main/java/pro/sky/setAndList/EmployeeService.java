package pro.sky.setAndList;

import org.springframework.stereotype.Service;
import pro.sky.setAndList.exception.BadEmployeeNumberException;
import pro.sky.setAndList.exception.EmployeeAlreadyAddedException;
import pro.sky.setAndList.exception.EmployeeNotFoundException;
import pro.sky.setAndList.exception.EmployeeStorageIsFullException;

@Service
public interface EmployeeService {
    String welcome();

    String information();

    String getEmployee(Integer Id) throws BadEmployeeNumberException;

    void addEmployee(Employee employee) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;

    String removeEmployee(Employee employee) throws EmployeeNotFoundException;

    String findEmployee(Employee employee) throws EmployeeNotFoundException;

}
