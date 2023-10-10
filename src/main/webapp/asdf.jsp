<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        *{
            box-sizing: border-box;
        }
        #con {
            display: flex;
            flex-direction: column;
            height: 100vh;
            border:1px solid black;
        }

        header {
            height: 50px;
            flex-shrink: 0;
            border:1px solid red;
        }

        footer {
            height: 50px;
            flex-shrink: 0;
            border:1px solid blue;
        }

        #main {
            flex: 1 0 auto;
        }
    </style>
</head>
<body>
    <div id="con">
        <header>헤더</header>
        <div id="main"></div>
        <footer>푸터</footer>
    </div>
</body>
</html>