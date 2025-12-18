import socket

s = socket.socket()
s.connect(("localhost", 7000))

while True:
    m = input()
    s.send(m.encode())
    if m == "exit":
        break
    print(s.recv(1024).decode())

s.close()
