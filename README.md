# Gomoku
Best game ever!

## Infos utiles
 ### Liste des IA disponibles:
 
+ Minimax1
+ Minimax1AggroFacteur2 
+ Minimax1AggroFacteur3 
+ Minimax1AggroFacteur4
+ AlphaBetaMinimax1 
+ AlphaBetaMinimax1AggroFacteur2

(plus d'info sur le Google Doc)

### Pour tester
+ Soit éxécuter la classe Gomoku.java pour faire une partie via interface graphique avec les joueurs de notre choix (plutot pour la demo)
+ Soit via le `main()` de la classe IATest.java qui permet de lancer automatiquement plusieurs parties de suite et d'afficher le score final (plutot pour les tests)

### Conseils
+ Les IA Alpha-Beta mettent assez longtemps à jouer car elles explorent l'arbre 1 niveau plus bas (malgré qu'elles soient plus optimisées)
+ Eviter les tailles de grille >8 sous peine de mourir d'ennui (surtout avec les IA Alpha-Beta)
