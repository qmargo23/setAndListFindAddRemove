package pro.sky.setAndList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.setAndList.exception.BadEmployeeNumberException;
import pro.sky.setAndList.exception.EmployeeAlreadyAddedException;
import pro.sky.setAndList.exception.EmployeeNotFoundException;
import pro.sky.setAndList.exception.EmployeeStorageIsFullException;

@RestController
public class EmployeeController {
    final private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @GetMapping
    public String welcome() {
        return employeeService.welcome();
    }

    @GetMapping(path = "/employee")
    public String information() {
        return employeeService.information();
    }

    @GetMapping(path = "/employee/get")
    public String getEmployee(@RequestParam("Id") Integer Id) {
        try {
            return employeeService.getEmployee(Id);
        } catch (BadEmployeeNumberException e) {
            return "BadEmployeeNumberException - введен неверный индекс колличества сотрудников!";
        }
    }

    @GetMapping(path = "/employee/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeStorageIsFullException e) {
            return "EmployeeStorageIsFullException (превышен лимит количества сотрудников в фирме)";
        } catch (EmployeeAlreadyAddedException e) {
            return "EmployeeAlreadyAddedException (уже есть такой сотрудник)";
        }
        return employee + " - Добавлен ";
    }

    @GetMapping(path = "/employee/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.findEmployee(employee);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFoundException (\"сотрудник отсутствует\")";
        }
        return employeeService.findEmployee(employee);
    }

    @GetMapping(path = "/employee/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName)  {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.removeEmployee(employee);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFoundException (сотрудник отсутствует)";
        }
        return employee+" - Удален";
    }

}

