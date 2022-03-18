#ecommerce project
E-Library book is a simple library that keeps books in appropriate category/genre and implements a favourite list of book feature. E-Library requires these following tools: Spring Framework, Java, MySQL, Maven tool and TDD.
#Features
RestAPI that  includes the following:
- Create book
- Delete book
- Outline all books
- Retrieve a book
- Edit book  
- Add books to category
- Edit Category
- Delete Category
- Query all categories
- Query a category
- Add book to favourite list
- Get a list of book

Spring RESTFUL APIs while using postman to test the APIs.

#Installation
- STEP 1: Clone repository
- STEP 2: Install dependencies: cd into root folder run the following command `mvn install` or `mvn verify` to both install and compile.
- STEP 3: Setup database: change directory into the db folder
- STEP 4: Run the project with `mvn spring-boot:run` to build

### URL
#### Book urls
Utilizing PathVariable and RequestBody
- `/book/id` [get single book]()
- `/book/all` [get all books]()
- `/book/create` [create book]()
- `/book/update/id` [edit book information]()
- `/del/id` [delete book]()

#### Category urls
Utilizing PathVariable and RequestBody
- `/category/create` [create book category]()
- `/category/id` [get book category]()
- `/category/categories` [get list of book categories]()
- `/category/edit/id` [edit book category]()
- `/category/addBook` [add book to category]()
- `/category/del/id` [delete category]()

#### Favourite urls
Utilizing PathVariable and RequestBody
- `/favourite/create` [create book favourite list]()
- `/favourite/id` [query a List]()
- `/favourite/addBook/id` [add book to favourite]()

### Methods
|`GET`|`POST`|`PATCH`|`DELETE`

I hope it was fun!!

Thanks for cloning or forking!!!
