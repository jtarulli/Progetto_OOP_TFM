<p align = "center">



<h1 align="center">Twitter Location&Trends

Progetto Programmazione ad Oggetti 2021/2022

### Un'applicazione Java che sfrutta il framework SpringBoot e l'API di Twitter per offrire all'utente la possibilità di visualizzare statistiche ed effettuare calcoli usando le location per le quali Twitter dispone di trend.


## **Indice** :bookmark_tabs:
* [Introduzione](#intro)
* [Configurazione](#config)
* [Rotte](#rotte)
* [Chiamate API](#calls)
* [Eccezioni](#eccez)
* [Test](#test)
* [Documentazione](#doc)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione :mega:
L’applicazione è stata sviluppata per restituire statistiche e dati relativi alle location per le quali Twitter dispone di trend. Sono state usate le API di riferimento di Twitter Get locations with trending topics  (Get Trends/closest e Get Trends/available), e all’API dev/geo/placeName che restituisce informazioni geografiche tra le quali le coordinate della località placeName. 
Il programma si compone di diversi packages:
  * Project: contiene il Main per avviare il programma
* Controller: contiene l’implementazione delle rotte
* Service: è il tramite tra Controller e Connection
* Connection: contiene le implementazioni delle chiamate effettive alle varie API e i calcoli necessari a ottenere le statistiche, usando i metodi del Need, e una classe che controlla i parametri passati in input
*	Model: contiene tutte le classi con gli oggetti da istanziare 
*	Need: contiene i metodi necessari ai calcoli o alle agevolazioni del codice
*	Exception: gestisce le eccezioni
  
  <a name="config"></a>
## Configurazione :bulb:
 * Clonare in locale la repository corrente da cmd con il seguente comando: git clone https: //github.com/jtarulli/Progetto_OOP_TFM,
 * Avviare il progetto da un IDE o da cmd come SpringBoot Application.
 * Una volta in esecuzione, utilizzare un client server (Postman) per usufruire dell'applicazione ed effettuare le GET requests all'indirizzo http://localhost:9900/
 
 <a name="rotte"></a>
## Rotte 🗺️:

 N° |Tipo | Rotta | Descrizione
 ----- | ------------ | -------------------- | ----------------------
 [1](#1) |` GET ` | `/TrendsAvailable` | restituisce il JSON trendsAvailable con tutte le location per le quali dispone di trend. Usa la chiamata API getTrendsAvailable
 [2](#2) |` GET ` | `/Metadata` | restituisce i metadati, ovvero le informazioni di ogni tipo di dato visualizzato
 [3](#3) |` GET ` | `/TrendsClosest` | restituisce la location più vicina a quella scritta nel file location.txt tra quelle presenti nel JSON trendsAvailable. Usa la chiamata API getTrendsClosest
 [4](#4) |` GET ` | `/Stats` | restituisce la classifica dei paesi con più locations per trend 
 [5](#5) |` GET ` | `/CountryCode` | restituisce le locations del JSON trendsAvailable della nazione specificata dal countryCode
 [6](#6) |` GET ` | `/ClosestLocations` | restituisce le locations del JSON trendsAvailable entro una certa distanza in km da un paese, entrambi dati in input
 
 <a name="calls"></a>
## Chiamate API :telephone_receiver:

#### :memo: Cosa passare in input:

N° | Parametri | Tipo 
----- | ------------ | -------------------- 
[5](#5) | `cc` | *String* 
[6](#6) | `placeName, distance` | *String, double* 

#### :page_with_curl: Cosa viene retituito:

<a name=1></a>
### 1. TrendsAvailable

Inserire screen

<a name=2></a>
### 2. Metadata

```json
{ "alias":"name",
  "sourceField":"name",
  "type":"String" },
  
{ "alias":"PlaceType",
  "sourceField":"placeType",
  "type":"Object" },
  
{ "alias":"placeTypeCode",
  "sourceField":"code",
  "type":"Integer" },
  
{ "alias":"placeTypeName",
  "sourceField":"name",
  "type":"String" },
  
{ "alias":"url",
  "sourceField":"url",
  "type":"String" },
  
{ "alias":"parentid",
  "sourceField":"parentid",
  "type":"Integer" },
  
{ "alias":"country",
  "sourceField":"country",
  "type":"String" },
  
{ "alias":"woeid",
  "sourceField":"woeid",
  "type":"Integer" },
  
{ "alias":"countryCode",
  "sourceField":"countryCode",
  "type":"String" }
```

<a name=3></a>
### 3. TrendsClosest
Dati relativi al file location.txt "Bologna, Pavia, Milano"
```json
{
    { "name":"Bologna",
      "placeType":
           { "code":7,
             "name":"Town" },
      "url":"http://where.yahooapis.com/v1/place/711080",
      "parentid":23424853,
      "country":"Italy",
      "woeid":711080,
      "countryCode":"IT" },
    { "name":"Milan",
      "placeType":
           { "code":7,
             "name":"Town" },
      "url":"http://where.yahooapis.com/v1/place/718345",
      "parentid":23424853,
      "country":"Italy",
      "woeid":718345,
      "countryCode":"IT" },
   { "name":"Milan",
      "placeType":
           { "code":7,
             "name":"Town" },
      "url":"http://where.yahooapis.com/v1/place/718345",
      "parentid":23424853,
      "country":"Italy",
      "woeid":718345,
      "countryCode":"IT" }
}
```

<a name=4></a>
### 4. Stats
 ```json
Inserire immagine output stats
```

<a name=5></a>
### 5. CountryCode?cc=IT
 ```json
 Inserire immagine output stats
 ```

<a name=6></a>
### 6. ClosestLocations?name=Montappone?distance=300
 ```json
Mettere screen
 ```
 
 <a name="eccez"></a>
## Eccezioni :x:
Sono state gestite sia eccezioni standard di Java che eccezioni personalizzate
 
 <a name="test"></a>
## Test :question:
Sono stati effettuati test usando il framework JUnit sul corretto inserimento degli input da parte dell'utente. 
 
 <a name="doc"></a>
## Documentazione :abc:
È stata usata la documentazione Javadoc.
 
 ## Autori e commenti :busts_in_silhouette:

Progetto realizzato da
 
* 33,3% Jacopo Tarulli: Control, Service, Connection, Readme
 
* 33,3% Matteo Forti: Need, Connection, Test
 
* 33,3% Federico Mennecozzi: Model, Exception, Javadoc
 
#### NOTA: ci sono stati problemi iniziali poi risolti riguardo push e pull, quindi il progetto è stato in larga parte realizzato lavorando sulla stessa macchina. 
 
