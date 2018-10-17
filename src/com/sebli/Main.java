package com.sebli;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Role> roles = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Role role1 = new Role(00001111, "Admin");
        roles.add(role1);
        User user1 = new User(11110000, "User1", "101010");
        users.add(user1);
        role1.getUsers().add(user1);
        user1.getRoles().add(role1);

        Role role2 = new Role(00002222, "Contributor");
        roles.add(role2);
        User user2 = new User(22220000, "User2", "202020");
        users.add(user2);
        role2.getUsers().add(user2);
        user2.getRoles().add(role2);

        Role role3 = new Role(00003333, "Editor");
        roles.add(role3);
        User user3 = new User(33330000, "User1", "303030");
        users.add(user3);
        role3.getUsers().add(user3);
        user3.getRoles().add(role3);

        String choice = "";

        String userName;
        long userId;
        String passWord;
        long roleId;
        String roleName;
        String wantToAdd = "";
        String anyOtherRoles = "";

        do {
            System.out.println("\n\n***MENU***\n1.Sign Up\n2.Add A New Role\n3.Add A Role to the User\n4.LOGIN\n5.Exit");
            choice = input.nextLine();
            switch (choice) {
                case "1": {
                    User auser = new User();
                    System.out.println("User Name:");
                    userName = input.nextLine();
                    auser.setUserName(userName);
                    System.out.println("User ID:");
                    userId = input.nextLong();
                    input.nextLine();
                    auser.setUserId(userId);
                    System.out.println("PassWord:");
                    passWord = input.nextLine();
                    if (confirmation(passWord, input)) {
                        auser.setPassWord(passWord);
                        users.add(auser);
                    }
                    System.out.println("Want to add some roles?(Yes/No");
                    wantToAdd = input.nextLine();
                    if (wantToAdd.equalsIgnoreCase("yes")) {
                        for (Role eachRole : roles) {
                            System.out.printf("Role Name:%s\t\tRole Id:%s\n", eachRole.getRoleName(), eachRole.getRoleId());
                        }

                        do {
                            System.out.println("\nEnter the ID of the role you want to add?");
                            roleId = input.nextLong();
                            input.nextLine();
                            Role theRole = findRole(roleId, roles);
                            if (theRole == null) {
                                System.out.println("Role not Found");
                            }
                            if (auser.getRoles().contains(theRole)) {
                                System.out.println("you can not have one role towice for one user");
                            }
                            if (theRole != null && !auser.getRoles().contains(theRole)) {
                                auser.getRoles().add(theRole);
                            } else {

                            }
                            System.out.println("\nAny other roles?(yes/no)");
                            anyOtherRoles = input.nextLine();
                        } while (anyOtherRoles.equalsIgnoreCase("yes"));

                    }
                    break;
                }
                case "2": {
                    do {
                        Role aRole = new Role();
                        System.out.println("Role ID:");
                        roleId = input.nextLong();
                        input.nextLine();
                        aRole.setRoleId(roleId);
                        System.out.println("Role Name:");
                        roleName = input.nextLine();
                        aRole.setRoleName(roleName);
                        roles.add(aRole);
                        System.out.println("Any Other Roles to add?(yes/no");
                        anyOtherRoles = input.nextLine();

                    } while (anyOtherRoles.equalsIgnoreCase("yes"));

                    break;
                }
                case "3": {
                    for (Role eachRole : roles) {
                        System.out.printf("Role Name: %s\t\t\t\t\t\t\tRole Id: %s\n", eachRole.getRoleName(), eachRole.getRoleId());
                    }
                    for (User eachUser : users) {
                        System.out.printf("User Name: %s\t\t\t\t\t\t\tUser Id: %s\n", eachUser.getUserName(), eachUser.getUserId());
                    }
                    System.out.println("User ID:");
                    userId = input.nextLong();
                    input.nextLine();
                    User theUser = findUser(userId, users);

                    if (theUser != null) {
                        do {
                            System.out.println("\nEnter the ID of the role you want to add?");
                            roleId = input.nextLong();
                            input.nextLine();
                            Role theRole = findRole(roleId, roles);
                            if (theUser.getRoles().contains(theRole)) {
                                System.out.println("The Role is Already in this User's Role List ");
                            }
                            if (theRole != null && !theUser.getRoles().contains(theRole)) {
                                theUser.getRoles().add(theRole);
                                if (theRole.getUsers().contains(theUser)) {
                                    System.out.println("The user is already on this Role's User List");
                                }
                                if (!theRole.getUsers().contains(theUser)) {
                                    theRole.getUsers().add(theUser);
                                }
                            }
                            if (theRole == null) {
                                System.out.println("Role not Found");
                            }
                            System.out.println("\nAny other roles?(yes/no)");
                            anyOtherRoles = input.nextLine();
                        } while (anyOtherRoles.equalsIgnoreCase("yes"));
                    } else
                        System.out.println("User Not found");
                    break;
                }
                case "4": {
                    System.out.println("***LOGIN***");
                    System.out.println("User Name:");
                    userName = input.nextLine();
                    System.out.println("User ID:");
                    userId = input.nextLong();
                    input.nextLine();
                    User theUser = findUser(userId, users);
                    if (theUser != null) {
                        System.out.println("Password");
                        passWord = input.nextLine();
                        if (validation(passWord, theUser, input)) {
                            System.out.println("\n***Your Roles***");
                            for (Role eachRole : theUser.getRoles()) {
                                System.out.printf("Role Name:%s\t\t\t\t\t\t\tRole Id:%s\n", eachRole.getRoleName(), eachRole.getRoleId());
                            }

                        } else
                            System.out.println("You can't log in");
                    } else
                        System.out.println("User not found");
                    break;
                }
                case "5": {
                    break;
                }
            }
        } while (!choice.equals("5"));
    }

    public static boolean confirmation(String passWord, Scanner input) {
        System.out.println("Confirm your PassWord");
        String passWordConfirm = input.nextLine();
        boolean confirm = false;
        do {
            if (!passWord.equals(passWordConfirm)) {
                System.out.println("PassWord Not Match please enter again");
                passWordConfirm = input.nextLine();
            } else
                confirm = true;

        } while (confirm == false);


        return confirm;
    }

    public static Role findRole(long roleId, ArrayList<Role> roles) {
        Role foundRole = null;
        for (Role eachRole : roles) {
            if (roleId == (eachRole.getRoleId())) {
                foundRole = eachRole;
            }
        }

        return foundRole;
    }

    public static User findUser(long userId, ArrayList<User> users) {
        User foundUser = null;
        for (User eachUser : users) {
            if (userId == (eachUser.getUserId())) {
                foundUser = eachUser;
            }
        }

        return foundUser;
    }

    public static boolean validation(String passWord, User user, Scanner input) {
        boolean validate = false;
        do {
            if (passWord.equals(user.getPassWord())) {
                System.out.println("Log In successful!");
                validate = true;
            } else {
                System.out.println("Password not correct\nPlease enter your password again");
                passWord = input.nextLine();
            }
        } while (validate == false);

        return validate;

    }


}
