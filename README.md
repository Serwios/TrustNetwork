<h1>Trust Network</h1>

This is a service that simulates the relationships between users in the 
network based on the level of trust, which is determined by the users themselves. 
Sending messages on a network with a minimum level of trust goes along a tree, 
where each subsequent node of the network sends data to its trusted node, which 
has the same topics and level of trust.

For simulating message service use additional column in User entity which can contain any text.

<h2>How to run service:</h2>
1. Build you jar by using <code>mvn clean install</code>
2. You need to build docker image and create container for spring app by using <code>docker-compose up --build</code> in terminal
3. Check if container is running by using <code> docker ps </code>
4. Try to send request to @localhos with port 8080

<h2>Testing</h2>

To test the service, small tests were written for validating entities in 
the database, creating users, 
and sending messages via the database.

<h2>Current problems</h2>
Service have some problems with entity mapping and general refactoring 
which require some time due to small time for being complete.

Also service require some more testing and writing framework for creating reusable integration tests.