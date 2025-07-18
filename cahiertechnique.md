# Projet TurkishSpacelines avec JavaFX

[Théo Suquet], ESIEE-IT

![Logo de Turkish Spacelines](./images/logonoir.png)

# Cahier technique

### Contexte

**TurkishSpacelines** s'inscrit dans une ambition visionnaire de révolutionner le transport spatial, en proposant des vols entre différentes planètes. Cette initiative repose sur une collaboration étroite entre des experts en ingénierie aérospatiale et des professionnels de la gestion de vols spatiaux, visant à ouvrir de nouvelles frontières dans l'exploration et l'exploitation spatiale.

Au cœur de ce projet, **deux pôles principaux**, l'**ingénieur des fusées** et l'**opérateur des lancements**, jouent des rôles cruciaux. L'ingénieur des fusées est chargé de la conception, du développement et de l'attribution des fusées pour chaque mission, garantissant ainsi que les technologies les plus avancées et les plus sûres sont utilisées. Parallèlement, l'opérateur des lancements orchestre la planification et l'exécution des missions, en veillant à la sécurité des vols et à la conformité avec les conditions météorologiques spatiales.

Ensemble, ces rôles forment une synergie essentielle pour le succès des missions de TurkishSpacelines, marquant un tournant dans l'histoire du voyage spatial en rendant l'espace plus accessible et en établissant de nouvelles normes de sécurité et d'efficacité dans ce domaine en plein essor.

### Modèle relationnel BDD

![](./images/bddrelationnel.png)

### Technologies utilisées pour le développement :

- **Langage utilisé :** Java _(Version: 8)_ - Pour le développement du backend et de la logique d'application.
- **Code du programme:**
  IntelliJ _(Version: 2023.2.2)_ - Un environnement de développement intégré (IDE) pour le développement Java.
  Visual Studio Code _(Version: 1.86.2)_ - Utilisé pour le markdown.
- **Framework de présentation :** JavaFX - Pour la création de l'interface utilisateur.
- **Gestion BDD:** PhpMyAdmin _(Version: 5.2.1)_ - Pour la gestion de la base de données MySQL.
- **Gestion du code:** Gitlab _(Version: 16.9.1)_ - Plateforme de gestion de version et de collaboration pour le code source.
- **Maquette:** Adobe XD _(Payante) (Version: 57.1.12.2)_ - Utilisé pour la conception et le prototypage de l'interface utilisateur.

### Technologies utilisées pour le déploiement :

- **Communication:** Discord _(Version: 267220 (23.0.0))_ - Utilisé pour la communication d'équipe et la coordination du projet.
- **Gestion planning:** GanttProject _(Version: 3.2.3247)_ - Outil de planification pour le suivi des étapes et des délais du projet.

### Commande pour lancer le projet :

mvn javafx:run