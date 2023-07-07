package pro.sky.setAndList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping
    public String welcome() {
        return EmployeeService.welcome();
    }

    @GetMapping(path = "/employee")
    public String information() {
        return EmployeeService.information();
    }

    @GetMapping(path = "/employee/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        return "Добавлен сотрудник: ";
    }
}
