### RecordShopAPI
###### for NorthCoders Solo Project
***

# **Welcome to the Record Shop API**


![alt text](src/main/resources/RecordShopLogo.jpg)

## "Step on in, where the vinyl spins—welcome to my groovy Record Shop!"
***

### "An API to find your inner Rock star, vibe to Hip-Hop, belt a ballad, or pop out with a dash of Swifty swagger!" 🎸 

🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤🎤

## Getting Started

* Download the code onto your machine from this repo.


* Open the project in your preferred IDE and run the `RecordshopapiApplication` class.


* In your favourite browser (we like Chrome), type:

`http://localhost:8080/api/v1/recordshopapi/swagger-ui/index.html#/`

* This will open the Record Shop API using Swagger. From here, you can run the following queries:


   > 🎸 `GET /api/v1/recordshop` - Get all albums
   >
   > - Accepts no parameters
   > - Returns a list of all the albums held in the database


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
>   * Invalid Exception Thrown: Record with id `albumId` cannot be found

> 🎸 `DELETE /api/v1/recordshop/{id}` - Delete an album by an album id
>
> - Accepts an `id` parameter
> - Returns:
>   * Success message: Album with id `albumId` has been deleted successfully
>   * Invalid message: Record with id `albumId` cannot be found to be deleted

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
>   * Invalid Exception Thrown: Album with id `albumId` cannot be found to be updated

> 🎸 `GET /api/v1/recordshop/albums` - Retrieve albums by Genre / Year Released / Artist
> 
> TODO
> 

> 
> 🎸 `GET /api/v1/recordshop/album/{albumName}` - Retrieve an album by album name
>  
> TODO
> 

---


### 🧰 Tech Stack
##### Built with The Spring Boot Framework

* #### Dependency Management - Maven
* #### Testing - JUnit, Mockito
* #### RESTful API testing and documentation - Swagger UI, Postman
* #### Database - PostGres, PGAdmin4, H2
* #### IDE - IntelliJ
---
###### Author - Rehana S.