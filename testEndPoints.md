-------------------------------------------------------------------------------
BookController Endpoints
-------------------------------------------------------------------------------
###
POST http://localhost:9090/addBook
Content-Type: application/json

{
    "title":"title111",
    "author":"author111",
    "publisher":"publisher111",
    "publishingYear":2011,
	"category": {
        "name" : "Sports",
        "description": "Sports Book"
    }
}

###
GET http://localhost:9090/getAllBooks

###
GET http://localhost:9090/getBookById/{{id}}

###
POST http://localhost:9090/updateBookById/{{id}}
Content-Type: application/json

{
    "title":"title",
    "author":"author",
    "publisher":"publisher",
    "publishingYear":2011,
	"category": {
        "name" : "Fiction",
        "description": "Fiction Book"
    }
}

###
DELETE http://localhost:9090/deleteBookById/{{id}}


###
POST http://localhost:9090/addBookAll
Content-Type: application/json

[
{
    "title":"title111",
    "author":"author111",
    "publisher":"publisher111",
    "publishingYear":2011,
	"category": {
        "name" : "Sports",
        "description": "Sports Book"
    }
},

{
    "title":"title222",
    "author":"author222",
    "publisher":"publisher222",
    "publishingYear":2012,
	"category": {
        "name" : "Fiction",
        "description": "Fiction Book"
    }
},

{
    "title":"title333",
    "author":"author333",
    "publisher":"publisher333",
    "publishingYear":2003,
	"category": {
        "name" : "Science",
        "description": "Science Book"
    }
},

{
    "title":"title444",
    "author":"author444",
    "publisher":"publisher444",
    "publishingYear":2013,
	"category": {
        "name" : "Art",
        "description": "Art Book"
    }
},

{
    "title":"title555",
    "author":"author555",
    "publisher":"publisher555",
    "publishingYear":2023,
	"category": {
        "name" : "Fiction",
        "description": "Fiction Book"
    }
}
]

-------------------------------------------------------------------------------------
CategoryController Endpoints
-------------------------------------------------------------------------------------
###
POST http://localhost:9090/addAllCategory
Content-Type: application/json

[
{
  "name": "Sports",
  "description": "Sports Book"
},
{
  "name": "Fiction",
   "description": "Fiction Book"
},
{
  "name": "Science",
  "description": "Science Book"
},
{
  "name": "Art",
  "description": "Art Book"
}
]

###
POST http://localhost:9090/addCategory
Content-Type: application/json

{
	"name": "Art",
	"description": "Art Book"
}

###
GET http://localhost:9090/getAllCategories

###
GET http://localhost:9090/getCategoryById/{{id}}

###
GET http://localhost:9090/getBookCountofCategoryById/{{categoryId}}

###
POST http://localhost:9090/updateCategoryById/{{id}}

###
DELETE http://localhost:9090/deleteCategoryById/{{id}}

-------------------------------------------------------------------------------------
CustomerController Endpoints
-------------------------------------------------------------------------------------
Content-Type: application/json

###
POST http://localhost:9090/login
Content-Type: application/json

{
    "email":"alex@gmail.com",
    "password" : "ABC@123"
}

###
POST http://localhost:9090/registration
Content-Type: application/json

{
    "name": "Alex",
    "email":"alex@gmail.com",
    "password" : "ABC@123"
}

###
GET http://localhost:9090/getAllCustomers

###
GET http://localhost:9090/getCustomerById/{{email}}

###
POST http://localhost:9090/updateCustomerByEmail/{{email}}
Content-Type: application/json

{
    "name": "John",
    "email":"alex@gmail.com",
    "password" : "ABC@123"
}

###
DELETE http://localhost:9090/deleteCustomerByEmail/{{email}}
