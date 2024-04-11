# Gestion de mini réseaux sociaux MiniSocs

Binôme :
* Yesmine LAJMI CHERIF
* Sabrine AZAIEZ

## Syntaxe MarkDown

La syntaxe MarkDown de ce document est compatible avec la syntaxe
GitLab, qui est documentée dans
https://docs.gitlab.com/ee/user/markdown.html

## 1. Spécification

### 1.1. Acteurs et cas d'utilisation

La **première étape** consiste à **bien comprendre le système** à
étudier. Dans le cadre de l'exercice, cela consiste à lire
attentivement l'énoncé. Cette lecture doit permettre de *délimiter les
contours du système* à réaliser. La méthode générale consiste à
retrouver les acteurs qui interagissent avec lui. Il est très
important de fixer des frontières au problème. Ensuite, nous
recherchons les fonctionnalités du système par la définition de ses
cas d'utilisation. Dans le cadre de ce module, il s'agit de rechercher
les principales fonctions attendues du système. Nous nous limitons aux
cas d'utilisation pour atteindre les premiers objectifs indiqués dans
le cahier des charges, en prenant en considération les simplifications
énoncées dans le cahier des charges.

Pour réaliser le diagramme de cas d'utilisation à partir de l'analyse
du texte :
* rechercher les acteurs, avec les potentielles relation de
  généralisation spécialisation,
* rechercher les fonctionnalités du système accessibles aux acteurs

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Voici ci-dessous le diagramme de cas d'utilisation avec les cas
d'utilisation les plus importants (code
[source](./Diagrammes/minisocs_uml_diag_cas_utilisation.pu)).

![diagrammecasutilisation](./Diagrammes/minisocs_uml_diag_cas_utilisation.png)

### 1.2. Priorités, préconditions et postconditions des cas d'utilisation

Les priorités des cas d'utilisation sont choisies avec les règles de
bon sens suivantes:

* pour retirer une entité du système, elle doit y être. La priorité de
l'ajout est donc supérieure ou égale à la priorité du retrait ;

* pour lister les entités d'un type donné, elles doivent y être. La
priorité de l'ajout est donc supérieure ou égale à la priorité du
listage ;

* il est *a priori* possible, c.-à-d. sans raison contraire, decsc4102-projet
démontrer la mise en œuvre d'un sous-ensemble des fonctionnalités du
système, et plus particulièrement la prise en compte des principales
règles de gestion, sans les retraits ou les listages ;

* la possibilité de lister aide au déverminage de l'application
pendant les activités d'exécution des tests de validation.

Par conséquent, les cas d'utilisation d'ajout sont *a priori* de
priorité « HAUTE », ceux de listage de priorité « Moyenne», et ceux de
retrait de priorité « basse ».

Voici les précondition et postcondition des cas d'utilisation de
priorité HAUTE.

#### Ajouter un utilisateur (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ nom bien formé  (non null ∧ non vide) \
∧ prénom bien formé  (non null ∧ non vide) \
∧ courriel bien formé (respectant le standard RFC822) \
∧ utilisateur avec ce pseudo inexistant
- postcondition : \
∧ utilisateur avec ce pseudo existant \
∧ le compte de l'utilisateur est actif

#### Désactiver son compte (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le compte n'est pas bloqué \
∧ le compte n'est pas désactivé \
∧ utilisateur avec ce pseudo existant
- postcondition : le compte de l'utilisateur est désactivé

#### Ajouter membre (HAUTE)
- précondition : \
∧ pseudo utilisateur bien formé (non null ∧ non vide) \
∧ pseudo moderateur bien formé (non null ∧ non vide) \
∧ pseudo utilisateur à ajouter bien formé (non null ∧ non vide) \
∧ pseudo nouveau membre bien formé (non null ∧ non vide) \
∧ nom réseau bien formé (non null ∧ non vide) \
∧ Le réseau existe ^ le réseau est ouvert \
∧ L'utilisateur(modérateur) existe (posséde un compte sur MiniSoc) ∧  son compte est actif ^ L'ajout se fait par un modérateur ^ l'ajout se fait dans un réseau social qui le modére ^ le compte modérateur correspond au compte utilisateur \
∧ utilisateur(à ajouter) existe ∧ le compte est actif ∧ n'est pas dans le réseau sous un autre pseudo\
∧ Le pseudo de ce nouveau membre n'existe pas sur le réseau 
∧ etat stratégie == "immédiat" ou "quotidien" ou "pas de notifications"
 
- postcondition : \
∧ consommateur et membre créé, membre ajouté au réseau et ajouté a la liste des membres de l'utilisateur

#### Modérer les messages (HAUTE)

- précondition : \
∧ pseudoUtilisateurModerateur!=null && pseudoUtilisateurModerateur!=vide \
∧ pseudonymeModerateur!=null && pseudonymeModerateur!=vide\
∧ nomReseau !=null && nomReseau!=vide \
∧ idMessage!=null\
∧ Le compte utilisateur (moderateur) existe ^ actif ^ le profil modérateur correspond à cet utilisateur\
∧ L'utilisateur a les droits de modération de ce réseau\
∧ réseau existe && reseau ouvert \
∧ Le message fait partie de ce réseau\
∧ message en attente de modération \
- postcondition : \
∧ Si le modérateur accepte le message, le message devient visible, message ajouté dans le réseau et notifications envoyées aux membres selon l'etat stratégie\
∧ Sinon si le modérateur refuse le message, il aura le statut Rejeté et reste non visible dans le réseau

#### Poster Message (HAUTE)
- précondition : \
∧ pseudonomUtilisateur != null && pseudonomUtilisateur!=vide \
∧ pseudonomMembre != null && pseudonomMembe!=vide \
∧ nomReseau!=null && nomRéseau!=vide \
∧ utilisateur exite, le compte de l'utilisateur est ACTIF et le compte membre correspond au compte utilisateur \
∧ reseau existe et non ferme/
∧ contenu non null ∧ contenu non vide \
∧ l'utilisateur est membre de ce reseau \
∧ Postcondition : \
∧ Si le membre est un modérateur le message est publié \
∧ Si non le message est en attente de modération

#### Créer réseau social (HAUTE)
- précondition : \
^ pseudonymeUtilisateur!= null && pseudonymeUtilisateur!= vide \
^ pseudonymeMembre!= null  && pseudonymeMembre!= vide \
^ nomReseau!=null && nomRéseau!=vide\
^ Utilisateur existe \
^ Etat du compte utilisateur Actif \
^ Reseau n'existe pas \
∧ postcondition : \
∧ Le réseau est créé \
∧ Un membre est créer avec droit de modération et ajouté à ce réseau

NB : l'opération est idempotente.
#### Autres cas d'utilisation et leur priorité respective

- Retirer un utilisateur (basse)

- Bloquer le compte d'un utilisateur (basse)

- Lister les utilisateurs (moyenne)

- Débloquer le compte d'un utilisateur (basse)

- Promotion membre (moyenne) 

- Cacher message (moyenne)

- Configurer compte (basse)

- Activer son compte (moyenne)

## 2. Préparation des tests de validation des cas d'utilisation

#### Ajouter un utilisateur (HAUTE)

|                                                     | 1 | 2 | 3 | 4 | 5 | 6 |
|:----------------------------------------------------|:--|:--|:--|---|---|---|
| pseudo bien formé (non null ∧ non vide)             | F | T | T | T | T | T |
| nom bien formé  (non null ∧ non vide)               |   | F | T | T | T | T |
| prénom bien formé  (non null ∧ non vide)            |   |   | F | T | T | T |
| courriel bien formé (respectant le standard RFC822) |   |   |   | F | T | T |
| utilisateur avec ce pseudo inexistant               |   |   |   |   | F | T |
|                                                     |   |   |   |   |   |   |
| utilisateur avec ce pseudo existant                 | F | F | F | F | F | T |
| compte de l'utilisateur actif                       | F | F | F | F | F | T |
|                                                     |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                | 2 | 2 | 2 | 3 | 1 | 1 |

Le jeu de test 4 comporte trois tests : non null, non vide, et adresse
courriel bien formée. On aurait pu n'en faire qu'un en considérant la
bibliothèque de validation RFC822 vérifie les deux premières
conditions.

#### Désactiver son compte (HAUTE)

|                                          | 1 | 2 | 3 | 4 |
|:-----------------------------------------|:--|:--|:--|:--|
| pseudo bien formé (non null ∧ non vide)  | F | T | T | T |
| le compte n'est pas bloqué               |   | F | T | T |
| utilisateur avec ce pseudo existant      |   |   | F | T |
|                                          |   |   |   |   |
| le compte de l'utilisateur est désactivé | F | F | F | T |
|                                          |   |   |   |   |
| nombre de tests dans le jeu de tests     | 2 | 1 | 1 | 1 |

#### Ajouter membre (HAUTE)
|                                          | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |
|:-----------------------------------------|:--|:--|:--|:--|:--|:--|:--|:--|:--|:--|:--|:--|
| pseudo utilisateur bien formé (non null ∧ non vide)  | F | T | T | T | T | T | T | T | T | T | T | T |
| pseudo moderateur bien formé (non null ∧ non vide)  |   | F | T | T | T | T | T | T | T | T | T | T |
| pseudo utilisateur à ajouter bien formé (non null ∧ non vide)  |  |  | F | T | T | T | T | T | T | T | T | T | T |
| pseudo nouveau membre bien formé (non null ∧ non vide)  |   |   |   | F | T | T | T | T | T | T | T | T |
| nom réseau bien formé (non null ∧ non vide)   |   |   |  |  | F | T | T | T | T | T | T | T |
| Le réseau existe ^le réseau est ouvert |   |   |   |    |  |  F | T | T | T | T | T | T |
| L'utilisateur (modérateur) posséde un compte actif sur MiniSoc ^ le compte modérateur correspond au utilisateur |   |   |   |   |   |  | F | T | T | T | T | T |
| L'ajout se fait par un modérateur ^ l'ajout se fait dans un réseau social qui le modére  |   |   |   |   |  |  |  | F | T | T | T | T |
| utilisateur(à ajouter) (existe ∧ le compte est actif ∧ n'est pas dans le réseau) |   |   |   |   |   |    |   |  | F | T | T | T |
|Le pseudo de ce nouveau membre n'existe pas sur le réseau     |   |   |   |   |   |    |   |  |  | F | T | T |
|etatStratégie == "immédiat" ou  "quotidien" ou "pas de notifications"  |   |   |   |   |   |    |   |  |  |   | F | T |
|   consommateur et membre créé, membre ajouté au réseau et ajouté a la liste des membres de l'utilisateur | F  | F  | F  | F  | F  | F  | F  | F  |  F | F | F | T |
 nombre de test dans le jeu de test | 2 | 2 | 2 | 2 | 2 | 2 | 4 | 2 | 4 | 1 | 1 | 1 |

#### Modérer les messages (HAUTE)

|                                          | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 |
|:-----------------------------------------|:--|:--|:--|:--|:--|:--|:--|:--|:--|:--|:--|
|pseudoUtilisateurModerateur!=null && pseudoUtilisateurModerateur!=vide                                                                                            | F | T | T | T | T | T | T | T | T | T | T |
| pseudonymeModerateur!=null && pseudonymeModerateur!=vide                                                                                                   |   | F | T | T | T | T | T | T | T | T | T | 
| nomReseau !=null && nomReseau!=vide      |   |   | F | T | T | T | T | T | T | T | T | 
| idMessage!=null                          |   |   |   | F | T | T | T | T | T | T | T | 
| Le compte utilisateur du moderateur existe ^ actif ^ le profil modérateur correspond à cet utilisateur               |   |   |   |   | F | T | T | T | T | T | T |
|L'utilisateur a les droits de modération de ce réseau ^ l'utilisateur est membre de ce réseau                               |   |   |   |   |   | F | T | T | T | T | T |
|réseau existe && reseau ouvert                                     |   |   |   |   |   |   | F | T | T | T | T |
|Le message fait partie de ce réseau       |   |   |   |   |   |   |   | F | T | T | T |
|message en attente de modération          |   |   |   |   |   |   |   |   | F | T | T |
|message accepté                           |   |   |   |   |   |   |   |   |   | F | T |
| message visible, ajouté dans le réseau et notifications envoyés aux membres selon l'etat stratégie| F | F | F | F | F | F | F | F | F | F | T |
| message rejeté                           | F | F | F | F | F | F | F | F | F | T | F |
| nombre de tests dans le jeu de tests     | 2 | 2 | 2 | 1 | 4 | 2 | 2 | 2 | 3 | 1 | 1 |

#### Poster Message (HAUTE) 

|                                          | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|:-----------------------------------------|:--|:--|:--|:--|:--|:--|:--|:--|:--|
| pseudonomUtilisateur != null && pseudonomUtilisateur!=vide     | F | T | T | T | T | T | T | T | T |
| pseudonomMembre != null && pseudonomMembe!=vide     |  | F | T | T | T | T | T | T | T |
| nomReseau!=null && nomRéseau!=vide       |   |   | F | T | T | T | T | T | T |
| utilisateur exite && Le compte de l'utilisateur est ACTIF  && compte membre correspond à l'utilisateur                 |   |   |   | F | T | T | T | T | T |
| contenu non null ∧ contenu non vide                                       |   |   |   |   | F | T | T | T | T |
| reseau existe ^ ouvert                   |   |   |   |   |   | F | T | T | T |
|  l'utilisateur est membre de ce reseau                                     |   |   |   |   |   |   | F | T | T |
|  l'utilisateur est modérateur de ce reseau                                     |   |   |   |   |   |   |   | F | T |
| le message est en attente de modération  | F | F | T | F | F | F | F | T | F |
| le message est publié                    | F | F | T | F | F | F | F | F | T |
| nombre de tests dans le jeu de tests     | 2 | 2 | 2 | 4 | 2 | 2 | 2 | 1 | 1 |

#### Créer réseau social (HAUTE)


|                                          | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:-----------------------------------------|:--|:--|:--|:--|:--|:--|:--|
| pseudonymeUtilisateur!= null && pseudonymeUtilisateur!= vide | F  | T | T | T | T | T | T |
| pseudonymeMembre!= null && pseudonymeMembre!= vide | |  F | F | T | T | T | T | T |
| nomReseau!=null && nomRéseau!=vide   |   |   | F  | T | T | T | T |
| Utilisateur existe |   |   |   |  F | T |  T | T |
| Etat du compte utilisateur Actif |   |   |   |   |   F | T | T |
| Reseau n’existe pas |   |   |   |   |  | F | T |
| Le réseau est créé | F | F | F | F | F | F  | T |
| Un membre est créer avec droit de modération et ajouté à ce réseau | F | F | F | F | F | F  | T |
| nombre de tests dans le jeu de tests     | 2 | 2 | 2 | 1 | 2 | 1 | 1 | 

# 3. Conception

## 3.1. Listes des classes candidates et de leurs attributs

Voici les listes des classes candidates et de leurs attributs:
- `MiniSocs` (mise en œuvre du patron de conception Façade) avec
  l'attribut `nom` pour le nom du système,
- `Utilisateur` avec les attributs `pseudo` pour identifier de manière
  unique un utilisateur, `nom` et `prénom`, adresse `courriel`, et
  `etatCompte` pour l'état de son compte,
- `ÉtatCompte` avec les énumérateurs `COMPTE_ACTIF` et `COMPTE_DÉSACTIVÉ`,

## 3.2. Premières opérations des classes

Les seules opérations que nous connaissons déjà sont celles
correspondant aux cas d'utilisation. Comme nous utilisons le patron de
conception Façade, toutes les opérations des cas d'utilisation sont
dans la Façade.

Donc, dans la classe `MiniSocs`, voici les premières opérations (en
ignorant celles de priorité « basse ») :
- `ajouterUtilisateur`,
- `désactiverCompte`,
- `bloquerCompte`,
- `listerUtilisateurs`.

## 3.3. Diagramme de classes

Le diagramme de classes obtenu lors d'une analyse à partir de l'énoncé
du problème est donné dans la figure qui suit. Dans ces diagrammes,
les opérations ne sont pas mentionnées parce qu'il y en aurait trop.

**Important: même dans les diagrammes de la conception détaillée, on
ne montre pas les attributs traduisant des associations.**

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Version sans les notifications
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu)).

![diagrammeclasses](./Diagrammes/minisocs_uml_diag_classes_sans_notif.png)
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu))

## 3.4. Diagrammes de séquence

Dans la suite, plusieurs versions d'un même diagramme de séquence sont proposés :
- une version dite « recommandée » **avec** les barres d'activation,
- une version dite « simplifiée » **sans** les barres d'activation.

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

#### Ajouter un utilisateur (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu)).

![diagrammeséquenceajouterutilisateur](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu))

Version simplifiée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur_version_simplifiee.pu)).

#### Ajouter membre (HAUTE)


![diagrammeséquenceajoutermembre](./Diagrammes/minisocs_uml_diag_seq_ajouter_membre.png)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_membre.pu))

#### Poster message (HAUTE)

![diagrammeséquencepostermessage](./Diagrammes/minisocs_uml_diag_seq_poster_message.png)
([source](./Diagrammes/minisocs_uml_diag_seq_poster_message.pu))

#### Créer Réseau (HAUTE)

![diagrammeséquencecréerréseau](./Diagrammes/minisocs_uml_diag_seq_creer_reseau_social.png)
([source](./Diagrammes/minisocs_uml_diag_seq_creer_reseau_social.pu))

# 7. Diagrammes de machine à états et invariants

Dans les diagrammes de machine à états, nous faisons le choix de faire
apparaître les états de création et de destruction. Ces états sont
transitoires, il est vrai, mais ils méritent cependant une attention
particulière.  L'état de création, en particulier, donne lieu, lors de
la réalisation dans un langage de programmation orienté objet, à
l'écriture d'une opération « constructeur » qui garantit que
tous les attributs sont initialisés correctement dès la création d'une
instance. Nous savons également qu'en Java la destruction se réalise
en « oubliant » l'objet : un mécanisme de ramasse
miettes détruit automatiquement les objets lorsqu'ils ne sont plus
référencés. Il n'en est pas de même dans tous les langages, et par
exemple en C++ qui ne possède pas de mécanisme de ramasse miettes, la
destruction des objets peut s'avérer un casse tête ardu.

Les actions provoquées par des appels en provenance d'autres objets
apparaissent sur les transitions. Nous avons gardé comme action
interne uniquement les actions correspondant à des appels que l'objet
fait seul ou fait de manière répétitive.  Les constructeurs et
destructeurs sont des exceptions (ils apparaissent en interne bien
qu'étant déclenchés par un autre objet).

## 7.1. Classe Utilisateur

### 7.1.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu)).

![diagrammemachineaétatsutilisateur](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu))


### 7.1.2. Fiche de la classe

Voici tous les attributs de la classe :
```
— final String pseudonyme
— String nom
— String prenom
— String courriel
— EtatCompte etatCompte
```

### 7.1.3. Invariant

```
  pseudonyme != null ∧ !pseudonyme.isBlank()
∧ nom != null ∧ !nom.isBlank()
∧ prenom != null ∧ !prenom.isBlank()
∧ EmailValidator.getInstance().isValid(courriel)
∧ etatCompte != null
```

## 7.2. Classe Message

### 7.2.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu)).

![diagrammemachineaétatsmessage](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.png)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu))

### 7.2.2. Fiche de la classe:
Voici tous les attributs de la classe:
(Rq: on a choisi une relation unidirectionnelle entre la classe membre et la classe message pour des raisons de simplifications de meme pour la classe Réseau social)
```
-Long id
-String contenu 
-LocalDateTime date
-EtatMessage etatMessage 
```

### 7.2.3. Invariant
```
∧ contenu != null ∧ !contenu.isBlank()
∧ etatMessage != null
∧ id!=null
∧ date!=null
```


# 8 Préparation des tests unitaires

## 8.1. Opérations de la classe Utilisateur

### Opération constructeur

|                                              | 1   | 2   | 3   | 4   | 5   |
|:---------------------------------------------|:----|:----|:----|:----|:----|
| pseudonyme bien formé (non null ∧ non vide)  | F   | T   | T   | T   | T   |
| nom bien formé (non null ∧ non vide)         |     | F   | T   | T   | T   |
| prénom bien formé  (non null ∧ non vide)     |     |     | F   | T   | T   |
| courriel bien formé selon le standard RFC822 |     |     |     | F   | T   |
|                                              |     |     |     |     |     |
| pseudonyme' = pseudonyme                     | F   | F   | F   | F   | T   |
| nom' = nom                                   | F   | F   | F   | F   | T   |
| prénom' = prénom                             | F   | F   | F   | F   | T   |
| courriel' = courriel                         | F   | F   | F   | F   | T   |
| étatCompte' = actif                          | F   | F   | F   | F   | T   |
|                                              |     |     |     |     |     |
| levée d'un exception                         | oui | oui | oui | oui | non |
|                                              |     |     |     |     |     |
| nombre de tests dans le jeu de tests         | 2   | 2   | 2   | 3   | 1   |

Trois tests dans le jeu de tests 5 pour non null, puis non vide, et
enfin une chaîne de caractères qui n'est pas une adresse courriel.

### Opération désactiverCompte

|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| étatCompte = actif                   | F   | T   |
|                                      |     |     |
| étatCompte' = désactivé              |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 2   |

Deux tests dans le jeu de tests 2 pour l'idempotence.

## 8.2. Opérations de la classe message

### Opération constructeur

|                                              | 1   | 2   |
|:---------------------------------------------|:----|:----|
| contenu bien formé (non null ∧ non vide)     | F   | T   |
| contenu' = contenu                           | F   | T   |
| etatMessage' = ENATTENTE                     | F   | T   |
| id' = lastIdUsed                             | F   | T   |
| date' =localDateTime()                       | F   | T   |
| levée d'un exception                         | oui | non |
|                                              |     |     |
| nombre de tests dans le jeu de tests         | 2   |  1  |




### Opération modererMessage

|                                      | 1   | 2   | 3   |
|:-------------------------------------|:----|:----|:----|
| status = ENATTENTE                   | F   | T   | T   |
| Acceptation                          |     | F   | T   |
| status' = VISIBLE                    |     | F   | T   |
| status' = REJETÉ                     |     | T   | F   |
| levée d'une exception                | non | non |non  |
|                                      |     |     |     |
| nombre de tests dans le jeu de tests | 6   | 1   | 1   |



### Opération rendreMessageVisible

|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| status = CACHÉ                       | F   | T   |
|                                      |     |     |
| status' = VISIBLE                    |     | T   |
|                                      |     |     |
| levée d'une exception                | non | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 3   | 1   |



### Opération CacherMessage
|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| status = VISIBLE                     | F   | T   |
|                                      |     |     |
| status' = CACHÉ                      |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 1   |


### Opération refuserMessage

|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| status = ENATTENTE                   | F   | T   |
|                                      |     |     |
| status' = REJETE                     |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 1   |






---
FIN DU DOCUMENT
