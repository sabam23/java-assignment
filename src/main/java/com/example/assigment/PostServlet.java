package com.example.assigment;

import java.io.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.example.assigment.models.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;

import static java.lang.System.out;

@WebServlet(name = "postServlet", value = "/post-servlet")
public class PostServlet extends HttpServlet {

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:4200/demo",
                "root",
                "Password"
        );
        return  connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = new ArrayList<>();

        try {
            Connection conn = connect();

            // Create a statement and execute the query
            Statement stmt = conn.createStatement();
            String query = "select * from demo.posts";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("header"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getTimestamp("date")
                );
                posts.add(post);
            }

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Header</th><th>Content</th><th>Author</th><th>Date</th></tr></thead>");
            out.println("<tbody>");
            posts.forEach(post -> {
                out.println("<tr>");
                out.println("<td>" + post.getId() + "</td>");
                out.println("<td>" + post.getHeader() + "</td>");
                out.println("<td>" + post.getContent() + "</td>");
                out.println("<td>" + post.getAuthor() + "</td>");
                out.println("<td>" + post.date() + "</td>");
                out.println("</tr>");
            });
            out.println("</tbody>");
            out.println("</table>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getParameter("header");
        String content = req.getParameter("content");
        String author = req.getParameter("author");

        try {
            Connection conn = connect();
            String query = "INSERT INTO posts (header, content, author) VALUES (?, ?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(query);
            statement.setString(1, header);
            statement.setString(2, content);
            statement.setString(3, author);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                String redirectUrl = "post-servlet";
                resp.setStatus(HttpServletResponse.SC_FOUND);
                resp.setHeader("Location", redirectUrl);
            }
            statement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}