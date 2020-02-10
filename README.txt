Ce projet consiste à tester le comportement d'exemples de code présent sur codepen.io
dans le cadre d'une exécution Squash TM - Squash TF.

De nombreux paramètres sont utilisés afin de vérifier que l'ensemble soit bien disponible.

Ils doivent donc être configurés au préalable.

    - DS_BROWSER :     Jeu de données, correspond au navigateur à utiliser. Une vérification est
                       faite dans le setup pour déterminer s'il faut instancier un ChromeDriver ou un FirefoxDriver.
                       Seules deux valeurs sont possibles : "Firefox" et "Chrome"

    - TS_CUF_URL :     Champ personnalisé au niveau de la test suite, correspond à l'URL de la page sur laquelle le test
                       va s'effectuer. Ceci implique la création de deux testsuite dans Squash TM.
                       La première suite va contenir les tests présent dans la classe AnimatedMenuTest avec l'URL suivante :
                       https://codepen.io/knyttneve/pen/LKrGBy
                       La seconde suite va contenir le test présent dans MutationObserverTest avec l'URL suivante :
                       https://codepen.io/dayvidwhy/pen/egdZyY

    - TC_CUF_LOCATOR : Champ personnalisé au niveau du cas de test qui correspond à un locator (id, texte ou autre qui
                       sera utilisé dans le test pour identifier un élément sur la page Web).
                       Le test MenuTestimonials aura pour valeur "Testimonials".
                       Le test MenuBlog aura pour valeur "Blog"
                       Le test testMutation() aura pour valeur "some-id"

    - IT_CUF_ERRMSG : Champ personnalisé au niveau de l'itération qui correspond au message d'erreur utilisé pour la
                      dernière assertion de chaque test. Ce paramètre peut être défini comme on le souhaite sans
                      incidence particulière.

    - CPG_CUF_TO :    Champ personnalisé au niveau de la campagne qui correspond au temps en seconde avant le timeout
                      (pour le paramétrage du driver). Ce temps peut être défini comme on le souhaite.
                      Il est tout de même préférable qu'il soit supérieur à 30.