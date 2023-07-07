package pro.sky.setAndList;

import org.springframework.stereotype.Service;
import pro.sky.setAndList.exception.BadEmployeeNumberException;
import pro.sky.setAndList.exception.EmployeeAlreadyAddedException;
import pro.sky.setAndList.exception.EmployeeNotFoundException;
import pro.sky.setAndList.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int maxId = 6;// max 6 employees

    List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("Кирилл", "Здоров"),
            new Employee("Игорь", "Горохов"),
            new Employee("Михаил", "Авдеев"),
            new Employee("Павел", "Богданов"),
            new Employee("Василий", "Орехов")
    ));

    public String welcome() {
        String s = "<h2>Добро пожаловать!</h2>\n" +
                "                 Работа с коллекцией сотрудников (Employee). \n" +
                "                 (/employee   -   корневая папка). \n" +
                "                 <h4>Пример использования:</h4>\n" +
                "                 <ul>\n" +
                "                 <li>Для добавления нового сотрудника введите /employee/add?firstName=Ivan&lastName=Ivanov </li>\n" +
                "                 <li>Для удаления сотрудника введите/employee/remove?firstName=Ivan&lastName=Ivanov </li>\n" +
                "                 <li>Чтобы найти сотрудника введите /employee/find?firstName=Ivan&lastName=Ivanov </li>\n" +
                "                 </ul>";
        return s;
    }

    public String information() {
        String s = "<h2> Вы находитесь в папке /employee</h2>\n" +
                "<h4><li>Для \"вывода\" сотрудника введите значение его Id (0-6) в строку /get?Id=0</li>\n</h4> " +
                "<li>Для добавления нового сотрудника введите /add?firstName=Ivan&lastName=Ivanov </li>\n" +
                "<li>Для удаления сотрудника введите /employee/remove?firstName=Ivan&lastName=Ivanov </li>\n" +
                "<li>Чтобы найти сотрудника введите /employee/find?firstName=Ivan&lastName=Ivanov </li>\n" +
                "</ul>";
        return s;
    }

    public String getEmployee(Integer Id) throws BadEmployeeNumberException {
        final Employee employee;
        if (Id >= employeeList.size() || Id < 0) {
            throw new BadEmployeeNumberException("BadEmployeeNumberException");//неверный индекс колличества сотрудников!
        }
        employee = employeeList.get(Id);
        return "по индексу: " + Id + " -  находится " + employee;
    }

    public void addEmployee(Employee employee) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        int Id = employeeList.size();
        if (Id > maxId) {
            throw new EmployeeStorageIsFullException();//ArrayIsFull - превышен лимит количества сотрудников в фирме
        }
        employeeList.add(employee);
        for (int i = 0; i < employeeList.size() - 1; i++) {
            if (employee.equals(employeeList.get(i))) {
                employeeList.remove(employee);
                throw new EmployeeAlreadyAddedException(); //уже есть такой сотрудник
            }
            Id++;
        }
    }

    @Override
    public String findEmployee(Employee employee) throws EmployeeNotFoundException {
        for (Employee value : employeeList) {
            if (value.hashCode() == employee.hashCode()) {
                return employee + " - Найден";
            }
        }
        throw new EmployeeNotFoundException("сотрудник отсутствует");
    }


    @Override
    public String removeEmployee(Employee employee) throws EmployeeNotFoundException {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employee.equals(employeeList.get(i))) {
                employeeList.remove(employee);
                return " Удален";
            }
        }
        throw new EmployeeNotFoundException("сотрудник отсутствует");
    }
}
