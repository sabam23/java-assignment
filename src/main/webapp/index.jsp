<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java Homework</title>
</head>
<style>
    form {
        display: flex;
        flex-direction: column;
        gap: 10px;
        justify-content: center;
    }

    button {
        cursor: pointer;
        width: 100px;
        border: none;
        border-radius: 10px;
        background: red;
        height: 35px;
        color: white;
    }

    input {
        width: 200px;
        height: 30px;
        padding-left: 10px;
    }
</style>
<body>
    <div style="display: flex; align-items: center; flex-direction: column">
        <h1><%= "Java Homework" %></h1>
        <h2>Posts form:</h2>
        <form method="POST" action="<%=request.getContextPath()%>/post-servlet">
            <input type="text" placeholder="Header" name="header">
            <input type="text" placeholder="Content" name="content">
            <input type="text" placeholder="Author" name="author">
            <button type="submit"> Submit </button>
        </form>
    </div>
</body>
</html>