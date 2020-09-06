# Library Management System
## orchestrator-microservice

This microservice is apart of the Library Management System project. To clone the entire project use the recursive flag and this repository:

git clone --recursive https://github.com/tbreitenfeldt/library-management-system.git

### Description

This service manages the borrower, librarian, and administrator microservices for a Library Management System using Eureka for service discovery. Spring security is used to secure each end point using the three user roles for authorization: BORROWER_ROLE, LIBRARIAN_ROLE, AND ADMIN_ROLE.
