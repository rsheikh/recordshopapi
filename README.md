### RecordShopAPI
###### for NorthCoders Solo Project
***

# **Welcome to the Record Shop API**
<p align="center">
  <img src =src/main/resources/record_shop_logo.png width="200" />


## "Step on in, where the vinyl spins—welcome to my groovy Record Shop!"
***
</p>

### "An API to find your inner Rock star, vibe to Hip-Hop, belt a ballad, or pop out with a dash of Swifty swagger!" 

🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤


## Project Overview

Mick Jagger has hung up his touring boots and opened his very own record shop. But even Mick knows it’s time to 
'Start Me Up' and get with the times, swapping his pen and paper for a digital move by computerising his inventory. 
No more _Dream On_ — just _Come as You Are_, show a little _Love and Affection_, and rock through the API like _Nothing
Else Matters._ 


## Getting Started


* Download the code onto your machine by running the following command:
  
        git clone https://github.com/rsheikh/recordshopapi


* Change to the `recordshopapi` directory and build the project


* Open the project in your preferred IDE (we like IntelliJ) and run the `RecordshopapiApplication` class.


* In your favourite browse, type the following:

        http://<server:port>/api/v1/recordshopapi/swagger-ui/index.html#/

* This will open the Record Shop API using Swagger. 


## Using the Swagger API

* From here, you can run the following queries:


   > 🎸 `GET /api/v1/recordshop` - Get all albums in stock
   >
   > - Accepts no parameters
   > - Returns a list of all the albums that are in stock


  > 🎸 `POST /api/v1/recordshop` - Add a new album
  >
  > - Accepts no parameters. Album information is added directly to the JSON object: <br/>
      ```{ ```<br/>
      ``` "artist": "Def Leppard",```  <br/>
      ```   "albumName": "Hysteria",  ``` <br/>
      ```    "yearReleased": 1987,  ``` <br/>
      ```   "genre": "ROCK"  ``` <br/>
      ```   } ```
  > - Returns a message: Album has been created
  >   - StockId and AlbumId will be auto-generated
  >   - Stock will implement its own endpoints to update the quantity in stock 


  > 🎸 `GET /api/v1/recordshop/{id}` - Retrieve an album by an album id
  > 
  > - Accepts an `id` parameter
  > - Returns: 
  >   * Success: The retrieved album if the record exists
  >   * Invalid Exception message: Record with id `<albumId>` cannot be found

  > 🎸 `DELETE /api/v1/recordshop/{id}` - Delete an album by an album id
  >
  > - Accepts an `id` parameter
  > - Returns:
  >   * Success message: Album with id `albumId` has been deleted successfully
  >   * Invalid Exception message: Record with id `<albumId>` cannot be found to be deleted

  > 🎸 `PATCH /api/v1/recordshop/{id}` - Update an album's information
  >
  > - Accepts an `id` parameter. Album information is added directly to the JSON object: <br/>
    ```{ ```<br/>
    ``` "artist": "Guns N' Roses",```  <br/>
    ```   "albumName": "Use Your Illusion 2",  ``` <br/>
    ```    "yearReleased": 1991,  ``` <br/>
    ```   "genre": "ROCK"  ``` <br/>
    ```   } ```
  > - Returns:
  >   * Success: The updated album
  >   * Invalid Exception message: Album with id `<albumId>` cannot be found to be updated

  > 🎸 `GET /api/v1/recordshop/albums` - Retrieve albums by Genre / Year Released / Artist
  > 
  > There are 3 individual queries you can search by here: Genre, the release year of the album and the artist
  > - Accepts a `searchBy` string parameter. Acceptable options are `genre`, `yearReleased`, `artist`
  > - Based on the `searchBy` parameter, provide a search filter in the corresponding field, e.g to search by an artist:
  >
  > <img src =src/main/resources/searchByArtist.png width="260" />  <br/>
  > - Returns: All full and partial matches to query filter, or an empty list when no matches are found
  > <br/><img src =src/main/resources/artistResult.png width="200" />

  
  > 🎸 `GET /api/v1/recordshop/album/{albumName}` - Retrieve an album by album name
  >
  > - Accepts an `albumName` parameter
  > - Returns:
  >   * Success: The retrieved album or albums for any full or partial case-insensitive matches
  >   * Invalid Exception message: No albums found that match album name of `<albumName>`


## Further Development

* Integration of a front-end GUI
* Implementation of OAuth2 security access and authorisation
* Implementation of transactional methods allowing update of stock by purchase/return of an album
* Separation of artists to their own class, associated to albums
* Implementation of publisher and song list classes for a fully normalised database


## Tech Stack

##### Developed with The Spring Boot Framework

* #### Dependency Management - Maven
* #### Testing - JUnit, Mockito
* #### RESTful API testing and documentation - Swagger UI, Postman
* #### Database - PostGres, PGAdmin4, H2
* #### IDE - IntelliJ
* #### Cloud Deployment and Containerisation - AWS, Docker, Elastic Beanstalk
---
###### Author - Rehana S.