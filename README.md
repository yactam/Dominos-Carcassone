# POO

Le projet propose deux jeux : Carcassone et Une variante de jeu de Dominos programmés en Java en utilisant swing pour l'interface graphique

## Compilation

Pour pouvoir compiler le projet lancez cette commande à partir du repertoire ou se trouve le dossier src du projet 

```bash
    javac -classpath src/ src/Main.java 
```

## Exécution

Pour pouvoir exécuter le projet lancez cette commande à partir du meme repertoire que la compilation

```bash
    java -classpath src/ Main 
```

## How to play

Il faut choisir d'abord le jeu dans le menu d'acceuil.
Toutes les actions sont à realiser avec la souris soit en cliquant sur des boutons, soit avec des drag and drop de la souris pour poser les tuiles sur le plateau.

  * Pour pouvoir piocher une tuile, il faut cliquer sur le bouton tout en bas à droite de la fenetre sous forme d'un carré avec les memes dimensions qu'une tuile avec une background image selon le jeu que vous choisissez.
  * Vous avez la possibilité de tourner la tuile une fois piochée avec les boutons tourner à droite et à gauche de la tuile.
  * Vous pouvez passer votre tour en cliquant sur le bouton passer tour en bas à droite de la fenetre.
  * Vous pouvez abandonner en cliquant sur le bouton abandonner en dessous de bouton passer tour.
  * Pour pouvoir placer une tuile sur le plateau il faut cliquer sur la tuile et maintenir la souris cliquée et la déplacer vers la case du plateau ou vous voulez poser votre tuile et la tuile sera ajouté au plateau à condition qu'elle corresponds aux tuiles adjacentes sur le plateau (drag and drop)
  * Pour le jeu de carcassonne vous pouvez placer un partisan sur les tuiles avec la meme fonctionnalité pour placer les tuiles sur le plateau et les partisans se trouvent à droite de la tuile (petite icone).

Enjoy !



