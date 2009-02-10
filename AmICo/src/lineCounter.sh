#! /bin/bash

count=0;

# Per ogni file leggo le righe
for i in $(find ./ -iname "*.java"); do 

result=$(wc -l $i); # Recupero le linee del file e il nome
firstSpace=$(expr index "$result" " "); # trovo il primo spazio (quello che c'è prima è il numero di righe)
count=$(($count+${result:0:firstSpace})); # Estraggo il numero di righe
done

echo "Le righe totali del progetto sono "$count;
