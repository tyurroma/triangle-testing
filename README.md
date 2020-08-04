# triangle-testing

1. Install maven plugin
2. Run: ```mvn clean test```
3. Docker: ```docker build -t triangle-testing -f Dockerfile . && docker run --rm --name triangle-testing triangle-testing```
 

### Bugs
### Typo in the "Possible HTTP codes" section on the main page.
Unprocessible -> UnprocessAble

### Creating triangle with invalid Json causes 500 error.
```
Request: => POST {"separator": ";", "insert": "3;4;5"} https://qa-quiz.natera.com/triangle
Response: <= 500 HTTP/1.1 500  {
    "timestamp": 1596532648477,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.lang.NullPointerException",
    "message": "No message available",
    "path": "/triangle"
}
```

### Creating triangle with empty body causes 500 error.
```
Request: => POST {} https://qa-quiz.natera.com/triangle
Response: <= 500 HTTP/1.1 500  {
    "timestamp": 1596533261154,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.lang.NullPointerException",
    "message": "No message available",
    "path": "/triangle"
}
```

### Creating triangle without mandatory field causes 500 error.
```
Request: => POST {"separator":";"} https://qa-quiz.natera.com/triangle
Response: <= 500 HTTP/1.1 500  {
    "timestamp": 1596532641228,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.lang.NullPointerException",
    "message": "No message available",
    "path": "/triangle"
}
```

### Availability of creating triangles with negative side.
```
Request: => POST {"separator":";","input":"-3;4;5"} https://qa-quiz.natera.com/triangle
Response: <= 200 HTTP/1.1 200  {
    "id": "5c1634e0-f338-497e-856c-16b7ed4d0e49",
    "firstSide": 3.0,
    "secondSide": 4.0,
    "thirdSide": 5.0
}
```

### Availability of creating triangles zero sides.
```
Request: => POST {"input":"0;0;0"} https://qa-quiz.natera.com/triangle
Response: <= 200 HTTP/1.1 200  {
    "id": "d68b4e8f-2028-4ea6-bd01-6e304da0b93d",
    "firstSide": 0.0,
    "secondSide": 0.0,
    "thirdSide": 0.0
}
```

### Availability of creating triangle with additional numbers in the "input" object.
```
Request: => POST {"separator":";","input":"3;4;5;3;4;5"} https://qa-quiz.natera.com/triangle
Response: <= 200 HTTP/1.1 200  {
    "id": "f778f574-1199-4712-ba91-08d0262abe89",
    "firstSide": 3.0,
    "secondSide": 4.0,
    "thirdSide": 5.0
}
```

### Availability of creating a line.
```
Request: => POST {"separator":";","input":"2;3;5"} https://qa-quiz.natera.com/triangle
Response: <= 200 HTTP/1.1 200  {
    "id": "3858444f-41d0-4c6a-a565-cb9c1e838105",
    "firstSide": 2.0,
    "secondSide": 3.0,
    "thirdSide": 5.0
}
```

###  Separators +, ^, ?, *, | cause errors (500, 422).
```
Request: => POST {"separator":"+","input":"3+4+5"} https://qa-quiz.natera.com/triangle
Response: <= 500 HTTP/1.1 500  {
    "timestamp": 1596532274512,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.util.regex.PatternSyntaxException",
    "message": "Dangling meta character '+' near index 0\n+\n^",
    "path": "/triangle"
}
```

```
Request: => POST {"separator":"^","input":"3^4^5"} https://qa-quiz.natera.com/triangle
Response: <= 422 HTTP/1.1 422  {
    "timestamp": 1596532292435,
    "status": 422,
    "error": "Unprocessable Entity",
    "exception": "com.natera.test.triangle.exception.UnprocessableDataException",
    "message": "Cannot process input",
    "path": "/triangle"
}
```

### No 10 triangles limit.
```
Request: => POST {"input":"3;4;5"} https://qa-quiz.natera.com/triangle
Response: <= 200 HTTP/1.1 200  {
    "id": "528a1827-75a9-46fe-ac9c-c214852cd5b9",
    "firstSide": 3.0,
    "secondSide": 4.0,
    "thirdSide": 5.0
}
```
### Creating triangle without body causes 400 error.
There is no description for 400 code in the Docs.

```
Request: => POST null https://qa-quiz.natera.com/triangle
Response: <= 400 HTTP/1.1 400  {
    "timestamp": 1596532643541,
    "status": 400,
    "error": "Bad Request",
    "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
    "message": "Required request body is missing: public com.natera.test.triangle.model.Triangle com.natera.test.triangle.controller.TriangleController.addTriangle(com.natera.test.triangle.model.TriangleInput,javax.servlet.http.HttpServletRequest)",
    "path": "/triangle"
}
```

### Creating triangle without body causes 400 error.
There is no description for 405 code in the Docs.

```
Request: => PATCH {"separator":";","input":"3;4;5"} https://qa-quiz.natera.com/triangle
Response: <= 405 HTTP/1.1 405  {
    "timestamp": 1596537639508,
    "status": 405,
    "error": "Method Not Allowed",
    "exception": "org.springframework.web.HttpRequestMethodNotSupportedException",
    "message": "Request method 'PATCH' not supported",
    "path": "/triangle"
}
```

