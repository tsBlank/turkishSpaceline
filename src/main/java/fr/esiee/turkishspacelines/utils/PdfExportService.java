package fr.esiee.turkishspacelines.utils;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fr.esiee.turkishspacelines.model.Vol;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfExportService {

    /**
     * Crée un document PDF récapitulatif de la liste des vols.
     * @param vols La liste des objets Vol à exporter.
     * @param destPath Le chemin complet où le fichier PDF sera sauvegardé.
     * @throws FileNotFoundException Si le chemin de destination n'est pas valide.
     */
    public void exportVolsToPdf(List<Vol> vols, String destPath) throws FileNotFoundException {
        // Initialisation du document PDF
        PdfWriter writer = new PdfWriter(destPath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Ajout du titre
        Paragraph title = new Paragraph("Récapitulatif des Vols - Turkish Spacelines")
                .setBold()
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Ajout de la date de génération
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Paragraph date = new Paragraph("Généré le : " + dtf.format(LocalDate.now()))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10);
        document.add(date);

        // Création du tableau
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3, 2, 2, 2, 2}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginTop(20);

        // Ajout des en-têtes du tableau
        addHeaderCell(table, "N° Vol");
        addHeaderCell(table, "Nom");
        addHeaderCell(table, "Départ (ID)");
        addHeaderCell(table, "Arrivée (ID)");
        addHeaderCell(table, "Fusée (ID)");
        addHeaderCell(table, "Passagers");

        // Remplissage du tableau avec les données des vols
        if (vols == null || vols.isEmpty()) {
            table.addCell(new Cell(1, 6).add(new Paragraph("Aucun vol enregistré.")));
        } else {
            for (Vol vol : vols) {
                table.addCell(String.valueOf(vol.getIdVol()));
                table.addCell(vol.getName());
                table.addCell(String.valueOf(vol.getIdStationDepart()));
                table.addCell(String.valueOf(vol.getIdStationArrivee()));
                table.addCell(String.valueOf(vol.getIdFusee()));
                int totalPassengers = vol.getNbFemme() + vol.getNbHomme() + vol.getNbEnfant();
                table.addCell(String.valueOf(totalPassengers));
            }
        }

        // Ajout du tableau au document
        document.add(table);

        // Fermeture du document
        document.close();
        System.out.println("PDF généré avec succès : " + destPath);
    }

    // Méthode utilitaire pour styliser les en-têtes
    private void addHeaderCell(Table table, String text) {
        table.addHeaderCell(
                new Cell().add(new Paragraph(text))
                        .setBold()
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.CENTER)
        );
    }
}