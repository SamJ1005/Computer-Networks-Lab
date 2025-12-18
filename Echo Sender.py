import socket

server = socket.socket()
server.bind(("localhost", 1234))
server.listen(1)

print("Echo Server Started...")
conn, addr = server.accept()

while True:
    data = conn.recv(1024).decode()
    if not data:
        break
    conn.send(data.encode())  # echo back

conn.close()
