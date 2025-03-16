# 🔄 **TP : Inversion de Contrôle et Injection de Dépendances en JEE**  

## 📌 **Introduction**  
L'Inversion de Contrôle (**IoC**) et l'Injection de Dépendances (**DI**) sont deux concepts fondamentaux en développement **Java/JEE** qui permettent d'améliorer la modularité, la flexibilité et la testabilité du code.  

- **Inversion de Contrôle (IoC)** : Plutôt que de laisser une classe créer et gérer ses propres objets, cette responsabilité est déléguée à un framework. L’objectif est de rendre l’application moins dépendante des instanciations internes et plus extensible.  
- **Injection de Dépendances (DI)** : Technique permettant d’injecter les dépendances externes dans une classe, évitant ainsi qu’elle ne les instancie elle-même. Cela favorise la séparation des responsabilités et simplifie la maintenance.  

---

## 💡 **Les Différentes Approches de l’Injection de Dépendances**  

### 🔹 **1. Injection des Dépendances par Instanciation Statique**  
C'est l’approche la plus simple, où les dépendances sont instanciées directement dans le code. Toutefois, cette méthode a des limites : elle entraîne un fort couplage entre les classes, rendant l’évolution et la maintenance du projet plus complexes.  

#### ✏️ **Exemple :**  
```java
public class PresentationV1 {
    public static void main(String[] args) {
        IDao dao = new DaoImpl(); // Instanciation directe
        IMetier metier = new MetierImpl(dao); // Injection via le constructeur
        System.out.println("Résultat : " + metier.calcul());
    }
}
```
🚨 **Inconvénients** :  
- Rigidité du code.  
- Difficulté à modifier les dépendances sans impacter le code source.  

---

### 🔹 **2. Injection des Dépendances par Instanciation Dynamique**  
Ici, les dépendances sont chargées dynamiquement à l’exécution grâce à la **réflexion Java**. Cela offre une plus grande flexibilité, car les classes peuvent être configurées sans être explicitement instanciées dans le code.  

#### ✏️ **Exemple :**  
```java
Scanner scanner = new Scanner(new File("config.txt"));
String daoClassname = scanner.nextLine();
Class<?> cDao = Class.forName(daoClassname);
IDao dao = (IDao) cDao.getConstructor().newInstance();
```
✅ **Avantages** :  
- **Découplage** entre les classes, ce qui facilite les modifications.  
- **Évolutivité** : il est possible de changer l’implémentation sans toucher au code source.  

---

## ⚙️ **Utilisation de Spring pour l'Injection de Dépendances**  
Le framework **Spring** automatise la gestion des dépendances, réduisant ainsi la complexité du code. Il propose deux principales méthodes : la **configuration XML** et l’**utilisation d’annotations**.  

### 📝 **Injection via un fichier de configuration XML**  
Dans cette approche, les dépendances sont définies dans un fichier XML externe. Cela permet de séparer la configuration de l’implémentation et de modifier les dépendances facilement.  

#### ✏️ **Exemple de `config.xml` :**  
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="dao" class="dao.DaoImpl"/>
    <bean id="metier" class="metier.MetierImpl">
        <property name="dao" ref="dao"/>
    </bean>
</beans>
```
✅ **Avantages** :  
- **Séparation nette** entre la configuration et le code métier.  
- **Facilité d’évolution** sans toucher au code source.  

---

### 🏷️ **Injection via Annotations**  
Spring permet également d’injecter les dépendances directement dans le code en utilisant des annotations. Cette approche réduit la nécessité d’un fichier de configuration externe et simplifie l’initialisation des objets.  

#### ✏️ **Exemple avec annotations :**  
```java
@Component
public class DaoImpl implements IDao {
    public double getData() {
        return 100;
    }
}
```

```java
@Service
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;
    
    public double calcul() {
        return dao.getData() * 2;
    }
}
```
✅ **Avantages** :  
- Réduction du nombre de fichiers de configuration.  
- Injection automatique avec `@Autowired`, facilitant la gestion des dépendances.  

---

## 🎯 **Conclusion**  
L’Injection de Dépendances est une approche essentielle pour concevoir des applications **flexibles et évolutives**. En passant d’une instanciation statique à dynamique, puis en exploitant la puissance de Spring, il devient plus simple de gérer les dépendances et de rendre son code **modulaire, testable et maintenable**.  

🔹 **Récapitulatif des avantages** :  
✔ Moins de couplage entre les classes.  
✔ Plus grande flexibilité et évolutivité.  
✔ Facilité de modification et de maintenance.  

👉 **Adopter l’Injection de Dépendances, c’est faire un pas vers une architecture plus robuste et moderne !** 🚀
