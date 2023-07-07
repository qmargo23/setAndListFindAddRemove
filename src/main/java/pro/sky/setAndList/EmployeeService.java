package pro.sky.setAndList;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final int maxId = 10;

    public static String welcome() {
        String s = "<h2>Добро пожаловать!</h2>\n" +
                "                 Работа с коллекцией сотрудников (Employee). \n" +
                "                 (/employee   -   корневая папка). \n" +
                "                 <h4>Пример использования:</h4>\n" +
                "                 <ul>\n" +
                "                 <li>Для добавления нового сотрудника введите /employee/add?firstName=Ivan&lastName=Ivanov</li>\n" +
                "                 <li>Для удаления сотрудника введите </li>\n" +
                "                 <li>Чтобы найти сотрудника введите;</li>\n" +
                "                 </ul>";
        return s;
    }
    public static String information() {
        String s = "<h2> Вы находитесь в папке /employee</h2>\n" +
                "<li>Для добавления нового сотрудника введите /add?firstName=Ivan&lastName=Ivanov</li>\n" +
                "<li>Для удаления сотрудника введите </li>\n" +
                "<li>Чтобы найти сотрудника введите;</li>\n" +
                "</ul>";
        return s;
    }

}
