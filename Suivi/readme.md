Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
  - RÉPONSE et éventuelles remarques de votre part, 

### Spécification et préparation des tests de validation

### Diagrammes de cas d'utilisation

[x] DIAGUC-01-Cas-utilisation-manquant

- Il manque un ou plusieurs cas d'utilisation qui vont vous
  manquer par la suite.

Vous devez mettre tous les cas d'utilisation dans le diagramme, pas seulement les plus importants.

### Préconditions et postconditions

1. Cas d'utilisation « créer un réseau social »
- [] PREPOSTCOND-06-Précondition-postcondition-incomplète
  
  - La précondition ou la postcondition d'un cas d'utilisation est
    incomplète. Relisez le cahier des charges pour trouver les
    conditions manquantes.
  
  A FAIRE
2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] PREPOSTCOND-06-Précondition-postcondition-incomplète
  
  - La précondition ou la postcondition d'un cas d'utilisation est
    incomplète. Relisez le cahier des charges pour trouver les
    conditions manquantes.
  
  A FAIRE
3. Cas d'utilisation « poster un message »
- [] PREPOSTCOND-06-Précondition-postcondition-incomplète
  
  - La précondition ou la postcondition d'un cas d'utilisation est
    incomplète. Relisez le cahier des charges pour trouver les
    conditions manquantes.
  
  A FAIRE

### Tables de décision des tests de validation

1. Cas d'utilisation « créer un réseau social »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.
  
  A FAIRE
2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.
  
  A FAIRE
3. Cas d'utilisation « poster un message »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.
  
  A FAIRE

## 3. Conception

### Diagramme de classes

[] DIAGCLAS-02-Compréhension-étude-de-cas

- Une ou plusieurs classes et/ou associations montrent un erreur
  de compréhension de l'étude de cas.

Je ne sais pas si vous comprenez la syntaxe et la sémantique du diagramme de classes - il y a beaucoup de problèmes - A VOIR  AVEC Paul - 

[] DIAGCLAS-14-Nom-classe

- Une ou plusieurs classes sont mal nommées : une majuscule au
  début, pas d'espace, un nom de classe qui contient « Acteur »
  (risque de confusion avec le concept d'acteur du système), etc.

EtatMembre devrait etre EtatMessage?

- [] DIAGCLAS-13-Présence-acteur-dans-système
  
  - Les acteurs n'ont pas tous vocation à être modélisés dans le
    système : par exemple, l'employé de la médiathèque n'est pas
    dans le diagramme de classes car aucun cas d'utilisation ne
    demande à utiliser des informations sur l'employé.
  
  Vous n'avez pas besoin des  classes -  Administrateur et Moderateur
  
  Vous n'avez pas besoin d'heritage

### Diagrammes de séquence

1. Cas d'utilisation « créer un réseau social »
- [] DIAGSEQ-02-Pb-cohérence-avec-diag-classes
  - Un ou plusieurs éléments de la séquence ne sont pas cohérents
    avec le diagramme de classes.

Pour rechercher le réseau, vous devez regarder dans la collection de réseaux

[] DIAGSEQ-03-Pb-cohérence-arguments-avec-précondition

- Pour un ou plusieurs diagrammes de séquence, les arguments du
  message en provenance de l'acteur ne correspondent pas avec les
  données de la précondition du cas d'utilisation correspondant.

- précondition :  

- ^ Le compte de l'utilisateur est (actif ∧ non bloqué)  

[] DIAGSEQ-05-Pb-cohérence-effet-non-complet

- Le diagramme de séquence incriminé ne réalise pas complètement
  tous les effets du cas d'utilisation. Il manque la création d'un
  objet ou l'instanciation d'une association ou un autre élément à
  modéliser pour avoir un cas d'utilisation complet.

- ∧ postcondition :  

- ∧ Le réseau est créé
  ∧ L'utilisateur avec droit de modération du réseau
2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] DIAGSEQ-03-Pb-cohérence-arguments-avec-précondition
  
  - Pour un ou plusieurs diagrammes de séquence, les arguments du
    message en provenance de l'acteur ne correspondent pas avec les
    données de la précondition du cas d'utilisation correspondant.

- [] DIAGSEQ-04-Pb-cohérence-précondition-non-complète
  
  - Le diagramme de séquence incriminé ne modélise pas complètement
    le cas d'utilisation car certaines préconditions ne sont pas
    vérifiées.

- [] DIAGSEQ-05-Pb-cohérence-effet-non-complet
  
  - Le diagramme de séquence incriminé ne réalise pas complètement
    tous les effets du cas d'utilisation. Il manque la création d'un
    objet ou l'instanciation d'une association ou un autre élément à
    modéliser pour avoir un cas d'utilisation complet.
3. Cas d'utilisation « poster un message »
- [] A VOIR AVEC PAUL

### Raffinement du diagramme de classes

1. Fiche de la classe « Message »
- [] pas coherent avec noms dans vos diagrammes

### Diagramme de machine à états et invariant

1. Diagramme de machine à états de la classe « Message »
- [] DIAGMACHETATS-03-Cohérence-avec-diagramme-classes
  
  - Un ou plusieurs états ne sont pas cohérents avec le diagramme de
    classes : par exemple, vous utilisez un type énuméré pour nommer
    les états, mais n'utilisez pas les mêmes noms dans le diagramme
    de machine à états.
    
    je ne comprends pas l'etat Brouillon
2. Invariant de la classe « Message »
- [] pas besoin de tester les valeurs d'une enumeration - (status = "En Attent" Or Status="Publié" Or Status="Rejeté"

## 4. Préparation des tests unitaires

1. Table de décision des tests unitaires de la méthode Message::constructeur

[] TABLEDECTU-02-Compréhension-étude-de-cas

- Une ou plusieurs tables de décision montrent un erreur de
  compréhension de l'étude de cas.

pourquoi F F F dans column 3?

- [] TABLEDECTU-08-Nombre-de-test-erreur-valeur
  
  - Une ou plusieurs valeurs, de la dernière ligne d'une table de
    décision indiquant le nombre de tests par test, sont à revoir.
  
  column 3 - nombre devrait etre 3
2. Table de décision des tests unitaires de la méthode Message::modérer
- [] OK
