# Badges

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/lyesDOUKI/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/lyesDOUKI/ceri-m1-techniques-de-test/tree/master)
[![codecov](https://codecov.io/gh/lyesDOUKI/ceri-m1-techniques-de-test/graph/badge.svg?token=7NS91W0IO7)](https://codecov.io/gh/lyesDOUKI/ceri-m1-techniques-de-test)
# badge checkstyle
* ![Checkstyle](target/site/badges/checkstyle-result.svg)
# UCE Génie Logiciel Avancé : Techniques de tests
# lien vers la javadoc
* [Javadoc](https://lyesdouki.github.io/ceri-m1-techniques-de-test/docs/fr/univavignon/pokedex/api/package-summary.html)
## Introduction

Vous allez à travers ces projet mettre en application une partie des aspects évoqués en cours vis à vis des techniques de tests.  
Pour cela nous allons réaliser un projet logiciel de petite taille, en suivant la roadmap suivante : 
- Setup du projet
- Mise en place des outils d’intégration continue
- Écriture des tests unitaires
- Écriture des mocks, et validation des tests
- Développement dirigé par les tests
- Documentation et conventions de style
- Test d'une implémentation donnée

Durant cette série de TPs, le gestionnaire de version Git sera utilisé à foison, à travers la plateforme GitHub. Si vous n’êtes pas à l’aise avec cet outil[^1], [voici](http://rogerdudler.github.io/git-guide/) un petit guide à garder sous la main.

## Sujets

L'ensemble des sujets de TPs peut être trouvé dans le dossier `TPs`.

Le dossier `src` contient la définition de l'ensemble des interfaces qui seront l'objet de vos travaux.

## Rendus

Le rendu des TPs se fait au rythme suivant :

- TP1 : 2ème séance
- TP2 : 2ème séance
- TP3 : 3ème séance
- TP4 : 5ème séance
- TP5 : dernière séance
- TP6 : dernière séance

Pour chaque rendu vous devez créer un tag à partir du commit qui correspond à la complétion du TP.  
Si vous ne spécifiez pas de tag, le dernier commit à la date-heure de la fin de séance sera celui considéré.

[^1]: Si vous n’êtes vraiment pas à l’aise avec cet outil nous vous conseillons quand même vivement de vous y mettre.

# Nom et prénom
- Nom : DOUKI
- Prénom : Lyes

# Groupe
- Groupe : m1-alternant

# Compte rendu du TP6 
## Tester la nouvelles classe avec les tests unitaires de IpokemonFactory
- J'ai testé la nouvelle classe avec les tests unitaires de IpokemonFactory : 
- Les tests echouent car la nouvelle classe permet de creer des pokemons avec un index negatif, ce qui n'est pas possible dans la classe PokemonFactory, comme c'est défini dans le cahier de charge.
- Aussi la valeur de IV balance entre 0 et 1 selon le pokemon, la valeur n'est pas fixé à 0
- Aussi, les pokemons sont créés en dure dans la nouvelle classe
## Conclusion
-Les tests en place ne sont sert pas assez robuste de mon point de vu pour tester de manière efficace la nouvelle classe, il faudrait ajouter des tests pour tester les cas limites et les cas d'erreurs.
-Aussi, le code fourni n'est pas robuste et ne respecte pas les normes de qualité de code, par exemple on génére des valeurs aléatoires sans mettre de validateurs et des controles sur ce qui a été généré.

# Les liens vers les commits : 
-TP6 : 3500bc7
-TP5 : 3f0315d
-TP4 : eb964bd
-TP3 : 0a6b91a
-TP2 : bdbe390
-TP1 : 969ab78
 