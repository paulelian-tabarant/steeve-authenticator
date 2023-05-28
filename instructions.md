Hello les amis,

J'aimerais vous lancer un petit chalenge.
L'objectif est de constater le niveau d'alignement ou de désalignement, d'ouvrir le débat et d'avoir un peu de fun.

KOIFER ?

Dans le langage de votre choix, réaliser une (petite) api qui aura comme endpoint POST /api/auth.
Ce endpoint récupérera dans le body un json comme celui là

```
{
"name":"dertex",
"password":"killer"
}
```

Et retournera
Un 401 (Status unauthorized) si l'accès n'est pas autorisé

* Si le name reçu n'est pas dans la satabase.
* Si le password ne correspond pas.
* Si pas de name/password dans le json on entrée (pour faire simple)
  Un token (on va dire pour faire simple => 32 chars aléatoires)

La couche de database peu être un service qui réponds en dur !

La contrainte :
**Clean archi**

ET APRES ?
Ben on compare les choix fait par chacun au niveau de je mets quoi et où, et on essaye ensemble de se forger une opinion
commune.

Je propose de faire le point début juin.