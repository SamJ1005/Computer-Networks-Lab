import socket

s = socket.socket()
s.connect(("localhost", 7000))
print("Connected to server")

while True:
    msg = input("Client: ")
    s.send(msg.encode())
    if msg == "exit":
        break
    print("Server:", s.recv(1024).decode())

s.close()
