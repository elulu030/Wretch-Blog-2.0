<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="UTF-8">

<%@ page import="java.sql.*"%>
<%

Class.forName("org.mariadb.jdbc.Driver").newInstance(); 
Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/java_test?user=root&password=123456789");

Statement smt=conn.createStatement();
String sql="";
ResultSet rs=null;

%>