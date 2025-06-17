package fr.esiee.turkishspacelines.controller;

import fr.esiee.turkishspacelines.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class OperateurLancementController {

    @FXML
    private Label operatorNameLabel;

    @FXML
    private Button logoutButton;

    public void initData(User user) {
        if (user != null && user.getFullName() != null) {
            operatorNameLabel.setText("Opérateur de lancement : " + user.getFullName());
        }
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        System.out.println("Déconnexion demandée.");

        try {

            Parent loginRoot = FXMLLoader.load(getClass().getResource("Authentification.fxml"));

            Scene loginScene = new Scene(loginRoot, 600, 500);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Authentification | Turkish Spacelines");
            stage.setScene(loginScene);

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de charger la page de connexion.");
            alert.setContentText("Une erreur est survenue lors du chargement de l'interface.");
            alert.showAndWait();
        }
    }
}