import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.dao.UserDAO;
import br.excepetion.EmptyStorageException;
import br.excepetion.UserNotFoundExcepetion;
import br.excepetion.ValidatorException;
import br.model.MenuOption;
import br.model.UserModel;
import br.validator.UserValidator;

public class App {

    private static final UserDAO dao = new UserDAO();

    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        while (true ){
        System.out.println("Bem vindo ao cadastro de usuario, selecione a opção desejada");
        System.out.println("1 - Cadastrar Usuario");
        System.out.println("2 - atualizar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Buscar por identficador");
        System.out.println("5 - Listar");
        System.out.println("6 - Sair");

        var userInput = scanner.nextInt();
            var selectedOption = MenuOption.values()[userInput -1];
            switch (selectedOption) {
                
                case SAVE -> {
                    try{
                        var user = dao.save(requestToSave());
                        System.out.printf("usuario salvo %s", user);
                    }catch (ValidatorException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();

                    }
                }
                case UPDATE -> {
                    try{
                        var user = dao.update(requestToUpdate());
                        System.out.printf("usuario atualizado %s", user);
                    }catch (UserNotFoundExcepetion | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());

                    }catch(ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    } 
                    finally{
                        System.out.println("=======================");
                    }
                }   
                case DELETE -> {
                    try{
                        var user = dao.delete(requestId());
                        System.out.printf("usuario excluido %s", user);
                    }catch (UserNotFoundExcepetion | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());

                    }
                }
                case FIND_BY_ID -> {
                    try {
                        var id = requestId();
                        var user = dao.findById(id);
                        System.out.printf("usuario encontrado %s", user);
                    } catch (UserNotFoundExcepetion | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());

                    }
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    System.out.println("usuarios encontrados ");
                    System.out.println("=======================");
                    users.forEach(System.out::println);
                    System.out.println("=======================");

                }
                case EXIT -> System.exit(0);
            }
        }
    }

    private static long requestId(){
        System.out.println("Informe o identificador: ");
        return scanner.nextLong();
    }

    private static UserModel requestToSave() throws ValidatorException{
        System.out.println("Informe o nome: ");
        var name = scanner.next();
        System.out.println("Informe o email: ");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento: (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        return validatorInputs(0, name, email, birthday);
        
        
    }

    private static UserModel validatorInputs(final long id, final String name, 
    final String email, final LocalDate birthday) throws ValidatorException  {
            
        var user = new UserModel(id, name, email, birthday);
        UserValidator.verifyModel(user);
        return user;
        
    }

    private static UserModel requestToUpdate()throws ValidatorException{
        System.out.println("Informe o identificador: ");
        var id = scanner.nextLong();
        System.out.println("Informe o nome: ");
        var name = scanner.next();
        System.out.println("Informe o email: ");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento: (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        return validatorInputs(0, name, email, birthday);
    }
        
        
        
        
}
    

