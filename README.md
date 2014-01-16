
Contact: yuye.wang@yale.edu
Date: Jan 2014


This project lets more than 2 peers to get the number of lines that are same within all their files, while keeping each of their files private and secured. 

TCP is used for connection between nodes. 
Murmurhash is used for hash each line of file. 
PohligHellma crypto system is used for its communitive encryption property. 




How to run:

0. The the files we are going to read in is in src folder, like "A.txt"
    Go to bin folder were .class files exist. Open terminal.
1. Open one server node: java Cardinality2 server
    Open 3 or more nodes: java Cardinality2, you will read the nodes' IP address and the assigned node name "A","B","C", etc.
2. For each node enter the ip address of its previous node (in form X.X.X.X:XXXX, use ":" to separate ip and port, no space allowed). 
    For example, if node A's next node is node B. Enter A's ip address in node B's terminal window. 
    When a node got connected with both previous and next node. It should show message saying both node connected.
    Make sure one node only connect to one next node. Error will happen if two node try to connect to one, or one node is connected to it self.
    A big loop of nodes should form.
3. Connect each node with server:
    At the server side, input each node's address, line by line. Both side should say connected if success.
    Enter "end", for ending the server connection, and the server will calculate and distribute public key and bigB_N to all nodes.
    (Note, the socket is still open, though it will not be used.)
4. For each node, enter "s" to start computing and sending encrypted and shuffled file to the next node. 
    And all node should process the encryption process, and finally receive the final encrypted file from this single node. 
    Do "s" for all node. All nodes should receive the encrypted file of all other nodes now. 
5. For each node, press "c" to start to compute the number of intersection lines among all files. The final value should be shown.
    All nodes should get the same value in the same round. 

6. Enter "q" to exit.
