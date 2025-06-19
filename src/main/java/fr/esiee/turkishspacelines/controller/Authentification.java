package fr.esiee.turkishspacelines.controller;

import fr.esiee.turkishspacelines.dao.UserDao;
import fr.esiee.turkishspacelines.dao.UserDaoImpl;
import fr.esiee.turkishspacelines.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Authentification {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private UserDao userDao = new UserDaoImpl();

    public void ButtonLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir un identifiant et un mot de passe.");
            return;
        }

        Optional<User> userOptional = userDao.findByEmailAndPassword(email, password);

        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            System.out.println("Connexion réussie pour : " + loggedInUser.getFullName());

            loadNextScene(event, loggedInUser);
        } else {

            showAlert(Alert.AlertType.ERROR, "Échec", "Identifiant ou mot de passe incorrect.");
        }
    }

    private void loadNextScene(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OperateurLancement.fxml"));
            Parent root = loader.load();

            OperateurLancementController controller = loader.getController();

            controller.initData(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nouvelleScene = new Scene(root, 1280, 650);
            stage.setTitle("Opérateur de lancement | Turkish Spacelines");
            stage.setScene(nouvelleScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}