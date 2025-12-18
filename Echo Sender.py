import socket

s = socket.socket()
s.bind(("localhost", 7000))
s.listen(1)

c, a = s.accept()

while True:
    m = c.recv(1024).decode()
    if m == "exit":
        break
    c.send(m.encode())

c.close()
s.close()