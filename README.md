# Wretch Blog 2.0
A modern reimagining of Taiwan’s iconic social platform Wretch.cc  
Built with Spring Boot, JSP, and MariaDB

## Project Overview
Wretch Blog 2.0 is a full-stack blog system inspired by the classic Taiwanese platform "Wretch.cc."  
It combines nostalgic design with modern web technologies to deliver a secure, scalable, and interactive blogging experience.  
Our team developed both frontend and backend features, focusing on user interaction, content management, and system performance.

## Architecture and Features
### MVC Design Pattern
- Backend powered by Spring Boot, following the Model-View-Controller (MVC) architecture
- Servlets handle user requests and business logic efficiently

### MariaDB Database Management
- Stores articles, comments, and user data with normalized schema for optimized queries
- Session management ensures secure and stable login states

### JSP Frontend Rendering
- JSP used for dynamic page rendering, preserving the classic Wretch aesthetic
- Responsive design implemented with Bootstrap for cross-device compatibility

### Blog and Comment System
- Full CRUD operations: users can create, edit, delete articles and view version history
- Interactive comment board with support for posting, deleting, and emoji reactions
- Role-based access control: guest, user, and admin permissions

### Deployment and CI/CD
- ngrok used for external access during development without server configuration
- CI/CD pipeline integrated for automated testing and stable deployment

## Tech Stack
- Backend: Spring Boot, Java 17, Servlet
- Frontend: JSP, Bootstrap 5, jQuery
- Database: MariaDB
- DevOps: ngrok, GitHub Actions (CI/CD)
- Authentication: Gmail OAuth 2.0

## Developer Notes

This project demonstrates full-stack Java development with a focus on clean architecture, secure authentication, and user-centric design.  
Rebuilding Wretch.cc was both a technical challenge and a tribute to the early days of Taiwanese internet culture.



