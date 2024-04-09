# Tema-PAO

Interogări/acțiuni:
- Crearea unui cont de utilizator
- Existența unui cont de administrator care poate să adauge diverse producți media din categoriile(anime, manga, novel) și conținut acestora(episoade, capitole, volume).
- Posibilitatea de a găsi o producție pe baza id-ului acesteia.
- Posibilitatea de a afișa detali despre o producție pe baza id-ului acesteia.
- Posibilitatea de a căuta o producție pe baza numelui acesteia și a tuturor producțiilor cu acel nume.
- Posibilitatea ca un utilizator să adauge o recenzie la o producție.
- Posibilitatea ca un utilizator să își steargă/actualizeze recenzia.
- Posibilitatea ca un utilizator să își adauge producți la un watchlist și să le scoată din acesta.
- Posibilitatea ca un utilizator să steargă o producție din watchlist.
- Posibilitatea ca un utilizator să își vadă toate recenziile pe care le-a adaugat.
- Posibilitatea ca un utilizator să își vadă wathlist-ul.


Obiectele utilizate:

Clasa Account este o clasă abstractă care este extinsă de clasa Admin și clasă User, ea conține ca variabile o mapă statică care ține evidența numelor utilizate pentru ca fiecare cont creat să aibă numele de utilizator unic, având constructorul protected, de asemenea mai conține și variabile nume si parolă care sunt folosite pentru autentificare.

Clasa User extinde class Account ca variabile mapa reviews care ține evitența recenziilor unui utilizator si lista watchlist. Clasa mai conține o funcțiile necesare pentru verificarea corectitudini unui parole și pentru a adăuga/sterge recenzi și producții din watch list.

Clasa Admin extinde class Accound find o clasă singelton, care crează un cont de administrator, acestă clasă mai contine funcțiile necesare creari de producții și conținut pentru acestea.

Clasa Media conține este o clasa abstractă extinsă de clasele, Anime, Manga și Novel. Ca variabile conține numele unei producții, variabila statică idMax care e folosită la atribuirea de id-uri, variabila id care repreintă id-ul unei producții și variabile rating, numberOfReviews care sunt folosite pentru a calcula ratingul unei producții și mapa statică productions stochează producțiile în funcție de id.

Clasa Anime extinde clasa Media, conține variabilele episodes care repiztă lista de episoade a anime-ului și duration care reprezintă durata totală a animeului în minute.

Clasa Episode reprezintă un episod dintr-un anime, aceasta are ca variabile name care reprezintă numele episodului, duration care este durata acestuia în minute, idMax care ajută la atribuirea de id-uri și variabila id care reprezintă id-ul unui episod.

Clasa Manga extinde clasa Media, conține variabilele chapters care reprezintă lista de capitole a seriei și variabila author care reprezintă autorul seriei.

Clasa Chapters reprezintă un capitol dintr-o serie manga, aceasta are ca variabile name care reprezintă numele capitolului, idMax care ajută la atribuirea de id-uri și variabila id care reprezintă id-ul unui capitol.

Clasa Novel extinde clasa Media, conține variabilele volumes care reprezintă lista de volumelor romanului, variabila author care reprezintă autorul seriei și variabila nrPages care reprezintă numarul total de pagini a tuturor volumelor acesteia.

Clasa Volume reprezintă un volum dintr-un roman, aceasta are ca variabile name care reprezintă numele volumului, nrPages care reprezintă numarul de pagini din volum, idMax care ajută la atribuirea de id-uri și variabila id care reprezintă id-ul unui volum.

Clasa meniu este o clasă de tip singelton, are ca varibile mapa media care ține evidența producțiilor în funcție de numele acestora și variabila scanner care e folosită la citirea inputului pentru navigarea în meniu. Clasa este o clasă serviciu care expune operațiile sistemului.

Clasa Main face apelul spre clasa serviciu, meniu.

