import socket

s = socket.socket()
s.bind(("localhost", 7000))
s.listen(1)

print("Server started. Waiting for client...")
c, a = s.accept()
print("Client connected:", a)

while True:
    msg = c.recv(1024).decode()
    if msg == "exit":
        break
    print("Client:", msg)
    c.send(msg.encode())   # echo back

c.close()
s.close()
