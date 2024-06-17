# CRUD Article API
Simple CRUD Article restful API

## How to run
1. Clone the repo
2. Make sure you already have required dependency installed
3. Copy `application.dev.properties` change it into `application.properties`
4. Fill `spring.data.mongodb.uri` with your MongoDB Uri
5. Fill `spring.data.mongodb.database` with your MongoDB database name

## Frontend Repo
See front end source code and features explanation here: https://github.com/FarhanHP/crud-article-web-app/tree/main

## API Spec

### 1. Create article
- Endpoint: ``` /api/article/create ```
- HTTP Method: ``` POST ```
#### Request Body
```
{
  "title": String, // article title max 20 characters long
  "body": String, // article body max 500 characters long
}
```
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": {
    "articleWebId": String // article id that already created
  }
}
```

### 2. Get All Articles
- Endpoint: ```/api/article/get```
- HTTP Method: ```GET```
#### URL Query Param
- ```pageNumber: Integer``` start from 0
- ```pageSize: Integer``` number of article fetched for each call / page
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": [
    {
      "articleWebId": String,
      "title": String,
      "body": String,
      "isEdited": Boolean, // when true, it means the article have been edited
      "createdAt": Long // seconds since epoch when article first created
    }
  ],
  "pagination": {
    "pageNumber": Integer,
    "pageSize": Integer,
    "hasNextPage": Boolean //when true, it means more data available on next call / page
  }
}
```

### 3. Get single article
- Endpoint: ```/api/article/{articleWebId}/get```
- HTTP Method: ```GET```
#### Path Variable
- ```articleWebId: String```
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": {
    "articleWebId": String,
    "title": String,
    "body": String,
    "isEdited": Boolean, // when true, it means the article have been edited
    "createdAt": Long // seconds since epoch when article first created
  }
}
```

### 4. Get edit histories of an article
- Endpoint: ```/api/article/{articleWebId}/getEditHistories```
- HTTP Method: ```GET```
#### URL Query Param
- ```pageNumber: Integer``` start from 0
- ```pageSize: Integer``` number of edit histories fetched for each call / page
#### Path Variable
- ```articleWebId: String```
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": [
    {
      "title": String,
      "body": String,
      "createdAt": Long // seconds since epoch when article first edited
    }
  ],
  "pagination": {
    "pageNumber": Integer,
    "pageSize": Integer,
    "hasNextPage": Boolean //when true, it means more data available on next call / page
  }
}
```

### 5. Edit Article
- Endpoint: ```/api/article/{articleWebId}/edit```
- HTTP Method: ```PUT```
#### Path Variable
- ```articleWebId: String```
#### Request Body
```
{
  "title": String, // article title max 20 characters long
  "body": String, // article body max 500 characters long
}
```
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": Boolean // true mean success to edit
}
```

### 6. Delete Article
- Endpoint: ```/api/article/{articleWebId}/delete```
- HTTP Method: ```DELETE```
#### Path Variable
- ```articleWebId: String```
#### Response Body
```
{
  "code": 200,
  "status": "OK",
  "data": Boolean // true mean success to delete
}
```
