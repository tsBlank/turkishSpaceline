package fr.esiee.turkishspacelines.controller;

import fr.esiee.turkishspacelines.dao.VolDao;
import fr.esiee.turkishspacelines.dao.VolDaoImpl;
import fr.esiee.turkishspacelines.model.User;
import fr.esiee.turkishspacelines.model.Vol;
import fr.esiee.turkishspacelines.utils.PdfExportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File; // <-- IMPORT AJOUTÉ
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List; // <-- IMPORT AJOUTÉ

public class OperateurLancementController {

    @FXML
    private Label operatorNameLabel;

    // Ces instances sont correctes
    private final VolDao volDao = new VolDaoImpl();
    private final PdfExportService pdfService = new PdfExportService();

    // Note : Cette variable n'est pas utilisée si vous avez un MenuButton.
    // Si vous avez toujours un bouton simple, elle est correcte.
    @FXML
    private Button logoutButton;

    public void initData(User user) {
        if (user != null && user.getFullName() != null) {
            operatorNameLabel.setText("Opérateur de lancement : " + user.getFullName());
        }
    }

    // Note : Cette méthode est correcte si vous avez toujours un bouton de déconnexion séparé.
    // Si vous utilisez un MenuButton, la façon de récupérer le Stage doit être adaptée.
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
            // ... (code de l'alerte)
        }
    }

    @FXML
    private void handleExportPdfButton() {
        // Cette méthode est maintenant correcte grâce aux imports
        List<Vol> allVols = volDao.findAll();

        if (allVols.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Il n'y a aucun vol à exporter.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le récapitulatif des vols");
        fileChooser.setInitialFileName("recapitulatif_vols.pdf");
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichier PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(operatorNameLabel.getScene().getWindow());

        if (file != null) {
            try {
                pdfService.exportVolsToPdf(allVols, file.getAbsolutePath());
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Le fichier PDF a été généré et sauvegardé avec succès.");
            } catch (FileNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur de Fichier", "Impossible de créer le fichier à l'emplacement choisi.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Exportation PDF annulée par l'utilisateur.");
        }
    }
   @FXML
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void handleShowStatistics() {
        try {
            // 1. Charger le FXML de la fenêtre de statistiques
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
            Parent root = loader.load();
            
            // 2. Créer une nouvelle fenêtre (un nouveau "Stage") pour les statistiques
            Stage statsStage = new Stage();
            statsStage.setTitle("Statistiques des Vols");
            statsStage.setScene(new Scene(root));
            
            // 3. (Optionnel) Rendre la fenêtre "modale" pour bloquer la fenêtre principale
            //    pendant que les statistiques sont ouvertes.
            statsStage.initModality(Modality.WINDOW_MODAL);
            // On lie la nouvelle fenêtre à la fenêtre principale
            statsStage.initOwner(operatorNameLabel.getScene().getWindow());
            
            // 4. Afficher la nouvelle fenêtre
            statsStage.show();

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la page des statistiques.");
            e.printStackTrace();
        }
    }

}