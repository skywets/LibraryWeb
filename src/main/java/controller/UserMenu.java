package controller;


import entity.User;
import lombok.Getter;
import lombok.Setter;
import service.UserService;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Getter
@Setter
public class UserMenu {

    Scanner sc = new Scanner(System.in);
    EducationMenu educationMenu;
    UserService userService;
    User user;
    private long id;
    private String login;
    private String password;
    private long educationId;
    private String name;
    private String country;
    private String language;
    private LocalDate birthday;

    public UserMenu(EducationMenu educationMenu, UserService userService, User user) {
        this.educationMenu = educationMenu;
        this.userService = userService;
        this.user = user;
    }

    public void showAddUser() {
        System.out.println("Enter user login: ");
        login = sc.next();
        System.out.println("Enter user password: ");
        password = sc.next();
        System.out.println("Enter user name: ");
        name = sc.next();
        System.out.println("Enter user country: ");
        country = sc.next();
        System.out.println("Enter user language: ");
        language = sc.next();
        System.out.println("Enter user education: ");
        educationId = educationMenu.showAddEducation();
        System.out.println("Enter user birthday: ");
        birthday = LocalDate.parse(sc.next());
        userService.createUser(new User(id, login, password, educationId, name, country, language, birthday));
    }

    public void showEditUser() {
        System.out.println("Enter user id: ");
        id = sc.nextLong();
        System.out.println("Enter user login: ");
        login = sc.next();
        System.out.println("Enter user password: ");
        password = sc.next();
        System.out.println("Enter user education: ");
        educationId = educationMenu.showEditEducation();
        System.out.println("Enter user name: ");
        name = sc.next();
        System.out.println("Enter user country: ");
        country = sc.next();
        System.out.println("Enter user language: ");
        language = sc.next();
        System.out.println("Enter user birthday: ");
        birthday = LocalDate.parse(sc.next());
        userService.editUser(id, new User(id, login, password, educationId, name, country, language, birthday));

    }

    public Long getItem(long id) {
        try {
            userService.getItem(id);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void showDelete(long id) {
        user.setId(id);
        userService.deleteBook(user);
    }


}
