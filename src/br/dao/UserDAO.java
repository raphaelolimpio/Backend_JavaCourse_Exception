package br.dao;

import java.util.ArrayList;
import java.util.List;

import br.excepetion.EmptyStorageException;
import br.model.UserModel;

public class UserDAO {
private long nextId = 1;

    private List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }
    public UserModel update(final UserModel model) {
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public UserModel delete(final long id) {
        var toDelete = findById(id);
        models.remove(toDelete);
        return toDelete;
    }

    public UserModel findById(final long id) {
        var  mensage = String.format("User not found with id: ", id);
        return models.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(mensage));
    } 
    public List<UserModel> findAll() {
        List<UserModel> result = null;
        try {
            verifyStorage();
        } catch (EmptyStorageException ex) {
            ex.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
        
    }

    private void verifyStorage(){
        if(models.isEmpty()) throw new EmptyStorageException("o armazenamento esta vazio");
    }
}
